package encryptdecrypt.algorithm;

public class ShiftAlgorithm implements CryptorAlgorithm {
    private static final int ENGLISH_ALPHABET_LENGTH = 26;
    private static final int FIRST_ENGLISH_LATTER_CODE = 97;
    private static final int LAST_ENGLISH_LATTER_CODE = 122;

    @Override
    public String encrypt(String stringToCrypt, int key) {
        StringBuilder stringBuilder = new StringBuilder(stringToCrypt);

        for (int i = 0; i < stringBuilder.length(); i++) {
            char currentLetter = stringBuilder.charAt(i);
            if (isEnglishLetter(currentLetter)) {
                stringBuilder.setCharAt(i, encryptLetter(currentLetter, key));
            }
        }
        return stringBuilder.toString();
    }

    private char encryptLetter(char latter, int key) {
        return (char)(FIRST_ENGLISH_LATTER_CODE + (latter - FIRST_ENGLISH_LATTER_CODE + key) % ENGLISH_ALPHABET_LENGTH);
    }

    @Override
    public String decrypt(String stringToCrypt, int key) {
        StringBuilder stringBuilder = new StringBuilder(stringToCrypt);

        for (int i = 0; i < stringBuilder.length(); i++) {
            char currentLatter = stringBuilder.charAt(i);
            if (isEnglishLetter(currentLatter)) {
                stringBuilder.setCharAt(i, decryptLetter(currentLatter, key));
            }
        }
        return stringBuilder.toString();
    }

    private char decryptLetter(char latter, int key) {
        if ((latter - FIRST_ENGLISH_LATTER_CODE - key) % ENGLISH_ALPHABET_LENGTH >= 0) {
            return (char) (FIRST_ENGLISH_LATTER_CODE + (latter - FIRST_ENGLISH_LATTER_CODE - key) % ENGLISH_ALPHABET_LENGTH);
        }
        else {
            return (char) (LAST_ENGLISH_LATTER_CODE + 1 + (latter - FIRST_ENGLISH_LATTER_CODE - key) % ENGLISH_ALPHABET_LENGTH);
        }
    }

    public boolean isEnglishLetter(char letter) {
        return letter >= FIRST_ENGLISH_LATTER_CODE && letter <= LAST_ENGLISH_LATTER_CODE;
    }
}