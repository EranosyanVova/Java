class Cryptor {
    private static final String ENCRYPT_MODE = "enc";
    private CryptorAlgorithm algorithm;
    private String mode;

    public void setAlgorithm(CryptorAlgorithm algorithm, String mode) {
        this.algorithm = algorithm;
        this.mode = mode;
    }

    public void useAlgorithm(StringBuilder stringBuilder, int key) {
        if (mode.equals(ENCRYPT_MODE) || mode.isEmpty()) {
            algorithm.encrypt(stringBuilder, key);
        }
        else {
            algorithm.decrypt(stringBuilder, key);
        }
    }
}