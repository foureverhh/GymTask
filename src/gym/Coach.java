package gym;

import dataHandling.MemberData;
import member.Member;
import utils.Inquiry;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Coach {

    private MemberData data = new MemberData();
    private final Path PATH_FOR_ALL_MEMBERS = Paths.get("files/members.txt");
    private final String PATH_FOR_MEMBER_TRAINING_HISTORY = "trainingRecords";

    public void getPaidMemberTrainingRecordForCoach() {
        Member selectedMember = null;
        //Get all data
        data.readAllMemberData(PATH_FOR_ALL_MEMBERS);

        String input = Inquiry.getUserInput();
        //To check whether the customer is a valid customer
        selectedMember = data.getSelectedMemberForCoach(input.trim(), data.getMemberLists());
        if(selectedMember!=null){
            getTrainingRecordByBufferedReader(selectedMember);
            // Handle not a valid member
        }else {
            JOptionPane.showMessageDialog(null, "No valid membership!");
        }
    }

    public void getTrainingRecordByBufferedReader(Member member){
        File rootPath = new File(PATH_FOR_MEMBER_TRAINING_HISTORY);
        System.out.println(member.getName());

        for (String child : rootPath.list()) {
            //The selectedMember has training history
            System.out.println("Child is: " + child);
            System.out.println(child.equals(member.getName() + " " + member.getId() + ".txt"));
            if (child.equals(member.getName() + " " + member.getId() + ".txt")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(rootPath + "/" + child))) {
                    String name = reader.readLine();
                    String id = reader.readLine();
                    String[] dates = reader.readLine().split(" ");
                    StringBuilder sb = new StringBuilder();
                    sb.append(id + "\n");
                    sb.append(name + "\n");
                    for (String date : dates) {
                        sb.append(date + "\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}