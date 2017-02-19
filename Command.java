/**
 * @author nathan
 *         created on 2017-02-18.
 */
public interface Command {
    void executeCommand(String[] cmd, VendingMachine vm);
}