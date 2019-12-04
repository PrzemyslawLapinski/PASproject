package borrow.model;

import accounter.model.Accounter;
import accounter.model.BronzeCard;
import accounter.model.ResourceUser;
import resource.model.Book;
import resource.model.Resource;
import resource.model.ResourceRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@ApplicationScoped
public class BorrowMenager {
    private  BorrowRepository borrowRepository;

    public BorrowMenager() {
        Set<Borrow> borrowList= new TreeSet<>();

        borrowList.add(new Borrow(1, new Date(2019,1,1),
                new Date(2019,2,2), new Book(1, "3", 12),
                new ResourceUser("klient1", true , new BronzeCard())));
        this.borrowRepository=new BorrowRepository(borrowList);
    }
    public Set<Borrow> getAll() {
        return borrowRepository.getAll();
    }

    public Borrow getByID(Integer ID) {
        return borrowRepository.getByID(ID);
    }

    public void update(Integer ID, Borrow borrow) {
        borrowRepository.update(ID,borrow);
    }

    public void addBorrow(Date startDate, Date finishDate, Resource resource, Accounter accounter) {
        borrowRepository.create(new Borrow(findId(),startDate,finishDate,resource,accounter));
    }

    public void deleteByID(Integer ID) {
        borrowRepository.deleteByID(ID);
        // BorrowMenager.deleteResourceReference(ID);
    }

    // znajduje maksymalne
    private Integer findId() {
        return borrowRepository.getAll().stream()
                .map(Borrow::getID)
                .mapToInt(n -> n)
                .max()
                .orElse(0) +1;

    }



}
