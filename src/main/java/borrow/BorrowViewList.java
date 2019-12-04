package borrow;


import accounter.model.AccounterMenager;
import borrow.model.Borrow;
import borrow.model.BorrowMenager;
import resource.model.Resource;
import resource.model.ResourceMenager;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Named
@ViewScoped
public class BorrowViewList implements Serializable {

    @Inject
    BorrowMenager borrowMenager;


    Set<Borrow> borrowSet;

    @PostConstruct
    public void init(){
        borrowSet = new TreeSet<>(borrowMenager.getAll());
    }

    public Set<Borrow> getBorrowSet() {
        return borrowSet;
    }


}
