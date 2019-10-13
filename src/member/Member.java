package member;

import dataHandling.MemberData;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String lastPayDate;
    private List<String> trainingHistory = new ArrayList<>();
    private static MemberData data;
    private static final Path PATH_FOR_ALL_MEMBERS = Paths.get("files/members.txt");
    private final String PATH_FOR_MEMBER_TRAINING_HISTORY = "trainingRecords/";

    public Member() {
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String id, String name, String lastPayDate) {
        this.id = id;
        this.name = name;
        this.lastPayDate = lastPayDate;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastPayDate() {
        return lastPayDate;
    }

    public List<String> getTrainingHistory() {
        return trainingHistory;
    }

    public void setLastPayDate(String lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    //Get a member instance,Only paid member can get instance by calling getSelectedMember()
    public static Member getInstance(String keyword){
        data = new MemberData();
        data.readAllMemberData(PATH_FOR_ALL_MEMBERS);
        Member temp = data.getSelectedMember(keyword,data.getMemberLists());
        if(temp==null){
            JOptionPane.showMessageDialog(null,"We get an invalid member here!!","Invalid Visit",JOptionPane.WARNING_MESSAGE);
        }
        return temp;
    }
/*
    //Add logInTime as Training time to member
    public void setTrainingHistoryRecord(){
        LocalDateTime logInTime = LocalDateTime.now();
        File memberTrainingFile = new File(PATH_FOR_MEMBER_TRAINING_HISTORY+this.getName()+".txt");
        if(memberTrainingFile.exists()) {
            try (
                    ObjectInputStream  ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(memberTrainingFile)));
                    ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(memberTrainingFile)))
            ) {
                Member oldRecord= (Member) ois.readObject();
                System.out.println(oldRecord+" "+oldRecord.getTrainingHistory());
                Thread.sleep(500);
                this.getTrainingHistory().addAll(oldRecord.getTrainingHistory());
                this.getTrainingHistory().add(logInTime.toString());
                oos.writeObject(this);
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
            this.getTrainingHistory().add(logInTime.toString());
            try(ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(memberTrainingFile)))){
                oos.writeObject(this);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //OverLoad to take a date string as parameter
    public void setTrainingHistoryRecord(String dateInString){
        this.trainingHistory.add(dateInString);
        String path = "trainingRecords/"+this.name+".txt";
        try(ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))){
            writer.writeObject(this);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public String toString() {
        return id+" "+name+" "+lastPayDate;
    }
}
