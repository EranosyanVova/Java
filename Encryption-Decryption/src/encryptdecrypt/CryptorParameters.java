package encryptdecrypt;

public class CryptorParameters {
    public String stringToCrypt = "";

    public String mode;
    public String algorithmName;
    public String inputFilePath;
    public String outFilePath;
    public int key;

    public boolean useUnicodeAlgorithm() {
        return "unicode".equals(algorithmName);
    }

    public boolean shouldReadStringToCryptFromFile() {
        return inputFilePath != null && stringToCrypt.isEmpty();
    }
}