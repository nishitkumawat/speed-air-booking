
import java.sql.*;
import java.util.HashMap;

public class CityCodes {
    static HashMap<String, String> citycodes = new HashMap<>();
    Connection con = Main.con;

    CityCodes() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from city_code");
        while (rs.next()) {
            citycodes.put(rs.getString(1), rs.getString(2));
        }
    }
}
