package member;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    Member m1 = new Member("id","m","2019-01-01");
    final String PATH_FOR_MEMBER_TRAINING_HISTORY = "trainingRecords";
    private final String SERIALIZE_DIR = "trainingRecordSer/";
   @Test
    void setTrainingHistoryRecord() {
        Member member = null;
        m1.setTrainingHistoryRecord("2019-01-02");
        m1.setTrainingHistoryRecord("2019-01-03");
        m1.setTrainingHistoryRecord("2019-01-04");
        String path = PATH_FOR_MEMBER_TRAINING_HISTORY+"/"+m1.getName()+".txt";
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

    @Test
    void getInstance() {
        Member instance = Member.getInstance("Diamanda Djedi");
        assertEquals("Diamanda Djedi",instance.getName());
        assertEquals("7608021234",instance.getId());
    }

    @Test
    void saveTrainingHistoryToMemberFileWithObjectOutputStream() {
        m1.saveTrainingHistoryToMemberFileWithObjectOutputStream();
        assertTrue(Files.exists(Paths.get(SERIALIZE_DIR+m1.getName()+" "+m1.getId()+".txt")));
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(SERIALIZE_DIR+m1.getName()+" "+m1.getId()+".txt")))) {
            Member member = (Member) ois.readObject();
            //assertEquals(1,member.getTrainingHistory().size());
            assertEquals(2,member.getTrainingHistory().size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}