package Aston1Stage.Lesson3;

public class Book implements Comparable<Book> {
    String title;
    String author;
    int pages;
    int year;


    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public Book(String title, String author, int pages, int year) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }

    @Override
    public int compareTo(Book otherBook) {
        return Integer.compare(this.pages, otherBook.pages);
    }
}
