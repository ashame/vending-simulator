/**
 * @author nathan
 *         created on 2017-02-19.
 */
public class SelectItem implements Command {

    public void executeCommand(String[] cmd, VendingMachine vm) {
        if (cmd.length < 2) {
            System.out.println("** Syntax: select [product name]");
            return;
        }

        String productName = "";
        for (int i = 1; i < cmd.length; i++) {
            productName += cmd[i];
            productName += " ";
        }
        productName = productName.trim();

        Product p = vm.getProductByName(productName);
        if (p != null)
            vm.selectProduct(p);
        else
            System.out.println("The product \"" + productName + "\" was not found.");
    }
}
