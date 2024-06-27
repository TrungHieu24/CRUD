package thidk.codelean.jdbc;

public class Menu {

    private int id;
    private String Name;
    private String Description;

    public Menu(String Name, String Description) {
        this.Name = Name;
        this.Description = Description;
    }

    public Menu(int id, String Name, String Description) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", Name=" + Name + ", Description=" + Description + "]";
    }
}
