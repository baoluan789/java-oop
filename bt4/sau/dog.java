package bt4.sau;

public class dog extends Mammal {
    public dog(String name) {
        super(name);
    }
    public void greets() {
        System.out.println("woof");
    }
    public void greets(dog another) {
        System.out.println("wooooof");
    }
    public String toString() {
        return "dog[" + super.toString() + "]";
    }
}
