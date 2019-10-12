package gym;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MemberData {
    public static void main(String[] args) {
        Path memberList = Paths.get("files/members.txt");
        readMemberData(memberList);
    }
    public static void readMemberData(Path filePath){
        try(BufferedReader reader = Files.newBufferedReader(filePath)){
            String content = null;
            //String[] IdAndNames = new String[2];
            while ((content=reader.readLine())!=null){

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
