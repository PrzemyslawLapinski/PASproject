package borrow.model;

import accounter.model.Accounter;
import resource.model.Resource;

import java.util.Set;

public class BorrowRepository {

    Set<Borrow> borrows;


    public BorrowRepository(Set<Borrow> borrows) {

        this.borrows=borrows;
    }

    public Set<Borrow> getAll() {
        return borrows;
    }

    public void create(Borrow borrow) {
        if (borrow.getAccounter().isActive() && borrow.getResource() != null) {
            borrows.add(borrow);
        }
    }

  








}

