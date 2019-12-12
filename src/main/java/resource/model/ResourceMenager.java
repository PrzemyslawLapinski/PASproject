package resource.model;



import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class ResourceMenager {
    private ResourceRepository resourceRepository;


    public ResourceMenager() {
        Set<Resource> resourceList= new TreeSet<>();


        resourceList.add(new Book(5,"Harry potter i wiezien Askabanu",301));
        resourceList.add(new Book(1,"Harry potter i kamień filozoficzny",121));
        resourceList.add(new Book(2,"Potop",221));
        resourceList.add(new Book(6,"Zbrodnia I Kara",231));
        resourceList.add(new Book(4,"Achaja",331));

        this.resourceRepository = new ResourceRepository(resourceList);
    }

    public Set<Resource> getAll() {
        return resourceRepository.getAll();
    }

    public Resource getByID(Integer ID) {
        return resourceRepository.getByID(ID);
    }

    public void update(Integer ID, Resource resource) throws Exception {
        if(!resourceRepository.getAll().stream().anyMatch(n -> n.getID().equals(ID))){
            throw new Exception("Resource was remove");
        }
        resourceRepository.update(ID,resource);
    }

    public void create(Resource resource) {
        resourceRepository.create(resource);
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

    // znajduje maksymalne
    private Integer findId() {
        return resourceRepository.getAll().stream()
                .map(Resource::getID)
                .mapToInt(n -> n)
                .max()
                .orElse(0) +1;

    }


//    // znajduje pierwsze wolne id
//    private Integer findId() {
//        Set<Integer> ids = resourceRepository.getAll().stream()
//                .map(Resource::getID)
//                .collect(Collectors.toSet());
//        return IntStream.iterate(1, n -> n + 1)
//                .filter(n -> ! ids.contains(n))
//                .findFirst().getAsInt();
//    }


}
