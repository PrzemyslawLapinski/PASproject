package model;




public class Book extends Resource{
    Integer numberOfPage;

    public Book(int ID, String tittle, Integer numberOfPage) {
        super(ID,tittle);
        this.numberOfPage=numberOfPage;
    }


}
