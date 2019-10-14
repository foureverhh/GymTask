package gym;

import dataHandling.MemberData;
import utils.Inquiry;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reception {

    private MemberData data = new MemberData();
    private final Path PATH = Paths.get("files/members.txt");

    public void checkMembership(){
        //Get all data
        data.readAllMemberData(PATH);
        String input = Inquiry.getUserInput();
        if(input!= null){
            String result = data.checkMembershipForReception(input.trim(), data.getMemberLists());
            JOptionPane.showMessageDialog(null,result);
        }
    }
}