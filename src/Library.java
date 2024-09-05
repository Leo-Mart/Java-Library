import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Library {

    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Book> loanedBooks = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public void displayMainMenu() {
        System.out.println("*************************************");
        System.out.println("*** Welcome  to the Library       ***");
        System.out.println("*** Make your selection below     ***");
        System.out.println("*** 1. Add a book to the library  ***");
        System.out.println("*** 2. Search for a book by name  ***");
        System.out.println("*** 3. List all available books   ***");
        System.out.println("*** 4. Return a book              ***");
        System.out.println("*** 5. Reserve a book             ***");
        System.out.println("*** 6. Create a new user          ***");
        System.out.println("*** 7. Login to user account      ***");
        System.out.println("*** 8. Exit the application       ***");
        System.out.println("*************************************");
    }

    public void displayUserMainMenu() {
        System.out.println("*************************************");
        System.out.println("*** Welcome  to the Library       ***");
        System.out.println("*** This is the user menu         ***");
        System.out.println("*** Make your selection below     ***");
        System.out.println("*** 1. Add a book to the library  ***");
        System.out.println("*** 2. Search for a book by name  ***");
        System.out.println("*** 3. List all available books   ***");
        System.out.println("*** 4. Return a book              ***");
        System.out.println("*** 5. Reserve a book             ***");
        System.out.println("*** 6. Create a new user          ***");
        System.out.println("*** 7. Login to user account      ***");
        System.out.println("*** 8. Exit the application       ***");
        System.out.println("*************************************");
    }

    public void displaySearchForBookMenu() {
        System.out.println("***********************************************************");
        System.out.println("*** Search for a book                                   ***");
        System.out.println("*** Enter the name of the book you wish to find below   ***");
        System.out.println("***********************************************************");
    }

    public void displayLoanMenu() {
        System.out.println("***                                                     ***");
        System.out.println("*** The book was found, what do you wish to do?         ***");
        System.out.println("*** 1. Loan the book                                    ***");
        System.out.println("*** 2. Go back to main menu                             ***");
        System.out.println("***********************************************************");
    }


    public void displayFoundBookInfo(Book book) {
        System.out.println("***********************************************************");
        System.out.println("*** Book found                                          ***");
        System.out.println("*** " + book + " ***");
        System.out.println("***                                                     ***");
    }

    public void displayLoanedBooksList(ArrayList<Book> loanedBooks) {
        System.out.println("***********************************************************");
        System.out.println("*** All Books currently on loan                         ***");
        for (Book book : loanedBooks) {
            System.out.println(book);
        }
        System.out.println("***********************************************************");
        System.out.println("*** If you wish to return a book, enter its name below  ***");
    }

    public boolean createNewUserMenu(Scanner sc) {
        System.out.println("***********************************************************");
        System.out.println("*** To create a new user, enter a username and password ***");
        System.out.println("***********************************************************");
        System.out.print("Enter your username: ");

        String userName = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        try {
            createNewUser(userName, password);
            System.out.println("New user created, go back to main menu to log in");
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong " + e.getMessage());
            return false;
        }

    }

    public boolean addBook(Scanner sc) {

        System.out.println("Please enter the title of the book: ");
        String title = sc.nextLine();
        System.out.println("Please enter the author of the book: ");
        String author = sc.nextLine();
        System.out.println("Please enter the year of publication for the book: ");
        int yearOfPublication = Integer.parseInt(sc.nextLine());
        System.out.println("Please enter the edition of the book: ");
        int edition = Integer.parseInt(sc.nextLine());

        try {
            Book newBook = new Book(title, author, yearOfPublication, edition);
            books.add(newBook);
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong " + e.getMessage());
            return false;
        }
    }

    public boolean loanBook(Book book) {
        if (book.isAvailable()) {
            try {
                book.setAvailable(false);
                loanedBooks.add(book);
                return true;
            } catch (Exception e) {
                System.out.println("Something went wrong " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Book is not available/already on loan");

            System.out.println("Do you wish to be added to the reservation list?");
            System.out.println("Your name has been added to the reservations");
            System.out.println("You will be notified when the book becomes available");

            return false;
        }

    }

    public void reserveBook(Scanner sc) {
        System.out.println("***********************************************************");
        System.out.println("*** Reserve a book                                      ***");
        System.out.println("*** Enter the name of the book you wish to reserve:     ***");
        String searchQuery = sc.nextLine();
        System.out.println("*** Enter you username:                                 ***");
        String userName = sc.nextLine();

        Book foundBook = getBookByTitle(searchQuery);

        if (foundBook == null) {
            System.out.println("Could not find the specified book, try again");
        } else if (foundBook.isAvailable()) {
            System.out.println("Book is available");
            System.out.println("Do you wish to loan the book now [y/n]?");

            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case 'y' ->{
                    if (loanBook(foundBook)) {
                        System.out.println("Book has been loaned");
                    }
                }

                case 'n' -> {}
            }
        } else {
            System.out.println("Book is not available");
            System.out.println("Your name has been added to the reservation list");
            System.out.println("You will be notified when the book becomes available");
            foundBook.reserve(userName);
        }

    }

    public boolean returnBook(Scanner sc) {
        displayLoanedBooksList(loanedBooks);
        String bookToSearchFor = sc.nextLine();
        Book foundLoanedBook = null;

        for (Book book : loanedBooks) {
            if (book.getTitle().equalsIgnoreCase(bookToSearchFor)) {
                foundLoanedBook = book;
            } else {
                System.out.println("Could not find the book you searched for, try again!");
            }
        }

        try {
            assert foundLoanedBook != null;
            if(foundLoanedBook.getReservations().isEmpty()) {
                foundLoanedBook.setAvailable(true);
                loanedBooks.remove(foundLoanedBook);
            } else if(!foundLoanedBook.getReservations().isEmpty()) {
                foundLoanedBook.setAvailable(false);
                foundLoanedBook.getReservations().removeFirst();
                // TODO: most likely want to get the first user here so I can use their info to send a notification that they are now able to get the book

            }

            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong " + e.getMessage());
            return false;
        }
    }

    public Book getBookByTitle(String title) {
        Book foundBook = null;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBook = book;
            }
        }

        // TODO: add some check if the book isn't found and return a error message instead.

        return foundBook;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void printBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void createNewUser(String userName, String password) {
        try {
            User newUser = new User(userName, password);
            users.add(newUser);
        } catch (Exception e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

    public boolean loginUser(Scanner sc) {
        boolean foundUser = false;

        System.out.println("***********************************************************");
        System.out.println("*** To login, enter a username and password below       ***");
        System.out.println("***********************************************************");
        System.out.print("Enter your username: ");

        String userName = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userName) && user.getPassword().equalsIgnoreCase(password)) {
                foundUser = true;
            } else if(!Objects.equals(user.getName(), userName)) {
                System.out.println("Found no user with that name");
            } else if (!Objects.equals(user.getPassword(), password)) {
                System.out.println("Password incorrect");
            }
        }
        return foundUser;
    }
}
