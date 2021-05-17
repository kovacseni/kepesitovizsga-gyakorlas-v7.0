package hu.nive.ujratervezes.kepesitovizsga.finelongwordonceagain;

public class FineLongWordOnceAgain {

    public char[][] getFineLongWordOnceAgainButNowInAReverseWay(String longWord, int horizontal) {
        validateHorizontal(longWord, horizontal);
        int vertical = longWord.length() + 1 - horizontal;
        longWord = longWord.toUpperCase();
        return getFineLongWordInAnArrayOfArrays(longWord, horizontal, vertical);
    }

    private void validateHorizontal(String longWord, int horizontal) {
        if (horizontal > longWord.length()) {
            throw new IllegalArgumentException("Number of letters cannot be more than length of the word!");
        }
    }

    private char[][] getFineLongWordInAnArrayOfArrays(String longWord, int horizontal, int vertical) {
        char[][] fineLongWordInAnArrayOfArrays = new char[vertical][horizontal];
        char[] charsOfLongWord = longWord.toCharArray();
        int count = 0;
        int letter = 0;
        while (count < vertical) {
            for (int i = 0; i < horizontal; i++) {
                fineLongWordInAnArrayOfArrays[count][i] = charsOfLongWord[letter];
                letter++;
            }
            letter = letter - horizontal + 1;
            count++;
        }
        return fineLongWordInAnArrayOfArrays;
    }
}
