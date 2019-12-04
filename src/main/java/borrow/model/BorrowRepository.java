package borrow.model;

import accounter.model.Accounter;
import resource.model.Resource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    public Borrow getByID(Integer ID) {
        return findByID(ID);
    }

    public void deleteByID(Integer ID) {
        borrows.remove(findByID(ID));
    }

    public void update(Integer id, Borrow resource) {
        Borrow borrow = findByID(id);
        borrow.setStartDate(resource.getStartDate());
        borrow.setFinishDate(resource.getFinishDate());
        borrow.setAccounter(resource.getAccounter());
        borrow.setResource(resource.getResource());

    }

    private Borrow findByID(Integer ID) {
        return borrows.stream().filter(n -> n.getID().equals(ID)).findFirst().orElse(null);
    }


}

