package bt4.sau;

public class cat extends Mammal{
    public cat(String name) {
        super(name);
    }
    public void greets() {
        System.out.println("meow");
    }
    public String toString(){
        return "Cat[" + super.toString() + "]";
    }
}
