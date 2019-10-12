package gym;

import dataHandling.MemberData;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reception {

    private MemberData data = new MemberData();
    private Path path = Paths.get("files/members.txt");

    public void checkMembership(){
        //Get all data
        data.readAllMemberData(path);
        //Get input from JOptionPane
        String input = JOptionPane.showInputDialog("Input name or id,please");
        String result = null;
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
            }
            if(input!=null) {
                result = data.checkMembershipForReception(input.trim(), data.getMemberLists());
                JOptionPane.showMessageDialog(null,result);
                input = JOptionPane.showInputDialog("Input name or id for next check!");
            }
        }
    }
}