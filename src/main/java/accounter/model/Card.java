package accounter.model;

public abstract class Card {
    protected int numberPossibleBorrows;
    public CardType cardType;
    protected String name;

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Card() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberPossibleBorrows() {
        return numberPossibleBorrows;
    }


    public void setNumberPossibleBorrows(int numberPossibleBorrows) {
        this.numberPossibleBorrows = numberPossibleBorrows;
    }

    @Override
    public String toString() {
        return "Card{" +
                "numberPossibleBorrows=" + numberPossibleBorrows +
                '}';
    }
}
