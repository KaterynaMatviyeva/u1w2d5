package it.epicode.library;

import it.epicode.library.model.Book;
import it.epicode.library.model.ElemCatalogo;
import it.epicode.library.model.Magazine;
import it.epicode.library.model.Periodicity;
import it.epicode.library.service.Archive;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Archive archive = new Archive();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Catalog Library ---");
            System.out.println("1. Add an element");
            System.out.println("2. Remove an element");
            System.out.println("3. Search by ISBN");
            System.out.println("4. Search by year of release");
            System.out.println("5. Search book by author");
            System.out.println("6. Upload an element");
            System.out.println("7. View catalog statistics");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int Choose = scanner.nextInt();
            scanner.nextLine();

            switch (Choose) {
                case 1 -> addElement(scanner, archive);
                case 2 -> removeElement(scanner, archive);
                case 3 -> searchByISBN(scanner, archive);
                case 4 -> searchByYear(scanner, archive);
                case 5 -> searchByAuthor(scanner, archive);
                case 6 -> uploadElement(scanner, archive);
                case 7 -> archive.statistics();
                case 8 -> {
                    System.out.println("Exit from menu.");
                    exit = true;
                }
                default -> System.out.println("Doesn't valid option, try again, please.");
            }
        }

        scanner.close();
    }


    private static void addElement(Scanner scanner, Archive archive) {
        System.out.println("\nDo you want to add:");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.print("Choose an option: ");
        int choose = scanner.nextInt();
        scanner.nextLine();

        switch (choose) {
            case 1 -> {
                System.out.print("Insert ISBN: ");
                String isbn = scanner.nextLine();
                System.out.print("Insert title: ");
                String title = scanner.nextLine();
                System.out.print("Insert release year: ");
                int year = scanner.nextInt();
                System.out.print("Insert number of pages: ");
                int pages = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Insert author: ");
                String author = scanner.nextLine();
                System.out.print("Insert genre: ");
                String genre = scanner.nextLine();

                Book book = new Book(isbn, title, year, pages, author, genre);
                archive.addElement(book);
                System.out.println("Book added successfully!");
            }
            case 2 -> {
                System.out.print("Insert ISBN: ");
                String isbn = scanner.nextLine();
                System.out.print("Insert title: ");
                String title = scanner.nextLine();
                System.out.print("Insert release year: ");
                int year = scanner.nextInt();
                System.out.print("Insert number of pages: ");
                int pages = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Choose periodicity (1. WEEKLY, 2. MONTHLY, 3. SEMIANNUALLY): ");
                int choosenPeridiocity = scanner.nextInt();
                scanner.nextLine();
                Periodicity periodicity = switch (choosenPeridiocity) {
                    case 1 -> Periodicity.WEEKLY;
                    case 2 -> Periodicity.MONTHLY;
                    case 3 -> Periodicity.SEMIANNUALLY;
                    default -> throw new IllegalArgumentException("Periodicity not valid.");
                };

                Magazine magazine = new Magazine(isbn, title, year, pages, periodicity);
                archive.addElement(magazine);
                System.out.println("Magazine added successfully!");
            }
            default -> System.out.println("Option not valid, back to the menu.");
        }
    }


    private static void removeElement(Scanner scanner, Archive archive) {
        System.out.print("\nInsert ISBN of element to remove: ");
        String isbn = scanner.nextLine();
        try {
            archive.removeElement(isbn);
            System.out.println("Element removed successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void searchByISBN(Scanner scanner, Archive archive) {
        System.out.print("\nInsert ISBN to search: ");
        String isbn = scanner.nextLine();
        try {
            ElemCatalogo element = archive.searchByIsbn(isbn);
            System.out.println("Element found: " + element);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void searchByYear(Scanner scanner, Archive archive) {
        System.out.print("\nInsert release year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        var results = archive.searchByYear(year);
        if (results.isEmpty()) {
            System.out.println("No founded elements by year " + year);
        } else {
            System.out.println("Founded elements: ");
            results.forEach(System.out::println);
        }
    }


    private static void searchByAuthor(Scanner scanner, Archive archive) {
        System.out.print("\nInsert author name: ");
        String author = scanner.nextLine();
        var results = archive.searchByAuthor(author);
        if (results.isEmpty()) {
            System.out.println("No book found by author " + author);
        } else {
            System.out.println("found books: ");
            results.forEach(System.out::println);
        }
    }


    private static void uploadElement(Scanner scanner, Archive archive) {
        System.out.print("\nInsert ISBN of element to upload: ");
        String isbn = scanner.nextLine();
        try {
            System.out.println("Insert new data of element.");
            addElement(scanner, archive);
            archive.removeElement(isbn);
            System.out.println("Element uploaded successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}