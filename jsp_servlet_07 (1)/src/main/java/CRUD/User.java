package CRUD;

public class User {

    private int id;
    private String FullName;
    private String Birthday;
    private String Email;
    private String Phone;
    private String Password;
    private String Role;
    private String Address;
    private String ResetToken;

    // Add this field

    // Constructor for adding a new menu item without an ID
    public User(String FullName, String Birthday,String Phone , String Email, String Password, String Role, String Address, String ResetToken) {
        this.FullName = FullName;
        this.Birthday = Birthday;
        this.Phone = Phone;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.Address = Address;
        this.ResetToken = ResetToken;

    }

    // Constructor for updating a menu item
    public User(int id, String FullName, String Birthday,String Phone, String Email, String Password, String Role, String Address, String ResetToken) {
        this.id = id;
        this.FullName = FullName;
        this.Birthday = Birthday;
        this.Phone = Phone;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.Address = Address;
        this.ResetToken = ResetToken;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return Phone;
    }
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }


    public String getBirthday() {
        return Birthday;
    }
    public void setBirthday(String Birthday) {this.Birthday = Birthday;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }
    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getResetToken() {
        return ResetToken;
    }
    public void setResetToken(String ResetToken) {
        this.ResetToken = ResetToken;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", FullName=" + FullName + ",Birthday=" + Birthday + ",Phone=" + Phone + ",Email=" + Email + ",Password=" + Password + ",Role=" + Role + ",,Address=" + Address + ",ResetToken=" + ResetToken + "]";
    }



}
