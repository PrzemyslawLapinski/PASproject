package model;




public abstract class Resource {
    private Integer ID;
    private String title;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public Resource(Integer ID, String tittle) {
        this.ID = ID;
        this.title = tittle;
    }


     public abstract Integer getAtrybut();
}
