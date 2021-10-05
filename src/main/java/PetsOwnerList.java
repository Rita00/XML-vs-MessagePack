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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "ListPeople")
@XmlAccessorType(XmlAccessType.FIELD)
@Message
public class PetsOwnerList {

    static long start;
    static long finish;
    static long timeElapsed;
    static FileWriter marshal, unmarshal, serialization, deserialization;
    
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

        start = System.currentTimeMillis();
        mar.marshal(this, new File("./owners.xml"));
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        try {
            marshal = new FileWriter("./Resources/TimeFiles/marshal_" + Project1.owners + ".txt", true);
            marshal.write(timeElapsed + "\n");
            marshal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PetsOwnerList unmarshal() {
        JAXBContext jaxbContext = null;
        File file = new File("owners.xml");
        try {
            jaxbContext = JAXBContext.newInstance(PetsOwnerList.class);
            start = System.currentTimeMillis();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PetsOwnerList allInfo = (PetsOwnerList) jaxbUnmarshaller.unmarshal(file);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            try {
                unmarshal = new FileWriter("./Resources/TimeFiles/unmarshal_" + Project1.owners + ".txt", true);
                unmarshal.write(timeElapsed + "\n");
                unmarshal.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return allInfo;
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
            FileOutputStream fos = new FileOutputStream("msgPack.txt");
            start = System.currentTimeMillis();
            packer.write(this);
            fos.write(out.toByteArray(), 0, out.toByteArray().length);
            fos.flush();
            fos.close();
//            Files.write(path, out.toByteArray());
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            serialization = new FileWriter("./Resources/TimeFiles/serialization_" + Project1.owners + ".txt", true);
            serialization.write(timeElapsed + "\n");
            serialization.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static PetsOwnerList msgPackDeserialize(byte[] bytes) {
        MessagePack msgpack = new MessagePack();
        msgpack.register(LocalDate.class, LocalDateSerializerTemplate.getInstance());

        PetsOwnerList dst1 = null;
        byte[] data;
        try {
            File inputFile = new File("msgPack.txt");
            data = new byte[(int) inputFile.length()];
            FileInputStream fis = new FileInputStream(inputFile);

            start = System.currentTimeMillis();

            fis.read(data, 0, data.length);
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            Unpacker unpacker = msgpack.createUnpacker(in);

            dst1 = unpacker.read(PetsOwnerList.class);

            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            fis.close();
            deserialization = new FileWriter("./Resources/TimeFiles/deserialization_" + Project1.owners + ".txt", true);
            deserialization.write(timeElapsed + "\n");
            deserialization.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dst1;
    }
}