import jakarta.xml.bind.JAXBException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) throws IOException {
        /*Calendar today = Calendar.getInstance();
        Owner newowner = new Owner();
        Pet newpet = new Pet(1, "ola", "s", "s", 21.0f, LocalDate.parse("2021-09-12"),"daf", newowner);
        System.out.println(today.get(Calendar.YEAR));
        System.out.println(newpet.getBirth());
        System.out.println(LocalDate.now());*/

        try {
            FileReader arq = new FileReader("file.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ArrayList<Owner> ownersList = new ArrayList<>();
            PetsOwnerList allOwners = new PetsOwnerList();

            while (linha != null) {
                //System.out.printf("%s\n", linha);

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

            // Medição de tempo em milisegundos
            long start = System.currentTimeMillis();
            allOwners.marshal();
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed);

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
