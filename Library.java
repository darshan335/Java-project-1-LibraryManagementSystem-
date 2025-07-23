import java.util.*;
import java.io.*;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private final String FILE_NAME = "library.txt";

    public Library() {
        loadFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveToFile();
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void deleteBook(String title) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (book.getTitle().equalsIgnoreCase(title)) {
                it.remove();
                saveToFile();
                System.out.println("Book deleted.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",", 3);
                if (data.length == 3) {
                    books.add(new Book(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from file.");
        }
    }
}

