package utils;

import com.sun.xml.internal.xsom.XSUnionSimpleType;
import gym.Coach;
import gym.Reception;

import javax.swing.*;

public class Inquiry {



    public static String getUserInput() {
        String input = JOptionPane.showInputDialog(null, "Input name or id,please", "Inquire a name or an id", JOptionPane.INFORMATION_MESSAGE);
        while (true) {
            if (input == null) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure to quite the inquiry?", "Warning", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) {
                    input = JOptionPane.showInputDialog("Please input a name or id");
                } else {
                    //System.exit(0);
                    JOptionPane.showMessageDialog(null,"GoodBye!");
                    userInquiry();
                }
            }
            else if (input.isEmpty()) {
                input = JOptionPane.showInputDialog("Input can not to be empty! Input a name or an id, please");
            }else {
                break;
            }
        }
        return input;
    }

    public static int checkUser(){
        Object[] options = {
                "Receptionist",
                "Coach"};
        int user = JOptionPane.showOptionDialog(
                null,
                "Choose your user type,please",
                "Log in",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return user;
    }

    public static void userInquiry(){
        int user = checkUser();
        while (true) {
            if (user == JOptionPane.NO_OPTION) {
                Coach coach = new Coach();
                coach.getPaidMemberTrainingRecordForCoach();

            } else if (user == JOptionPane.YES_OPTION) {
                Reception reception = new Reception();
                reception.checkMembership();

            } else {
                int quit = JOptionPane.showConfirmDialog(null, "Are you sure to quit the system?", "Quit?", JOptionPane.YES_NO_OPTION);
                if (quit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }else{
                    user = checkUser();
                }
            }
        }
    }
}

