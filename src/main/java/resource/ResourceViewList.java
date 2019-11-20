package resource;

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


    public String deleteResource(Integer ID) {
        Resource resourceToRemove = resourceList.stream().filter(e -> e.getID().equals(ID)).findFirst().get();
        resourceList.remove(resourceToRemove);
        try {
            resourceMenager.deleteByID(ID);
        } catch (NullPointerException e) {
           System.out.println("nie ma");
        }

        return "resourceList?faces-redirect=true";
    }





}
