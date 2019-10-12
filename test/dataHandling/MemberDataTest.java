package dataHandling;

import Member.Member;
import gym.Reception;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberDataTest {

    MemberData item = new MemberData();
    Path memberList = Paths.get("files/members.txt");
    List<Member> list = new ArrayList<Member>();

    @BeforeEach
    @Test
    public void readMemberData() {
        try (BufferedReader reader = Files.newBufferedReader(memberList);
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
        assertTrue(item.compareDate("2018-07-01"));
        assertFalse(item.compareDate("2018-12-02"));
        assertTrue(item.compareDate("2018-10-12"));
        assertTrue(item.compareDate("1999-12-16"));
        assertFalse(item.compareDate("2018-12-22"));
    }

    @Test
    void checkMembership() {
        System.out.println("Inside checkMembership");
        System.out.println(list);

        assertEquals("Fritjoff Flacon's membership expired, last pay date is: 1999-12-16",item.checkMembership("7911061234",list));
        assertEquals("Fritjoff Flacon's membership expired, last pay date is: 1999-12-16",item.checkMembership("Fritjoff Flacon",list));

        assertEquals("Mitsuko Mayotte's membership is valid, last pay date is: 2018-12-22",item.checkMembership("Mitsuko Mayotte",list));
        assertEquals("Mitsuko Mayotte's membership is valid, last pay date is: 2018-12-22",item.checkMembership("7907281234",list));

        assertEquals("Not member at all",item.checkMembership("X",list));
        assertEquals("Not member at all",item.checkMembership("3",list));


    }
}