import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EncDec encDec = new EncDec();
        StringBuilder string = new StringBuilder("");
        int key = 0;
        String alg = "";
        String mode = "";
        String in = "";
        String out = "";
        boolean inCheck = false;
        boolean outCheck = false;
        boolean dataCheck = false;
        boolean algCheck = false;

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-mode"))
                mode = args[i + 1];
            if (args[i].equals("-key"))
                key = Integer.parseInt(args[i + 1]);
            if (args[i].equals("-data")) {
                string.append(args[i + 1]);
                dataCheck = true;
            }
            if (args[i].equals("-in")) {
                in = args[i + 1];
                inCheck = true;
            }
            if (args[i].equals("-out")) {
                out = args[i + 1];
                outCheck = true;
            }
            if (args[i].equals("-alg")) {
                alg = args[i + 1];
                algCheck = true;
            }
        }
        if (!algCheck || alg.equals("shift")) {
            encDec.setAlgorithm(new Shift(), mode);
        } else encDec.setAlgorithm(new Unicode(), mode);

        if (!dataCheck && !inCheck) {
            encDec.useAlgorithm(string, key);
            System.out.println(string.toString());
        } else if (dataCheck && inCheck) {
            encDec.useAlgorithm(string, key);
            System.out.println(string.toString());
        } else if (inCheck && !outCheck) {
            try (Scanner scanner = new Scanner(new File(in))) {
                string.append(scanner.nextLine());
                encDec.useAlgorithm(string, key);
                System.out.println(string.toString());
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else if (inCheck && outCheck) {
            try (Scanner scanner = new Scanner(new File(in));
                 FileWriter writer = new FileWriter(new File(out), false)) {
                string.append(scanner.nextLine());
                encDec.useAlgorithm(string, key);
                writer.write(string.toString());
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else {
            encDec.useAlgorithm(string, key);
        }
    }
}
