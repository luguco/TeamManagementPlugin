package me.luguco.teammanagement.mysql;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by luguco on 20.06.2017.
 */
public class MySQLConnection {

    private static Connection con;
    private static String prefix;

    public static void connect(String host, int port, String database, String username, String password, String prefix) {
        try {
            MySQLConnection.prefix = prefix;
        }catch (Exception e){
            e.printStackTrace();
            return;

        }
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                Bukkit.getConsoleSender().sendMessage(prefix + "§aMySQL connected!");
            } catch (Exception e2) {
                con = null;
                e2.printStackTrace();
            }
        }
    }

    public static void disconnect() {

        if (isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(prefix + "§aMySQL disconnected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static boolean isConnected() {
        return (con == null ? false : true);
    }

    static Connection getConnection() {
        return con;
    }

}
