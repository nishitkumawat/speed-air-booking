import java.sql.*;
import java.util.*;
import javax.swing.*;

import data_structure.StackString;

import java.awt.*;
import java.awt.event.*;

public class BookPage1 extends JFrame implements ActionListener {

    Connection con;

    ArrayList<ArrayList<JButton>> layout = new ArrayList<>();

    ImageIcon redSeat = new ImageIcon("c2.png");
    ImageIcon blueSeat = new ImageIcon("c3.png");
    ImageIcon yellowSeat = new ImageIcon("c1.png");

    JPanel panel;
    JPanel gridLayout;
    JPanel gridLayout2;

    JLabel label;
    JLabel availableFlights;

    JRadioButton international;
    JRadioButton domestic;
    JRadioButton flight1;
    JRadioButton flight2;

    ButtonGroup group;
    JButton go;
    JButton getTickets;
    JButton selectSeats;
    JButton home;
    JButton[] seat1 = new JButton[24];
    JButton[] seat2 = new JButton[24];

    JComboBox<String> start;
    JComboBox<String> end;
    JComboBox<String> date;

    static String source = "";
    static String destination = "";
    static int price = 0;
    static StackString selectedSeats = new StackString();
    static String flightDetails = "";

    String selectedDate = "";
    int startSize = 0;
    int endSize = 0;

    BookPage1() throws Exception {
        con = Main.con;
        this.setBounds(0, 0, 1536, 864);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(false);

        panel = new JPanel();
        label = new JLabel();
        ImageIcon img = new ImageIcon("bg4.png");
        label.setIcon(img);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        label.setVisible(true);

        seats();

        home = new JButton("<< HOME");
        home.setFocusable(false);
        home.setBounds(120, 70, 100, 20);
        home.setBackground(new Color(0x084cac));
        home.setForeground(Color.WHITE);
        home.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        home.addActionListener(this);

        JLabel welcomeText = new JLabel();
        welcomeText.setBounds(235, 124, 500, 54);
        welcomeText.setText("SELECT JOURNEY DETAILS");
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        welcomeText.setForeground(Color.white);

        domestic = new JRadioButton("DOMESTIC");
        international = new JRadioButton("INTERNATIONAL");
        domestic.setSelected(false);
        international.setSelected(false);
        domestic.setBounds(182, 190, 200, 25);
        international.setBounds(413, 190, 242, 25);
        domestic.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        international.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        domestic.setForeground(Color.white);
        international.setForeground(Color.white);
        domestic.setOpaque(false);
        international.setOpaque(false);
        domestic.setFocusable(false);
        international.setFocusable(false);

        group = new ButtonGroup();
        group.add(international);
        group.add(domestic);
        domestic.addActionListener(this);
        international.addActionListener(this);

        start = new JComboBox<>();
        start.setBounds(162, 230, 500, 54);
        start.setOpaque(false);
        start.setBorder(BorderFactory.createTitledBorder("FROM"));
        start.setFocusable(false);
        start.addActionListener(this);

        end = new JComboBox<>();
        end.setBounds(162, 290, 500, 54);
        end.setOpaque(false);
        end.setBorder(BorderFactory.createTitledBorder("TO"));
        end.setFocusable(false);
        end.addActionListener(this);

        String[] d = addingdates();
        date = new JComboBox<>(d);
        date.setBounds(162, 350, 500, 54);
        date.setOpaque(false);
        date.setBorder(BorderFactory.createTitledBorder("DATE"));
        date.setFocusable(false);
        date.addActionListener(this);

        go = new JButton();
        go.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        go.setFocusable(false);
        go.setBounds(340, 410, 140, 25);
        go.setForeground(Color.WHITE);
        go.setBackground(Color.BLACK);
        go.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        go.setText("SEARCH");
        go.addActionListener(this);

        availableFlights = new JLabel();
        availableFlights.setBounds(182, 430, 500, 54);
        availableFlights.setText("AVAILABLE FLIGHTS");
        availableFlights.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        availableFlights.setForeground(Color.white);
        availableFlights.setVisible(false);

        flight1 = new JRadioButton();
        flight2 = new JRadioButton();
        flight1.setBounds(182, 470, 400, 25);
        flight2.setBounds(182, 490, 400, 25);
        flight1.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        flight2.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        flight1.setForeground(Color.white);
        flight2.setForeground(Color.white);
        flight1.setOpaque(false);
        flight2.setOpaque(false);
        flight1.setFocusable(false);
        flight2.setFocusable(false);
        flight1.setVisible(false);
        flight2.setVisible(false);

        ButtonGroup group2 = new ButtonGroup();
        group2.add(flight2);
        group2.add(flight1);
        flight1.addActionListener(this);
        flight2.addActionListener(this);

        selectSeats = new JButton();
        selectSeats.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        selectSeats.setFocusable(false);
        selectSeats.setBounds(340, 520, 140, 25);
        selectSeats.setForeground(Color.WHITE);
        selectSeats.setBackground(Color.BLACK);
        selectSeats.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        selectSeats.setText("SELECT SEATS");
        selectSeats.setVisible(false);
        selectSeats.addActionListener(this);

        getTickets = new JButton("GET TICKETS");
        getTickets.setFont(new Font(Font.SANS_SERIF, Font.TYPE1_FONT, 16));
        getTickets.setFocusable(false);
        getTickets.setBounds(340, 560, 140, 25);
        getTickets.setForeground(Color.WHITE);
        getTickets.setBackground(new Color(0x004aad));
        getTickets.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        getTickets.setVisible(false);
        getTickets.addActionListener(this);

        gridLayout.setVisible(false);
        gridLayout2.setVisible(false);
        this.add(home);
        this.add(getTickets);
        this.add(selectSeats);
        this.add(availableFlights);
        this.add(flight1);
        this.add(flight2);
        this.add(date);
        this.add(end);
        this.add(start);
        this.add(international);
        this.add(domestic);
        this.add(go);
        this.add(welcomeText);
        this.add(gridLayout);
        this.add(gridLayout2);
        panel.setVisible(true);
        this.add(panel);
        this.setVisible(true);
    }

