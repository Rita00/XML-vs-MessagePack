import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Project1 {
    public static void main(String[] args) {
        Calendar today = Calendar.getInstance();
        Owner newowner = new Owner();
        Pet newpet = new Pet(1, "ola", "s", "s", 21.0f, LocalDate.parse("2021-09-12"),"daf", newowner);
        System.out.println(today.get(Calendar.YEAR));
        System.out.println(newpet.getBirth());
        System.out.println(LocalDate.now());
    }
}
