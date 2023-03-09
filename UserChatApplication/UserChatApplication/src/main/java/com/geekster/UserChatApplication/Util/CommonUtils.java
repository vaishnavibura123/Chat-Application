package com.geekster.UserChatApplication.Util;
import java.util.regex.*;

public class CommonUtils {
public static boolean isValidPassword(String password){

    String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";

    Pattern p = Pattern.compile(regex);


    Matcher m = p.matcher(password);

    return m.matches();
}

    public  static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^\\d{10}$");

        Matcher m = p.matcher(phoneNumber);

        return (m.matches());
    }

}
