package it.epicode.library.service;

import it.epicode.library.exception.ISBNNotFoundException;
import it.epicode.library.model.Book;
import it.epicode.library.model.ElemCatalogo;
import it.epicode.library.model.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Archive {
    private List<ElemCatalogo> catalog = new ArrayList<>();

    public void addElement(ElemCatalogo element){
        if(catalog.stream().anyMatch(e -> e.getIsbn().equals(element.getIsbn()))){
            throw new IllegalArgumentException("Element with ISBN already exist ");
        }
        catalog.add(element);
    }

    public void removeElement(String isbn) throws ISBNNotFoundException {
        if(!catalog.removeIf(e -> e.getIsbn().equals(isbn))){
            throw new ISBNNotFoundException("Element with ISBN " + isbn + " not found");
        }
    }

    public ElemCatalogo searchByIsbn(String isbn){
        return catalog.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ISBNNotFoundException("Element with ISBN " + isbn + " not found"));
    }

    public List<ElemCatalogo> searchByYear(int year){
        return catalog.stream()
                .filter(e-> e.getReleaseYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author){
        return catalog.stream()
                .filter(e -> e instanceof Book && ((Book) e).getAuthor().equalsIgnoreCase(author))
                .map(e-> (Book) e)
                .collect(Collectors.toList());
    }

    public void uploadElement(String isbn, ElemCatalogo newElem){
        removeElement(isbn);
        addElement(newElem);
    }

    public void statistics(){
        long booksNumber = catalog.stream().filter(e->e instanceof Book).count();
        long magazineNumber = catalog.stream().filter(e -> e instanceof Magazine).count();
        int totalPages = catalog.stream().mapToInt(ElemCatalogo::getPagesNumb).sum();
        double averagePages = catalog.stream().mapToInt(ElemCatalogo::getPagesNumb).average().orElse(0);

        System.out.println("Number of books " + booksNumber);
        System.out.println("Number of magazines " + magazineNumber);
        System.out.println("Total of pages " + totalPages);
        System.out.println("Average of pages " + averagePages);
    }
}
