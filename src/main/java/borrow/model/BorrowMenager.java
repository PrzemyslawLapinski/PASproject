package borrow.model;

import accounter.model.Accounter;
import accounter.model.BronzeCard;
import accounter.model.ResourceUser;
import resource.model.Book;
import resource.model.Resource;
import resource.model.ResourceMenager;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@ApplicationScoped
public class BorrowMenager {

    @Inject
    ResourceMenager resourceMenager;

    private  BorrowRepository borrowRepository;

    public BorrowMenager() {
        Set<Borrow> borrowList= new TreeSet<>();

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

    public void addBorrow(Date startDate, Date finishDate, Resource resource, Accounter accounter) throws Exception {
        if(!(accounter instanceof ResourceUser))
            throw new Exception("Only Client can borrow books/audiobooks");
        if(accounter.isActive==false)
            throw new Exception("Client is no active");
        if(!resourceExist(resource)){
            throw new Exception("Resouce was remove");
        }
        if(isResourceAvailable(resource))
            throw new Exception("Resource with id: "+ resource.getID() + " tittle: " + resource.getTitle() + " actually is allocate");
        borrowRepository.create(new Borrow(findId(),startDate,finishDate,resource,accounter));
    }

    private boolean resourceExist(Resource resource) {
        return resourceMenager.getAll().contains(resource);
    }

    private boolean isResourceAvailable(Resource resource){
        return borrowRepository.getAll().stream().anyMatch(n -> n.getResource().getID().equals(resource.getID()) && n.getFinishDate()==null);
    }
    public void deleteResourceReference(Resource resource){
         borrowRepository.getAll().stream().filter(n -> n.getResource().getID().equals(resource.getID()))
                .forEach(n -> n.setResource(null));
    }

    public void deleteByID(Integer ID) {
        borrowRepository.deleteByID(ID);

    }

    // znajduje maksymalne i dodaje +1
    private Integer findId() {
        return borrowRepository.getAll().stream()
                .map(Borrow::getID)
                .mapToInt(n -> n)
                .max()
                .orElse(0) +1;

    }



}
