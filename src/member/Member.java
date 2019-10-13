package member;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable{
    private String id;
    private String name;
    private String lastPayDate;
    private List<String> trainingHistory = new ArrayList<>();

    public Member() {
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String id, String name, String lastPayDate) {
        this.id = id;
        this.name = name;
        this.lastPayDate = lastPayDate;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastPayDate() {
        return lastPayDate;
    }

    public List<String> getTrainingHistory() {
        return trainingHistory;
    }

    public void setLastPayDate(String lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrainingHistoryRecord(String dateString){
        this.trainingHistory.add(dateString);
        String path = "trainingRecords"+this.name+".txt";
        try(ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))){
            writer.writeObject(this);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return id+" "+name+" "+lastPayDate;
    }
}
