package gym;

import dataHandling.MemberData;
import member.Member;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Coach {
    private MemberData data= new MemberData();
    private Path path = Paths.get("files/members.txt");

    public void saveRecord(){
        data.readAllMemberData(path);

    }
}
