package accounter.model;

public class BronzeCard extends Card {



    public BronzeCard() {
        this.numberPossibleBorrows = 1;
        this.name = "BronzeCard";
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
        return "Bronze";
    }
}
