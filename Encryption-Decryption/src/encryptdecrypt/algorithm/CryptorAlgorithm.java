package encryptdecrypt.algorithm;

public interface CryptorAlgorithm {
    String encrypt(String stringToCrypt, int key);
    String decrypt(String stringToCrypt, int key);
}