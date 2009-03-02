package proj.pe.util;

import java.util.regex.PatternSyntaxException;

/**
 * Validator class provides some convenient methods for validation of the data.
 */
public class Validator {

    /**
     * Validates a user name. It allows min 2 and max 30 symbols (chars, digits
     * and spaces).
     * 
     * @param name
     *                the name to be checked
     * @return true if the name is valid
     */
    public static boolean validateName(String name) {
	if (name != null) {
	    try {
		if (name.matches("[\\w\\s]{2,30}")) {
		    return true;
		}
	    } catch (PatternSyntaxException ex) {
		// Syntax error in the regular expression
	    }
	}
	return false;
    }

    /**
     * Validates faculty number or egn according the second parameter provided.
     * 
     * @param source
     *                the source string to be validated
     * @param type
     *                the type of the string that forces validator to apply
     *                appropriate matcher
     * @return true if the source string is valid according its type
     */
    public static boolean validateEgnOrFacNumber(String source, int type) {
	if (source == null) {
	    return false;
	}
	try {
	    switch (type) {
	    case 1:
		if (source.matches("\\d{10}")) {
		    return true;
		}
		break;
	    case 2:
		if (source.matches("\\d{6}")) {
		    return true;
		}
		break;
	    default:
		break;
	    }
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	} catch (PatternSyntaxException ex) {
	    ex.printStackTrace();
	}
	return false;
    }

    /**
     * Validates the student results.
     * 
     * @param result
     * @return true if the argument is valid one
     */
    public static boolean validateResult(String result) {
	if (result == null) {
	    return false;
	}
	try {
	    Float res = Float.valueOf(result);
	    if (res >= 2.0 && res <= 6.0) {
		return true;
	    }
	    return false;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    public static boolean validateRoomNumber(String number) {
	// TODO validation method
	return true;
    }

    public static boolean validatePhoneNumber(String number) {
	// TODO validation method
	return true;
    }
}
