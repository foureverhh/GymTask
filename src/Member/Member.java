package Member;

public class Member {
    private String id;
    private String name;
    private String lastPayDate;

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

    public void setLastPayDate(String lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id+" "+name+" "+lastPayDate;
    }
}
