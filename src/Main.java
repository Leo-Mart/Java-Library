import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean running = true;
        boolean isLoggedIn = false;
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (running) {

            if (isLoggedIn) {
                System.out.println("Welcome to super secret user-menu");
            } else {
                library.displayMainMenu();
            }

            int choice = Integer.parseInt(sc.nextLine());

            switch(choice) {
                case 1 -> {
                    if (library.addBook(sc)) {
                        System.out.println("Book added");
                    } else {
                        System.out.println("Book was not added, check the errors");
                    }

                }
                case 2 -> {
                    library.displaySearchForBookMenu();
                    String searchQuery = sc.nextLine();

                    Book foundBook = library.getBookByTitle(searchQuery);

                    if (foundBook != null) {

                        library.displayFoundBookInfo(foundBook);
                        library.displayLoanMenu();

                        choice = Integer.parseInt(sc.nextLine());

                        switch(choice) {
                            case 1 -> {
                                if(library.loanBook(foundBook)) {
                                    System.out.println("Book was successfully loaned");
                                } else {
                                    System.out.println("Book was not successfully loaned");
                                }
                            }
                            case 2 -> {}
                        }
                    }
                }
                case 3 -> library.printBooks();
                case 4 -> {
                    if (library.returnBook(sc)) {
                        System.out.println("Book returned");
                    }
                }
                case 5 -> library.reserveBook(sc);
                case 6 -> library.createNewUserMenu(sc);
                case 7 -> {
                    if(library.loginUser(sc)) {
                        System.out.println("User logged in");
                        isLoggedIn = true;
                    }
                }
                case 8 -> running = false;

            }
        }
        sc.close();
    }
}