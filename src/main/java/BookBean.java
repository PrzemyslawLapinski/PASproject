import model.Book;
import model.Resource;
import model.ResourceMenager;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BookBean implements Serializable {
    private Integer ID;
    private String title;
    Integer numberOfPage;



    List<Resource> resourceList;

    public BookBean() {
        ResourceMenager resourceMenager = new ResourceMenager();
        resourceList = resourceMenager.getAll();
    }

    public String save(){
        resourceList.add(new Book(ID,title,numberOfPage));
        return "index";
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
