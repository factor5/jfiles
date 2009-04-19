/**
 * 
 */
package proj.mod;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Svilen Velikov
 * 
 * 20.01.2009
 */
public class Start {

    private ResourceBundle bundle = null;

    /**
     * Loads configuration based in properties file. It sets the file type
     * extension taken from configuration file and loads all strings that should
     * be replaced. Those strings are found by its prefix also taken from
     * configuration.
     * 
     * @return an array of strings
     * @throws MissingResourceException
     */
    public String[] loadProperties() throws MissingResourceException {
	bundle = ResourceBundle.getBundle("config");
	final String PREFIX = bundle.getString("prefix");
	FileModifier.FILE_EXT_TYPE = bundle.getString("fileExt");
	List<String> strings = new LinkedList<String>();
	Set<String> keys = bundle.keySet();
	for (String key : keys) {
	    if (key.startsWith(PREFIX)) {
		strings.add(bundle.getString(key));
	    }
	}
	return strings.size() != 0 ? strings
		.toArray(new String[strings.size()]) : null;
    }

    /**
     * 
     * @param strings
     * @return
     */
    public String[][] separate(final String[] strings) {
	final String[][] separatedStrings = new String[strings.length][strings.length];
	final String EMPTY_STRING = "";
	for (int i = 0; i < strings.length; i++) {
	    String[] temp = strings[i].split("\\|");
	    separatedStrings[i][0] = temp[0];
	    if (temp.length == 1) {
		separatedStrings[i][1] = EMPTY_STRING;
	    } else {
		separatedStrings[i][1] = temp[1];
	    }
	}

	return separatedStrings;
    }

    /**
     * @param args
     */
    public static void main(String args[]) {
	Start starter = new Start();
	String[] strings = null;
	FileModifier sm = null;
	try {
	    strings = starter.loadProperties();

	    if (strings == null) {
		throw new Exception();
	    }
	    sm = new FileModifier();
	    sm.startModifying(starter.separate(strings));
	} catch (MissingResourceException e) {
	    sm
		    .displayMessage(
			    "Application can't be started because of missing config.properties file or wrong format!",
			    "Error", Byte.valueOf("1"));
	    e.printStackTrace();
	} catch (Exception e) {
	    sm
		    .displayMessage(
			    "There are no strings found for replacement in config file!",
			    "Error", Byte.valueOf("1"));
	    e.printStackTrace();
	}
    }
}
