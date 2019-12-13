package accounter.model;

import resource.model.ResourceRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class AccounterMenager {
    private AccounterRepository accounterRepository;

    public AccounterMenager() {
        Set<Accounter> resourceList = new TreeSet<>();


        resourceList.add(new ResourceUser("klient", true , new BronzeCard()) {
        });
        resourceList.add(new Admin("admin", true) {
        });
        resourceList.add(new ResourceManager("menager", true) {
        });




        this.accounterRepository = new AccounterRepository(resourceList);
    }


    public Set<Accounter> getAll() {
        return accounterRepository.getAll();
    }


    public Accounter getByLogin(String login) {
        return accounterRepository.getByLogin(login);
    }

    public void addResourceUser(String login, Card card, boolean active) {
        accounterRepository.create(new ResourceUser(login,active, card));
    }


   public void addResourceManager(String login, boolean active) {
        accounterRepository.create(new ResourceManager(login,active));
   }

    public void addAdmin(String login, boolean active) {
        accounterRepository.create(new Admin(login,active));
    }


//   public void updateResourceUser(String login, Accounter accounter) {
////        accounterRepository.updateActivity(login, accounter);
////        accounterRepository.updateCard(login, accounter);
////   }
////
////   public void updateResourceManager(String login, Accounter accounter) {
//////       accounterRepository.updateActivity(login, accounter);
////   }

    public void update(String login, Accounter accounter) {
        accounterRepository.update(login, accounter);

   }


}
