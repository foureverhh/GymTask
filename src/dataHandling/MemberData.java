package dataHandling;

import member.Member;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MemberData {

    private final List<Member> memberLists = new ArrayList<>();
    private final static String PATH_FOR_MEMBER_TRAINING_HISTORY = "trainingRecords/";

    public  void readAllMemberData(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String content = null;
            String[] IdAndNames = null;
            String id = null;
            String name = null;
            String lastPayDate = null;
            while ((content = reader.readLine()) != null) {
                IdAndNames = content.split(",");
                id = IdAndNames[0].trim();
                name = IdAndNames[1].trim();
                //System.out.println("Here is id and name: " + id + " " + name);
                lastPayDate = reader.readLine();
                //System.out.println("Here is lastPayDate: " + lastPayDate);
                Member member = new Member(id,name,lastPayDate);
                memberLists.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  boolean compareDate(String dateString){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sourceDate = LocalDate.parse(dateString,formatter);
        Period period = Period.between(sourceDate,currentDate);
        return period.getYears() >= 1;
    }

    //To check valid client for receptionist
    public String checkMembershipForReception(String keyword, List<Member> list){
        String msg ="Not member at all";
        for(Member member:list){
            if(member.getId().equalsIgnoreCase(keyword)||member.getName().equalsIgnoreCase(keyword)){
               if(compareDate(member.getLastPayDate())){
                   msg = member.getName()+"'s membership expired, last pay date is: "+member.getLastPayDate();
                   break;
               }else {
                   msg = member.getName()+"'s membership is valid, last pay date is: "+member.getLastPayDate();
                   break;
               }
            }
        }
        return msg;
    }

    //To check paid client for coach
    public Member getSelectedMember(String keyword, List<Member> list){
        Member selectedMember = null;
        for(Member member:list){
            if(member.getId().equalsIgnoreCase(keyword)||member.getName().equalsIgnoreCase(keyword)){
                if(compareDate(member.getLastPayDate())){
                    break;
                }else {
                   selectedMember = member;
                    break;
                }
            }
        }
        return selectedMember;
    }


 /*   public boolean saveMemberTrainingRecord(String keyword,List<Member> list, Member member){
        if(checkMembershipForCoach(keyword,list)){
            LocalDateTime dateTime = LocalDateTime.now();
            member.getTrainingHistory().add(dateTime.toString());
        }
    }*/
    public List<Member> getMemberLists() {
        return memberLists;
    }

    //Add logInTime as Training time to member
    public static void setTrainingHistoryRecord(Member member){
        LocalDateTime logInTime = LocalDateTime.now();
        File memberTrainingFile = new File(PATH_FOR_MEMBER_TRAINING_HISTORY+member.getName()+".txt");
        if(memberTrainingFile.exists()) {
            try (
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(memberTrainingFile));
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(memberTrainingFile))
            ) {
                Object obj = null;
                Member oldRecord = null;
                while((obj=ois.readObject())!=null){
                    oldRecord= (Member) obj;
                }
                    //Member oldRecord= (Member) ois.readObject();
                System.out.println(oldRecord+" "+oldRecord.getTrainingHistory());
                Thread.sleep(500);
                member.getTrainingHistory().addAll(oldRecord.getTrainingHistory());
                member.getTrainingHistory().add(logInTime.toString());
                oos.writeObject(member);
                oos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            member.getTrainingHistory().add(logInTime.toString());
            try(ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(memberTrainingFile)))){
                oos.writeObject(member);
                oos.writeObject(null);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
