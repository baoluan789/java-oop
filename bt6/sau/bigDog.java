package bt6.sau;

public class bigDog  extends dog{
    public bigDog(String name) {
        super(name);
    }
    public void greets() {
        System.out.println("woow");
    }
    public void greets(dog another) {
        System.out.println("wooooow");
    }
    public void greets(bigDog another) {
        System.out.println("woooooooooow");
    }
}
