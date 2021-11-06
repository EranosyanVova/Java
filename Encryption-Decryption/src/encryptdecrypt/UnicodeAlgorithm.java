package encryptdecrypt;

public class UnicodeAlgorithm implements CryptorAlgorithm {

    public void encrypt(StringBuilder stringBuilder, int key) {
        for (int i = 0; i < stringBuilder.length(); i++) {
            stringBuilder.setCharAt(i, (char)(stringBuilder.charAt(i) + key));
        }
    }

    public void decrypt(StringBuilder stringBuilder, int key) {
        for (int i = 0; i < stringBuilder.length(); i++) {
            stringBuilder.setCharAt(i, (char)(stringBuilder.charAt(i) - key));
        }
    }
}