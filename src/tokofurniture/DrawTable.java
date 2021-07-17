/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokofurniture;

import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP KOMPUTER
 */
public class DrawTable{
    
    private ConnectionClass connection = new ConnectionClass(); 
    private JTable table;
    private JLabel labelContaier;
    private JTextField namaBarangTextField;
    private JTextField hargaBarangTextField;
    private JTextField stokTextField;
    private JTextArea deskripsiTextArea;
    
    /*Constructor*/
    public DrawTable() {
    }
    
    public DrawTable(JTable table) {
        this.table = table;
    }
    
    public DrawTable(JTable table, JLabel labelContainer, JTextField namaBarangTextField, 
            JTextField hargaBarangTextField, JTextField stokTextField, 
            JTextArea deskripsiTextArea) {
        this.table = table;
        this.labelContaier = labelContainer;
        this.namaBarangTextField = namaBarangTextField;
        this.hargaBarangTextField = hargaBarangTextField;
        this.stokTextField = stokTextField;
        this.deskripsiTextArea = deskripsiTextArea;
    }

    public void setDeskripsiTextArea(JTextArea deskripsiTextArea) {
        this.deskripsiTextArea = deskripsiTextArea;
    }

    public JTextArea getDeskripsiTextArea() {
        return deskripsiTextArea;
    }

    public void setLabelContaier(JLabel labelContaier) {
        this.labelContaier = labelContaier;
    }

    public JLabel getLabelContaier() {
        return labelContaier;
    }
            
    
    public void tableClickedHome() {
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int myIndex = table.getSelectedRow();
        
        try{
            
            deskripsiTextArea.setText(model.getValueAt(myIndex, 3).toString());
            if(table.getValueAt(myIndex, 4) != null) {
                ImageIcon image1 = (ImageIcon)table.getValueAt(myIndex, 4);
                Image image2 = image1.getImage().getScaledInstance(labelContaier.getWidth(), 
                               labelContaier.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image3 = new ImageIcon(image2);
                labelContaier.setIcon(image3);
            } else{
                labelContaier.setText("NO IMAGE");
            }
        
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Tidak ada data");
        }
    }
    
    public void tableClickedAdmin() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int myIndex = table.getSelectedRow();
        
        try{
            labelContaier.setText(model.getValueAt(myIndex, 0).toString());
            namaBarangTextField.setText(model.getValueAt(myIndex, 1).toString());
            hargaBarangTextField.setText(model.getValueAt(myIndex, 2).toString());
            stokTextField.setText(model.getValueAt(myIndex, 3).toString());
            deskripsiTextArea.setText(model.getValueAt(myIndex, 4).toString());
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Tidak ada data");
        }
    }
    
    // method untuk menampilkan data ke tabel admin
    public void showDataToTableHome(JTable table)  {
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        String title[] = {"NAMA BARANG", "HARGA BARANG", 
                          "STOK", "DESKRIPSI", "GAMBAR"};
        try {
            
            Connection con = connection.openConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT nama_barang, harga_barang, "
                  + "stok, deskripsi, photo from barang"
            );
            
            // untuk menampilkan gambar di tabel
            model = new DefaultTableModel(null, title){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 4) {
                        return ImageIcon.class;
                    }      
                    return Object.class;
                }
            };
            
            Object[] o = new Object[6];
            while(rs.next()){
                o[0] = rs.getString("nama_barang");
                o[1] = rs.getInt("harga_barang");
                o[2] = rs.getInt("stok");
                o[3] = rs.getString("deskripsi");
                o[4] = new ImageIcon(rs.getBytes("photo"));
                model.addRow(o);
            }
            
            table.setRowHeight(120);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.setModel(model);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showDataToTableAdmin(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        String title[] = {"ID BARANG", "NAMA BARANG", "HARGA BARANG", 
                          "STOK", "DESKRIPSI", "GAMBAR"
        };
        
        try {
         
            Connection con = connection.openConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from barang");
            
            model = new DefaultTableModel(null, title){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 5) {
                        return ImageIcon.class;
                    }
                    return Object.class;
                }
            };
            
            Object[] o = new Object[6];
            while(rs.next()){
                o[0] = rs.getInt("id_barang");
                o[1] = rs.getString("nama_barang");
                o[2] = rs.getInt("harga_barang");
                o[3] = rs.getInt("stok");
                o[4] = rs.getString("deskripsi");
                o[5] = new ImageIcon(rs.getBytes("photo"));
                model.addRow(o);
            }
            
            table.setRowHeight(120);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.setModel(model);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
