package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers;

import android.content.res.Resources;
import android.widget.EditText;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.R;

import java.util.regex.Pattern;

// Class helps with validation of
public class ValidationHelper {
    // All necessary regular expressions for the methods below.
    private static final String
        ID_REGEX = "^\\d{8,}$", // number with minimum 8 digits
        NAME_REGEX = "^[A-Za-z\\-']+$", // Only letters, hyphens & apostrophes
        ROOM_REGEX = "^[A-Z]+\\s*\\-?\\s*\\d+$", // E.g. "A12", "B- 23", "H-1", "F 4"
        PASSWORD_REGEX = "^.{8,32}$"; // anything between 8 & 32 characters

    // Checks if the field has a valid id & sets id to callback
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

        // Has a default value so there is no need for an error message
        if (!validate(field, regex, 0, callback)) {
            // sets default value if value in field is not good
            try { callback.setValue(defaultValue); }
            catch (Exception e) { return false; }
        }
        return true;
    }

    public static boolean validate(
            EditText field, String regex,
            int errMsg, SetCallback<String> callback) {

        String text = field.getText().toString().trim(); // retrieves text
        if (Pattern.matches(regex, text)) {
            // Checks if pattern matches and if
            // data is safely sent to the callback
            try {
                callback.setValue(text);
                return true;
            } catch (Exception ignore) { }
        }
        if (errMsg != 0) {
            // Only displays an error if required
            Resources res = field.getResources();
            field.setError(res.getString(errMsg));
        }
        return false;
    }

    // Wrap to ensure a Integer callback is being passed a safe int
    private static SetCallback<String> wrapIdCallback(SetCallback<Integer> callback) {
        return str -> {
            int id = Integer.parseInt(str);
            if (id == 0) throw new Exception();
            callback.setValue(id);
        };
    }

    // Generic callback to set the data from field
    // after it has been retrieved and checked
    public interface SetCallback<T> { void setValue(T value) throws Exception; }
}
