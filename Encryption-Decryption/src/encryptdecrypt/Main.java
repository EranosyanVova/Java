package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class CryptorParameters {



}

public class Main {
    public static void main(String[] args) {
        Cryptor cryptor = new Cryptor();
        StringBuilder stringBuilder = new StringBuilder();
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
            if (args[i].equals("-mode")) {
                mode = args[i + 1];
            }
            if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equals("-data")) {
                stringBuilder.append(args[i + 1]);
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
            cryptor.setAlgorithm(new ShiftAlgorithm(), mode);
        } else {
            cryptor.setAlgorithm(new UnicodeAlgorithm(), mode);
        }

        if (!dataCheck && !inCheck) {
            cryptor.useAlgorithm(stringBuilder, key);
            System.out.println(stringBuilder);
        }
        else if (dataCheck && inCheck) {
            cryptor.useAlgorithm(stringBuilder, key);
            System.out.println(stringBuilder);
        }
        else if (inCheck && !outCheck) {
            try (Scanner scanner = new Scanner(new File(in))) {
                stringBuilder.append(scanner.nextLine());
                cryptor.useAlgorithm(stringBuilder, key);
                System.out.println(stringBuilder);
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        else if (inCheck) {
            try (Scanner scanner = new Scanner(new File(in));
                 FileWriter writer = new FileWriter(new File(out), false)) {
                stringBuilder.append(scanner.nextLine());
                cryptor.useAlgorithm(stringBuilder, key);
                writer.write(stringBuilder.toString());
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        else {
            cryptor.useAlgorithm(stringBuilder, key);
        }
    }
}
