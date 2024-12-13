package View;
import javax.swing.*;
import Controller.*;
import Model.Class.User;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;

public class AddArtworkView extends JFrame {
    public AddArtworkView(User user) {
        setTitle("Add Artwork");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JTextField titleField = new JTextField();
        JTextArea descriptionArea = new JTextArea();
        JButton uploadButton = new JButton("Upload Image");
        JButton submitButton = new JButton("Submit");

        String[] imagePath = {""}; 

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Description:"));
        add(new JScrollPane(descriptionArea));
        add(uploadButton);
        add(new JLabel());
        add(submitButton);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select an Image File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));

                int result = fileChooser.showOpenDialog(AddArtworkView.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath[0] = selectedFile.getAbsolutePath(); 
                    JOptionPane.showMessageDialog(null, "Image selected: " + imagePath[0]);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();

                if (imagePath[0].isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please upload an image.");
                    return;
                }

                try {
                    Connection conn = Database.getConnection();
                    String query = "INSERT INTO artworks (title, description, image_path, user_id) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, title);
                    stmt.setString(2, description);
                    stmt.setString(3, imagePath[0]);
                    stmt.setInt(4, user.getId());
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Artwork berhasil ditambahkan.");
                    dispose();
                    ArtworkListView artworkListView = new ArtworkListView(user);
                    artworkListView.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Kesalahan dalam menyimpan data artwork.");
                    ex.printStackTrace();
                }
            }
        });
    }
}
