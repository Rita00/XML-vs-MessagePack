import java.util.ArrayList;
import java.util.Date;

public class Owner {
    private int id;
    private String name;
    private Date birth;
    private long phone;
    private String address;

    private ArrayList<Pet> petsList;

    public Owner(int id, String name, Date birth, long phone, String address, ArrayList<Pet> petsList) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.petsList = petsList;
    }

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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Pet> getPetsList() {
        return petsList;
    }

    public void setPetsList(ArrayList<Pet> petsList) {
        this.petsList = petsList;
    }
}
