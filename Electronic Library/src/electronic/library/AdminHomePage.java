/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package electronic.library;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author Humza_ach
 */
public class AdminHomePage extends JFrame implements ActionListener, MenuListener {

    JButton Search_Book = new JButton("Search Book");
    JButton Add_Book = new JButton("Add Book");
    JButton Update_Book = new JButton("Update Book");
    JButton Delete_Book = new JButton("Delete Book");
    JButton View_Users = new JButton("View Users");

    JMenuBar m = new JMenuBar();
    JMenu home = new JMenu("Home");
    JMenu logout = new JMenu("Logout");
    JMenu help = new JMenu("help");

    AdminHomePage() {

        home.add("Home");
        home.setFont(new Font("Cambria", Font.BOLD, 18));

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

        Color grey = new Color(0xd0d2d3);

        ImageIcon logo = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\ElectronicLibraryxs.png");
        ImageIcon bimg = new ImageIcon("C:\\Users\\hp\\Desktop\\java project\\bgimage.jpg");

        JLabel top = new JLabel(); //JLabel Creation

        top.setText("Electronic Library");
        top.setIcon(logo);
        top.setFont(new Font("Cambria", Font.BOLD, 40));
        top.setBounds(20, 10, 795, 90);
        top.setIconTextGap(30);
        top.setBackground(grey);
        top.setOpaque(true);

        JLabel adminlabel = new JLabel("HELLO ADMIN!");
        adminlabel.setBounds(0, 80, 795, 35);
        adminlabel.setHorizontalAlignment(JLabel.CENTER);
        adminlabel.setFont(new Font("Cambria", Font.BOLD, 30));
        adminlabel.setBackground(new Color(0, 0, 0, 150));
        adminlabel.setOpaque(true);
        adminlabel.setForeground(Color.WHITE);


        Font bfont = new Font("Cambria", Font.BOLD, 22);

        Search_Book.setBounds(310, 165, 180, 60);
        Search_Book.setBackground(new Color(0, 0, 0));
        Search_Book.setFont(bfont);
        Search_Book.setForeground(Color.WHITE);
        Search_Book.addActionListener(this);
        Search_Book.setFocusable(false);

        Add_Book.setBounds(310, 265, 180, 60);
        Add_Book.setBackground(new Color(0, 0, 0));
        Add_Book.setFont(bfont);
        Add_Book.setForeground(Color.WHITE);
        Add_Book.setFocusable(false);
        Add_Book.addActionListener(this);

        Update_Book.setBounds(310, 365, 180, 60);
        Update_Book.setBackground(new Color(0, 0, 0));
        Update_Book.setFont(bfont);
        Update_Book.setForeground(Color.WHITE);
        Update_Book.setFocusable(false);
        Update_Book.addActionListener(this);

        Delete_Book.setBounds(310, 465, 180, 60);
        Delete_Book.setBackground(new Color(0, 0, 0));
        Delete_Book.setFont(bfont);
        Delete_Book.setForeground(Color.WHITE);
        Delete_Book.setFocusable(false);
        Delete_Book.addActionListener(this);

        View_Users.setBounds(310, 565, 180, 60);
        View_Users.setBackground(new Color(0, 0, 0));
        View_Users.setFont(bfont);
        View_Users.setForeground(Color.WHITE);
        View_Users.setFocusable(false);
        View_Users.addActionListener(this);

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bimg.getImage(), 0, 0, null);
            }
        };

        p.setBounds(20, 120, 795, 680);
        p.setLayout(null);
        p.add(adminlabel);
        p.add(Search_Book);
        p.add(Add_Book);
        p.add(Update_Book);
        p.add(Delete_Book);
        p.add(View_Users);

        this.setSize(850, 850);
        this.setLayout(null);
        this.setIconImage(logo.getImage());
        this.setTitle("Electronic Library");

        this.add(top);
        this.add(p);
        this.setJMenuBar(m);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Search_Book) {
            dispose();
            new searchbook();
        }
        if (e.getSource() == Add_Book) {
            dispose();
            new addbook();
        }
        if (e.getSource() == Update_Book) {
            dispose();
            new updatebook();
        }
        if (e.getSource() == Delete_Book) {
            dispose();
            new deletebook();
        }
        if (e.getSource() == View_Users) {
            dispose();
            new viewusers();
        }

    }

    @Override
    public void menuSelected(MenuEvent e) {
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
