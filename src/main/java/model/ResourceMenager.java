package model;



import java.util.ArrayList;
import java.util.List;


public class ResourceMenager {
    private ResourceRepository resourceRepository;

    public ResourceMenager() {
        List<Resource> resourceList= new  ArrayList<Resource>();
        resourceList.add(new Book(1,"Harry poter 1",231));
        resourceList.add(new Book(2,"Harry poter 2",231));
        resourceList.add(new Book(3,"Harry poter 3",231));
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
