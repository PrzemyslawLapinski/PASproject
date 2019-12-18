package accounter.model;

import com.sun.mail.imap.protocol.ID;

public abstract class Accounter implements Comparable<Accounter> {

    public String login;
    public String password;

    public boolean isActive;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public Accounter(String login, String password, boolean isActive) {
        this.login = login;
        this.password = password;
        this.isActive = isActive;
    }

    public String getResourceType(){


        if( this instanceof ResourceUser)
           return "ResourceUser";
        if( this instanceof ResourceManager)
            return "ResourceMenager";
        if( this instanceof Admin)
            return "Admin";
        return "--";
    }

    @Override
    public int compareTo(Accounter accounter) {
        return  this.login.compareTo(accounter.login);
    }
}
