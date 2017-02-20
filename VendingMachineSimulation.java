import javax.swing.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author nathan
 *         created on 2017-02-18.
 */
public class VendingMachineSimulation {
    private static HashMap<String, Command> commands = registerCommands();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean gui;

        if (args.length > 0) {
            gui = args[0].equalsIgnoreCase("--gui");
        } else {
            System.out.print("GUI (y/n): ");
            gui = in.nextLine().equalsIgnoreCase("y");
        }

        VendingMachine machine = new VendingMachine(
                new Product("Potato Chips", 1.25),
                new Product("Chocolate Bar", 1.50),
                new Product("Jell-O Cup", 1.50),
                new Product("Gummy Candies", 1.5),
                new Product("Cookies", 1.75),
                new Product("Granola Bar", 2.00),
                new Product("Mixed Nuts", 2.25),
                new Product("Dried Fruits", 3.00));

        if (gui) {
            in.close();
            VendingGUI vGUI = new VendingGUI(machine);
            SwingUtilities.invokeLater(() -> {
                vGUI.setVisible(true);
                vGUI.setAlwaysOnTop(true);
                vGUI.setAlwaysOnTop(false);
                vGUI.setLocationRelativeTo(null);
            });
        } else {
            processCommands(in, machine);
        }
    }

    /**
     * Processes commands from an input stream
     * @param in the input stream to read commands from
     * @param vm the {@link VendingMachine} to process commands to
     */
    private static void processCommands(Scanner in, VendingMachine vm) {
        String[] input;
        printCommands();
        do {
            System.out.print("\r\nEnter a command: ");
            input = in.nextLine().split(" ");
            Command cmd = commands.get(input[0]);
            if (cmd != null) {
                cmd.executeCommand(input, vm);
            } else {
                if (input[0].equals("quit"))
                    return;
                System.out.println("** " + String.join(" ", input) + " is not a valid command");
                printCommands();
            }
        } while (!input[0].equals("quit"));
    }

    /**
     * Registers {@link Command}s to be processed
     * @return a HashMap of all available commands
     */
    private static HashMap<String, Command> registerCommands() {
        HashMap<String, Command> commands = new HashMap<>();

        commands.put("add", new AddMoney());
        commands.put("list", new ListItems());
        commands.put("select", new SelectProduct());
        commands.put("restock", new RestockProduct());

        return commands;
    }

    /**
     * Prints all available {@link Command}s to console
     */
    private static void printCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\nCommands: ");
        for (String s : commands.keySet()) {
            sb.append(s);
            sb.append(", ");
        }
        sb.append("quit");
        System.out.println(sb.toString());
    }
}