package borrow;

import accounter.model.Accounter;
import accounter.model.AccounterMenager;
import borrow.model.BorrowMenager;
import resource.model.Resource;
import resource.model.ResourceMenager;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Named
@ConversationScoped
public class BorrowPageBean implements Serializable {
    private Date startDate;
    private Date finishDate;
    private Resource resource;
    private Accounter accounter;

    @Inject
    Conversation conversation;
    @Inject
    BorrowMenager borrowMenager;
    @Inject
    ResourceMenager resourceMenager;
    @Inject
    AccounterMenager accounterMenager;

    public String createBorrow(){
        conversation.begin();
        startDate =new Date();
        return "viewBorrowResource?faces-redirect=true";
    }
    public Set<Resource> resourceList(){
        return resourceMenager.getAll();
    }

    public String selectResource(Integer resourceID){
        resource = resourceMenager.getByID(resourceID);
        return "viewBorrowClient?faces-redirect=true";
    }
    public Set<Accounter> accounterList(){
        return accounterMenager.getAll();
    }
    public String selectAccounter(String login) throws Exception {
        accounter = accounterMenager.getByLogin(login);
        startDate = startDate!=null ? startDate : new Date();
        finishDate = null;

        borrowMenager.addBorrow(startDate,finishDate,resource,accounter);
        conversation.end();
        return "borrowList?faces-redirect=true";
    }


    public String cancel() {
        conversation.end();
        return "borrowList?faces-redirect=true";
    }
    public Date minDate(){
        return startDate;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Accounter getAccounter() {
        return accounter;
    }

    public void setAccounter(Accounter accounter) {
        this.accounter = accounter;
    }
}
