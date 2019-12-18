package security;


import accounter.model.AccounterMenager;
import accounter.model.BronzeCard;
import accounter.model.Card;
import accounter.model.GoldCard;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Named
@RequestScoped
public class Registration {

    @Inject
    AccounterMenager menager;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;

    private String ImageURL = "/.../resources/refresh.png";

    private String name;

    private String password;

    private String confirmPassword;

    private String captchaText;

    private Card card;
     private List<Card> cards;

//    public Registration() {
//        cards = new ArrayList<>();
//
//        cards.add(new BronzeCard());
//        cards.add(new GoldCard());
//    }

    @PostConstruct
    public void init(){
        cards = new ArrayList<>();

        cards.add(new BronzeCard());
        cards.add(new GoldCard());
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
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

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public void createNewUser() throws Exception {

        if (getPassword().compareTo(getConfirmPassword()) != 0) {
            setCaptchaText("");
            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            "messages",
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                    "Password and Confirm Password does not match ",
                                    ""));

        } else {
            menager.addResourceUser(name, password, card, false);
            externalContext.redirect(externalContext.getRequestContextPath() + "/app/commonXhtml/index.xhtml");
        }
    }


}
