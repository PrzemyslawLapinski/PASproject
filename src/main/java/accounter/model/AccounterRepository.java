package accounter.model;

import com.sun.mail.imap.protocol.ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccounterRepository {

    Set<Accounter> accounters;

    public AccounterRepository(Set<Accounter> accounters) {
        this.accounters=accounters;
    }

    public Set<Accounter> getAll() {
        return accounters;
    }

    public void create(Accounter accounter) {
        accounters.add(accounter);
    }

    private Accounter findByLogin(String login) {
        return accounters.stream().filter(n -> n.getLogin().equals(login)).findFirst().orElse(null);

    }


    public Accounter getByLogin(String login) {
        return findByLogin(login);
    }

//
//    public void updateCard(String login, Accounter accounter) {
//
//        if(accounter instanceof ResourceUser) {
//
//
//            ((ResourceUser)accounter).setCard(((ResourceUser) accounter).getCard());
//        }
//    }
//
//
//    public void updateActivity(String login, Accounter accounter) {
//        findByLogin(login).setActive(accounter.isActive());
//
//    }

    public void update(String login, Accounter accounter) {
        findByLogin(login).setActive(accounter.isActive());
        if(accounter instanceof ResourceUser) {


            ((ResourceUser)accounter).setCard(((ResourceUser) accounter).getCard());
        }
    }



    public boolean activeAccounter(String login) {
        Accounter accounter = findByLogin(login);

        if(accounter instanceof ResourceUser) {


            if(((ResourceUser)accounter).getCard().getNumberPossibleBorrows() == 0 ) {
                return false;
            }
        }
        return true;
    }





}
