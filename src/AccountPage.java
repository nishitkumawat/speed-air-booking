import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class AccountPage extends JFrame implements ActionListener {

    JButton[] edit = new JButton[5];
    JButton back;
    static Connection con = Main.con;

    AccountPage() {

        this.setBounds(0, 0, 350, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(false);

        JLabel title = new JLabel("YOUR ACCOUNT DETAILS", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        for (int i = 0; i < edit.length; i++) {
            edit[i] = new JButton("edit");
            edit[i].setBackground(Color.white);
            edit[i].setForeground(Color.BLUE);
            edit[i].setFocusable(false);
            edit[i].setOpaque(false);
            edit[i].setContentAreaFilled(false);
            edit[i].setBorder(BorderFactory.createEmptyBorder());
            edit[i].addActionListener(this);
        }

        JPanel centrePanel = new JPanel(new GridLayout(12, 1));
        centrePanel.add(new JLabel());
        centrePanel.add(labelCreator("NAME", Main.currentUser.name, edit[0]));
        centrePanel.add(new JLabel());
        centrePanel.add(labelCreator("EMAIL", Main.currentUser.email, edit[1]));
        centrePanel.add(new JLabel());
        centrePanel.add(labelCreator("PHONE NO", Main.currentUser.phoneno, edit[2]));
        centrePanel.add(new JLabel());
        centrePanel.add(labelCreator("DOB", Main.currentUser.DOB, edit[3]));
        centrePanel.add(new JLabel());
        centrePanel.add(labelCreator("Password", Main.currentUser.password, edit[4]));
        centrePanel.add(new JLabel());

        back = new JButton("BACK");
        back.setBackground(Color.white);
        back.setForeground(Color.BLUE);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setFocusable(false);
        back.addActionListener(this);
        centrePanel.add(back);

        this.add(centrePanel, BorderLayout.CENTER);
        this.add(title, BorderLayout.NORTH);
        this.add(new Label("        "), BorderLayout.WEST);
        this.add(new Label("    "), BorderLayout.EAST);
        this.setVisible(true);
    }

    AccountPage(String name, String email, String phono, String DOB, String password) {
        Main.currentUser.name = name;
        Main.currentUser.email = email;
        Main.currentUser.phoneno = phono;
        Main.currentUser.DOB = DOB;
        Main.currentUser.password = password;
        this();
    }

    static JPanel labelCreator(String title, String detail, JButton edit) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title + " : " + detail);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        panel.add(label, BorderLayout.CENTER);
        panel.add(edit, BorderLayout.EAST);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();
        }

        if (edit[0] == e.getSource()) {

            String x = JOptionPane.showInputDialog(null, "ENTER NEW NAME");
            if (x != null) {

                try {
                    Statement st = con.createStatement();
                    int n = st.executeUpdate(
                            "update user_info set name = '" + x + "' where email_id = '" + Main.currentUser.email
                                    + "'");
                    if (n > 0) {
                        this.dispose();
                        new AccountPage(x, Main.currentUser.email, Main.currentUser.phoneno, Main.currentUser.DOB,
                                Main.currentUser.password);
                    }
                } catch (SQLException e1) {
                }
            }

        }
        if (edit[1] == e.getSource()) {
            String x = JOptionPane.showInputDialog(null, "ENTER NEW EMAIL");
            if (x != null) {

                try {
                    Statement st = con.createStatement();
                    boolean contain = false;
                    ResultSet rs = st.executeQuery("select email_id from user_info");
                    while (rs.next()) {
                        if (rs.getString(1).equals(x)) {
                            contain = true;
                            break;
                        }
                    }
                    if (contain) {
                        JOptionPane.showMessageDialog(null, "EMAIL ALREADY EXISTS");
                    } else {
                        int n = st.executeUpdate(
                                "update user_info set email_id = '" + x + "' where email_id = '"
                                        + Main.currentUser.email
                                        + "'");
                        if (n > 0) {
                            st.executeUpdate("update user_flight_details set email_id = '" + x
                                    + "' where email_id = '" + Main.currentUser.email + "'");
                            this.dispose();
                            new AccountPage(Main.currentUser.name, x, Main.currentUser.phoneno, Main.currentUser.DOB,
                                    Main.currentUser.password);
                        }
                    }
                } catch (SQLException e1) {
                }
            }
        }
        if (edit[2] == e.getSource()) {
            String x = JOptionPane.showInputDialog(null, "ENTER NEW PHONE NUMBER");
            boolean flag = true;
            if (x != null && x.length() == 10) {
                for (int i = 0; i < 10; i++) {
                    if (x.charAt(i) > '9' || x.charAt(i) < '0') {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "INVALID PHONE NUMBER");
                } else {
                    try {
                        Statement st = con.createStatement();
                        boolean contain = false;
                        ResultSet rs = st.executeQuery("select phone_no from user_info");
                        while (rs.next()) {
                            if (rs.getString(1).equals(x)) {
                                contain = true;
                                break;
                            }
                        }
                        if (contain) {
                            JOptionPane.showMessageDialog(null, "PHONE NUMBER ALREADY EXISTS");
                        } else {
                            int n = st.executeUpdate(
                                    "update user_info set phone_no = '" + x + "' where email_id = '"
                                            + Main.currentUser.email
                                            + "'");
                            if (n > 0) {
                                this.dispose();
                                new AccountPage(Main.currentUser.name, Main.currentUser.email, x,
                                        Main.currentUser.DOB, Main.currentUser.password);
                            }
                        }
                    } catch (SQLException e1) {
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "INVALID PHONE NUMBER");
            }
        }
        if (edit[3] == e.getSource()) {
            String x = JOptionPane.showInputDialog(null, "ENTER NEW DOB(YYYY-MM-DD)");
            boolean flag = true;
            if (x != null && x.length() == 10 && x.charAt(4) == '/' && x.charAt(7) == '/') {
                for (int i = 0; i < 10; i++) {
                    if (x.charAt(i) > '9' || x.charAt(i) < '0') {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "INVALID DOB");
                } else {
                    try {
                        Statement st = con.createStatement();
                        int n = st.executeUpdate(
                                "update user_info set dob = '" + x + "' where email_id = '" + Main.currentUser.email
                                        + "'");
                        if (n > 0) {
                            this.dispose();
                            new AccountPage(Main.currentUser.name, Main.currentUser.email, Main.currentUser.phoneno, x,
                                    Main.currentUser.password);
                        }
                    } catch (SQLException e1) {
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "INVALID DOB");
            }
        }
        if (e.getSource() == edit[4]) {
            String x = JOptionPane.showInputDialog(null, "ENTER NEW PASSWORD");
            if (x != null) {

                try {
                    Statement st = con.createStatement();
                    int n = st.executeUpdate(
                            "update user_info set password = '" + x + "' where email_id = '" + Main.currentUser.email
                                    + "'");
                    if (n > 0) {
                        this.dispose();
                        new AccountPage(Main.currentUser.name, Main.currentUser.email, Main.currentUser.phoneno,
                                Main.currentUser.DOB, x);
                    }
                } catch (SQLException e1) {
                }
            }
        }
    }
}
