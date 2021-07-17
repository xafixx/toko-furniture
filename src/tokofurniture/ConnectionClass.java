/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokofurniture;

import java.sql.*;

public class ConnectionClass {
    private Connection openConnection;
    public static boolean isLogin;
    
    public ConnectionClass() {
    }
    
    public ConnectionClass(boolean isLogin) {
        this.isLogin = isLogin;
    }
   
    public ConnectionClass(Connection openConnection) {
        this.openConnection = openConnection;
    }
    
    public static void setIsLogin(boolean isLogin) {
        ConnectionClass.isLogin = isLogin;
    }
    
    

    public void setOpenConnection(Connection openConnection) {
        this.openConnection = openConnection;
    }
   
    public Connection openConnection(){
        if (openConnection == null) {
            
            try{
                
                String url = "jdbc:mysql://localhost:3306/tokofurnituredb";
                String user = "root";
                String pass = "";
                openConnection = DriverManager.getConnection(url, user, pass);
                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return openConnection;
    }
    
}