import java.util.regex.Pattern;

public class Utils {
    public static String checkRegNumberFormat(String regNumber){
        if(!Pattern.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}", regNumber)) throw new RegistrationNumberFormatException();
        return regNumber;
    }
}
