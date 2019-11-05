import model.Book;
import model.Resource;
import model.ResourceMenager;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class Controller implements Serializable {
   // @Inject
 // private BookBean bookBean;

    List<Resource> resourceList;
    ResourceMenager resourceMenager;

    public Controller() {
        resourceMenager = new ResourceMenager();
        resourceList = resourceMenager.getAll();
    }


    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
    public String save(BookBean bookBean){
        resourceList.add(new Book(bookBean.getID(),bookBean.getTitle(),bookBean.getNumberOfPage()));
        return "index";
    }
}
