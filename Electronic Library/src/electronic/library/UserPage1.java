/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Zulekha
 */
public class UserPage1 extends JFrame implements ActionListener, MouseListener {

    JMenuBar menubar = new JMenuBar();
    JPanel header = new JPanel();
    JLabel top = new JLabel();
    JLabel SearchBooks = new JLabel("Enter Book Name");
    JTextField searchBar = new JTextField();
    JButton Search = new JButton("Search");
    JMenu File;
    JMenu Books;
    JMenu Help;
    JMenuItem Logout;
    JMenuItem DeleteAccount;
    JMenuItem Recents;
    JMenu Novel;
    JMenu Subject;
    JMenuItem Contact;

    JMenuItem Java;
    JMenuItem Discrete;
    JMenuItem English;
    JMenuItem Database;
    JMenuItem JKRowling;
    JMenuItem RickRiordan;
    JMenuItem EnidBlyton;
    JMenuItem PauloCoelho;

    String cols[] = {"Name", "Author", "Release Date"};

    TableModel tableModel = new DefaultTableModel(cols, 0);
    JTable book_table = new JTable(tableModel);

    int UserID;
    String UserName;
    ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibraryxs.png");
    Color grey = new Color(0xd0d2d3);
    Color lightBlue = new Color(0x0071ad);
    Color darkBlue = new Color(0x004f76);

    Cursor hand = new Cursor(Cursor.HAND_CURSOR);

