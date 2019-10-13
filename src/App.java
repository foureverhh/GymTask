import gym.Coach;
import gym.Reception;
import member.Member;

public class App {
    public static void main(String[] args) {
        /*
        Reception reception = new Reception();
        reception.checkMembership();
        */
        Coach coach = new Coach();
        coach.getPaidMemberTrainingRecord();
    }
}
