package resource.model;




import accounter.model.ResourceUser;

import java.util.Set;

public class ResourceRepository {
    Set<Resource> resourceList;

    public ResourceRepository(Set<Resource> resources) {
        resourceList=resources;
    }

    public Set<Resource> getAll() {
        return resourceList;
    }

    public Resource getByID(Integer ID) {
        return findByID(ID);
    }

    public  void update(Integer ID, Resource resource) {
        findByID(ID).setTitle(resource.getTitle());
            if(resource instanceof Book)
                ((Book) findByID(ID)).setNumberOfPage(((Book) resource).getNumberOfPage());
        if(resource instanceof AudioBook)
            ((AudioBook) findByID(ID)).setDuration(((AudioBook) resource).getDuration());
    }

    public void create(Resource resource) {
        resourceList.add(resource);
    }

    public  void deleteByID(Integer ID) {
        resourceList.remove(findByID(ID));
    }

    private Resource findByID(Integer ID) {
        return resourceList.stream().filter(n -> n.getID().equals(ID)).findFirst().orElse(null);
    }


}
