/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronic.library;

/**
 *
 * @author Zulekha
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class AdminSearch extends JFrame implements MenuListener {

    JMenuBar m = new JMenuBar();
    JMenu home = new JMenu("Home");
    JMenu logout = new JMenu("Logout");
    JMenu help = new JMenu("help");
    Color grey = new Color(0xd0d2d3);

    AdminSearch() {
        home.setFont(new Font("Cambria", Font.BOLD, 18));
        home.addMenuListener(this);

        logout.setFont(new Font("Cambria", Font.BOLD, 18));
        logout.addMenuListener(this);

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

            JOptionPane.showMessageDialog(null, "Contact us at - Service@gmail.com", "Help!", JOptionPane.INFORMATION_MESSAGE);

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
