package hu.nive.ujratervezes.kepesitovizsga.fractionreduction;

public class FractionReduction {

    public int getNumberOfNotReductiveFractions() {
        int sum = 0;
        for (int i = 1; i < 144; i++) {
            sum += isReductive(i);
        }
        return sum;
    }

    private int isReductive(int i) {
        for (int j = 2; j < 72; j++) {
            if (i % j == 0 && 144 % j == 0) {
                return 0;
            }
        }
        return 1;
    }
}
