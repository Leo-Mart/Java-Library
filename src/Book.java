import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private int yearOfPublication;
    private int edition;
    private boolean available;
    private ArrayList<String> reservations;

    public Book() {};

    public Book(String title, String author, int yearOfPublication, int edition) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.edition = edition;
        this.available = true;
        this.reservations = new ArrayList<>();
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

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void reserve(String userName) {
        this.reservations.add(userName);
    }

    public ArrayList<String> getReservations() {
        return reservations;
    }

    public String toString() {
        return String.format("%s, %s, %s, %d", title, author, yearOfPublication, edition);
    }


}
