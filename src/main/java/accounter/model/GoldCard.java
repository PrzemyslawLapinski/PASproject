package accounter.model;

public class GoldCard extends Card {


    public GoldCard() {
        this.numberPossibleBorrows = 2;
        this.name = "GoldCard";

    }

    @Override
    public int getNumberPossibleBorrows() {
        return super.getNumberPossibleBorrows();
    }

    @Override
    public void setNumberPossibleBorrows(int numberPossibleBorrows) {
        super.setNumberPossibleBorrows(numberPossibleBorrows);
    }

    @Override
    public String toString() {
        return "Gold";
    }
}
