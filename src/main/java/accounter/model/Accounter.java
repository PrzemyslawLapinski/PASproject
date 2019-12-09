package accounter.model;

import com.sun.mail.imap.protocol.ID;

public abstract class Accounter implements Comparable<Accounter> {

    public String login;

    public boolean isActive;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Accounter(String login) {
        this.login = login;
    }

    public Accounter(String login, boolean isActive) {
        this.login = login;
        this.isActive = isActive;
    }

    @Override
    public int compareTo(Accounter accounter) {
        return  this.login.compareTo(accounter.login);
    }
}
