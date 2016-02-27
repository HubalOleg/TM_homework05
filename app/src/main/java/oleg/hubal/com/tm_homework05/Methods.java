package oleg.hubal.com.tm_homework05;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 27.02.2016.
 */
public class Methods {

    public static boolean isEmptyFields(String... s) {
//        Проверяем каждое поле на пустоту
        for (String str : s)
            if(str.isEmpty()) return true;

        return false;
    }

    public static boolean isValidEmailAdress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
