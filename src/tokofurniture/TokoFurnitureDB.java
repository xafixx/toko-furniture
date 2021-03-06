/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokofurniture;


import java.io.FileInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TokoFurnitureDB {
    
    private static Connection connection;
    private static ConnectionClass connect = new ConnectionClass(connection);
    
    public static boolean loginAdmin(String username, String password){
        try {
            Connection con = connect.openConnection();
         
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM admin where user=? and pass=?"
            );
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TokoFurnitureDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void insertIntoDatabase(String namaBarang, int hargaBarang, int stok,  
                                           String deskripsi, FileInputStream fileGambar) 
    {
        try {
            Connection con = connect.openConnection();
         
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO barang(nama_barang, "
                    + "harga_barang, stok, deskripsi, photo) VALUES(?,?,?,?,?)"
            );
            
            ps.setString(1, namaBarang);
            ps.setInt(2, hargaBarang);
            ps.setInt(3, stok);
            ps.setString(4, deskripsi);
            ps.setBinaryStream(5, fileGambar);
            
            if(ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Berhasil menambahkan data!!!"); 
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menambahkan data!!!");
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TokoFurnitureDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateToDatabase(int idBarang, String namaBarang, int hargaBarang, int stok,  
                                        String deskripsi) 
    {
        try {
            Connection con = connect.openConnection();
         
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE barang SET nama_barang=?, "
                    + "harga_barang=?, stok=?, deskripsi=? WHERE id_barang=?"
            );
            
            ps.setString(1, namaBarang);
            ps.setInt(2, hargaBarang);
            ps.setInt(3, stok);
            ps.setString(4, deskripsi);
            ps.setInt(5, idBarang);
            
            if(ps.executeUpdate()==1) {
                JOptionPane.showMessageDialog(null, "Berasil mengedit data!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal mengedit data");
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(TokoFurnitureDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void deleteFromDatabase(int idBarang) {
        try {
            
            Connection con = connect.openConnection();
         
            PreparedStatement ps = con.prepareStatement(
                    "DELETE from barang WHERE id_barang=?"
            );
            
            ps.setInt(1, idBarang);
            
            if(ps.executeUpdate()==1) {
                JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data");
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(TokoFurnitureDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}