package dataHandling;

import member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberDataTest {

    MemberData item = new MemberData();
    Path path = Paths.get("files/members.txt");
    List<Member> list = new ArrayList<Member>();

    @BeforeEach
    @Test
    public void readMemberData() {
        try (BufferedReader reader = Files.newBufferedReader(path);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get("files/membersIn.txt"))) {
            String content = null;
            String[] IdAndNames = null;
            String id = null;
            String name = null;
            String date = null;
            int readLines = 0;
            int writeLines = 0;
            while ((content = reader.readLine()) != null) {
                readLines++;
                IdAndNames = content.split(",");
                id = IdAndNames[0].trim();
                name = IdAndNames[1].trim();

                date = reader.readLine();
                readLines++;
                System.out.println(id + " " + name + " " + date);
                Member member = new Member(id, name, date);
                list.add(member);
                writer.write(id + " " + name + "\n");
                writeLines++;
                writer.write(date + "\n");
                writeLines++;

            }
            assertEquals(readLines,writeLines);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void compareDate() {
        LocalDate dateOverOneYear = LocalDate.now().minusYears(1);
        LocalDate dateLessOneYear = LocalDate.now().plusYears(1);
        System.out.println(dateOverOneYear.toString());
        System.out.println(dateLessOneYear.toString());
        assertFalse(item.compareDate(dateLessOneYear.toString()));
        assertTrue(item.compareDate(dateOverOneYear.toString()));
        /*
        assertTrue(item.compareDate("2018-10-12"));
        assertTrue(item.compareDate("1999-12-16"));
        assertFalse(item.compareDate("2018-12-22"));
        */
    }

    @Test
    void checkMembership() {
        //Check keyword as id and name for invalid member
        assertEquals("Fritjoff Flacon's membership expired, last pay date is: 1999-12-16",item.checkMembershipForReception("7911061234",list));
        assertEquals("Fritjoff Flacon's membership expired, last pay date is: 1999-12-16",item.checkMembershipForReception("Fritjoff Flacon",list));

        //Check keyword as id and name for valid member
        assertEquals("Mitsuko Mayotte's membership is valid, last pay date is: 2018-12-22",item.checkMembershipForReception("Mitsuko Mayotte",list));
        assertEquals("Mitsuko Mayotte's membership is valid, last pay date is: 2018-12-22",item.checkMembershipForReception("7907281234",list));

        //Check keyword as id and name for empty name,null, and other names
        assertEquals("Not member at all",item.checkMembershipForReception("X",list));
        assertEquals("Not member at all",item.checkMembershipForReception("3",list));
        assertEquals("Not member at all",item.checkMembershipForReception("",list));
        assertEquals("Not member at all",item.checkMembershipForReception(null,list));

    }
}