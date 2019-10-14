package gym;

import dataHandling.MemberData;
import member.Member;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Coach {

    private MemberData data= new MemberData();
    private final Path PATH_FOR_ALL_MEMBERS = Paths.get("files/members.txt");
    private final String PATH_FOR_MEMBER_TRAINING_HISTORY = "trainingRecords";

    public void getPaidMemberTrainingRecordWithBufferedReader(){
        Member selectedMember = null;
        //Get all data
        data.readAllMemberData(PATH_FOR_ALL_MEMBERS);
        //Get input from JOptionPane
        String input = JOptionPane.showInputDialog(null,"Input name or id,please","Coach",JOptionPane.INFORMATION_MESSAGE);
        while (true){
            if(input == null){
                int choice = JOptionPane.showConfirmDialog(null,"Are you sure to quite?","Warning",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.NO_OPTION){
                    input = JOptionPane.showInputDialog("Please input a name or id");
                    continue;
                }else {
                    System.exit(0);
                }
            }
            if(input.isEmpty()){
                input = JOptionPane.showInputDialog("Input can not to be empty! Input a name or an id, please");
                continue;
            }
            //To check whether the customer is a valid customer
            selectedMember = data.getSelectedMember(input.trim(), data.getMemberLists());

            //Handle the case the member is valid
            if(selectedMember!=null) {
                File rootPath = new File(PATH_FOR_MEMBER_TRAINING_HISTORY);
                System.out.println(selectedMember.getName());

                for(String child : rootPath.list()){
                    //The selectedMember has training history
                    System.out.println("Child is: "+child);
                    System.out.println(child.equals(selectedMember.getName()+" "+selectedMember.getId()+".txt"));
                    if(child.equals(selectedMember.getName()+" "+selectedMember.getId()+".txt")){
                        try(BufferedReader reader = new BufferedReader(new FileReader(rootPath+"/"+child))) {
                            String name = reader.readLine();
                            String id = reader.readLine();
                            String[] dates = reader.readLine().split(" ");
                            StringBuilder sb = new StringBuilder();
                            sb.append(id+"\n");
                            sb.append(name+"\n");
                            for(String date:dates){
                                sb.append(date+"\n");
                            }
                            JOptionPane.showMessageDialog(null,sb.toString());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        /*try(ObjectInputStream ois = new ObjectInputStream(
                                        new FileInputStream(PATH_FOR_MEMBER_TRAINING_HISTORY+"/"+child))){
                            *//*Object obj = null;
                            Member memberForCheckTrainingHistory = null;
                            while ((obj=ois.readObject())!=null) {
                                memberForCheckTrainingHistory = (Member) obj;
                            }*//*
                               // Member memberForCheckTrainingHistory = (Member) ois.readObject();
                            List<Member> members= (List<Member>) ois.readObject();
                            Member memberForCheckTrainingHistory = members.get(0);
                            System.out.println(memberForCheckTrainingHistory);
                            if(memberForCheckTrainingHistory.getTrainingHistory().isEmpty()){
                                JOptionPane.showMessageDialog(null,"No training record!");
                                break;
                            }else{
                                StringBuilder sb = new StringBuilder();
                                sb.append(memberForCheckTrainingHistory.getId()+"\n");
                                sb.append(memberForCheckTrainingHistory.getName()+"\n");
                                for(String dateString : memberForCheckTrainingHistory.getTrainingHistory()){
                                    sb.append(dateString+"\n");
                                }
                                JOptionPane.showMessageDialog(null,sb.toString());
                                break;
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
                /*
                if(!selectedMember.getTrainingHistory().isEmpty()){
                    StringBuilder sb = new StringBuilder();
                    sb.append(selectedMember.getId()+"\n");
                    sb.append(selectedMember.getName()+"\n");
                    for(String dateString : selectedMember.getTrainingHistory()){
                        sb.append(dateString+"\n");
                    }
                    JOptionPane.showMessageDialog(null,sb.toString());
                } else{
                    JOptionPane.showMessageDialog(null,"No training record!");
                }
                */
            }
            // Handle not a valid member
            else {
                JOptionPane.showMessageDialog(null,"No valid membership!");
            }

            input = JOptionPane.showInputDialog("Input a wanted id or name,please");
        }

    }
}
