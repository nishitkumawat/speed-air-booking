import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class Ticket extends JFrame implements ActionListener {
    JButton print;
    String name;
    String phoneno;
    String email;
    String source;
    String destination;
    int price;
    String flight;
    String dateAndTime;
    String ticketIDString = "";
    String x[];

    Ticket() throws Exception {
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(false);

        name = Main.currentUser.name;
        phoneno = Main.currentUser.phoneno;
        email = Main.currentUser.email;
        source = BookPage1.source;
        destination = BookPage1.destination;
        price = BookPage1.price;
        String temp[] = BookPage1.flightDetails.split(" ");
        dateAndTime = temp[0] + " " + temp[1];
        flight = temp[2];
        System.out.println(Main.currentUser.name + Main.currentUser.phoneno + Main.currentUser.email + BookPage1.source
                + BookPage1.destination + BookPage1.price + BookPage1.flightDetails);
        System.out.println(price);

        JPanel ticket = new JPanel();
        ticket.setLayout(new GridLayout(17, 1));
        JLabel lname = new JLabel("Name : " + name);
        JLabel lphone = new JLabel("Phone No : " + phoneno);
        JLabel lemail = new JLabel("Email : " + email);
        JLabel lsource = new JLabel("Source : " + source);
        JLabel ldestination = new JLabel("Destination : " + destination);
        JLabel lprice = new JLabel("Price : " + price);
        JLabel ldateAndTime = new JLabel("Date & Time : " + dateAndTime);
        JLabel lflight = new JLabel("Flight : " + flight);
        String seats = "";
        for (int i = 0; i < BookPage1.selectedSeats.size(); i++) {
            System.out.println(BookPage1.selectedSeats.get(i));
            seats = seats + "," + BookPage1.selectedSeats.get(i);
        }
        seats = seats.substring(1, seats.length());
        JLabel lseat = new JLabel("Seats : " + seats);
        x = dateAndTime.split(" ");

        // Inserting booked seats in DATABASE
        PreparedStatement ps = Main.con.prepareStatement(
                "INSERT INTO user_flight_details(email_id, source, destination, price, date) values(?,?,?,?,?)");
        ps.setString(1, email);
        String[] spliter = source.split(" ");
        ps.setString(2, spliter[0]);
        String[] spliter2 = destination.split(" ");
        ps.setString(3, spliter2[0]);
        ps.setInt(4, price);
        ps.setString(5, x[0]);
        ps.executeUpdate();

        Statement st = Main.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT id from user_flight_details order by id desc limit 1");

        while (rs.next()) {
            ticketIDString = rs.getString(1);
        }
        JLabel ticketID = new JLabel("Ticket ID : " + ticketIDString);

        print = new JButton("PRINT");
        print.setFocusable(false);
        print.setSize(100, 20);
        print.setBackground(Color.white);
        print.setForeground(Color.BLUE);
        print.setBorder(BorderFactory.createMatteBorder(
                0, 0, 0, 0, Color.white));
        print.addActionListener(this);

        ticket.add(ticketID);
        ticket.add(lname);
        ticket.add(lphone);
        ticket.add(lemail);
        ticket.add(lsource);
        ticket.add(ldestination);
        ticket.add(lprice);
        ticket.add(ldateAndTime);
        ticket.add(lflight);
        ticket.add(lseat);
        ticket.setVisible(true);
        ticket.add(print);
        this.add(ticket, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == print) {
            try {
                System.out.println("print");
                BufferedWriter fw = new BufferedWriter(
                        new FileWriter("D:\\connect_air\\Tickets\\" + email + ticketIDString + ".txt"));
                fw.write("Ticket ID :" + ticketIDString);
                fw.newLine();
                fw.write("Name : " + name);
                fw.newLine();
                fw.write("Phone No : " + phoneno);
                fw.newLine();
                fw.write("Email : " + email);
                fw.newLine();
                fw.write("Source : " + source);
                fw.newLine();
                fw.write("Destination : " + destination);
                fw.newLine();
                fw.write("Price : " + price);
                fw.newLine();
                fw.write("Date & Time : " + dateAndTime);
                fw.newLine();
                fw.write("Flight : " + flight);

                System.out.println("Name : " + name + "\n" + //
                        "Phone No : " + phoneno + "\n" + //
                        "Email : " + email + "\n" + //
                        "Source : " + source + "\n" + //
                        "Destination : " + destination + "\n" + //
                        "Price : " + price + "\nFlight : " + flight);
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

}
