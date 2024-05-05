import java.sql.*;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Admin {

    private String username;
    private String password;
    private String email;
    private static final String url = "jdbc:mysql://127.0.0.1/eyaddb";
    private static final String usernamee = "root";
    private static final String passwordd = "eyad07";

    public Admin(){}

    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean signUp() {
        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            // Prepare the SQL statement
            String sql = "INSERT INTO admin (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            conn.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Adminlogin(String username, String password) throws SQLException {

        String name;
        Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
        String sqlcheckLogin = "SELECT * FROM admin WHERE BINARY username=? AND BINARY password=?";
        PreparedStatement statement = conn.prepareStatement(sqlcheckLogin);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet checkLoginResult = statement.executeQuery();

        if (checkLoginResult.next()) {
            name = checkLoginResult.getString("username");
            return true;
        } else {
            return false;
        }
    }

    public static void showBooks() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, usernamee, passwordd)) {
            String query = "SELECT  *  FROM BOOK ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String category = resultSet.getString("Category");
                    String name = resultSet.getString("Name");
                    String authorName = resultSet.getString("Author_Name");
                    String publisher = resultSet.getString("Publisher");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ObservableList<Account> getUsersFromDatabase() {
        ObservableList<Account> accountList = FXCollections.observableArrayList();
    
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
             //Statements are used to execute SQL queries against the database to retrive data from database
            Statement stmt = conn.createStatement();
            String sql = "SELECT userId, username, email, phonenumber FROM user";
    
            ResultSet rs = stmt.executeQuery(sql); //ResultSet stores the rows and columns returned by the SQL query
            while (rs.next()) {
                String userId = rs.getString("UserID");
                String username = rs.getString("Username");
                String email = rs.getString("Email");
                String phonenumber = rs.getString("Phonenumber");
    
                // Create Account object and add it to the list
                Account account = new Account(userId,username, email, phonenumber);
                accountList.add(account);
            }
            // Don't forget to close the ResultSet, Statement, and Connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle exceptions here
            e.printStackTrace();
        }
    
        return accountList;
    }

    public ObservableList<BorrowedBook> getBorrowedBooks() {
        ObservableList<BorrowedBook> borrowedBooksList = FXCollections.observableArrayList();
    
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            Statement stmt = conn.createStatement();
            String sql = "SELECT user_id, user_username, book_id, book_name, booking_id FROM booking";
    
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String User_username = rs.getString("user_username");
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                int bookingId = rs.getInt("booking_id");
                LocalDate returnDate = null;
                // Create Account object and add it to the list
                BorrowedBook borrowedBooks = new BorrowedBook(userId, User_username, bookId, bookName, bookingId, returnDate);
                borrowedBooksList.add(borrowedBooks);
            }
            // Don't forget to close the ResultSet, Statement, and Connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle exceptions here
            e.printStackTrace();
        }
    
        return borrowedBooksList;
    }

    public boolean addBook(String book_Name, String category, String author_Name, String publisher, String availability) {
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)) {


            int nextBookId = generateNextBookId(conn);

            String sql = "INSERT INTO book (BookID, BookName, BookCategory, BookAuthor , Publisher, Availability) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, nextBookId);
            statement.setString(2, book_Name);
            statement.setString(3, category);
            statement.setString(4, author_Name);
            statement.setString(5, publisher);
            statement.setString(6, availability);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            conn.close();
            System.out.println("Book successfully added");
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int generateNextBookId(Connection conn) throws SQLException {
        String sqlGetMaxBookId = "SELECT MAX(BookId) AS max_id FROM Book";
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlGetMaxBookId);
            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                return maxId + 1;
            }
        }
        return 1;
    }

    public static boolean deleteBook(int BookID, String BookName) {
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);

            String sql = "DELETE FROM book WHERE BINARY BookID = ? AND BINARY BookName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, BookID);
            statement.setString(2, BookName);

            int rowsAffected = statement.executeUpdate();
            statement.close();
            conn.close();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Book> getBooksFromDatabase() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
    
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            Statement stmt = conn.createStatement();
            String sql = "SELECT BookID, BookName, BookCategory, BookAuthor, Publisher , Availability FROM book";
    
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int bookId = rs.getInt("BookID");
                String bookName = rs.getString("BookName");
                String category = rs.getString("BookCategory");
                String author = rs.getString("BookAuthor");
                String publisher = rs.getString("Publisher");
                String availability = rs.getString("Availability");

    
                Book book = new Book(bookId, bookName, category, author, publisher, availability);
                bookList.add(book);
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return bookList;
    }
    


}