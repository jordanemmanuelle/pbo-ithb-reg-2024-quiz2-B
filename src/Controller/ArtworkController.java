package Controller;

import Model.Class.Artwork;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtworkController {

    private Database dbConnection;

    public ArtworkController() {
        dbConnection = new Database();
    }

    public boolean addArtwork(int userId, String title, String description, String imagePath) {
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String query = "INSERT INTO artworks (user_id, title, description, image_path) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, imagePath);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
    
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
        }
        return false;
    }

    public List<Artwork> getArtworksByUser(int userId) {
        List<Artwork> artworks = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Database.getConnection();
            String query = "SELECT * FROM artworks WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String imagePath = resultSet.getString("image_path");

                artworks.add(new Artwork(id, title, description, imagePath, String.valueOf(userId)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
        }
        return artworks;
    }
}
