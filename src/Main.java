import java.sql.*;

public class Main {
    static CurrentUser currentUser;
    static Connection con;

    public static void main(String[] args) throws Exception {
        intialization();
        new LoginPage();
    }

    static void intialization() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nishit", "root", "");

        currentUser = new CurrentUser("", "", "");
        new CityCodes();
    }

    static String currentDate() throws SQLException {
        CallableStatement cs = con.prepareCall("{call todaysDate(?)}");
        cs.registerOutParameter(1, java.sql.Types.DATE);
        cs.execute();
        return cs.getString(1);
    }
}