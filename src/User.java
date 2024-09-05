import java.util.ArrayList;

public class User {
    private String name;
    private String password;
    private ArrayList<Book> loanedBooks;
    private ArrayList<Book> reservedBooks;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.loanedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    public User() {}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
