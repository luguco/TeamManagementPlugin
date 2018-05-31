package me.luguco.teammanagement.mysql;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetDatas {

    private static PreparedStatement ps;

    public static void Register(String name, String uuid, String passwordhash) {

        StringBuilder result = new StringBuilder();
        URL url = null;
        try {
            url = new URL("https://youmara.com/pwenc.php?password=" + passwordhash);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String pw_hash = result.toString();

        try {
            ps = MySQLConnection.getConnection().prepareStatement("INSERT INTO `User` ( `Username`, `UUID`, `Password_Hash`) VALUES ('" + name + "', '" + uuid+ "', '"+ pw_hash + "')");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
