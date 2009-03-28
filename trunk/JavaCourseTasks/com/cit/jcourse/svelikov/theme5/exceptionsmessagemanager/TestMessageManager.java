/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.exceptionsmessagemanager;

/**
 * A TestMessageManager class invokes fillExceptionsTable method
 * to prepare the hashtable for tests that follows.
 * 
 * @author  Svilen Velikov
 * @version 01 04.01.07
 */
public class TestMessageManager {

	/**
	 * Passes some messages that have to be added to the combination.
	 * Then prints constructed message as whole string by getMessage() method
	 * and as separated strigs by the getMessages() method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionsMessageManager emm = new ExceptionsMessageManager();
		
		int hash;
		String key;
		String msg;
		String[] messArray = {"Wrong ID","Invalid postal code",
				"Invalid debit-card number","Wrong password"};
		/*fill in the hashtable */
		for (int i = 0; i < messArray.length; i++) {
			emm.putMessage(String.valueOf(i), messArray[i]);
		}
		
		try {
			//pass some 'hashcoded' messages to be added to combination
//			hash = "Invalid postal code".hashCode();
//			msg = String.valueOf(hash);
//			emm.addExceptionMessageUsingCode(msg);
//			hash = "Wrong ID".hashCode();
//			msg = String.valueOf(hash);
//			emm.addExceptionMessageUsingCode(msg);
//			hash = "Wrong password".hashCode();
//			msg = String.valueOf(hash);
//			emm.addExceptionMessageUsingCode(msg);
//			System.out.println("whole message combination : "+emm.getMessage());
//			ExceptionsMessageManager.getMessages(emm.getMessage());
			
			//pass some messages to be added to combination
			
		    emm.addExceptionMessage("Invalid postal code");
			emm.addExceptionMessage("Wrong ID");
			emm.addExceptionMessage("Wrong password");
			System.out.println("whole message combination : "+emm.getMessage());
			ExceptionsMessageManager.getMessages(emm.getMessage());
		} catch (InvalidCodeOrMessageException e) {
			System.err.println(e.getMessage());
		}
	}
}
