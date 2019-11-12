package model;




public class AudioBook extends Resource {
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    private Integer duration;

    public AudioBook(Integer ID, String tittle, Integer duration) {
        super(ID, tittle);
        this.duration = duration;
    }


}
