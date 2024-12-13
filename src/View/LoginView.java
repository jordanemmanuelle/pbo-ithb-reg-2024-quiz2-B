package View;

import javax.swing.*;
import Controller.UserController;
import Model.Class.User;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


// LOGIN
// EMAIL: jordan@gmail.com
// PASSWORD: 123



public class LoginView extends JFrame {
    private JTextField Email;
    private JPasswordField Password;
    private JButton loginButton;

    public LoginView() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        Email = new JTextField();
        Password = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Email:"));
        add(Email);
        add(new JLabel("Password:"));
        add(Password);
        add(new JLabel());
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = Email.getText();
                    String password = new String(Password.getPassword());
                    User user = UserController.login(email, password);
                    JOptionPane.showMessageDialog(null, "Selamat datang, " + user.getName() + "! ");
                    dispose();
                    ArtworkListView artworkListView = new ArtworkListView(user);
                    artworkListView.setVisible(true);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke database salah");
                }
            }
        });
    }
}
