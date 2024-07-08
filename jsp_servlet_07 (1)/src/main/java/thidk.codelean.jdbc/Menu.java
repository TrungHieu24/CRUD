package thidk.codelean.jdbc;

public class Menu {

    private int id;
    private String Name;
    private int Price;
    private String Description;
    private String Image; // Add this field

    // Constructor for adding a new menu item without an ID
    public Menu(String Name, int Price, String Description, String Image) {
        this.Name = Name;
        this.Price = Price;
        this.Description = Description;
        this.Image = Image;
    }

    // Constructor for updating a menu item
    public Menu(int id, String Name, int Price, String Description, String Image) {
        this.id = id;
        this.Name = Name;
        this.Price = Price;
        this.Description = Description;
        this.Image = Image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return Price;
    }
    public void setPrice(int Price) {
        this.Price = Price;
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

    public String getImagePath() {return Image;}
    public void setImage(String Image) {
        this.Image = Image;
    }



    @Override
    public String toString() {
        return "Menu [id=" + id + ", Name=" + Name + ",Price=" + Price + ", Description=" + Description + "]";
    }



}
