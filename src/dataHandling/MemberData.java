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
                lastPayDate = reader.readLine();
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
    public Member getSelectedMemberForCoach(String keyword, List<Member> list){
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

    public List<Member> getMemberLists() {
        return memberLists;
    }

}
