package Controller;

import java.sql.*;
import Model.Class.User;

public class UserController {
    public static User login(String email, String password) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new User(
                resultSet.getInt("id"), 
                resultSet.getString("name"), 
                email, 
                password
            );
        } else {
            throw new IllegalArgumentException("Email atau password salah.");
        }
    }
}
