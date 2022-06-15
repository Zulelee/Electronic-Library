/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Zulekha
 */
public class LogIn extends JFrame implements ActionListener {

    JLabel welcome = new JLabel();
    JLabel username = new JLabel();
    JLabel password = new JLabel();
    JLabel signUp = new JLabel();
    JTextField TUsername = new JTextField();
    JPasswordField TPassword = new JPasswordField();
    JButton BSignUp = new JButton("SignUp");
    JButton BLogin = new JButton("LOGIN");

    ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibrarysmall.png");
    Color grey = new Color(0xd0d2d3);
    Color lightBlue = new Color(0x0071ad);
    Color darkBlue = new Color(0x004f76);

    Cursor hand = new Cursor(Cursor.HAND_CURSOR);
    int UserID;

    LogIn() {
        JPanel header = new JPanel();
        header.setBounds(20, 20, 800, 250);

        welcome.setText("Welcome to Electronic Library");
        welcome.setIcon(logo);
        welcome.setIconTextGap(15);
        welcome.setHorizontalTextPosition(JLabel.CENTER);
        welcome.setVerticalTextPosition(JLabel.BOTTOM);
        welcome.setFont(new Font("Cambria", Font.BOLD, 40));
        header.add(welcome);

        JPanel box = new JPanel();
        box.setBounds(200, 300, 450, 450);
        box.setLayout(null);

        username.setText("Username: ");
        username.setFont(new Font("Cambria", Font.BOLD, 25));
        username.setBounds(15, 30, 150, 40);

        TUsername.setBounds(165, 30, 250, 40);
        TUsername.setFont(new Font("Calibri", Font.PLAIN, 25));
        TUsername.setAlignmentY(JTextField.CENTER_ALIGNMENT);

        password.setText("Password: ");
        password.setFont(new Font("Cambria", Font.BOLD, 25));
        password.setBounds(15, 100, 150, 40);

        TPassword.setBounds(165, 100, 250, 40);
        TPassword.setFont(new Font("Calibri", Font.PLAIN, 25));

        signUp.setText("Not a Member? ");
        signUp.setBounds(100, 150, 250, 40);
        signUp.setFont(new Font("Cambria", Font.PLAIN, 20));

        BSignUp.setBounds(230, 150, 100, 40);
        BSignUp.setFont(new Font("Cambria", Font.PLAIN, 20));
        BSignUp.setBackground(new Color(0xeeeeee));
        BSignUp.setForeground(lightBlue);
        BSignUp.setBorder(null);
        BSignUp.setFocusable(false);
        BSignUp.setCursor(hand);
        BSignUp.addActionListener(this);

        BLogin.setBounds(130, 220, 150, 50);
        BLogin.setFont(new Font("Cambria", Font.BOLD, 20));
        BLogin.setBackground(darkBlue);
        BLogin.setForeground(Color.WHITE);
        BLogin.setCursor(hand);
        BLogin.setFocusable(false);
        BLogin.addActionListener(this);

        box.add(username);
        box.add(password);
        box.add(TUsername);
        box.add(TPassword);
        box.add(signUp);
        box.add(BSignUp);
        box.add(BLogin);

        this.add(header);
        this.add(box);
        this.setSize(850, 850);
        this.setLayout(null);
        this.setIconImage(logo.getImage());
        this.setTitle("Electronic Library");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BSignUp) {
            dispose();
            new SignUp();
        }
        if (e.getSource() == BLogin) {
            int found = 0;
            int admin = 0;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();
                ResultSet login_data = s1.executeQuery("select user_id,userName, user_Password from login_details");

                while (login_data.next()) {
                    if ((TUsername.getText().equals(login_data.getString("userName"))) && (TPassword.getText().equals(login_data.getString("user_Password")))) {
                        UserID = login_data.getInt("user_id");
                        found = 1;
                    }
                    if ((TUsername.getText().equals("admin")) && (TPassword.getText().equals("admin"))) {
                        admin = 1;
                    }
                }
                connection.close();
            } catch (Exception a1) {
                System.out.println(a1);
            }

            if (admin == 1) {
                JOptionPane.showMessageDialog(null, "LogIn Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AdminHomePage();
            } else if (found == 1) {
                JOptionPane.showMessageDialog(null, "LogIn Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new UserPage1(UserID, TUsername.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password incorect!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
