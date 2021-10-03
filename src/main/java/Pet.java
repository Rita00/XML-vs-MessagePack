import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.msgpack.annotation.Message;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "specie", "gender", "weight", "birth", "description"})
@Message
public class Pet {
    @XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "specie")
    private String specie;

    @XmlElement(name = "gender")
    private String gender;

    @XmlElement(name = "weight")
    private float weight;

    @XmlJavaTypeAdapter(value = LocalDateXml.class)
    private LocalDate birth;

    @XmlElement(name = "description")
    private String description;

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", birth=" + birth +
                ", description='" + description + '\'' +
                '}';
    }

    public Pet(int id, String name, String specie, String gender, float weight, LocalDate birth, String description) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.gender = gender;
        this.weight = weight;
        this.birth = birth;
        this.description = description;
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

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
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