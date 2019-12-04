package borrow.model;

import accounter.model.Accounter;
import accounter.model.ResourceUser;
import resource.model.Book;
import resource.model.Resource;
import resource.model.ResourceRepository;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class BorrowMenager {


    private  BorrowRepository borrowRepository;

    public BorrowMenager() {

        Set<Borrow> borrowList= new TreeSet<>();

        //borrowList.add(new Borrow(new Date(2019,1,1)), new Date(2019,2,2), new Book(1, "3", 12), new ResourceUser())
    }
}
