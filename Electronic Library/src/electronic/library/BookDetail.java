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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Zulekha
 */
public class BookDetail extends JFrame implements ActionListener {

    String bookName;
    JPanel header = new JPanel();
    JLabel top = new JLabel();
    JLabel bookDetail = new JLabel("Book Details");
    JLabel Name = new JLabel("Name:");
    JLabel Author = new JLabel("Author:");
    JLabel ReleaseDate = new JLabel("Release Date:");
    JLabel Summary = new JLabel("Summary:");

    JTextField TName = new JTextField();
    JTextField TAuthor = new JTextField();
    JTextField TReleaseDate = new JTextField();
    JTextArea TSummary = new JTextArea();

    JButton readBook = new JButton("Read Book");

    String filePath;

    ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibraryxs.png");
    Color grey = new Color(0xd0d2d3);
    Color lightBlue = new Color(0x0071ad);
    Color darkBlue = new Color(0x004f76);
    String UserName;
    Cursor hand = new Cursor(Cursor.HAND_CURSOR);

    BookDetail(String name, String UserName) {
        bookName = name;
        this.UserName = UserName;

        System.out.println(name);
        top.setText("Electronic Library");
        top.setIcon(logo);
        top.setFont(new Font("Cambria", Font.BOLD, 30));
        top.setBounds(10, 10, 620, 70);
        top.setIconTextGap(30);
        top.setBackground(grey);
        top.setOpaque(true);

        header.add(top);
        header.setBounds(0, 0, 640, 80);
        header.setLayout(null);

        bookDetail.setBounds(250, 90, 150, 50);
        bookDetail.setFont(new Font("Cambria", Font.BOLD, 25));

        Name.setBounds(30, 150, 150, 30);
        Name.setFont(new Font("Cambria", Font.BOLD, 20));
        TName.setBounds(180, 150, 400, 30);
        TName.setFont(new Font("Cambria", Font.PLAIN, 18));
        TName.setEditable(false);

        Author.setBounds(30, 200, 150, 30);
        Author.setFont(new Font("Cambria", Font.BOLD, 20));
        TAuthor.setBounds(180, 200, 400, 30);
        TAuthor.setFont(new Font("Cambria", Font.PLAIN, 18));
        TAuthor.setEditable(false);

        ReleaseDate.setBounds(30, 250, 150, 30);
        ReleaseDate.setFont(new Font("Cambria", Font.BOLD, 20));
        TReleaseDate.setBounds(180, 250, 400, 30);
        TReleaseDate.setFont(new Font("Cambria", Font.PLAIN, 18));
        TReleaseDate.setEditable(false);

        Summary.setBounds(30, 300, 150, 30);
        Summary.setFont(new Font("Cambria", Font.BOLD, 20));

        TSummary.setFont(new Font("Cambria", Font.PLAIN, 18));
        TSummary.setEditable(false);
        TSummary.setLineWrap(true);
        JScrollPane summaryScroll = new JScrollPane(TSummary);
        summaryScroll.setBounds(180, 300, 400, 200);

        readBook.setBounds(250, 530, 150, 50);
        readBook.setFont(new Font("Cambria", Font.BOLD, 20));
        readBook.setBackground(darkBlue);
        readBook.setForeground(Color.white);
        readBook.setCursor(hand);
        readBook.addActionListener(this);
        readBook.setFocusable(false);

        this.add(Name);
        this.add(TName);
        this.add(Author);
        this.add(TAuthor);
        this.add(ReleaseDate);
        this.add(TReleaseDate);
        this.add(Summary);
        this.add(summaryScroll);
        this.add(readBook);

        this.add(bookDetail);
        this.add(header);
        this.setSize(650, 650);
        this.setLayout(null);
        this.setIconImage(logo.getImage());
        this.setTitle("Electronic Library");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.showData();
    }

    void showData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

            Statement s1 = connection.createStatement();
            ResultSet books = s1.executeQuery("select * from books where book_name='" + bookName + "'");
            while (books.next()) {
                TName.setText(books.getString("book_name"));
                TAuthor.setText(books.getString("author"));
                TReleaseDate.setText(books.getString("release_date"));
                TSummary.setText(books.getString("summary"));
                filePath = books.getString("book_path");
            }

            connection.close();
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readBook) {
            try {
                if (filePath != null) {

                    Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + filePath);

                    //save recent books details in database
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");
                        int id = 0;
                        String username = null;
                        Statement s1 = connection.createStatement();
                        Statement s2 = connection.createStatement();
                        Statement s3 = connection.createStatement();

                        ResultSet user = s1.executeQuery("select user_id, userName from login_details where userName='" + UserName + "'");
                        while (user.next()) {
                            id = user.getInt("user_id");
                            username = user.getString("userName");
                        }

                        int bookid = 0;
                        String bookname = null;
                        ResultSet book = s2.executeQuery("select book_id, book_name from books where book_name='" + bookName + "'");

                        while (book.next()) {
                            bookid = book.getInt("book_id");
                            bookname = book.getString("book_name");
                        }

                        s3.executeUpdate("insert into recent_books (user_id, userName, book_id, bookName) values(" + id + ",'" + username + "'," + bookid + ",'" + bookname + "')");

                        connection.close();
                    } catch (Exception a) {
                        System.out.println(a);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "File Path Missing", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception a1) {
                System.out.println(a1);
            }
        }
    }
}
