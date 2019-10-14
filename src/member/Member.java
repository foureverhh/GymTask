package member;

import dataHandling.MemberData;
import utils.Inquiry;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String lastPayDate;
    private List<String> trainingHistory;
    private static MemberData data;
    private static final Path PATH_FOR_ALL_MEMBERS = Paths.get("files/members.txt");
    private final String PATH_FOR_MEMBER_TRAINING_HISTORY = "trainingRecords/";
    private final Path SERIALIZE_DIR = Paths.get("trainingRecordSer");
    private final Path SERIALIZE_FILE = Paths.get("trainingRecordSer");


    public Member(String id, String name, String lastPayDate) {
        this.id = id;
        this.name = name;
        this.lastPayDate = lastPayDate;
        this.trainingHistory = new ArrayList<>();
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


    public static void MemberVisiting(String idOrName){
        Member member = getInstance(idOrName);
        if(member!=null)
            member.saveTrainingHistoryToMemberFileWithBufferedWriter();
        Inquiry.userInquiry();
    }
    //Get a member instance,Only paid member can get instance by calling getSelectedMember()
    public static Member getInstance(String keyword){
        data = new MemberData();
        data.readAllMemberData(PATH_FOR_ALL_MEMBERS);
        Member temp = data.getSelectedMemberForCoach(keyword,data.getMemberLists());
        if(temp==null){
            JOptionPane.showMessageDialog(null,"We get an invalid member here!!","Invalid Visit",JOptionPane.WARNING_MESSAGE);
        }
        return temp;
    }

    public void saveTrainingHistoryToMemberFileWithBufferedWriter(){
        LocalDateTime dateTime = LocalDateTime.now();
        Path path = Paths.get(PATH_FOR_MEMBER_TRAINING_HISTORY);
        Path filePath = Paths.get(PATH_FOR_MEMBER_TRAINING_HISTORY+name+" "+id+".txt");
        if(!Files.exists(path) || !Files.exists(filePath)){
           try {
               if(!Files.exists(path)){
                   Files.createDirectory(path);
               }
               if(!Files.exists(filePath)){
                   Files.createFile(filePath);
               }
               trainingHistory.add(dateTime.toString());
               try(BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.WRITE)){
                    writer.write(id+"\n");
                    writer.write(name+"\n");
                    for(String date: trainingHistory){
                        writer.write(date+" ");
                    }
                    writer.write("\n");
                    writer.flush();
               }

           } catch (IOException e) {
               e.printStackTrace();
           }
        }else{

            System.out.println(path);
                String content = null;
                String nameInfo = null;
                String idInfo = null;
            try(BufferedReader reader = Files.newBufferedReader(filePath)
            ){
                while((content=reader.readLine())!=null){
                     nameInfo = content;
                     System.out.println(nameInfo);
                     idInfo = reader.readLine();
                     System.out.println(idInfo);
                     String[] dateStrings = reader.readLine().split(" ");
                     for(String string : dateStrings){
                         trainingHistory.add(string);
                     }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            trainingHistory.add(dateTime.toString());
            try(BufferedWriter writer = Files.newBufferedWriter(filePath)){
                writer.write(nameInfo+"\n");
                writer.write(idInfo+"\n");
                for(String date : trainingHistory){
                    writer.write(date+" ");
                }
                writer.write("\n");
                writer.flush();
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
    }

    public void saveTrainingHistoryToMemberFileWithObjectOutputStream(){

    }

    @Override
    public String toString() {
        return id+" "+name+" "+lastPayDate;
    }
}
