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
            // Tạo một DefaultTableCellRenderer và thiết lập căn giữa
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            
            // Áp dụng renderer cho từng cột
            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
            // Căn giữa tiêu đề cột (Optional)
            ((DefaultTableCellRenderer)jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
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

        background1 = new com.component.Background();
        panelBorder1 = new com.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        hoTen = new javax.swing.JTextField();
        diaChi = new javax.swing.JTextField();
        soDT = new javax.swing.JTextField();
        tongNo = new javax.swing.JTextField();
        maKH = new javax.swing.JTextField();
        Sua = new javax.swing.JButton();
        tablePanel = new com.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(850, 695));

        panelBorder1.setBackground(new java.awt.Color(0, 88, 128));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 88, 128));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");
        panelBorder1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 300, -1));

        hoTen.setBackground(new java.awt.Color(138, 218, 254));
        hoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hoTen.setForeground(new java.awt.Color(0, 49, 64));
        hoTen.setText("Họ và tên");
        hoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        hoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hoTenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hoTenFocusLost(evt);
            }
        });

        diaChi.setBackground(new java.awt.Color(138, 218, 254));
        diaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        diaChi.setForeground(new java.awt.Color(0, 49, 64));
        diaChi.setText("Địa chỉ");
        diaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        diaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                diaChiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                diaChiFocusLost(evt);
            }
        });

        soDT.setBackground(new java.awt.Color(138, 218, 254));
        soDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        soDT.setForeground(new java.awt.Color(0, 49, 64));
        soDT.setText("Số điện thoại");
        soDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        soDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                soDTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                soDTFocusLost(evt);
            }
        });

        tongNo.setBackground(new java.awt.Color(138, 218, 254));
        tongNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tongNo.setForeground(new java.awt.Color(0, 49, 64));
        tongNo.setText("Tổng nợ");
        tongNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        tongNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tongNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tongNoFocusLost(evt);
            }
        });

        maKH.setEditable(false);
        maKH.setBackground(new java.awt.Color(138, 218, 254));
        maKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        maKH.setForeground(new java.awt.Color(0, 49, 64));
        maKH.setText("Mã khách hàng");
        maKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        maKH.setFocusable(false);

        Sua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Sua.setForeground(new java.awt.Color(0, 88, 128));
        Sua.setText("Cập nhật");
        Sua.setToolTipText("");
        Sua.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 88, 128), 3, true));
        Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaActionPerformed(evt);
            }
        });

        tablePanel.setBackground(new java.awt.Color(0, 88, 128));

        jScrollPane1.setBackground(new java.awt.Color(0, 88, 128));
        jScrollPane1.setBorder(null);

        jTable1.setBackground(new java.awt.Color(0, 88, 128));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại", "Tổng nợ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setFillsViewportHeight(true);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(40);
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionForeground(new java.awt.Color(0, 88, 128));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getCustomerFromTable(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 838, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jScrollPane1)
                    .addGap(17, 17, 17)))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(soDT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hoTen, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maKH, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tongNo, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                            .addComponent(diaChi))
                        .addGap(6, 6, 6)))
                .addGap(78, 78, 78))
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(524, Short.MAX_VALUE))
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoTen)
                    .addComponent(diaChi))
                .addGap(47, 47, 47)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soDT)
                    .addComponent(tongNo))
                .addGap(45, 45, 45)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hoTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTenFocusGained
        if("Họ và tên".equals(hoTen.getText())){
            hoTen.setText("");
        }
    }//GEN-LAST:event_hoTenFocusGained

    private void hoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTenFocusLost
        if("".equals(hoTen.getText())){
            hoTen.setText("Họ và tên");
        }
    }//GEN-LAST:event_hoTenFocusLost

    private void diaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_diaChiFocusGained
        if("Địa chỉ".equals(diaChi.getText())){
            diaChi.setText("");
        }
    }//GEN-LAST:event_diaChiFocusGained

    private void diaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_diaChiFocusLost
        if("".equals(diaChi.getText())){
            diaChi.setText("Địa chỉ");
        }
    }//GEN-LAST:event_diaChiFocusLost

    private void soDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_soDTFocusGained
        if("Số điện thoại".equals(soDT.getText())){
            soDT.setText("");
        }
    }//GEN-LAST:event_soDTFocusGained

    private void soDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_soDTFocusLost
        if("".equals(soDT.getText())){
            soDT.setText("Số điện thoại");
        }
    }//GEN-LAST:event_soDTFocusLost

    private void tongNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tongNoFocusGained
        if("Tổng nợ".equals(tongNo.getText())){
            tongNo.setText("");
        }
    }//GEN-LAST:event_tongNoFocusGained

    private void tongNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tongNoFocusLost
        if("".equals(tongNo.getText())){
            tongNo.setText("Tổng nợ");
        }
    }//GEN-LAST:event_tongNoFocusLost

    private void SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaActionPerformed
        String ht = hoTen.getText();
        String dc = diaChi.getText();
        String sdt = soDT.getText();
        String tn = tongNo.getText();
        String makh = maKH.getText();
        if(ht.equals("Họ và tên")||sdt.equals("Số điện thoại")||tn.equals("Tổng nợ")){
            JOptionPane.showMessageDialog(QLKH.this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        int result = updateCustomer(makh,ht,dc,sdt,tn);
        if(result==1){
            JOptionPane.showMessageDialog(QLKH.this, "Cập nhật khách hàng thành công");
            hoTen.setText("Họ và tên");
            diaChi.setText("Địa chỉ");
            soDT.setText("Số điện thoại");
            tongNo.setText("Tổng nợ");
            maKH.setText("Mã khách hàng");
            loadTable1();
            Sua.setVisible(false);
            return;
        }
        JOptionPane.showMessageDialog(QLKH.this, "Cập nhật khách hàng không thành công");  
    }//GEN-LAST:event_SuaActionPerformed

    private void getCustomerFromTable(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getCustomerFromTable
        int rowNo = jTable1.getSelectedRow();
        if(rowNo < 0 ) return;
        Sua.setVisible(true);
        TableModel model = jTable1.getModel();
        maKH.setText(model.getValueAt(rowNo, 0).toString());
        hoTen.setText(model.getValueAt(rowNo, 1).toString());
        if(model.getValueAt(rowNo, 2)!=null){
            diaChi.setText(model.getValueAt(rowNo, 2).toString());
        }        
        soDT.setText(model.getValueAt(rowNo, 3).toString());
        tongNo.setText(model.getValueAt(rowNo, 4).toString());
    }//GEN-LAST:event_getCustomerFromTable


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sua;
    private com.component.Background background1;
    private javax.swing.JTextField diaChi;
    private javax.swing.JTextField hoTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField maKH;
    private com.swing.PanelBorder panelBorder1;
    private javax.swing.JTextField soDT;
    private com.swing.PanelBorder tablePanel;
    private javax.swing.JTextField tongNo;
    // End of variables declaration//GEN-END:variables

    

}
