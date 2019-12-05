package accounter;


import accounter.model.*;
import resource.model.AudioBook;
import resource.model.ResourceType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ConversationScoped
public class AccounterPageBean implements Serializable {

    String login;
    Card card;
    List<Card> cards;
    Boolean isActive;
    AccounterType accounterType;
    Boolean isCreated;

    @Inject
    Conversation conversation;

    @Inject
    private AccounterMenager menager;

    public AccounterPageBean() {
        cards = new ArrayList<>();

        cards.add(new BronzeCard());
        cards.add(new GoldCard());
    }





    public String createResourceUser() {
        conversation.begin();
        isCreated = true;
        accounterType=AccounterType.ResourceUser;
        return "viewAccounter?faces-redirect=true";
    }

    public String createResourceManager() {
        conversation.begin();
        isCreated = true;
        accounterType= AccounterType.ResourceManager;
        return "viewAccounter?faces-redirect=true";
    }

    public String updateAccounter(String login) {
        this.conversation.begin();
        isCreated = false;
        Accounter account = menager.getByLogin(login);
        if (account instanceof ResourceUser) {
            this.setAccounterType(AccounterType.ResourceUser);
            this.setLogin(account.getLogin());
            this.setCard(account.getCard());
            this.setActive(account.isActive());
            return "viewAccounter?faces-redirect=true";
        } else if (account instanceof ResourceManager) {
            this.setAccounterType(AccounterType.ResourceManager);
            this.setLogin(account.getLogin());
            this.setActive(account.isActive());

            return "viewAccounter?faces-redirect=true";
        }
        conversation.end();
        return null;
    }


    public String save(){

        if(accounterType.equals(AccounterType.ResourceUser)){
            if (!isCreated) {
                menager.update(login, new ResourceUser(login, isActive, getCARD(login)));

            } else {
                menager.AddResourceUser(login, card, isActive);
            }


        } else if (accounterType.equals(AccounterType.ResourceManager)){
            if (!isCreated) {
                menager.update(login, new ResourceManager(login, isActive));
            } else {
                menager.AddResourceManager(login, isActive);
            }
        }

        conversation.end();



        return "accounterList?faces-redirect=true";
    }
    public String cancel() {
        conversation.end();
        return "accounterList?faces-redirect=true";
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public AccounterType getAccounterType() {
        return accounterType;
    }

    public void setAccounterType(AccounterType accounterType) {
        this.accounterType = accounterType;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card getCARD(String id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Card c : cards){
            if (id.equals(c.getName())){
                return c;
            }
        }
        return null;
    }

    public Boolean getCreated() {
        return isCreated;
    }

    public void setCreated(Boolean created) {
        isCreated = created;
    }
}
