public interface CryptorAlgorithm {
    void encrypt(StringBuilder stringBuilder, int key);
    void decrypt(StringBuilder stringBuilder, int key);
}