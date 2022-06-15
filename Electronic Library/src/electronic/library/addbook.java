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
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author humza
 */
public class addbook extends JFrame implements ActionListener, MenuListener {

    String path;

    JMenuBar m = new JMenuBar();
    JMenu home = new JMenu("Home");
    JMenu logout = new JMenu("Logout");
    JMenu help = new JMenu("help");

    JButton browse = new JButton("Browse");
    JButton addbook = new JButton("Add Book");

    JTextField namet = new JTextField();
    JTextField authort = new JTextField();
    JTextArea summaryt = new JTextArea();
    JTextField filepatht = new JTextField();
    JTextField releaset = new JTextField();

    JComboBox combo = new JComboBox();
    Font fx = new Font("Cambria", Font.PLAIN, 25);
    Font f = new Font("Cambria", Font.BOLD, 32);

    addbook() {

        Color grey = new Color(0xd0d2d3);

        combo.addItem("Novel");
        combo.addItem("Subject");
        combo.setBounds(390, 410, 270, 40);
        combo.setFont(fx);

        JLabel namel = new JLabel("Name :");
        JLabel authorl = new JLabel("Author :");
        JLabel summaryl = new JLabel("Summary :");
        JLabel filepathl = new JLabel("File Path :");
        JLabel typel = new JLabel("Type :");
        JLabel releasel = new JLabel("Release Date:");

        releasel.setBounds(180, 470, 200, 40);
        releasel.setFont(f);

        releaset.setBounds(390, 470, 270, 40);
        releaset.setFont(fx);
        releaset.setToolTipText("YYYY-MM-DD");

        typel.setBounds(180, 410, 100, 40);
        typel.setFont(f);

        namel.setBounds(180, 170, 100, 40);
        namel.setFont(f);

        namet.setBounds(390, 170, 270, 40);
        namet.setFont(fx);

        authorl.setBounds(180, 230, 150, 40);
        authorl.setFont(f);

        authort.setBounds(390, 230, 270, 40);
        authort.setFont(fx);

        summaryl.setBounds(180, 290, 170, 40);
        summaryl.setFont(f);
        summaryt.setFont(fx);
        JScrollPane sp = new JScrollPane(summaryt);
        sp.setBounds(390, 290, 270, 45);

        filepathl.setBounds(180, 350, 170, 40);
        filepathl.setFont(f);

        filepatht.setBounds(390, 350, 270, 40);
        filepatht.setFont(fx);

        browse.setBounds(690, 350, 100, 40);
        browse.setFont(new Font("Cambria", Font.BOLD, 18));
        browse.setBackground(grey);
        browse.setFocusable(false);
        browse.addActionListener(this);

        addbook.setBounds(390, 550, 270, 40);
        addbook.setFont(new Font("Cambria", Font.BOLD, 25));
        addbook.setBackground(grey);
        addbook.setFocusable(false);
        addbook.addActionListener(this);

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
        this.setTitle("Add Book");
        this.add(top);
        this.setJMenuBar(m);

        this.add(namel);
        this.add(namet);
        this.add(authorl);
        this.add(authort);
        this.add(sp);
        this.add(summaryl);
        this.add(filepathl);
        this.add(filepatht);
        this.add(releasel);
        this.add(releaset);

        this.add(combo);
        this.add(typel);
        this.add(browse);
        this.add(addbook);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addbook) {
            if (namet.getText().length() != 0 && authort.getText().length() != 0 && summaryt.getText().length() != 0 && filepatht.getText().length() != 0) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroniclibrary", "root", "1234");
                    Statement s = c.createStatement();
                    s.executeUpdate("Insert into books(book_name,release_date,author,summary,book_type,book_path) values('" + namet.getText() + "','" + releaset.getText() + "','" + authort.getText() + "','" + summaryt.getText() + "','" + combo.getSelectedItem().toString() + "','" + filepatht.getText() + "')");
                    System.out.println(filepatht.getText());
                    JOptionPane.showMessageDialog(null, "Book Added!", "Added!", JOptionPane.INFORMATION_MESSAGE);
                    c.close();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, " Operation Failed!" + e1, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fill All Fields !", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == browse) {
            int response;
            JFileChooser choose = new JFileChooser();
            response = choose.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                path = choose.getSelectedFile().getAbsolutePath();
                filepatht.setText(path);
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
