package resource;

import borrow.model.BorrowMenager;
import resource.model.Resource;
import resource.model.ResourceMenager;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Named
@ViewScoped
public class ResourceViewList implements Serializable {

    @Inject
    ResourceMenager resourceMenager;
    @Inject
    BorrowMenager borrowMenager;

    Set<Resource> resourceList;

    @PostConstruct
    public void init(){
        resourceList = new TreeSet<>(resourceMenager.getAll());
    }

    public Set<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(Set<Resource> resourceList) {
        this.resourceList = resourceList;
    }


    public String deleteResource(Integer ID) throws Exception {
        Resource resourceToRemove = resourceList.stream().filter(e -> e.getID().equals(ID)).findFirst().get();
        borrowMenager.deleteResourceReference(resourceToRemove);
        resourceList.remove(resourceToRemove);

        try {
            resourceMenager.deleteByID(ID);
        } catch (NullPointerException e) {
           throw new Exception("Nie ma podanego resource");
        }

        return "resourceList?faces-redirect=true";
    }





}
