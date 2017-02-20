/**
 * @author nathan
 *         created on 2017-02-19.
 */
public class RestockProduct implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        if (cmd.length < 2) {
            System.out.println("** Syntax: restock [product] [quantity]");
            return;
        }
        String productName = "";
        for (int i = 1; i < cmd.length - 1; i++) {
            productName += cmd[i];
            productName += " ";
        }
        productName = productName.trim();

        int quantity = 5; //default to 5 if no quantity / invalid quantity entered
        try {
            quantity = Integer.parseInt(cmd[cmd.length - 1]);
        } catch (NumberFormatException ignored) {
        }

        if (cmd[1].equalsIgnoreCase("all")) {
            vm.restockAll(quantity);
        } else {
            Product p = vm.getProductByName(productName);
            if (p != null)
                vm.restock(p, quantity);
            else
                System.out.println("\"" + productName + "\" does not exist in the vending machine.");
        }

    }
}
