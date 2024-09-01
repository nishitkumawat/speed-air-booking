import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Payment extends JFrame implements ActionListener {

    JLabel amount;
    JLabel title;

    JPanel choice;
    JPanel cardNumber;
    JPanel upiNumber;
    JPanel cardPIN;
    JPanel cardOTP;
    JPanel upiPIN;
    JPanel upiOTP;
    JPanel payOTP1;
    JPanel payOTP2;
    JPanel cardPanel;
    JPanel upiPanel;
    JPanel northPanel;
    JPanel payment;
    JPanel mainPanel;
    JPanel discountPanel1;
    JPanel discountPanel2;

    CardLayout cardLayout;

    JButton upi;
    JButton card;
    JButton submitCard;
    JButton submitUPI;
    JButton getOTP1;
    JButton pay1;
    JButton getOTP2;
    JButton pay2;

    JTextField cardNumberField;
    JTextField cardPINField;
    JTextField cardOTPField;
    JTextField cardDiscount;
    JTextField upiNumberField;
    JTextField upiPINField;
    JTextField upiOTPField;
    JTextField upiDiscount;

    static int price;
    static int randomOTP = 0;
    static Color blue = new Color(0x004aad);
    static int i = 1;

    Payment(int amt) throws Exception {
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setVisible(false);

        price = amt;

        northPanel = new JPanel(new GridLayout(2, 1));
        title = new JLabel("SELECT PAYMENT MODE", JLabel.CENTER);
        choice = new JPanel();
        choice.setLayout(new GridLayout(1, 2));
        amount = new JLabel("Total Amount : " + price + "Rs.", JLabel.CENTER);
        amount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        upi = new JButton("UPI");
        card = new JButton("CARD");
        card.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        upi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        card.setForeground(Color.WHITE);
        card.setBackground(new Color(0x004aad));
        upi.setForeground(Color.BLACK);
        upi.setBackground(Color.WHITE);
        upi.setFocusable(false);
        card.setFocusable(false);
        upi.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        card.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        upi.addActionListener(this);
        card.addActionListener(this);

        choice.add(card);
        choice.add(upi);

        northPanel.add(title);
        northPanel.add(choice);

        payment = new JPanel(new BorderLayout());

        cardNumber = new JPanel(new GridLayout(1, 2));
        JLabel cardNumberLabel = new JLabel("Enter Card Number (16 DIGITS) : ");
        cardNumberField = new JTextField();
        cardNumber.add(cardNumberLabel);
        cardNumber.add(cardNumberField);

        cardPIN = new JPanel(new GridLayout(1, 2));
        JLabel cardPINLabel = new JLabel("Enter Card PIN (4 DIGITS) : ");
        cardPINField = new JTextField();
        cardPIN.add(cardPINLabel);
        cardPIN.add(cardPINField);

        cardOTP = new JPanel(new GridLayout(1, 2));
        JLabel cardOTPLabel = new JLabel("Enter OTP (6 DIGITS) : ");
        cardOTPField = new JTextField();
        cardOTP.add(cardOTPLabel);
        cardOTP.add(cardOTPField);

        discountPanel1 = new JPanel(new GridLayout(1, 2));
        JLabel discountLabel1 = new JLabel("Enter PROMOCODE : ");
        cardDiscount = new JTextField();
        discountPanel1.add(discountLabel1);
        discountPanel1.add(cardDiscount);

        upiNumber = new JPanel(new GridLayout(1, 2));
        JLabel upiNumberLabel = new JLabel("Enter UPI Number (10 DIGITS): ");
        upiNumberField = new JTextField();
        upiNumber.add(upiNumberLabel);
        upiNumber.add(upiNumberField);

        upiPIN = new JPanel(new GridLayout(1, 2));
        JLabel upiPINLabel = new JLabel("Enter UPI PIN (6 DIGITS): ");
        upiPINField = new JTextField();
        upiPIN.add(upiPINLabel);
        upiPIN.add(upiPINField);

        upiOTP = new JPanel(new GridLayout(1, 2));
        JLabel upiOTPLabel = new JLabel("Enter OTP (6 DIGITS): ");
        upiOTPField = new JTextField();
        upiOTP.add(upiOTPLabel);
        upiOTP.add(upiOTPField);

        discountPanel2 = new JPanel(new GridLayout(1, 2));
        JLabel discountLabel2 = new JLabel("Enter PROMOCODE : ");
        upiDiscount = new JTextField();
        discountPanel2.add(discountLabel2);
        discountPanel2.add(upiDiscount);

        getOTP1 = new JButton("GET OTP");
        pay1 = new JButton("PAY");
        getOTP1.setForeground(Color.WHITE);
        getOTP1.setBackground(blue);
        pay1.setForeground(Color.WHITE);
        pay1.setBackground(blue);
        getOTP1.setFocusable(false);
        pay1.setFocusable(false);
        getOTP1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        pay1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        getOTP1.addActionListener(this);
        pay1.addActionListener(this);

        payOTP1 = new JPanel();
        payOTP1.setLayout(new GridLayout(1, 2, 10, 0));
        payOTP1.add(getOTP1);
        payOTP1.add(pay1);

        getOTP2 = new JButton("GET OTP");
        pay2 = new JButton("PAY");
        getOTP2.setForeground(Color.WHITE);
        getOTP2.setBackground(blue);
        pay2.setForeground(Color.WHITE);
        pay2.setBackground(blue);
        getOTP2.setFocusable(false);
        pay2.setFocusable(false);
        getOTP2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        pay2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        getOTP2.addActionListener(this);
        pay2.addActionListener(this);

        payOTP2 = new JPanel();
        payOTP2.setLayout(new GridLayout(1, 2, 10, 0));
        payOTP2.add(getOTP2);
        payOTP2.add(pay2);

        cardPanel = new JPanel(new GridLayout(12, 1));
        cardPanel.add(new JLabel());
        cardPanel.add(cardNumber);
        cardPanel.add(new JLabel());
        cardPanel.add(cardPIN);
        cardPanel.add(new JLabel());
        cardPanel.add(cardOTP);
        cardPanel.add(new JLabel());
        cardPanel.add(discountPanel1);
        cardPanel.add(new JLabel());
        cardPanel.add(payOTP1);
        cardPanel.setVisible(false);

        upiPanel = new JPanel(new GridLayout(12, 1));
        upiPanel.add(new JLabel());
        upiPanel.add(upiNumber);
        upiPanel.add(new JLabel());
        upiPanel.add(upiPIN);
        upiPanel.add(new JLabel());
        upiPanel.add(upiOTP);
        upiPanel.add(new JLabel());
        upiPanel.add(discountPanel2);
        upiPanel.add(new JLabel());
        upiPanel.add(payOTP2);
        upiPanel.setVisible(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(cardPanel, "Card");
        mainPanel.add(upiPanel, "UPI");

        payment.add(mainPanel, BorderLayout.CENTER);
        payment.add(northPanel, BorderLayout.NORTH);
        payment.add(amount, BorderLayout.SOUTH);

        this.add(payment, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == card) {
            i = 1;
            upi.setForeground(Color.BLACK);
            upi.setBackground(Color.WHITE);
            card.setForeground(Color.WHITE);
            card.setBackground(blue);
            cardLayout.show(mainPanel, "Card");

        } else if (e.getSource() == upi) {
            i = 2;
            card.setForeground(Color.BLACK);
            card.setBackground(Color.WHITE);
            upi.setForeground(Color.WHITE);
            upi.setBackground(blue);
            cardLayout.show(mainPanel, "UPI");
        }
        if (e.getSource() == getOTP1 || e.getSource() == getOTP2) {
            randomOTP = (int) (100000 + Math.random() * 899999);
            JOptionPane.showMessageDialog(new JFrame(), "OTP : " + randomOTP);
        }
        if (e.getSource() == pay1 || e.getSource() == pay2) {
            if (i == 1) {

                String temp = cardNumberField.getText();
                boolean cardCheck = true;
                if (temp.length() == 16) {
                    for (int j = 0; j < 16; j++) {
                        if (temp.charAt(j) < '0' && temp.charAt(j) > '9') {
                            cardCheck = false;
                            break;
                        }
                    }
                } else {
                    cardCheck = false;
                }
                if (!cardCheck) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID CARD NUMBER");
                }
                String temp2 = cardPINField.getText();
                boolean pinCheck = true;
                if (temp2.length() == 4) {
                    for (int j = 0; j < 4; j++) {
                        if (temp2.charAt(j) < '0' && temp2.charAt(j) > '9') {
                            pinCheck = false;
                            break;
                        }
                    }
                } else {
                    pinCheck = false;
                }
                if (!pinCheck) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID PIN");
                }
                String temp3 = cardOTPField.getText();
                boolean otpCheck = true;
                try {
                    int temp4 = Integer.parseInt(temp3);
                    System.out.println(temp4);
                    if (temp4 == randomOTP) {
                        otpCheck = true;
                    } else {
                        otpCheck = false;
                        JOptionPane.showMessageDialog(new JFrame(), "INVALID OTP");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID OTP (ENTER ONLY NUMBER)");
                }
                if (otpCheck && pinCheck && cardCheck) {
                    price -= (int) (price / 20);
                    if (cardDiscount != null) {
                        try {
                            int discount = discountCalculator(cardDiscount.getText(), price);
                            price -= discount;
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "EXTRA DISCOUNT OF Rs " + discount
                                            + " is applied!! (-5% Card Discount is Applied)");

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    try {
                        JOptionPane.showMessageDialog(new JFrame(), price + "Rs Deposited from your Card : " + temp);
                        BookPage1.price = price;
                        new Ticket();
                        this.dispose();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println("error");
                }
            } else if (i == 2) {
                String temp = upiNumberField.getText();
                boolean upiCheck = true;
                if (temp.length() == 10) {
                    for (int j = 0; j < 10; j++) {
                        if (temp.charAt(j) < '0' && temp.charAt(j) > '9') {
                            upiCheck = false;
                            break;
                        }
                    }
                } else {
                    upiCheck = false;
                }
                if (!upiCheck) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID upi NUMBER");
                }
                String temp2 = upiPINField.getText();
                boolean pinCheck = true;
                if (temp2.length() == 6) {
                    for (int j = 0; j < 6; j++) {
                        if (temp2.charAt(j) < '0' && temp2.charAt(j) > '9') {
                            pinCheck = false;
                            break;
                        }
                    }
                } else {
                    pinCheck = false;
                }
                if (!pinCheck) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID PIN");
                }
                String temp3 = upiOTPField.getText();
                boolean otpCheck = true;
                try {
                    int temp4 = Integer.parseInt(temp3);
                    System.out.println(temp4);
                    if (temp4 == randomOTP) {
                        otpCheck = true;
                    } else {
                        otpCheck = false;
                        JOptionPane.showMessageDialog(new JFrame(), "INVALID OTP");

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID OTP (ENTER ONLY NUMBER)");

                }
                if (otpCheck && pinCheck && upiCheck) {
                    if (upiDiscount != null) {
                        try {
                            int discount = discountCalculator(upiDiscount.getText(), price);
                            price -= discount;
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "DISCOUNT OF Rs " + discount + " is applied!!");

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    try {
                        JOptionPane.showMessageDialog(new JFrame(),
                                price + "Rs Deposited from your UPI Number: " + temp);
                        BookPage1.price = price;
                        new Ticket();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    int discountCalculator(String promocode, int price) throws SQLException {
        int newprice = 0;
        if (promocode.equals("firstflight")) {
            Statement st = Main.con.createStatement();
            ResultSet rs = st
                    .executeQuery("select * from user_flight_details where email_id ='" + Main.currentUser.email + "'");
            if (!rs.next()) {
                newprice = (int) (price / 10);
            }
        } else if (promocode.equals("BIRTHDAY")) {
            Statement st = Main.con.createStatement();
            ResultSet rs = st
                    .executeQuery("select dob from user_info where email_id ='" + Main.currentUser.email + "'");
            rs.next();
            if (rs.getString(1).substring(4).equals(Main.currentDate().substring(4))) {
                newprice = (int) (price / 5);
            }
        } else if (promocode.equals("1500")) {
            if (price >= 15000) {
                newprice = 1500;
            }
        }
        return newprice;
    }
}
