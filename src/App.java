import dataHandling.MemberData;
import gym.Coach;
import gym.Reception;
import member.Member;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws InterruptedException {
        /*
        Reception reception = new Reception();
        reception.checkMembership();
        */

        Member visitingMember = Member.getInstance("Diamanda Djedi");
        MemberData.setTrainingHistoryRecord(visitingMember);
        /*
        Thread.sleep(1000);
        visitingMember.setTrainingHistoryRecord();
        */
        Coach coach = new Coach();
        coach.getPaidMemberTrainingRecord();
    }
}
