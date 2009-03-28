/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.exceptionsmessagemanager;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * This class arranges a combination from preliminary defined
 * composition of messages in one string. The message composition 
 * is stored in a hashtable. 
 * 
 * @author  Svilen Velikov
 * @version 04.01.07
 */
public class ExceptionsMessageManager {
	
	/** Holds generated combination of messages */
	private String message = null;
	
	/** The separator */
	private static final String separator = ":";
	
	/** Holds the messages and the keys for them */
	private Map exceptions = new Hashtable(10);
	
	/**
	 * Adds messages given as string to the combination. The new message
	 * will be added only if the hashtable contains such message as value.
	 * 
	 * @param mess The message that have to be added to the combination.
	 * @throws InvalidCodeOrMessageException If the hashtable doesn't contain given message.
	 */
	public void addExceptionMessage(String mess) throws InvalidCodeOrMessageException {
		if(exceptions.containsValue(mess)){			/*hashtable contains this message*/
			/*if combination is empty, first message will be added without separator before*/
			message = ((message!=null)?(message+=separator+mess):(message=mess));
		} else {									/*no such value in hashtable*/
			throw new InvalidCodeOrMessageException("Choosen message doesn't exist in the table!");
		}
	}
	
	/**
	 * Adds messages given as code in string representation to the combination.
	 * The new message will be added only if the hashtable contains such message
	 * as value.
	 * 
	 * @param messageCode The hash code for this message to be added.
	 * @throws InvalidCodeOrMessageException If the hashtable doesn't contain given code as key.
	 */
	public void addExceptionMessageUsingCode(String messageCode) throws InvalidCodeOrMessageException {
		if(exceptions.containsKey(messageCode)) {			/*hashtable contains this code as key*/
			String valueField = (String) exceptions.get(messageCode);/*get message maped with this key*/
			/*if combination is empty, first message will be added without separator before*/
			message = ((message!=null)?(message+=separator+valueField):(message=valueField));
		} else {											/*no such key in hashtable*/
			throw new InvalidCodeOrMessageException("The given code doesn't map any message in the table!");
		}
	}
	
	/**
	 * Returns constructed combination of messages if it was created.
	 * @return The combination as whole string.
	 */
	public String getMessage() {
		return (message = ((message != null)?message:"There isn't message constructed!"));
	}
	
	/**
	 * Splits this message combination to strings using separator and returns
	 * them as List.
	 * 
	 * @param  messagesCombination The combination to process.
	 * @return A List that contains separated messages.
	 */
	public static List getMessages(String messagesCombination) {
		String[] messArray = null;						/*temp Array*/
		/*splits the argument to separate strings using separator*/
		messArray = messagesCombination.split(separator);
		/*puts the contains of messArray to a List*/
		List separateMess = Arrays.asList(messArray);
		return separateMess;
	}
	
	/**
	 * Adds key/message in the table.
	 * @param key The key.
	 * @param msg The message to be added.
	 */
	public void putMessage(String key, String msg) {
		exceptions.put(key, msg);
	}
}
