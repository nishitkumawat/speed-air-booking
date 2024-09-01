import java.sql.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class LoginPage extends JFrame implements ActionListener, MouseListener {
    Connection con = Main.con;
    Scanner sc = new Scanner(System.in);
    JTextField textEmail;
    JTextField textPass;
    JButton loginButton;
    JButton regButton;
    JPanel main;

    LoginPage() throws Exception {

        this.setBounds(0, 0, 1536, 864);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(false);

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("D:\\connect_air\\jdbc\\login2.png");
        label.setIcon(img);
        label.setBounds(0, 0, 1536, 864);
        panel.add(label);
        panel.setBounds(0, 0, 1536, 8964);

        login();

        this.add(panel);
        this.setVisible(true);
    }

    void login() throws SQLException {

        JLabel label = new JLabel();
        label.setBounds(597, 232, 350, 49);
        label.setText("WELCOME TO SPEED AIR");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

        JLabel label2 = new JLabel();
        label2.setBounds(597 + 65, 302, 350, 49);
        label2.setText("ENTER YOUR LOGIN DETIALS");
        label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        textEmail = new JTextField();
        textEmail.setBounds(597, 362, 340, 45);
        textEmail.setBackground(Color.white);
        textEmail.setText("Enter Email ID");
        textEmail.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        textEmail.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        textEmail.addMouseListener(this);

        textPass = new JTextField();
        textPass.setBounds(597, 432, 340, 45);
        textPass.setBackground(Color.white);
        textPass.setText("Enter Password");
        textPass.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        textPass.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        textPass.addMouseListener(this);

        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        loginButton.setFocusable(false);
        loginButton.setBounds(688, 500, 160, 37);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.white);

        JLabel label3 = new JLabel();
        label3.setBounds(597 + 90, 560, 350, 49);
        label3.setText("Don't Have An Account");
        label3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        regButton = new JButton("REGISTER");
        regButton.setFocusable(false);
        regButton.setBounds(715, 600, 100, 20);
        regButton.setBackground(Color.white);
        regButton.setForeground(Color.BLUE);
        regButton.setBorder(BorderFactory.createMatteBorder(
                0, 0, 0, 0, Color.white));

        this.add(label);
        this.add(label2);
        this.add(loginButton);
        this.add(textEmail);
        this.add(textPass);
        this.add(label3);
        this.add(regButton);

        regButton.addActionListener(this);
        loginButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st
                        .executeQuery("select * from user_info where email_id='" + textEmail.getText() + "'");

                if (rs.next()) {

                    if (rs.getString(5).equals(textPass.getText())) {
                        Main.currentUser = new CurrentUser(rs.getString(1), rs.getString(2), rs.getString(3));
                        new HomePage();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Incorrect Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "No user found");
                }

            } catch (Exception e1) {

                e1.printStackTrace();
            }
        } else if (e.getSource() == regButton) {
            try {
                new RegisterPage();
            } catch (Exception e1) {
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == textPass) {
            textPass.setText("");

        }
        if (e.getSource() == textEmail) {
            textEmail.setText("");
        }
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