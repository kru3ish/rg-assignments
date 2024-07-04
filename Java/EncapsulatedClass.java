package Java;

public class EncapsulatedClass {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        EncapsulatedClass obj = new EncapsulatedClass();
        obj.setId(1);
        obj.setName("Example");
        System.out.println("ID: " + obj.getId() + ", Name: " + obj.getName());
    }
}
