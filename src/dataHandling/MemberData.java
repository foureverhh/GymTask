package dataHandling;

import Member.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MemberData {

    final List<Member> memberLists = new ArrayList<>();

    public  void readAllMemberData(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String content = null;
            String[] IdAndNames = null;
            String id = null;
            String name = null;
            String lastPayDate = null;
            while ((content = reader.readLine()) != null) {
                IdAndNames = content.split(",");
                id = IdAndNames[0].trim();
                name = IdAndNames[1].trim();
                System.out.println("Here is id and name: " + id + " " + name);
                lastPayDate = reader.readLine();
                System.out.println("Here is lastPayDate: " + lastPayDate);
                Member member = new Member(id,name,lastPayDate);
                memberLists.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  boolean compareDate(String dateString){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sourceDate = LocalDate.parse(dateString,formatter);
        Period period = Period.between(sourceDate,currentDate);
        return period.getYears() >= 1;
    }

    public String checkMembership(String keyword,List<Member> list){
        String msg ="Not member at all";
        for(Member member:list){
            if(member.getId().equalsIgnoreCase(keyword)||member.getName().equalsIgnoreCase(keyword)){
               if(compareDate(member.getLastPayDate())){
                   msg = member.getName()+"'s membership expired, last pay date is: "+member.getLastPayDate();
                   break;
               }else {
                   msg = member.getName()+"'s membership is valid, last pay date is: "+member.getLastPayDate();
                   break;
               }
            }
        }
        return msg;
    }

}