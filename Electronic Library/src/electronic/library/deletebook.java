/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author humza
 */
public class deletebook extends JFrame implements ActionListener, MenuListener {

    JLabel SearchBooks = new JLabel("Enter Book Name");
    JTextField searchBar = new JTextField();
    JButton delete = new JButton("Delete");

    JMenuBar m = new JMenuBar();
    JMenu home = new JMenu("Home");
    JMenu logout = new JMenu("Logout");
    JMenu help = new JMenu("help");

    deletebook() {
        Color grey = new Color(0xd0d2d3);

        SearchBooks.setBounds(290, 150, 250, 80);
        SearchBooks.setFont(new Font("Cambria", Font.BOLD, 30));

        searchBar.setBounds(110, 230, 600, 50);
        searchBar.setFont(new Font("Cambria", Font.PLAIN, 25));

        delete.setBounds(330, 330, 150, 50);
        delete.setFont(new Font("Cambria", Font.BOLD, 20));
        delete.setBackground(grey);
        delete.setFocusable(false);
        delete.addActionListener(this);

        home.add("Home");
        home.setFont(new Font("Cambria", Font.BOLD, 18));
        home.addMenuListener(this);

        logout.add("logout");
        logout.setFont(new Font("Cambria", Font.BOLD, 18));
        logout.addMenuListener(this);

        help.add("Help");
        help.setFont(new Font("Cambria", Font.BOLD, 18));
        help.addMenuListener(this);

        m.add(home);
        m.add(logout);
        m.add(help);

        m.setPreferredSize(new Dimension(850, 40));

        ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibraryxs.png");

        JLabel top = new JLabel();

        top.setText("Electronic Library");
        top.setIcon(logo);
        top.setFont(new Font("Cambria", Font.BOLD, 40));
        top.setBounds(20, 10, 795, 90);
        top.setIconTextGap(30);
        top.setBackground(grey);
        top.setOpaque(true);

        this.setSize(850, 850);
        this.setLayout(null);
        this.setTitle("Delete Books");
        this.add(top);
        this.add(SearchBooks);
        this.add(searchBar);
        this.add(delete);
        this.setJMenuBar(m);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            //NEED DATA CONNECTION HERE
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();
                Statement s2 = connection.createStatement();
                Statement s3 = connection.createStatement();
                ResultSet bookss = s1.executeQuery("select * from books ");

                while (bookss.next()) {
                    if (searchBar.getText().equals(bookss.getString("book_name"))) {
                        int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION) {
                            s2.executeUpdate("delete from recent_books where bookName='" + searchBar.getText() + "'");
                            s3.executeUpdate("delete from books where book_name='" + searchBar.getText() + "'");
                            JOptionPane.showConfirmDialog(null, "Book is deleted!", "Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "Book not found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == home) {
            dispose();
            new AdminHomePage();

        }
        if (e.getSource() == help) {

            JOptionPane.showMessageDialog(null, "Service@gmail.com", "Help!", JOptionPane.INFORMATION_MESSAGE);

        }
        if (e.getSource() == logout) {
            dispose();
            new LogIn();
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

}
