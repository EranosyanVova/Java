package encryptdecrypt;

import encryptdecrypt.algorithm.ShiftAlgorithm;
import encryptdecrypt.algorithm.UnicodeAlgorithm;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CryptorParameters cryptorParameters = parseArgumentsToParameters(args);

        Cryptor cryptor = new Cryptor(
                cryptorParameters.useUnicodeAlgorithm() ? new UnicodeAlgorithm() : new ShiftAlgorithm(),
                cryptorParameters.mode
        );

        if (cryptorParameters.shouldReadStringToCryptFromFile()) {
            cryptorParameters.stringToCrypt = readFromFile(cryptorParameters.inputFilePath);
        }

        String result = cryptor.useAlgorithm(cryptorParameters.stringToCrypt, cryptorParameters.key);

        if (cryptorParameters.outFilePath != null) {
            writeToFile(cryptorParameters.outFilePath, result);
        } else {
            System.out.println(result);
        }
    }

    private static CryptorParameters parseArgumentsToParameters(String[] args) {
        CryptorParameters parameters = new CryptorParameters();

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-mode")) {
                parameters.mode = args[i + 1];
            }
            if (args[i].equals("-key")) {
                parameters.key = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equals("-data")) {
                parameters.stringToCrypt = (args[i + 1]);
            }
            if (args[i].equals("-in")) {
                parameters.inputFilePath = args[i + 1];
            }
            if (args[i].equals("-out")) {
                parameters.outFilePath = args[i + 1];
            }
            if (args[i].equals("-alg")) {
                parameters.algorithmName = args[i + 1];
            }
        }
        return parameters;
    }

    private static String readFromFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            return scanner.nextLine();
        } catch (IOException e) {
            System.out.println("Error while reading from file: " + path);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    private static void writeToFile(String path, String data) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + path);
        }
    }
}
