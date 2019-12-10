package accounter.model;

public class Admin extends Accounter {


    public Admin(String login) {
        super(login);
    }

    public Admin(String login, boolean isActive) {
        super(login, isActive);
    }

}
