package it.epicode.library.model;

public class Magazine extends ElemCatalogo{

    private Periodicity periodicity;

    public Magazine(String isbn, String title, int releaseYear, int pagesNumb, Periodicity periodicity) {
        super(isbn, title, releaseYear, pagesNumb);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }
    @Override
    public String toString(){
        return super.toString() + ", Periodicity " + periodicity;
    }
}
