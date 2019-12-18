package accounter.model;

public class ResourceManager extends Accounter {
    public ResourceManager(String login) {
        super(login);
    }

    public ResourceManager(String login, boolean isActive) {
        super(login, isActive);
    }

    public ResourceManager(String login, String password, boolean isActive) {
        super(login, password, isActive);
    }
}