    // From HomePage
    BookPage1(String alreadySelected) throws Exception {
        this();
        String[] s = alreadySelected.split(" ");
        String source = s[0];
        String destination = s[1];
        String start = source + " - " + CityCodes.citycodes.get(source);
        String end = destination + " - " + CityCodes.citycodes.get(destination);

        if (s[2].equals("D")) {

            group.setSelected(domestic.getModel(), true);

            startDomestic();
            for (int i = 0; i < startSize; i++) {
                if (this.start.getItemAt(i).equals(start)) {
                    this.start.setSelectedIndex(i);
                    break;
                }
            }
            endDomestic();

            for (int i = 0; i < endSize; i++) {
                System.out.println(i);
                if (this.end.getItemAt(i).equals(end)) {
                    System.out.println(i);
                    this.end.setSelectedIndex(i);
                    break;
                }
            }
        } else if (s[2].equals("I")) {
            group.setSelected(international.getModel(), true);
            startInternational();
            for (int i = 0; i < startSize; i++) {
                if (this.start.getItemAt(i).equals(start)) {
                    this.start.setSelectedIndex(i);
                    break;
                }
            }
            endInternational();
            for (int i = 0; i < endSize; i++) {
                System.out.println(i);
                System.out.println(this.end.getItemAt(i));
                if (this.end.getItemAt(i).equals(end)) {
                    System.out.println(i);
                    this.end.setSelectedIndex(i);
                    break;
                }
            }
        }

    }

    int type = 0;
    boolean flag = false;