    UserPage1(int UserID, String UserName) {
        this.UserID = UserID;
        this.UserName = UserName;

        File = new JMenu("File");
        File.setFont(new Font("Cambria", Font.BOLD, 18));
        File.setForeground(Color.white);

        Books = new JMenu("Books");
        Books.setFont(new Font("Cambria", Font.BOLD, 18));
        Books.setForeground(Color.white);

        Help = new JMenu("Help");
        Help.setFont(new Font("Cambria", Font.BOLD, 18));
        Help.setForeground(Color.white);

        Logout = new JMenuItem("Logout");
        Logout.setFont(new Font("Cambria", Font.PLAIN, 18));
        Logout.addActionListener(this);
        Logout.setMnemonic(KeyEvent.VK_L);

        DeleteAccount = new JMenuItem("Delete Account");
        DeleteAccount.setFont(new Font("Cambria", Font.PLAIN, 18));
        DeleteAccount.addActionListener(this);

        Recents = new JMenuItem("Recents");
        Recents.setFont(new Font("Cambria", Font.PLAIN, 18));
        Recents.addActionListener(this);

        Novel = new JMenu("Novel");
        Novel.setFont(new Font("Cambria", Font.PLAIN, 18));
        Novel.addActionListener(this);

        Subject = new JMenu("Subject");
        Subject.setFont(new Font("Cambria", Font.PLAIN, 18));
        Subject.addActionListener(this);

        Contact = new JMenuItem("Contact");
        Contact.setFont(new Font("Cambria", Font.PLAIN, 18));
        Contact.addActionListener(this);

        Java = new JMenuItem("Java");
        Java.setFont(new Font("Cambria", Font.PLAIN, 18));
        Java.addActionListener(this);

        Discrete = new JMenuItem("Discrete");
        Discrete.setFont(new Font("Cambria", Font.PLAIN, 18));
        Discrete.addActionListener(this);

        English = new JMenuItem("English");
        English.setFont(new Font("Cambria", Font.PLAIN, 18));
        English.addActionListener(this);

        Database = new JMenuItem("Database");
        Database.setFont(new Font("Cambria", Font.PLAIN, 18));
        Database.addActionListener(this);

        JKRowling = new JMenuItem("JK Rowling");
        JKRowling.setFont(new Font("Cambria", Font.PLAIN, 18));
        JKRowling.addActionListener(this);

        RickRiordan = new JMenuItem("Rick Riordan");
        RickRiordan.setFont(new Font("Cambria", Font.PLAIN, 18));
        RickRiordan.addActionListener(this);

        EnidBlyton = new JMenuItem("Enid Blyton");
        EnidBlyton.setFont(new Font("Cambria", Font.PLAIN, 18));
        EnidBlyton.addActionListener(this);

        PauloCoelho = new JMenuItem("Paulo Coelho");
        PauloCoelho.setFont(new Font("Cambria", Font.PLAIN, 18));
        PauloCoelho.addActionListener(this);

        Subject.add(Java);
        Subject.add(Discrete);
        Subject.add(English);
        Subject.add(Database);

        Novel.add(JKRowling);
        Novel.add(RickRiordan);
        Novel.add(PauloCoelho);
        Novel.add(EnidBlyton);

        File.add(Logout);
        File.add(DeleteAccount);

        Books.add(Recents);
        Books.addSeparator();
        Books.add(Novel);
        Books.add(Subject);

        Help.add(Contact);

        menubar.add(File);
        menubar.add(Books);
        menubar.add(Help);

        menubar.setPreferredSize(new Dimension(850, 40));
        menubar.setBackground(darkBlue);

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

        SearchBooks.setBounds(20, 120, 250, 80);
        SearchBooks.setFont(new Font("Cambria", Font.BOLD, 30));

        searchBar.setBounds(20, 200, 600, 50);
        searchBar.setFont(new Font("Cambria", Font.PLAIN, 25));

        Search.setBounds(650, 200, 150, 50);
        Search.setFont(new Font("Cambria", Font.BOLD, 20));
        Search.setBackground(darkBlue);
        Search.setForeground(Color.WHITE);
        Search.setCursor(hand);
        Search.setFocusable(false);
        Search.addActionListener(this);
        Search.setMnemonic(KeyEvent.VK_S);

        JScrollPane sp = new JScrollPane(book_table);
        sp.setBounds(20, 300, 790, 400);
        book_table.setCursor(hand);
        book_table.addMouseListener(this);

        this.add(top);
        this.add(SearchBooks);
        this.add(searchBar);
        this.add(Search);
        this.add(sp);
        this.setJMenuBar(menubar);
        this.setSize(850, 850);
        this.setLayout(null);
        this.setIconImage(logo.getImage());
        this.setTitle("Electronic Library");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Search) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();
                ResultSet books = s1.executeQuery("select * from books where book_name like '%" + searchBar.getText() + "%'");

                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Book not found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == Contact) {
            JOptionPane.showMessageDialog(null, "Contact us at - service@gmail.com", "Contact Us", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == Logout) {
            dispose();
            new LogIn();
        }
        if (e.getSource() == DeleteAccount) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                s1.executeUpdate("delete from login_details where user_id=" + UserID);
                JOptionPane.showMessageDialog(null, "Account Deleted Successfully!", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LogIn();
            } catch (Exception a1) {
                System.out.println(a1);
            }
        }
        if (e.getSource() == Recents) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                Statement s2 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from recent_books where user_id=" + UserID);
                String author = null;
                String release_date = null;
                String name = null;
                while (books.next()) {
                    name = books.getString("bookName");
                    ResultSet books2 = s2.executeQuery("select * from books where book_name='" + name + "'");
                    while (books2.next()) {
                        author = books2.getString("author");
                        release_date = books2.getString("release_date");

                        Object[] book_data = {name, author, release_date};
                        DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                        bookModel.addRow(book_data);
                    }
                }

                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books read recently!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == JKRowling) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where author='" + JKRowling.getText() + "'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == RickRiordan) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where author='" + RickRiordan.getText() + "'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == PauloCoelho) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where author='" + PauloCoelho.getText() + "'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == EnidBlyton) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where author='" + EnidBlyton.getText() + "'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == Java) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where book_name like '%" + Java.getText() + "%'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == Discrete) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where book_name like '%" + Discrete.getText() + "%'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == English) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where book_name like '%" + English.getText() + "%'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == Database) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();

                ResultSet books = s1.executeQuery("select * from books where book_name like '%" + Database.getText() + "%'");
                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) book_table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "No books found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) book_table.getModel();
        dtm.setNumRows(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = book_table.rowAtPoint(e.getPoint());
        int col = book_table.columnAtPoint(e.getPoint());

        String name = (String) book_table.getValueAt(row, col);

        new BookDetail(name, UserName);

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
