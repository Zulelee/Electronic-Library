/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Humza_ach
 */
public class changes extends JFrame implements ActionListener {

    int Book_ID;
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

    JButton submit = new JButton("Submit");

    ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibraryxs.png");

    Color grey = new Color(0xd0d2d3);
    Color lightBlue = new Color(0x0071ad);
    Color darkBlue = new Color(0x004f76);
    String UserName;
    Cursor hand = new Cursor(Cursor.HAND_CURSOR);

    changes(String name) {
        bookName = name;

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

        submit.setBounds(250, 530, 150, 50);
        submit.setFont(new Font("Cambria", Font.BOLD, 20));
        submit.setBackground(grey);
        submit.setCursor(hand);
        submit.addActionListener(this);
        submit.setFocusable(false);

        Name.setBounds(30, 150, 150, 30);
        Name.setFont(new Font("Cambria", Font.BOLD, 20));
        TName.setBounds(180, 150, 400, 30);
        TName.setFont(new Font("Cambria", Font.PLAIN, 18));

        Author.setBounds(30, 200, 150, 30);
        Author.setFont(new Font("Cambria", Font.BOLD, 20));
        TAuthor.setBounds(180, 200, 400, 30);
        TAuthor.setFont(new Font("Cambria", Font.PLAIN, 18));

        ReleaseDate.setBounds(30, 250, 150, 30);
        ReleaseDate.setFont(new Font("Cambria", Font.BOLD, 20));
        TReleaseDate.setBounds(180, 250, 400, 30);
        TReleaseDate.setFont(new Font("Cambria", Font.PLAIN, 18));

        Summary.setBounds(30, 300, 150, 30);
        Summary.setFont(new Font("Cambria", Font.BOLD, 20));

        TSummary.setLineWrap(true);
        JScrollPane summaryScroll = new JScrollPane(TSummary);
        summaryScroll.setBounds(180, 300, 400, 200);

        this.add(Name);
        this.add(TName);
        this.add(Author);
        this.add(TAuthor);
        this.add(ReleaseDate);
        this.add(TReleaseDate);
        this.add(Summary);
        this.add(submit);

        this.add(summaryScroll);

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
                Book_ID = books.getInt("book_id");
                TName.setText(books.getString("book_name"));
                TAuthor.setText(books.getString("author"));
                TReleaseDate.setText(books.getString("release_date"));
                TSummary.setText(books.getString("summary"));
            }

            connection.close();
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            if (TName.getText().length() != 0 && TAuthor.getText().length() != 0 && TSummary.getText().length() != 0) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");
                    Statement s = c.createStatement();
                    s.executeUpdate("update books set book_name='" + TName.getText() + "',author='" + TAuthor.getText() + "',release_date='" + TReleaseDate.getText() + "',summary='" + TSummary.getText() + "' where book_id=" + Book_ID);
                    JOptionPane.showMessageDialog(null, "Book Updated!", "Updated!", JOptionPane.INFORMATION_MESSAGE);
                    c.close();
                } catch (Exception e1) {
                    System.out.println(e1);
                    JOptionPane.showMessageDialog(null, " Operation Failed!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fill All Fields !", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
