import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.*;

public class Account {

    private final StringProperty id;
    private final StringProperty username;
    private final StringProperty email;
    private final StringProperty phone;
    private final IntegerProperty totalBorrowedBooks;
    private static final String url = "jdbc:mysql://127.0.0.1/eyaddb";
    private static final String usernamee = "root";
    private static final String passwordd = "eyad07";

    public Account(String id, String username, String email, String phone) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.totalBorrowedBooks = new SimpleIntegerProperty(0);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks.get();
    }

    public IntegerProperty totalBorrowedBooksProperty() {
        return totalBorrowedBooks;
    }

    public void borrowBook() {
        totalBorrowedBooks.set(totalBorrowedBooks.get() + 1);
    }

    public void returnBook() {
        if (totalBorrowedBooks.get() > 0) {
            totalBorrowedBooks.set(totalBorrowedBooks.get() - 1);
        }
    }

    public static int getUserBooksCount(String userName) {
        int counter = 0;
    
        String sql = "SELECT booking_id FROM booking WHERE user_username = ?";
    
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, userName);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    counter++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
}