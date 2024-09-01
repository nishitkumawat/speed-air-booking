import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrentUser {
    String email;
    String name;
    String phoneno;
    String DOB;
    String password;
    Connection con = Main.con;

    public CurrentUser(String email, String name, String phoneno) throws SQLException {
        this.email = email;
        this.name = name;
        this.phoneno = phoneno;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select dob,password from user_info where email_id = '" + email + "'");
        while (rs.next()) {
            DOB = rs.getString(1);
            password = rs.getString(2);
        }
    }

}
