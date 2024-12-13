package View;

import javax.swing.*;
import Model.Class.User;
import Controller.Database;
import java.awt.*;
import java.sql.*;

public class ArtworkListView extends JFrame {
    public ArtworkListView(User user) {
        setTitle("Artwork List - Welcome " + user.getName());
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addArtworkButton = new JButton("Add Artwork");
        add(addArtworkButton, BorderLayout.NORTH);

        JPanel artworkPanel = new JPanel();
        artworkPanel.setLayout(new GridLayout(0, 1));

        try {
            Connection conn = Database.getConnection();
            String query = "SELECT a.title, a.description, a.image_path, u.name " +
                           "FROM artworks a JOIN users u ON a.user_id = u.id " +
                           "WHERE a.user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
        
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("title")); 
                JPanel itemPanel = new JPanel(new GridLayout(1, 3));
                itemPanel.add(new JLabel("Title: " + rs.getString("title")));
                itemPanel.add(new JLabel("Artist: " + rs.getString("name")));
                itemPanel.add(new JLabel("Description: " + rs.getString("description")));
                artworkPanel.add(itemPanel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Kesalahan dalam memuat data karya seni.");
        }

        add(new JScrollPane(artworkPanel), BorderLayout.CENTER);

        addArtworkButton.addActionListener(e -> {
            dispose();
            AddArtworkView addArtworkView = new AddArtworkView(user);
            addArtworkView.setVisible(true);
        });
    }
}
