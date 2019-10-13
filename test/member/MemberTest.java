package member;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    Member m = new Member("id","m","2019-01-01");
    @Test
    void setTrainingHistoryRecord() {
        Member member = null;
        m.setTrainingHistoryRecord("2019-01-02");
        m.setTrainingHistoryRecord("2019-01-03");
        m.setTrainingHistoryRecord("2019-01-04");
        String path = m.getName()+".txt";
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {
             member = (Member) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       assertEquals("2019-01-02",member.getTrainingHistory().get(0));
       assertEquals("2019-01-03",member.getTrainingHistory().get(1));
       assertEquals("2019-01-04",member.getTrainingHistory().get(2));
    }
}