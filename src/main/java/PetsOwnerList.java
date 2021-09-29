import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.util.ArrayList;

@XmlRootElement(name="ListPeople")
@XmlAccessorType(XmlAccessType.FIELD)

public class PetsOwnerList {

    @XmlElement(name = "owner")
    ArrayList<Owner> allOwners;

    public ArrayList<Owner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(ArrayList<Owner> allOwners) {
        this.allOwners = allOwners;
    }

    public void marshal() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PetsOwnerList.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

       // mar.marshal(this, System.out);
        mar.marshal(this, new File("./owners.xml"));
    }
}
