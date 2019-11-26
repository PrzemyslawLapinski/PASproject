package resource.model;



import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class ResourceMenager {
    private ResourceRepository resourceRepository;

    public ResourceMenager() {
        Set<Resource> resourceList= new TreeSet<>();


        resourceList.add(new Book(5,"Harry poter i wiezien Askabanu",301));
        resourceList.add(new Book(1,"Harry poter i kamień filozoficzny",121));
        resourceList.add(new Book(2,"Harry poter i kamień filozoficzny 2",221));
        resourceList.add(new Book(6,"Harry poter i komnata tajemnic",231));
        resourceList.add(new Book(4,"Harry poter i komnata tajemnic 2 ",331));

        this.resourceRepository = new ResourceRepository(resourceList);
    }

    public Set<Resource> getAll() {
        return resourceRepository.getAll();
    }

    public Resource getByID(Integer ID) {
        return resourceRepository.getByID(ID);
    }

    public void update(Integer ID, Resource resource) {
        resourceRepository.update(ID,resource);
    }

    public void deleteByID(Integer ID) {
        resourceRepository.deleteByID(ID);
       // BorrowMenager.deleteResourceReference(ID);
    }


    public void addBook( String title, Integer numberOfPage) {

        resourceRepository.create(new Book(findId(),title,numberOfPage));
    }

    public void addAudioBook(String title, Integer duration) {
        resourceRepository.create(new AudioBook(findId(),title,duration));
    }
    // znajduje pierwsze wolne id
    private Integer findId() {
        Set<Integer> ids = resourceRepository.getAll().stream()
                .map(Resource::getID)
                .collect(Collectors.toSet());
        return IntStream.iterate(1, n -> n + 1)
                .filter(n -> ! ids.contains(n))
                .findFirst().getAsInt();
    }
}
