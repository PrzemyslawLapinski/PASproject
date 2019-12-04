package borrow.model;

import accounter.model.Accounter;
import resource.model.Resource;

import javax.xml.crypto.Data;
import java.util.Date;

public class Borrow implements Comparable<Borrow> {
    private Integer ID;
    private Date startDate;
    private Date finishDate;
    private Resource resource;
    private Accounter accounter;

    public Borrow(int id,Date startDate, Date finishDate, Resource resource, Accounter accounter) {
        this.ID=id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.resource = resource;
        this.accounter = accounter;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    @Override
    public int compareTo(Borrow borrow) {
        return this.ID.compareTo(borrow.ID);
    }
}
