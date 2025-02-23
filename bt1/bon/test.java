package bt1.bon;

public class test {
    public static void main(String[] args) {
        // Test constructor and toString()
        Employee e1 = new Employee(8, "Peter", "Tan", 2500);
        System.out.println(e1);  // toString();

        // Test Setters and Getters
        e1.setSaraly(999);
        System.out.println(e1);  // toString();
        System.out.println("id is: " + e1.getId());
        System.out.println("firstname is: " + e1.getFistname());
        System.out.println("lastname is: " + e1.getLastname());
        System.out.println("salary is: " + e1.getSaraly());

        System.out.println("name is: " + e1.getName());
        System.out.println("annual salary is: " + e1.getAnnuaSaraly()); // Test method

        // Test raiseSalary()
        System.out.println(e1.getRaiseSaraly(10));
        System.out.println(e1);
    }
}
