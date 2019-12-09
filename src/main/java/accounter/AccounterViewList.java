package accounter;

import accounter.model.Accounter;
import accounter.model.AccounterMenager;
import accounter.model.ResourceUser;
import borrow.model.Borrow;
import borrow.model.BorrowMenager;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Named
@ViewScoped
public class AccounterViewList implements Serializable {
    @Inject
    private AccounterMenager accounterMenager;



    Set<Accounter> accounterList;


    @PostConstruct
    public void init(){
        accounterList = new HashSet<>(accounterMenager.getAll());
    }

    public Set<Accounter> getAccounterList() {
        return accounterList;
    }

    public void setAccounterList(Set<Accounter> accounterList) {
        this.accounterList = accounterList;
    }


}
