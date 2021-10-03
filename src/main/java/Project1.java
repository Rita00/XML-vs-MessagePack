import jakarta.xml.bind.JAXBException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Project1 {
    public static void main(String[] args) {

        try {
            FileReader arq = new FileReader("file.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ArrayList<Owner> ownersList = new ArrayList<>();
            PetsOwnerList allOwners = new PetsOwnerList();

            while (linha != null) {
                ArrayList<Pet> petList = new ArrayList<>();

                String[] owner_caract = linha.split("#");

                String[] list_pets = owner_caract[5].split("\\?");


                for (String line_pet : list_pets) {
                    String[] pet_caract = line_pet.split("\\+");
                    Pet newPet = new Pet(Integer.parseInt(pet_caract[0]), pet_caract[1], pet_caract[2], pet_caract[3],
                            Float.parseFloat(pet_caract[4]), LocalDate.parse(pet_caract[5], DateTimeFormatter.ofPattern("yyyy/MM/dd")), pet_caract[6]);

                    petList.add(newPet);

                }

                Owner newOwner = new Owner(Integer.parseInt(owner_caract[0]), owner_caract[1],
                        LocalDate.parse(owner_caract[2], DateTimeFormatter.ofPattern("yyyy/MM/dd")), Long.parseLong(owner_caract[3]), owner_caract[4], petList);

                newOwner.setPetsList(petList);

                ownersList.add(newOwner);
                linha = lerArq.readLine();
            }
            
            allOwners.setAllOwners(ownersList);
            arq.close();

            long start = System.currentTimeMillis();
            allOwners.marshal();
            allOwners = PetsOwnerList.unmarshal();

            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            FileWriter fw = new FileWriter("tempos_JavaToXml.txt", true);
            fw.write(timeElapsed + "\n");
            
            byte[] bytes = allOwners.msgPackSerialize();

            allOwners = PetsOwnerList.msgPackDeserialize(bytes);
            //System.out.println(allOwners.toString());
            fw.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        } catch (JAXBException e) {
//            //e.printStackTrace();
        }
    }
}
