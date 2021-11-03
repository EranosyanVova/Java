package encryptdecrypt;

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
            }
            else if (dataCheck && inCheck) {
                encDec.useAlgorithm(string, key);
                System.out.println(string.toString());
            }
            else if (inCheck && !outCheck) {
                try (Scanner scanner = new Scanner(new File(in))) {
                    string.append(scanner.nextLine());
                    encDec.useAlgorithm(string, key);
                    System.out.println(string.toString());
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
            else if (inCheck && outCheck) {
                try (Scanner scanner = new Scanner(new File(in));
                     FileWriter writer = new FileWriter(new File(out), false)) {
                    string.append(scanner.nextLine());
                    encDec.useAlgorithm(string, key);
                    writer.write(string.toString());
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
            else {
                encDec.useAlgorithm(string, key);
            }
    }
}

class EncDec {
    private EncodingAlgorithm algorithm;
    private String mode;

    public void setAlgorithm(EncodingAlgorithm algorithm, String mode) {
        this.algorithm = algorithm;
        this.mode = mode;
    }

    public void useAlgorithm(StringBuilder string, int key) {
        if (mode.equals("enc") || mode.equals(""))
            this.algorithm.encrypted(string, key);
        else this.algorithm.decrypted(string, key);
    }
}



interface EncodingAlgorithm {
    void encrypted(StringBuilder string, int key);
    void decrypted(StringBuilder string, int key);
}

class Shift implements EncodingAlgorithm {

    public void encrypted(StringBuilder string, int key) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 97 && string.charAt(i) <= 122) {
                string.setCharAt(i, (char)(97 + (string.charAt(i) - 97 + key) % 26));
            }
        }
    }

    public void decrypted(StringBuilder string, int key) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 97 && string.charAt(i) <= 122) {
                string.setCharAt(i, (char)((string.charAt(i) - 97 - key) % 26 >= 0 ? 97 + (string.charAt(i) - 97 - key) % 26 : 123 + (string.charAt(i) - 97 - key) % 26));
            }
        }
    }
}

class Unicode implements EncodingAlgorithm {

    public void encrypted(StringBuilder string, int key) {
        for (int i = 0; i < string.length(); i++) {
            string.setCharAt(i, (char)(string.charAt(i) + key));
        }
    }

    public void decrypted(StringBuilder string, int key) {
        for (int i = 0; i < string.length(); i++) {
            string.setCharAt(i, (char)(string.charAt(i) - key));
        }
    }
}