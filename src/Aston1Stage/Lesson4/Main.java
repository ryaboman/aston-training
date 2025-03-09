package Aston1Stage.Lesson4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Совершенный код", "Стив Макконнелл", 896));

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.dat"))) {
            oos.writeObject( new Book("Философия Java", "Брюс Эккель", 1168) );
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("book.dat"))) {
            books.add( (Book)ois.readObject() );
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject( new Student("Алексей", books) );
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            System.out.println( (Student)ois.readObject() );
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        try (Stream<Path> paths = Files.walk(Paths.get("/home/aleksey/Загрузки/Other"))){
            paths.filter(Files::isRegularFile).forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("text.txt"), StandardOpenOption.APPEND)){
            String newLine = System.lineSeparator() + "New line";
            bufferedWriter.write(newLine);
            bufferedWriter.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
