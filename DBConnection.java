package com.cwm;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private final String PASS_SQL="******";
    private final String USER_SQL="user";
    private final String URL_DB="jdbc:mysql://192.168.0.251:3306/cwm_devel";
    private final String HOST_SSH="192.168.0.250";
    private final int PORT_SSH=22;
    private final String USR_SSH="root";
    private final String PASS_SSH="********";
    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private Session session=null;
    private Properties config;
   private JSch jSch=new JSch();
    com.mysql.jdbc.Connection connection;

    public Session getSession() {
        return session;
    }

       public Connection getConnection() {
        return connection;
    }

    public void openSessionSSH() throws JSchException {
     session=jSch.getSession(USR_SSH,HOST_SSH, PORT_SSH);
        session.setPassword(PASS_SSH);
        config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        config.setProperty("user",USER_SQL);
        config.setProperty("password",PASS_SQL);
        config.setProperty("useSSL","false");
        config.setProperty("autoReconnect","true");
        session.setConfig(config);

        session.connect();
        session.setPortForwardingL(22,"192.168.10.251",3306);
 }
public void connectDB() throws ClassNotFoundException, SQLException {
    Class.forName(DRIVER_NAME);
     connection= (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://192.168.0.251:3306/cwm_devel",config);
}









}
