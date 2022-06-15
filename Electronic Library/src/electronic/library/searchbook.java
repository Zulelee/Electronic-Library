/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author humza
 */
public class searchbook extends JFrame implements ActionListener, MenuListener, MouseListener {

    JLabel SearchBooks = new JLabel("Enter Book Name");
    JTextField searchBar = new JTextField();
    JButton Search = new JButton("Search");
    JMenuBar m = new JMenuBar();
    JMenu home = new JMenu("Home");
    JMenu logout = new JMenu("Logout");
    JMenu help = new JMenu("help");
    String cols[] = {"Name", "Autor", "Release Date"};
    TableModel tablemodel = new DefaultTableModel(cols, 0);
    JTable table = new JTable(tablemodel);

    searchbook() {

        Color grey = new Color(0xd0d2d3);
        Color darkBlue = new Color(0x004f76);

        Cursor hand = new Cursor(Cursor.HAND_CURSOR);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 300, 790, 400);
        table.setCursor(hand);
        table.addMouseListener(this);

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

        JLabel top = new JLabel(); //JLabel Creation

        top.setText("Electronic Library");
        top.setIcon(logo);
        top.setFont(new Font("Cambria", Font.BOLD, 40));
        top.setBounds(20, 10, 795, 90);
        top.setIconTextGap(30);
        top.setBackground(grey);
        top.setOpaque(true);

        this.setSize(850, 850);
        this.setLayout(null);
        this.setTitle("Search Book");
        this.add(top);
        this.setJMenuBar(m);
        this.add(SearchBooks);
        this.add(searchBar);
        this.add(Search);
        this.add(sp);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setNumRows(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Search) {
            try {

                clearTable();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

                Statement s1 = connection.createStatement();
                ResultSet books = s1.executeQuery("select * from books where book_name like '" + searchBar.getText() + "%'");

                while (books.next()) {
                    String name = books.getString("book_name");
                    String author = books.getString("author");
                    String release_date = books.getString("release_date");

                    Object[] book_data = {name, author, release_date};
                    DefaultTableModel bookModel = (DefaultTableModel) table.getModel();

                    bookModel.addRow(book_data);
                }
                connection.close();
            } catch (Exception a) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int col = table.columnAtPoint(e.getPoint());

        String name = (String) table.getValueAt(row, col);

        new adminBookDetail(name);
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
