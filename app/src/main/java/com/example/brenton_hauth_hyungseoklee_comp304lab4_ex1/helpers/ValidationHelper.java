package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers;

import android.content.res.Resources;
import android.widget.EditText;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.R;

import java.util.regex.Pattern;

public class ValidationHelper {
    private static final String
        ID_REGEX = "^\\d{8,}$",
        NAME_REGEX = "^[\\w\\-']+$",
        ROOM_REGEX = "^\\w+\\s*\\-?\\s*\\d+$",
        PASSWORD_REGEX = "^.{8,32}$";

    public static boolean validateId(EditText field, SetCallback<Integer> callback) {
        return validate(field, ID_REGEX, R.string.username_err_msg, wrapIdCallback(callback));
    }

    public static boolean validateId(
            EditText field, int defaultValue,
            SetCallback<Integer> callback) {
        // Wraps validate, but passes ID_REGEX, and errMsg to 0,
        // if it's not valid then we set the default value
        if (!validate(field, ID_REGEX, 0, wrapIdCallback(callback))) {
            try { callback.setValue(defaultValue); }
            catch (Exception e) { return false; }
        }
        return true;
    }

    public static boolean validateName(EditText field, SetCallback<String> callback) {
        return validate(field, NAME_REGEX, R.string.name_err_msg, callback);
    }

    public static boolean validatePassword(EditText field, SetCallback<String> callback) {
        return validate(field, PASSWORD_REGEX, R.string.password_err_msg, callback);
    }

    public static boolean validateRoom(EditText field, SetCallback<String> callback) {
        return validate(field, ROOM_REGEX, R.string.room_err_msg, callback);
    }

    public static boolean validate(
            EditText field, String regex, String defaultValue,
            SetCallback<String> callback) {

        if (!validate(field, regex, 0, callback)) {
            try { callback.setValue(defaultValue); }
            catch (Exception e) { return false; }
        }
        return true;
    }

    public static boolean validate(
            EditText field, String regex,
            int errMsg, SetCallback<String> callback) {

        String text = field.getText().toString().trim();
        if (Pattern.matches(regex, text)) {
            try {
                callback.setValue(text);
                return true;
            } catch (Exception ignore) { }
        }
        if (errMsg != 0) {
            Resources res = field.getResources();
            field.setError(res.getString(errMsg));
        }
        return false;
    }

    private static SetCallback<String> wrapIdCallback(SetCallback<Integer> callback) {
        // Quick reuse of code;
        return str -> {
            int id = Integer.parseInt(str);
            if (id == 0) throw new Exception();
            callback.setValue(id);
        };
    }

    public interface SetCallback<T> { void setValue(T value) throws Exception; }
}
