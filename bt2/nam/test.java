package bt2.nam;

public class test {
   public static void main(String[]args) {
       Customer c1 = new Customer(1,"luan",'m');
       Customer c2 = new Customer(2,"huan",'f');
       System.out.println(c1);
       account a1 = new account(c1,2,55);
       System.out.println(a1);
       account a2 = new account(c2,3);
       a2.deposit(50);
       a1.withdraw(50);
       System.out.println(a1);
       System.out.println(a2);
       System.out.println(a1.withdraw(10));
   }
}
