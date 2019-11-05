package model;




public class AudioBook extends Resource {
    Integer duration;

    public AudioBook(Integer ID, String tittle, Integer duration) {
        super(ID, tittle);
        this.duration = duration;
    }
}
