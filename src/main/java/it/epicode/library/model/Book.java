package it.epicode.library.model;

public class Book extends ElemCatalogo{
    private String author;
    private String genre;

    public Book(String isbn, String title, int releaseYear, int pagesNumb, String author, String genre) {
        super(isbn, title, releaseYear, pagesNumb);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString(){
        return super.toString() + ", Autor: " + author + " Genre: " + genre;
    }
}
