/**
 * 
 */
package proj.subs.logger;

/**
 * @author Svilen Velikov
 * 
 * 18.04.2009
 */
public interface ILogger {

    void appendText(String text, boolean bold);

    void appendLine(String text, boolean bold);

}
