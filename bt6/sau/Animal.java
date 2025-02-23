package bt6.sau;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }
    public String toString() {
        return "Animal[name = " + name + "]";
    }
    public abstract void greets();
}
