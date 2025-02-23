package bt1.bon;

public class Employee {
    private int Id;
    private String Fistname;
    private String Lastname;
    private int Saraly;

    public Employee(int Id,String Fistname,String LastName,int Saraly) {
        this.Id = Id;
        this.Fistname = Fistname;
        this.Lastname = LastName;
        this.Saraly = Saraly;
    }

    public int getId() {
        return Id;
    }
     public String getFistname() {
        return  Fistname;
     }
     public String getLastname() {
        return Lastname;
     }
     public String getName() {
        return Fistname + " " +  Lastname;
     }
     public int getSaraly() {
        return Saraly;
     }
     public void setSaraly(int newSaraly) {
        Saraly = newSaraly;
     }
     public int getAnnuaSaraly() {
        return Saraly * 12;
     }
     public int getRaiseSaraly(int percent) {
        return Saraly * percent / 100 + Saraly;
     }
     public String toString() {
        return "Employee[id = " + Id + " fistname = " + Fistname + " lastname = " + Lastname + " saraly = " + Saraly + "]";
     }
}
