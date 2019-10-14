import gym.Coach;
import gym.Reception;
import member.Member;
import utils.Inquiry;

public class App {
    public static void main(String[] args) throws InterruptedException {
/*

        Reception reception = new Reception();
        reception.checkMembership();
*/


        /*
        Member visitingMember = Member.getInstance("Diamanda Djedi");
        MemberData.setTrainingHistoryRecord(visitingMember);
        Thread.sleep(1000);
        visitingMember.setTrainingHistoryRecord();
        */
     /*   Member visitingMember = Member.getInstance("Diamanda Djedi");
        visitingMember.saveTrainingHistoryToMemberFileWithBufferedWriter();
*/
        Inquiry.userInquiry();
        /*Coach coach = new Coach();
        coach.getPaidMemberTrainingRecordForCoach();*/
    }
}
