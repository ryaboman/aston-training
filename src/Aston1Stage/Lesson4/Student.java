package Aston1Stage.Lesson4;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Student implements Externalizable {
    private String name;
    private List<Book> books;

    public Student() {}
    public Student(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(books.size());
        for (Book book : books) {
            out.writeObject(book);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int size = in.readInt();
        books = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            books.add((Book) in.readObject());
        }
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
        return name + " " + books;
    }
}
