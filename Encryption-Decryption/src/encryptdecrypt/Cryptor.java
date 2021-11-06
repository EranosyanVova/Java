class Cryptor {
    private CryptorAlgorithm algorithm;
    private String mode;
    static final String encryptMode = "enc";

    public void setAlgorithm(CryptorAlgorithm algorithm, String mode) {
        this.algorithm = algorithm;
        this.mode = mode;
    }

    public void useAlgorithm(StringBuilder stringBuilder, int key) {
        if (mode.equals(encryptMode) || mode.isEmpty()) {
            algorithm.encrypt(stringBuilder, key);
        }
        else {
            algorithm.decrypt(stringBuilder, key);
        }
    }
}