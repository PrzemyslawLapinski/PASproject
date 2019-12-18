package accounter.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;


public class ResourceUser extends Accounter {

    public Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


    public ResourceUser(String login) {
        super(login);
    }

    public ResourceUser(String login, Card card) {
        super(login);
        this.card = card;
    }

    public ResourceUser(String login, boolean isActive, Card card) {
        super(login, isActive);
        this.card = card;
    }

    public ResourceUser(String login, String password, boolean isActive, Card card) {
        super(login, password, isActive);
        this.card = card;
    }
}
