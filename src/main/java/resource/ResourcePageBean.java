package resource;

import resource.model.*;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class ResourcePageBean implements Serializable{

    private ResourceType resourceType;
    private Integer ID;
    private String title;
    private Integer numberOfPage;
    private Integer duration;

    @Inject
    Conversation conversation;
    @Inject
    ResourceMenager resourceMenager;


    public String createBook(){
        conversation.begin();
        resourceType=ResourceType.Book;
        return "viewResource?faces-redirect=true";
    }
    public String createAudioBook(){
        conversation.begin();
        resourceType=(ResourceType.AudioBook);
        return "viewResource?faces-redirect=true";
    }

    public String updateResource(Integer ID) {
        this.conversation.begin();
        Resource resource = resourceMenager.getByID(ID);
        if (resource instanceof Book) {
            this.setResourceType(ResourceType.Book);
            this.setID(resource.getID());
            this.setTitle(resource.getTitle());
            this.setNumberOfPage(((Book) resource).getNumberOfPage());
            return "viewResource?faces-redirect=true";
        } else if (resource instanceof AudioBook) {
            this.setResourceType(ResourceType.AudioBook);
            this.setID(ID);
            this.setTitle(resource.getTitle());
            this.setDuration(((AudioBook) resource).getDuration());
            return "viewResource?faces-redirect=true";
        }
        conversation.end();
        return null;
    }

    public String save() throws Exception {
        if(resourceType.equals(ResourceType.AudioBook)){
            if (ID != null) {
                resourceMenager.update(ID, new AudioBook(ID, title, duration));
            } else {
                resourceMenager.addAudioBook(title, duration);
            }

        } else {
            if (ID != null) {
                resourceMenager.update(ID, new Book(ID, title, duration));
            } else {
                resourceMenager.addBook(title,numberOfPage);
            }
        }
        conversation.end();
        return "resourceList?faces-redirect=true";
    }
    public String cancel() {
        conversation.end();
        return "resourceList?faces-redirect=true";
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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


}
