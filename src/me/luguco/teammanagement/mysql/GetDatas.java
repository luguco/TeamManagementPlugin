package me.luguco.teammanagement.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDatas {

    private static PreparedStatement ps;

    public static boolean UserExisits(String uuid){
        try {
            ps = MySQLConnection.getConnection().prepareStatement("SELECT * FROM User WHERE UUID = '" + uuid + "'");
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
