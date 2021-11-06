public class ShiftAlgorithm implements CryptorAlgorithm {
    public static final int FIRST_ENGLISH_LATTER_CODE = 97;
    public static final int LAST_ENGLISH_LATTER_CODE = 122;
    public static final int ENGLISH_ALPHABET_LENGTH = 26;

    public void encrypt(StringBuilder stringBuilder, int key) {
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) >= FIRST_ENGLISH_LATTER_CODE && stringBuilder.charAt(i) <= LAST_ENGLISH_LATTER_CODE) {
                stringBuilder.setCharAt(i, (char)(FIRST_ENGLISH_LATTER_CODE + (stringBuilder.charAt(i) - FIRST_ENGLISH_LATTER_CODE + key) % ENGLISH_ALPHABET_LENGTH));
            }
        }
    }

    public void decrypt(StringBuilder stringBuilder, int key) {
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) >= FIRST_ENGLISH_LATTER_CODE && stringBuilder.charAt(i) <= LAST_ENGLISH_LATTER_CODE) {
                stringBuilder.setCharAt(i, (char)((stringBuilder.charAt(i) - FIRST_ENGLISH_LATTER_CODE - key) % ENGLISH_ALPHABET_LENGTH >= 0 ?
                        FIRST_ENGLISH_LATTER_CODE + (stringBuilder.charAt(i) - FIRST_ENGLISH_LATTER_CODE - key) % ENGLISH_ALPHABET_LENGTH :
                        123 + (stringBuilder.charAt(i) - FIRST_ENGLISH_LATTER_CODE - key) % ENGLISH_ALPHABET_LENGTH));
            }
        }
    }
}