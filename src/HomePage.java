import javax.swing.*;

import data_structure.LinkedListString;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * HomePage
 */
public class HomePage extends JFrame implements ActionListener {
    JPanel topBar;
    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;
    JPanel eastPanel;
    JPanel discountPanel1;
    JPanel discountPanel2;
    JPanel discountPanel3;
    JPanel discountPanel4;
    JPanel ad1;
    JPanel ad2;
    JPanel mainCenterPanel;

    JButton getAccountDetails;
    JButton logout;
    JButton bookFlight;
    JButton[] buttons = new JButton[6];

    CardLayout card1;
    CardLayout card2;

    JLabel welcomeLabel;

    LinkedListString linkedlist = new LinkedListString();
    Connection con = Main.con;
    static Color cyan = new Color(0x38b6ff);

    HomePage() throws Exception {
        this.setBounds(0, 0, 1536, 864);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(40, 15));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(false);
        JPanel mainPanel = new JPanel(new BorderLayout(40, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // NORTH PANEL
        northPanel = new JPanel(new BorderLayout(0, 20));

        welcomeLabel = new JLabel("WELCOME TO SPEED AIR BOOKING", JLabel.CENTER);
        welcomeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(getWidth(), 50));
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.LINE_AXIS));
        topBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        topBar.setBackground(cyan);

        getAccountDetails = buttonCreater("  ACCOUNT  ");
        logout = buttonCreater("  LOGOUT  ");
        getAccountDetails.addActionListener(this);
        logout.addActionListener(this);

        topBar.add(logout);
        topBar.add(Box.createHorizontalStrut(10));
        topBar.add(getAccountDetails);
        topBar.add(Box.createHorizontalStrut(10));

        northPanel.add(welcomeLabel, BorderLayout.CENTER);
        northPanel.add(topBar, BorderLayout.NORTH);

        //
        // CENTER PANEL
        mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BorderLayout(10, 0));

        centerPanel = new JPanel(new GridLayout(7, 1, 10, 10));

        JPanel suggestionTitlePanel = new JPanel(new GridLayout(2, 1));
        JLabel suggestionTitle = new JLabel("HERE ARE SOME TRENDING FLIGHTS ", JLabel.CENTER);
        suggestionTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        suggestionTitle.setForeground(Color.BLACK);
        suggestionTitlePanel.add(new JLabel());
        suggestionTitlePanel.add(suggestionTitle);
        centerPanel.add(suggestionTitlePanel);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(" BOOK ");
            buttons[i].addActionListener(this);
        }

        JPanel flight1 = flightsSuggestion("AHMEDABAD TO NEWYORK", "TYPE - INTERNATIONAL", "AMD - JFK", buttons[0]);
        linkedlist.add("AMD JFK I");
        centerPanel.add(flight1);
        JPanel flight2 = flightsSuggestion("MUMBAI TO LONDON", "TYPE - INTERNATIONAL", "BOM - YXU", buttons[1]);
        centerPanel.add(flight2);
        linkedlist.add("BOM YXU I");
        JPanel flight3 = flightsSuggestion("TORONTO TO NEWYORK", "TYPE - INTERNATIONAL", "YYZ - JFK", buttons[2]);
        centerPanel.add(flight3);
        linkedlist.add("YYZ JFK I");
        JPanel flight4 = flightsSuggestion("MUMBAI TO HYDERABAD", "TYPE - DOMESTIC", "BOM - HYD", buttons[3]);
        centerPanel.add(flight4);
        linkedlist.add("BOM HYD D");
        JPanel flight5 = flightsSuggestion("AHMEDABAD TO DELHI", "TYPE - DOMESTIC", "AMD - DEL", buttons[4]);
        centerPanel.add(flight5);
        linkedlist.add("AMD DEL D");
        JPanel flight6 = flightsSuggestion("GOA TO MUMBAI", "TYPE - DOMESTIC", "GOI - BOM", buttons[5]);
        centerPanel.add(flight6);
        linkedlist.add("GOI BOM D");

        southPanel = new JPanel(new GridLayout(2, 1));
        bookFlight = new JButton("  BOOK ANOTHER FLIGHT  ");
        bookFlight.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        bookFlight.setFocusable(false);
        bookFlight.addActionListener(this);
        bookFlight.setForeground(Color.black);
        bookFlight.setBackground(cyan);
        bookFlight.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.black));
        // southPanel.add(bookFlight);

        mainCenterPanel.add(bookFlight, BorderLayout.NORTH);
        mainCenterPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        JLabel contactUs = new JLabel("CONTACT US : speedairbooking@gmail.com", JLabel.CENTER);
        JLabel todaysDate = new JLabel("DATE : " + Main.currentDate(), JLabel.CENTER);

        infoPanel.add(todaysDate);
        infoPanel.add(contactUs);
        southPanel.add(infoPanel);

        eastPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        discountPanel1 = discountPanel("10.png",
                "GET 10% OFF-ON YOUR FIRST FLIGHT-USE PROMOCODE : firstflight");
        discountPanel2 = discountPanel("1500.png", "GET Rs1500 OFF-ON BILL ABOVE 150000-USE PROMOCODE : 1500");
        discountPanel3 = discountPanel("5.png", "GET 5% OFF-ON CARD PAYMENT-NO PROMOCODE REQUIRED");
        discountPanel4 = discountPanel("20.png", "GET 20% OFF-ON YOUR BIRTHDAY-USE PROMOCODE : BIRTHDAY");

        card1 = new CardLayout();
        ad1 = new JPanel(card1);
        ad1.add(discountPanel1, "a");
        ad1.add(discountPanel3, "b");

        card2 = new CardLayout();
        ad2 = new JPanel(card2);
        ad2.add(discountPanel2, "a");
        ad2.add(discountPanel4, "b");

        // TIMER
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                card1.next(ad1);
                card2.next(ad2);
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);

        eastPanel.add(ad1);
        eastPanel.add(ad2);

        //
        // Adding Componenets to Main Frame
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    JButton buttonCreater(String x) {
        JButton newButton = new JButton();
        newButton.setForeground(Color.WHITE);
        newButton.setPreferredSize(new Dimension(45, topBar.getHeight() - 5));
        newButton.setBackground(new Color(15, 33, 82));
        newButton.setBorder(BorderFactory.createMatteBorder(
                3, 3, 3, 3, new Color(15, 33, 82)));
        newButton.setText(x);
        newButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        return newButton;
    }

    JPanel flightsSuggestion(String main, String type, String journey, JButton button) throws SQLException {
        JPanel flight = new JPanel(new BorderLayout());
        JPanel detailsPanel = new JPanel(new GridLayout(4, 1));
        detailsPanel.setBackground(Color.white);
        JLabel detailsLabel1 = new JLabel("  " + main);
        detailsLabel1.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        JLabel detailsLabel3 = new JLabel("  " + type + " (" + journey + ")");
        type = type.substring(7);
        String source = journey.substring(0, 3);
        String destination = journey.substring(6);

        int price = 0;

        button.setFocusable(false);
        button.setBackground(cyan);

        if (type.equals("INTERNATIONAL")) {
            PreparedStatement st = con
                    .prepareStatement("select price from international_flights where source = ? and destination = ?");
            st.setString(1, source);
            st.setString(2, destination);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                price = rs.getInt(1);
            }
        } else {
            PreparedStatement st = con
                    .prepareStatement("select price from domestic_flights where source = ? and destination = ?");
            st.setString(1, source);
            st.setString(2, destination);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                price = rs.getInt(1);
            }
        }
        JLabel detailsLabel4 = new JLabel("  " + "PRICE : " + price);

        detailsPanel.add(detailsLabel1);
        detailsPanel.add(detailsLabel3);
        detailsPanel.add(detailsLabel4);

        flight.add(detailsPanel, BorderLayout.CENTER);
        flight.add(button, BorderLayout.EAST);
        flight.setBorder(BorderFactory.createLineBorder(Color.black));
        return flight;
    }

    JPanel discountPanel(String imgIcon, String text) {
        JPanel discountPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        JLabel img = new JLabel(new ImageIcon(imgIcon));
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);

        String[] t = text.split("-");
        JPanel textPanel = new JPanel(new GridLayout(6, 1));
        JLabel discountLabel1 = new JLabel(t[0], JLabel.CENTER);
        JLabel discountLabel2 = new JLabel(t[1], JLabel.CENTER);
        JLabel discountLabel3 = new JLabel(t[2], JLabel.CENTER);
        discountLabel1.setFont(f);
        discountLabel2.setFont(f);
        discountLabel3.setFont(f);

        textPanel.add(new JLabel());
        textPanel.add(discountLabel1);
        textPanel.add(discountLabel2);
        textPanel.add(new JLabel());
        textPanel.add(discountLabel3);
        textPanel.add(new JLabel());
        textPanel.setBackground(cyan);

        discountPanel.setBackground(cyan);
        discountPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, cyan));
        discountPanel.add(img);
        discountPanel.add(textPanel);
        return discountPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == e.getSource()) {
                try {
                    new BookPage1(linkedlist.get(i));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

        if (e.getSource() == bookFlight) {
            try {
                this.dispose();
                new BookPage1();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == getAccountDetails) {
            System.out.println("account");
            new AccountPage();
        }
        if (e.getSource() == logout) {
            this.dispose();
            try {
                new LoginPage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}