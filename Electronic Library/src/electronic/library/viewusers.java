/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * /**
 *
 * @author humza
 */
public class viewusers extends JFrame implements MenuListener {

    JMenuBar m = new JMenuBar();
    JMenu home = new JMenu("Home");
    JMenu logout = new JMenu("Logout");
    JMenu help = new JMenu("help");

    String cols[] = {"First Name", "Last Name", "User Name"};
    TableModel tablemodel = new DefaultTableModel(cols, 0);
    JTable table = new JTable(tablemodel);

    viewusers() {
        Color grey = new Color(0xd0d2d3);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 230, 790, 400);

        JLabel userlist = new JLabel("Users List");
        userlist.setBounds(350, 130, 200, 60);
        userlist.setFont(new Font("Cambria", Font.BOLD, 30));

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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");

            Statement s1 = connection.createStatement();
            ResultSet user = s1.executeQuery("select * from login_details");

            while (user.next()) {

                String Fname = user.getString("firstName");
                String Lname = user.getString("lastName");
                String Uname = user.getString("userName");

                Object[] book_data = {Fname, Lname, Uname};
                DefaultTableModel bookModel = (DefaultTableModel) table.getModel();

                bookModel.addRow(book_data);

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No Users found!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        this.setSize(850, 850);
        this.setLayout(null);
        this.setTitle("View Users");
        this.add(top);
        this.add(userlist);
        this.setJMenuBar(m);
        this.add(sp);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
