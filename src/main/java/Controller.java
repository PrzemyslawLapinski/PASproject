import model.AudioBook;
import model.Book;
import model.Resource;
import model.ResourceMenager;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named
@ApplicationScoped
public class Controller implements Serializable {
    @Inject
    private BookBean bookBean;
    @Inject
    private AudioBookBean audioBookBean;

    Set<Resource> resourceList;
    ResourceMenager resourceMenager;

    public Controller() {
        resourceMenager = new ResourceMenager();
        resourceList = resourceMenager.getAll();
    }
//    @PostConstruct
//    void init() {
//        resourceList = resourceMenager.getAll();
//    }

    public Set<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(Set<Resource> resourceList) {
        this.resourceList = resourceList;
    }
    public String save(BookBean bookBean){
        resourceMenager.addBook(bookBean.getTitle(),bookBean.getNumberOfPage());
        return "resource?faces-redirect=true";
    }
    public String save(AudioBookBean audioBookBean){
        resourceMenager.addAudioBook(audioBookBean.getTitle(),audioBookBean.getDuration());
        return "resource?faces-redirect=true";
    }
    public String updateResource(Integer ID){
        Resource resource = resourceMenager.getByID(ID);
        if(resource instanceof Book){
           bookBean.setID(ID);
           bookBean.setTitle(resource.getTitle());
           bookBean.setNumberOfPage(((Book) resource).getNumberOfPage());
            return "createBook?faces-redirect=true";
        } else if (resource instanceof AudioBook){
            audioBookBean.setID(ID);
            audioBookBean.setTitle(resource.getTitle());
            audioBookBean.setDuration(((AudioBook) resource).getDuration());
            return "createAudioBook?faces-redirect=true";
        }
        return null;
    }
    public String deleteResource(Integer ID){
        resourceMenager.deleteByID(ID);
        return "resource?faces-redirect=true";
    }
}
