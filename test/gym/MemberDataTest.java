package gym;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MemberDataTest {
    Path memberList = Paths.get("files/members.txt");

    @Test
    public void readMemberDataTest(){
        try(BufferedReader reader = Files.newBufferedReader(memberList);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("files/membersIn.txt"))){
            String content = null;
            String[] IdAndNames = null;
            String id = null;
            String name = null;
            String date = null;
            int readLines = 0;
            int writeLines = 0;
            while ((content=reader.readLine())!=null){
                if(content.contains(",")){
                    IdAndNames = content.split(",");
                    id = IdAndNames[0].trim();
                    name = IdAndNames[1].trim();
                    System.out.println("Here is id and name: "+id + " " + name);

                    //Check id and name are not null
                    assertNotNull(id);
                    assertNotNull(name);

                    writer.write(id+" "+name+"\n");
                    writeLines++;
                    writer.flush();

                }else{
                    date = content;
                    System.out.println("Here is date: "+date);

                    //Check date is not null
                    assertNotNull(date);

                    writer.write(date+"\n");
                    writeLines++;
                    writer.flush();
                }
                readLines++;
            }
            //Check read lines equals write lines
            assertEquals(writeLines,readLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}