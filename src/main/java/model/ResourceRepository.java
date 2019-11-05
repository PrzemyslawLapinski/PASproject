package model;




import java.util.ArrayList;
import java.util.List;

public class ResourceRepository {
    List<Resource> resourceList;

    public ResourceRepository(List<Resource> resources) {
        resourceList=resources;
    }

    public List<Resource> getAll() {
        return resourceList;
    }

    public Resource getByID(Integer ID) {
        return findByID(ID);
    }

    public void update(Integer ID, Resource resource) {
        findByID(ID).setTitle(resource.getTitle());
    }

    public void create(Resource resource) {
        resourceList.add(resource);
    }

    public void deleteByID(Integer ID) {
        resourceList.remove(findByID(ID));
    }

    private Resource findByID(Integer ID) {
        return resourceList.stream().filter(n -> n.getID().equals(ID)).findFirst().orElse(null);
    }


}
