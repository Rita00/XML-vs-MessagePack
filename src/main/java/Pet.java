import java.time.LocalDate;
import java.util.Calendar;

public class Pet {
    private int id;
    private String name;
    private String species;
    private String gender;
    private float weight;
    private LocalDate birth;
    private String description;

    //private Owner owner;

    public Pet(int id, String name, String species, String gender, float weight, LocalDate birth, String description) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.weight = weight;
        this.birth = birth;
        this.description = description;
        //this.owner = owner;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}