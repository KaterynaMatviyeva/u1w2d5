package it.epicode.library.model;

public abstract class ElemCatalogo {
    private String isbn;
    private String title;
    private int releaseYear;
    private int pagesNumb;

    public ElemCatalogo(String isbn, String title, int releaseYear, int pagesNumb) {
        this.isbn = isbn;
        this.title = title;
        this.releaseYear = releaseYear;
        this.pagesNumb = pagesNumb;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getPagesNumb() {
        return pagesNumb;
    }

    @Override
    public String toString(){
        return "ISBN: " + isbn + " title: " + title + " year of release: " + releaseYear + "number of pages: " + pagesNumb;
    }
}
