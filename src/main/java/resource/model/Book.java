package resource.model;




public class Book extends Resource{
    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    private Integer numberOfPage;

    public Book(int ID, String tittle, Integer numberOfPage) {
        super(ID,tittle);
        this.numberOfPage=numberOfPage;
    }


}
