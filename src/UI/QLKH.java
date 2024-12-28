/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import static UI.DatabaseConnect.getJDBCConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class QLKH extends javax.swing.JPanel {
    Connection conn = getJDBCConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    /**
     * Creates new form QLKH
     */
    public QLKH() {
        initComponents();
        setTable1Header();
        loadTable1();
        //Them.setVisible(true);
        Sua.setVisible(false);
    }
    private void loadTable1(){
        // create jTable1 columns
        DefaultTableModel model = new DefaultTableModel(){
            //không cho chỉnh sửa trong bảng
            @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        };
       
        String tieude[] = new String[]{ "Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại", "Tổng nợ"};
        model.setColumnIdentifiers(tieude);
        String sql = "SELECT * FROM khachhang";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            DecimalFormat df = new DecimalFormat("#.##");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("MAKH"),rs.getString("TENKH"), rs.getString("DIACHI"), rs.getString("SODT"),  df.format(rs.getFloat("TONGNO"))});
            }
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(QLKH.class.getName()).log(Level.SEVERE, null, ex);
        }      
           
    }
    private void setTable1Header(){
        jTable1.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            l.setBackground(new Color(0,88,128));
            l.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            l.setHorizontalAlignment(CENTER);
            l.setFont(new Font("Segoe UI",Font.BOLD, 16));            
            return l;
        }
        });
    }
    private int addCustomer(String ht, String dc, String sdt, String tn) {
        String sql = "INSERT INTO khachhang (MAKH,TENKH,DIACHI,SODT,TONGNO) VALUES (?,?,?,?,?)";
        String makh = "KH".concat(String.valueOf(jTable1.getRowCount()+1)) ;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,makh);
            ps.setString(2,ht);
            ps.setString(3,dc);
            ps.setString(4,sdt);
            ps.setFloat(5,Float.parseFloat(tn)); 
            ps.executeUpdate();
            loadTable1();
            return 1;
        } catch (SQLException ex) {
            if(ex.getMessage().contains("SODT")){
                JOptionPane.showMessageDialog(QLKH.this, "Số điện thoại không hợp lệ");
            }
        }catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(QLKH.this, "Tổng nợ không hợp lệ");
        }
        return 0;
    }
    private int deleteCustomer(String makh) {
        String sql = "DELETE FROM khachhang WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,makh);
            ps.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            if(ex.getMessage().contains("SODT")){
                JOptionPane.showMessageDialog(QLKH.this, "Số điện thoại không hợp lệ");
            }
        }catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(QLKH.this, "Tổng nợ không hợp lệ");
        }
        return 0;
    }
    private int updateCustomer(String makh, String ht, String dc, String sdt, String tn) {
        String sql = "UPDATE khachhang SET TENKH = ?, DIACHI = ?, SODT=?, TONGNO=? WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,ht);
            ps.setString(2,dc);
            ps.setString(3,sdt);
            ps.setFloat(4,Float.parseFloat(tn)); 
            ps.setString(5,makh);
            ps.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            if(ex.getMessage().contains("SODT")){
                JOptionPane.showMessageDialog(QLKH.this, "Số điện thoại không hợp lệ");
            }
        }catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(QLKH.this, "Tổng nợ không hợp lệ");
        }
        return 0;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(850, 695));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    

}
