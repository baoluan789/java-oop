package bt6.sau;

public class dog extends Animal{
    public dog(String name) {
        super(name);
    }
    public void greets() {
        System.out.println("woof");
    }
    public void greets(dog another) {
        System.out.println("wooooof");
    }
}
