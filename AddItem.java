import java.util.Arrays;

/**
 * @author nathan
 *         created on 2017-02-18.
 */
public class AddItem implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        if (cmd.length < 2) {
            System.out.println("** Syntax: add [amount]");
            return;
        }
        switch (cmd[1]) {
            case "loonie":
                vm.addMoney(Money.LOONIE);
                break;
            case "toonie":
                vm.addMoney(Money.TOONIE);
                break;
            case "five":
                vm.addMoney(Money.FIVE);
                break;
            case "ten":
                vm.addMoney(Money.TEN);
                break;
            case "twenty":
                vm.addMoney(Money.TWENTY);
                break;
            default:
                System.out.println("Amount must be one of the following: " + Arrays.toString(Money.values()));
                return;
        }
        System.out.printf("New balance: %.2f\r\n", vm.getUserBalance());
    }
}