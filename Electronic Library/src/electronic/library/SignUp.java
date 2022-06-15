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
public class SignUp extends JFrame implements ActionListener {

    ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibraryxs.png");
    JPanel header = new JPanel();
    JLabel top = new JLabel();
    JPanel formBox = new JPanel();
    JLabel signUpForm = new JLabel("Registration Form");
    JLabel Username = new JLabel("Enter Username:");
    JLabel Password = new JLabel("Enter Password:");
    JLabel firstName = new JLabel("Enter First Name:");
    JLabel lastName = new JLabel("Enter Last Name:");
    JTextField TUsername = new JTextField();
    JTextField TFirstName = new JTextField();
    JTextField TLastName = new JTextField();
    JPasswordField TPassword = new JPasswordField();
    JButton BRegister = new JButton("REGITSER");

    Cursor hand = new Cursor(Cursor.HAND_CURSOR);
    Font cambriaBold = new Font("Cambria", Font.BOLD, 20);
    Font calibriPlain = new Font("Calibri", Font.PLAIN, 20);

    Color grey = new Color(0xd0d2d3);
    Color lightBlue = new Color(0x0071ad);
    Color darkBlue = new Color(0x004f76);

    SignUp() {
        top.setText("Electronic Library");
        top.setIcon(logo);
        top.setFont(new Font("Cambria", Font.BOLD, 40));
        top.setBounds(20, 10, 795, 90);
        top.setIconTextGap(30);
        top.setBackground(grey);
        top.setOpaque(true);

        header.add(top);
        header.setBounds(5, 10, 850, 110);
        header.setLayout(null);

        formBox.setBounds(25, 130, 795, 650);
        formBox.add(signUpForm);
        formBox.add(firstName);
        formBox.add(TFirstName);
        formBox.add(lastName);
        formBox.add(TLastName);
        formBox.add(Username);
        formBox.add(TUsername);
        formBox.add(Password);
        formBox.add(TPassword);
        formBox.add(BRegister);
        formBox.setLayout(null);

        signUpForm.setFont(new Font("Cambria", Font.BOLD, 25));
        signUpForm.setBounds(290, 20, 250, 40);

        firstName.setFont(cambriaBold);
        firstName.setBounds(150, 110, 200, 40);
        TFirstName.setFont(calibriPlain);
        TFirstName.setBounds(350, 110, 300, 40);

        lastName.setFont(cambriaBold);
        lastName.setBounds(150, 180, 200, 40);
        TLastName.setFont(calibriPlain);
        TLastName.setBounds(350, 180, 300, 40);

        Username.setFont(cambriaBold);
        Username.setBounds(150, 250, 200, 40);
        TUsername.setFont(calibriPlain);
        TUsername.setBounds(350, 250, 300, 40);

        Password.setFont(cambriaBold);
        Password.setBounds(150, 320, 200, 40);
        TPassword.setFont(calibriPlain);
        TPassword.setBounds(350, 320, 300, 40);

        BRegister.setBounds(300, 450, 150, 50);
        BRegister.setFont(new Font("Cambria", Font.BOLD, 20));
        BRegister.setBackground(darkBlue);
        BRegister.setForeground(Color.WHITE);
        BRegister.setCursor(hand);
        BRegister.setFocusable(false);
        BRegister.addActionListener(this);

        this.add(header);
        this.add(formBox);
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

        int error = 0;
        int success = 0;
        int ptr = 0;

        if (e.getSource() == BRegister) {
            if (TFirstName.getText().length() == 0 || TLastName.getText().length() == 0 || TUsername.getText().length() == 0 || TPassword.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "All Fields are required to be filled!", "ERROR", JOptionPane.ERROR_MESSAGE);
                error = 1;
            }

            if (error == 0) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234"); //STEP2
                    Statement s1 = connection.createStatement();

                    Statement s2 = connection.createStatement();
                    ResultSet details = s2.executeQuery("select * from login_details");
                    while (details.next()) {
                        if (TUsername.getText().equals(details.getString("userName"))) {
                            ptr = 1;
                        }
                    }

                    if (ptr == 0) {
                        s1.executeUpdate("insert into login_details (firstName ,lastName ,userName ,user_Password ) values('" + TFirstName.getText() + "','" + TLastName.getText() + "','" + TUsername.getText() + "','" + TPassword.getText() + "')");
                        JOptionPane.showMessageDialog(null, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        success = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "Username already taken!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    connection.close();
                } catch (Exception a1) {
                    System.out.println(a1);
                }
            }
            if (success == 1) {
                dispose();
                new LogIn();
            }

        }
    }
}
