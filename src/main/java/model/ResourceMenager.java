package model;



import java.util.ArrayList;
import java.util.List;


public class ResourceMenager {
    private ResourceRepository resourceRepository;

    public ResourceMenager() {
        List<Resource> resourceList= new  ArrayList<Resource>();
        resourceList.add(new Book(1,"Harry poter i kamień filozoficzny",121));
        resourceList.add(new Book(2,"Harry poter i komnata tajemnic",231));
        resourceList.add(new Book(3,"Harry poter i wiezien Askabanu",301));
        this.resourceRepository = new ResourceRepository(resourceList);
    }

    public List<Resource> getAll() {
        return resourceRepository.getAll();
    }

    public Resource getByID(Integer ID) {
        return resourceRepository.getByID(ID);
    }

    public void update(Integer ID, Resource resource) {
        resourceRepository.update(ID,resource);
    }

    public void create(Resource resource) {
        resourceRepository.create(resource);
    }

    public void deleteByID(Integer ID) {
        resourceRepository.deleteByID(ID);
       // BorrowMenager.deleteResourceReference(ID);
    }


}