    @Override
    public void actionPerformed(ActionEvent e) {

        // SEATS SELECTION
        for (int i = 0; i < 24; i++) {
            if (seat1[i] == e.getSource()) {
                if (seat1[i].getIcon() == redSeat) {
                    JOptionPane.showMessageDialog(new JFrame(), "Seat Already Booked");
                } else if (seat1[i].getIcon() == yellowSeat) {
                    seat1[i].setIcon(blueSeat);
                } else {
                    seat1[i].setIcon(yellowSeat);
                }
            }
            if (seat2[i] == e.getSource()) {
                if (seat2[i].getIcon() == redSeat) {
                    JOptionPane.showMessageDialog(new JFrame(), "Seat Already Booked");
                } else if (seat2[i].getIcon() == yellowSeat) {
                    seat2[i].setIcon(blueSeat);
                } else {
                    seat2[i].setIcon(yellowSeat);
                }
            }
        }

        // Home Button
        if (e.getSource() == home) {
            try {
                new HomePage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            this.dispose();
        }

        // DOMESTIC & INTERNATIONAL
        if (e.getSource() == domestic) {
            startDomestic();
        } else if (e.getSource() == international) {
            startInternational();
        }

        // SELECTION OF DESTINATION
        if (type == 1 && e.getSource() == start) {
            endDomestic();
        }
        if (type == 2 && e.getSource() == start) {
            endInternational();
        }

        if (e.getSource() == end) {
        }

        if (e.getSource() == go) {
            assignSourceDestination();
            try {
                price = priceCalculater(source.substring(0, 3), destination.substring(0, 3));
            } catch (SQLException e1) {
            }
            if (!source.equals("") && !destination.equals("")) {

                selectedDate = date.getSelectedItem().toString();

                if (type == 1) {
                    try {
                        PreparedStatement ps = con.prepareStatement(
                                "select * from domestic_aircraft_data a inner join domestic_flights d on d.id = a.id where d.source = ? and d.destination = ?");
                        ps.setString(1, source.substring(0, 3));
                        ps.setString(2, destination.substring(0, 3));
                        ResultSet rs = ps.executeQuery();
                        rs.next();
                        flight1.setText(
                                selectedDate + " " + rs.getString(4) + " " + rs.getString(3) + "       Rs." + price);
                        rs.next();
                        flight2.setText(
                                selectedDate + " " + rs.getString(4) + " " + rs.getString(3) + "       Rs." + price);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if (type == 2) {
                    try {
                        PreparedStatement ps = con.prepareStatement(
                                "select * from international_aircraft_data a inner join international_flights d on d.id = a.id where d.source = ? and d.destination = ?");
                        ps.setString(1, source.substring(0, 3));
                        ps.setString(2, destination.substring(0, 3));
                        ResultSet rs = ps.executeQuery();
                        rs.next();
                        flight1.setText(
                                selectedDate + " " + rs.getString(4) + " " + rs.getString(3) + "       Rs." + price);
                        rs.next();
                        flight2.setText(
                                selectedDate + " " + rs.getString(4) + " " + rs.getString(3) + "       Rs." + price);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                flight1.setVisible(true);
                flight2.setVisible(true);
                availableFlights.setVisible(true);
                selectSeats.setVisible(true);

            }
        }

        if (e.getSource() == flight1) {
            flightDetails = flight1.getText();
            flag = true;
        } else if (e.getSource() == flight2) {
            flightDetails = flight2.getText();
            flag = true;
        }

        if (e.getSource() == selectSeats && flag) {
            try {
                alreadyBookedSeats();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            gridLayout.setVisible(true);
            gridLayout2.setVisible(true);
            getTickets.setVisible(true);

        }

        if (e.getSource() == getTickets) {
            seatSelector();
        }
    }

    String[] addingdates() throws SQLException {
        String[] re = new String[10];
        if (date != null) {
            date.removeAll();
        }
        CallableStatement cs = con.prepareCall("{call nextDate(?,?)}");
        String todaysDate = Main.currentDate();
        re[0] = todaysDate;
        for (int i = 1; i <= 9; i++) {
            cs.setString(1, todaysDate);
            cs.registerOutParameter(2, java.sql.Types.DATE);
            cs.execute();
            re[i] = cs.getString(2);
            todaysDate = cs.getString(2);
        }
        return re;

    }

    String[] removeDuplicates(ArrayList<String> x) {
        ArrayList<String> al = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < x.size(); i++) {
            if (!al.contains(x.get(i))) {
                al.add(x.get(i));
                size++;
            }
        }
        String[] ans = new String[size];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = al.get(i);
        }
        return ans;
    }

    void alreadyBookedSeats() throws SQLException {
        String alreadyBookedSeats = "";
        if (type == 1) {

            PreparedStatement ps = con.prepareStatement(
                    "select booked_seats from domestic_aircraft_data where aircraft_name = ? and dep_time = ?");
            String[] spliter = flightDetails.split(" ");
            ps.setString(1, spliter[2]);
            ps.setString(2, spliter[1]);
            ResultSet rs = ps.executeQuery();
            rs.next();
            alreadyBookedSeats = rs.getString(1);

        } else if (type == 2) {

            PreparedStatement ps = con.prepareStatement(
                    "select booked_seats from international_aircraft_data where aircraft_name = ? and dep_time = ?");
            String[] spliter = flightDetails.split(" ");
            ps.setString(1, spliter[2]);
            ps.setString(2, spliter[1]);
            ResultSet rs = ps.executeQuery();
            rs.next();
            alreadyBookedSeats = rs.getString(1);

        }
        System.out.println(" sfe" + alreadyBookedSeats);
        String[] spliter = alreadyBookedSeats.split(",");
        for (int i = 0; i < spliter.length; i++) {
            if (spliter[i].charAt(0) >= 'A' && spliter[i].charAt(0) <= 'C') {
                int x1 = (spliter[i].charAt(0) - 64);
                int x2 = spliter[i].charAt(1) - 48;
                int x3 = ((x2 - 1) * 3) + (x1 - 1);
                seat1[x3].setIcon(redSeat);
            } else if (spliter[i].charAt(0) >= 'D' && spliter[i].charAt(0) <= 'F') {
                int x1 = (spliter[i].charAt(0) - 67);
                int x2 = spliter[i].charAt(1) - 48;
                int x3 = ((x2 - 1) * 3) + (x1 - 1);
                seat2[x3].setIcon(redSeat);
            }
        }
    }

    void seats() {
        gridLayout = new JPanel();
        GridLayout gd = new GridLayout(8, 3, 6, 10);
        gridLayout.setBackground(Color.white);
        gridLayout.setLayout(gd);
        gridLayout.setBounds(900, 109, 233, 645);

        for (int i = 0; i < 24; i++) {

            JButton seat = new JButton();
            seat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            seat.setFocusable(false);
            seat.setSize(40, 40);
            seat.setIcon(blueSeat);
            seat.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
            seat.addActionListener(this);
            seat.setOpaque(false);
            seat.setContentAreaFilled(false);
            gridLayout.add(seat);
            seat1[i] = seat;
        }

        gridLayout2 = new JPanel();
        GridLayout gd2 = new GridLayout(8, 3, 6, 10);
        gridLayout2.setBackground(Color.white);
        gridLayout2.setLayout(gd2);
        gridLayout2.setBounds(1205, 109, 233, 645);

        for (int i = 0; i < 24; i++) {

            JButton seat = new JButton();
            seat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            seat.setFocusable(false);
            seat.setSize(35, 35);
            seat.setIcon(blueSeat);
            seat.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
            seat.addActionListener(this);
            seat.setOpaque(false);
            seat.setContentAreaFilled(false);
            gridLayout2.add(seat);
            seat2[i] = seat;
        }

    }

    void startDomestic() {
        try {
            start.removeAllItems();
            type = 1;
            PreparedStatement ps = con.prepareStatement("select source from domestic_flights");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> al = new ArrayList<>();
            while (rs.next()) {

                al.add(rs.getString(1));
            }
            String[] temp = removeDuplicates(al);
            for (int i = 0; i < temp.length; i++) {
                Statement st = con.createStatement();
                ResultSet rs2 = st.executeQuery("select * from city_code where code = '" + temp[i] + "'");
                rs2.next();
                startSize++;
                start.addItem(temp[i] + " - " + rs2.getString(2));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    void startInternational() {
        try {
            start.removeAllItems();
            type = 2;
            PreparedStatement ps = con.prepareStatement("select source from international_flights");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> al = new ArrayList<>();
            while (rs.next()) {
                al.add(rs.getString(1));
            }
            String[] temp = removeDuplicates(al);
            for (int i = 0; i < temp.length; i++) {
                Statement st = con.createStatement();
                ResultSet rs2 = st.executeQuery("select * from city_code where code = '" + temp[i] + "'");
                rs2.next();
                startSize++;
                start.addItem(temp[i] + " - " + rs2.getString(2));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    void endDomestic() {
        try {
            end.removeAllItems();
            endSize = 0;
            String x = start.getSelectedItem().toString().substring(0, 3);
            System.out.println(x);
            PreparedStatement ps2 = con
                    .prepareStatement("select destination from domestic_flights where source = ?");
            ps2.setString(1, x);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                Statement st = con.createStatement();
                ResultSet rs3 = st.executeQuery("select * from city_code where code = '" + rs2.getString(1) + "'");
                rs3.next();
                endSize++;
                end.addItem(rs2.getString(1) + " - " + rs3.getString(2));
            }
        } catch (Exception a) {
        }
    }

    void endInternational() {
        try {
            end.removeAllItems();
            endSize = 0;
            String x = start.getSelectedItem().toString().substring(0, 3);

            PreparedStatement ps2 = con
                    .prepareStatement("select destination from international_flights where source = ?");
            ps2.setString(1, x);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                Statement st = con.createStatement();
                ResultSet rs3 = st.executeQuery("select * from city_code where code = '" + rs2.getString(1) + "'");
                rs3.next();
                end.addItem(rs2.getString(1) + " - " + rs3.getString(2));
                endSize++;
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    void assignSourceDestination() {
        if (this.end.getSelectedItem() != null && this.start.getSelectedItem() != null) {
            source = this.start.getSelectedItem().toString();
            destination = this.end.getSelectedItem().toString();
        }
    }

    void seatSelector() {
        selectedSeats.clear();
        for (int i = 0; i < 24; i++) {
            if (seat2[i].getIcon() == yellowSeat) {
                int x = 68 + ((i) % 3);
                int y = (i / 3) + 1;
                selectedSeats.push("" + (char) x + y);
            }
            if (seat1[i].getIcon() == yellowSeat) {
                int x = 65 + ((i) % 3);
                int y = (i / 3) + 1;
                selectedSeats.push("" + (char) x + y);
            }
        }
        System.out.println(selectedSeats);
        if (!selectedSeats.isEmpty()) {
            price = price * selectedSeats.size();
            System.out.println("====================" + price);
            System.out.println("DONE");
            try {
                addingBookedSeatstoDB();
                new Payment(price);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "SELECT ATLEAST 1 SEAT");
        }
    }

    int priceCalculater(String source, String destination) throws SQLException {
        int amt = -1;
        PreparedStatement ps = con
                .prepareStatement("select price from domestic_flights where source = ? and destination = ?");
        ps.setString(1, source);
        ps.setString(2, destination);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            amt = rs.getInt(1);
        } else {
            PreparedStatement ps2 = con
                    .prepareStatement("select price from international_flights where source = ? and destination = ?");
            ps2.setString(1, source);
            ps2.setString(2, destination);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                amt = rs2.getInt(1);
            }
        }
        System.out.println("==================================" + amt);
        return amt;
    }

    void addingBookedSeatstoDB() throws SQLException {
        if (type == 1) {

            PreparedStatement ps = con.prepareStatement(
                    "select booked_seats from domestic_aircraft_data where aircraft_name = ? and dep_time = ?");
            String[] spliter = flightDetails.split(" ");
            ps.setString(1, spliter[2]);
            ps.setString(2, spliter[1]);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String booked_seats = rs.getString(1);
            for (int i = 0; i < selectedSeats.size(); i++) {
                booked_seats = booked_seats + selectedSeats.get(i) + ",";
            }
            PreparedStatement ps2 = con.prepareStatement(
                    "update domestic_aircraft_data set booked_seats = ?  where aircraft_name = ? and dep_time = ?");
            ps2.setString(1, booked_seats);
            ps2.setString(2, spliter[2]);
            ps2.setString(3, spliter[1]);
            ps2.executeUpdate();

        } else if (type == 2) {

            PreparedStatement ps = con.prepareStatement(
                    "select booked_seats from international_aircraft_data where aircraft_name = ? and dep_time = ?");
            String[] spliter = flightDetails.split(" ");
            ps.setString(1, spliter[2]);
            ps.setString(2, spliter[1]);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String booked_seats = rs.getString(1);
            for (int i = 0; i < selectedSeats.size(); i++) {
                booked_seats = booked_seats + selectedSeats.get(i) + ",";
            }

            PreparedStatement ps2 = con.prepareStatement(
                    "update international_aircraft_data set booked_seats = ?  where aircraft_name = ? and dep_time = ?");
            ps2.setString(1, booked_seats);
            ps2.setString(2, spliter[2]);
            ps2.setString(3, spliter[1]);
            ps2.executeUpdate();
        }
    }
}
