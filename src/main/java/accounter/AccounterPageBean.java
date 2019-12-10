package accounter;


import accounter.model.*;
import borrow.model.Borrow;
import borrow.model.BorrowMenager;
import resource.model.AudioBook;
import resource.model.ResourceType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@ConversationScoped
public class AccounterPageBean implements Serializable {

    String login;
    Card card;
    List<Card> cards;
    Boolean isActive;
    AccounterType accounterType;
    Boolean isCreated;
    Set<Borrow> accounterBorrowList;
    Boolean disabled;



    @Inject
    private BorrowMenager borrowmenager;

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

    public String createAdmin() {
        conversation.begin();
        isCreated = true;
        accounterType= AccounterType.Admin;
        return "viewAccounter?faces-redirect=true";
    }

    public String updateAccounter(String login) {
        this.conversation.begin();
        isCreated = false;
        Accounter account = menager.getByLogin(login);
        if (account instanceof ResourceUser) {
            this.setAccounterType(AccounterType.ResourceUser);
            this.setLogin(account.getLogin());
            this.setCard(((ResourceUser)account).getCard());
            this.setActive(account.isActive());
            return "viewAccounter?faces-redirect=true";
        } else if (account instanceof ResourceManager) {
            this.setAccounterType(AccounterType.ResourceManager);
            this.setLogin(account.getLogin());
            this.setActive(account.isActive());

            return "viewAccounter?faces-redirect=true";
        } else if (account instanceof Admin) {
            this.setAccounterType(AccounterType.Admin);
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
                menager.addResourceUser(login, card, isActive);
            }


        } else if (accounterType.equals(AccounterType.ResourceManager)){
            if (!isCreated) {
                menager.update(login, new ResourceManager(login, isActive));
            } else {
                menager.addResourceManager(login, isActive);
            }
        } else if(accounterType.equals(AccounterType.Admin)) {
            if (!isCreated) {
                menager.update(login, new Admin(login, isActive));
            } else {
                menager.addAdmin(login, isActive);
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

    public String showDetails(String login) {
        conversation.begin();

        Accounter accounter = menager.getByLogin(login);
        accounterBorrowList = new HashSet<>(borrowmenager.listAccounterBorrows(login));
        this.setCard(((ResourceUser)accounter).getCard());

        return "accounterDetails?faces-redirect=true";
    }

    public Set<Borrow> getAccounterBorrowList() {
        return accounterBorrowList;
    }

    public void setAccounterBorrowList(Set<Borrow> accounterBorrowList) {
        this.accounterBorrowList = accounterBorrowList;
    }

    public void checkifDisabled() {
//        Accounter account = menager.getByLogin(login);
//
//        if (account instanceof ResourceUser) {
//            this.setDisabled(false);
//        } else {
//            this.setDisabled(true);
//        }
        if(accounterType.equals(AccounterType.ResourceUser)){
            disabled = false;
        } else {
            disabled = true;
        }
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
