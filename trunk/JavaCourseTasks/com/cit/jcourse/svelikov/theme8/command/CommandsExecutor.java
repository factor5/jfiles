package com.cit.jcourse.svelikov.theme8.command;

/**
 * The only method in this class is to apply any command
 * passed as argument. All the command objects implements
 * the Command interface with the given method execute().
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class CommandsExecutor {
	
	/** The real command that has to be applied. */
	private Command command;
	
	/**
	 * Invokes the execute method from the command object
	 * given as argument.
	 * @param com The real command.
	 */
	public void makeCalculation(Command com) {
		command = com;
		command.execute();
	}
}
