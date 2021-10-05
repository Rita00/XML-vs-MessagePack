import jakarta.xml.bind.JAXBException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Project1 {
    static String owners = "200000";
    public static void main(String[] args) {

        try {
            FileReader arq = new FileReader("Resources/TestFiles/" + owners + ".txt");
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
            
            switch (args[0]) {
                case "xml":
                    for (int i = 0; i < 100; i++) {
                        allOwners.marshal();
                        allOwners = PetsOwnerList.unmarshal();
                    }
                    break;
                case "msgPack":
                    for (int i = 0; i < 100; i++) {
//                        System.gc();
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        byte[] bytes = allOwners.msgPackSerialize();
                        allOwners = PetsOwnerList.msgPackDeserialize(bytes);
                    }
                    break;
                default: 
                    break;
            }

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        } catch (JAXBException e) {
//            //e.printStackTrace();
        }
    }
}
