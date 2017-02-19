/**
 * @author nathan
 *         created on 2017-02-18.
 */
public class ListItems implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        System.out.println(vm.toString());
    }
}