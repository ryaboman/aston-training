package Aston1Stage.ThirdLesson;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Book> books;

    public Student(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
