import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;
import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;



@XmlRootElement(name = "ListPeople")
@XmlAccessorType(XmlAccessType.FIELD)
@Message
public class PetsOwnerList {
    long start;
    long finish;
    long timeElapsed;

    @XmlElement(name = "owner")
    ArrayList<Owner> allOwners;

    public PetsOwnerList() {
    }

    public ArrayList<Owner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(ArrayList<Owner> allOwners) {
        this.allOwners = allOwners;
    }

    @Override
    public String toString() {
        return "PetsOwnerList{" +
                "allOwners=" + getAllOwners().toString() +
                '}';
    }

    public void marshal() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PetsOwnerList.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // mar.marshal(this, System.out);
        // Medição de tempo em milisegundos
        start = System.currentTimeMillis();
        mar.marshal(this, new File("./owners.xml"));
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;

        try {
            FileWriter fw = new FileWriter("tempos_JavaToXml.txt", true);
            fw.write(timeElapsed + "\n");
            System.out.println("XML " + timeElapsed);

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PetsOwnerList unmarshal() {
        JAXBContext jaxbContext = null;
        File file = new File("owners.xml");
        try {
            jaxbContext = JAXBContext.newInstance(PetsOwnerList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (PetsOwnerList) jaxbUnmarshaller.unmarshal(file);
            //System.out.println(allInfo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public byte[] msgPackSerialize() {
        MessagePack msgpack = new MessagePack();
        msgpack.register(LocalDate.class, LocalDateSerializerTemplate.getInstance());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Packer packer = msgpack.createPacker(out);
        try {
            start = System.currentTimeMillis();
            packer.write(this);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("msgPack " + timeElapsed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static PetsOwnerList msgPackDeserialize(byte[] bytes) {
        MessagePack msgpack = new MessagePack();
        msgpack.register(LocalDate.class, LocalDateSerializerTemplate.getInstance());

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Unpacker unpacker = msgpack.createUnpacker(in);
        PetsOwnerList dst1 = null;
        try {
            dst1 = unpacker.read(PetsOwnerList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dst1;
    }
}