import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;

public class RegisterPage extends JFrame implements ActionListener, MouseListener {
    Connection con = Main.con;
    JTextField email;
    JTextField name;
    JTextField phno;
    JTextField dob;
    JTextField pass;
    JButton loginButton;
    JButton regButton;

    RegisterPage() throws Exception {

        this.setBounds(0, 0, 1536, 864);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(false);

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("D:\\connect_air\\jdbc\\login2.png");

        JLabel label3 = new JLabel();
        label3.setBounds(654, 204, 258, 37);
        label3.setText("Enter Your Details");
        label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        label.setIcon(img);
        label.setBounds(0, 0, 1536, 864);
        panel.add(label);
        panel.setBounds(0, 0, 1536, 8964);

        name = new JTextField();
        name.setBounds(630, 281, 274, 38);
        name.setBackground(Color.WHITE);
        name.setText("Enter Name");
        name.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        name.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));

        email = new JTextField();
        email.setBounds(630, 353, 274, 38);
        email.setBackground(Color.WHITE);
        email.setText("Enter Email ID");
        email.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        email.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));

        phno = new JTextField();
        phno.setBounds(630, 424, 274, 38);
        phno.setBackground(Color.WHITE);
        phno.setText("Enter Phone No.");
        phno.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        phno.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));

        dob = new JTextField();
        dob.setBounds(630, 495, 274, 38);
        dob.setBackground(Color.WHITE);
        dob.setText("Enter DOB");
        dob.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        dob.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));

        pass = new JTextField();
        pass.setBounds(630, 566, 274, 38);
        pass.setBackground(Color.WHITE);
        pass.setText("Enter PASSWORD");
        pass.setBorder(BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.BLUE));
        pass.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));

        regButton = new JButton("REGISTER");
        regButton.setFocusable(false);
        regButton.setBounds(688, 631, 160, 37);
        regButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        regButton.setBackground(Color.BLACK);
        regButton.setForeground(Color.white);
        regButton.setVisible(true);

        JLabel label4 = new JLabel();
        label4.setBounds(597 + 85, 680, 350, 49);
        label4.setText("Already Have An Account");
        label4.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        loginButton = new JButton("LOGIN");
        loginButton.setFocusable(false);
        loginButton.setBounds(710, 715, 100, 20);
        loginButton.setBackground(Color.white);
        loginButton.setForeground(Color.BLUE);
        loginButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));

        name.addMouseListener(this);
        dob.addMouseListener(this);
        email.addMouseListener(this);
        phno.addMouseListener(this);
        pass.addMouseListener(this);

        this.add(label4);
        this.add(loginButton);
        this.add(label3);
        this.add(name);
        this.add(email);
        this.add(phno);
        this.add(dob);
        this.add(pass);
        this.add(regButton);
        regButton.addActionListener(this);
        loginButton.addActionListener(this);
        this.add(panel);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regButton) {

            String email = this.email.getText();
            String Name = this.name.getText();
            String dob = this.dob.getText();
            String phno = this.phno.getText();
            String pass = this.pass.getText();
            boolean flag = false;
            boolean emailCheck = false;

            if (email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@outlook.com")) {
                if (email.equals("@gmail.com") || email.equals("@yahoo.com") || email.equals("@outlook.com")) {
                    emailCheck = false;
                } else {
                    emailCheck = true;
                }
            }

            if (phno.length() == 10) {
                for (int i = 0; i < phno.length(); i++) {
                    if (phno.charAt(i) >= '0' && phno.charAt(i) <= '9') {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) {

                JOptionPane.showMessageDialog(new JFrame(), "Enter Valid Phone Number");

            } else {

                if (dob.charAt(4) == '/' && dob.charAt(7) == '/' && dob.length() == 10) {

                    if (emailCheck) {

                        try {
                            Statement st = con.createStatement();
                            ResultSet rs = st
                                    .executeQuery("select password from user_info where email_id='" + email + "'");
                            if (!rs.next()) {
                                PreparedStatement ps = con.prepareStatement("insert into user_info values(?,?,?,?,?)");
                                ps.setString(1, email);
                                ps.setString(2, Name);
                                ps.setString(3, phno);
                                ps.setString(4, dob);
                                ps.setString(5, pass);
                                ps.executeUpdate();
                                Main.currentUser = new CurrentUser(email, Name, phno);
                                new HomePage();
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(),
                                        "Account Already Exists With these EMail ID");
                                System.out.println("Account Already Exists With these EMail ID");

                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(new JFrame(), "ENTERED DETAILS ARE INVALID");
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Enter Valid Email");
                    }
                } else {

                    JOptionPane.showMessageDialog(new JFrame(), "Enter Valid DOB (YYYY/MM/DD)");

                }
            }
        }
        if (e.getSource() == loginButton) {
            try {
                new LoginPage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == name) {
            name.setText("");
        }
        if (e.getSource() == pass) {
            pass.setText("");
        }
        if (e.getSource() == phno) {
            phno.setText("");
        }
        if (e.getSource() == email) {
            email.setText("");
        }
        if (e.getSource() == dob) {
            dob.setText("");
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
