package gym;

import dataHandling.MemberData;
import member.Member;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Coach {

    private MemberData data= new MemberData();
    private final Path PATH = Paths.get("files/members.txt");

    public void getPaidMemberTrainingRecord(){
        Member selectedMember = null;
        //Get all data
        data.readAllMemberData(PATH);
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
            selectedMember = data.getSelectedMember(input.trim(), data.getMemberLists());
            if(selectedMember!=null) {
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
            }else {
                JOptionPane.showMessageDialog(null,"No valid membership!");
            }
            input = JOptionPane.showInputDialog("Input a wanted id or name,please");
        }

    }
}
