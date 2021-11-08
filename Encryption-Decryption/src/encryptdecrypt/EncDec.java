package encryptdecrypt;

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