package encryptdecrypt.algorithm;

public class UnicodeAlgorithm implements CryptorAlgorithm {

    @Override
    public String encrypt(String stringToCrypt, int key) {
        StringBuilder stringBuilder = new StringBuilder(stringToCrypt);

        for (int i = 0; i < stringBuilder.length(); i++) {
            stringBuilder.setCharAt(i, (char)(stringBuilder.charAt(i) + key));
        }

        return stringBuilder.toString();
    }

    @Override
    public String decrypt(String stringToCrypt, int key) {
        StringBuilder stringBuilder = new StringBuilder(stringToCrypt);

        for (int i = 0; i < stringBuilder.length(); i++) {
            stringBuilder.setCharAt(i, (char)(stringBuilder.charAt(i) - key));
        }

        return stringBuilder.toString();
    }
}