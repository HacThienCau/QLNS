/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import javax.swing.JTextField;
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
public class ThuTien extends javax.swing.JPanel {
    Connection conn = getJDBCConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    private String selectedMaPhieuThu = null;
    private double selectedSoTienThu = 0.0;
    /**
     * Creates new form ThuTien
     */
    public ThuTien() {
        initComponents();
        loadComboBox();
           // Thiết lập trạng thái ban đầu cho các nút
                   // Thiết lập trạng thái ban đầu cho các nút
        XacNhan.setVisible(true);  // Hiển thị nút Xác Nhận ban đầu
        Update.setVisible(false); // Ẩn nút OK ban đầu
        Xoa.setVisible(false);
        // Đảm bảo rằng không có hàng nào được chọn khi khởi tạo bảng
        jTable1.clearSelection();
         // Lấy component JTextField của JDateChooser
        JTextField dateTextField = (JTextField) jDateChooser1.getDateEditor().getUiComponent();
        setTable1Header();
        loadTable1();
        // Thiết lập màu nền cho JTextField
        dateTextField.setBackground(new java.awt.Color(98, 204, 254));
        JTextField dateField = (JTextField) jDateChooser1.getDateEditor().getUiComponent();
        // Đặt placeholder
        String placeholder = "Ngày thu tiền";
        dateField.setText(placeholder);
        dateField.setForeground(new java.awt.Color(0,49,64));
        // Thêm sự kiện Focus để xử lý khi focus vào hoặc ra khỏi trường nhập liệu
        dateField.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (dateField.getText().equals(placeholder)) {
                dateField.setText("");
                dateField.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (dateField.getText().isEmpty()) {
                dateField.setText(placeholder);
                dateField.setForeground(Color.GRAY);
            }
        }
    });

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

      String tieude[] = new String[]{ "Mã phiếu thu","Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại", "Ngày thu", "Tổng tiền thu"};
      model.setColumnIdentifiers(tieude);
       String sql = "SELECT pt.MAPHIEUTHU, kh.MAKH, kh.TENKH, kh.DIACHI, kh.SODT, pt.NGAYTHU, pt.SOTIEN " +
               "FROM phieuthutien pt " +
               "JOIN khachhang kh ON pt.MAKH = kh.MAKH";
      try {
          ps = conn.prepareStatement(sql);
          rs = ps.executeQuery();
          DecimalFormat df = new DecimalFormat("#.##");
           while(rs.next()){
          model.addRow(new Object[]{
              rs.getString("MAPHIEUTHU"),
              rs.getString("MAKH"),
              rs.getString("TENKH"),
              rs.getString("DIACHI"),
              rs.getString("SODT"),
              rs.getDate("NGAYTHU"),
              df.format(rs.getDouble("SOTIEN"))
          });
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
          jTable1.clearSelection();
      } catch (SQLException ex) {
          Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          try {
              if(rs != null) rs.close();
              if(ps != null) ps.close();
          } catch (SQLException ex) {
              Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
          }
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
    
    private void resetInputFields() {
    hoTen.setText("Họ và tên");
    hoTen.setForeground(Color.GRAY);
    maKH.setForeground(Color.GRAY);
    maKH.setSelectedItem("Chọn mã khách hàng");
    jDateChooser1.setDate(null);
    soTienThu.setText("Số tiền thu");
    soTienThu.setForeground(Color.GRAY);
    }
    private void loadComboBox(){
        maKH.removeAllItems();
        String sql = "SELECT MAKH FROM KHACHHANG";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            maKH.addItem("Chọn mã khách hàng");
            while (rs.next()){
               maKH.addItem(rs.getString("MAKH"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        hoTen = new javax.swing.JTextField();
        soTienThu = new javax.swing.JTextField();
        panelBorder2 = new com.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        XacNhan = new rojerusan.RSMaterialButtonRectangle();
        Update = new rojerusan.RSMaterialButtonRectangle();
        maKH = new javax.swing.JComboBox<>();
        Xoa = new rojerusan.RSMaterialButtonRectangle();

        setPreferredSize(new java.awt.Dimension(851, 700));

        panelBorder1.setBackground(new java.awt.Color(0, 88, 128));

        jLabel1.setBackground(new java.awt.Color(0, 88, 128));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU THU TIỀN");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDateChooser1.setBackground(new java.awt.Color(98, 204, 254));
        jDateChooser1.setForeground(new java.awt.Color(0, 49, 64));

        hoTen.setBackground(new java.awt.Color(138, 218, 254));
        hoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hoTen.setForeground(new java.awt.Color(0, 49, 64));
        hoTen.setText("Họ và tên");
        hoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        hoTen.setFocusable(false);
        hoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hoTenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hoTenFocusLost(evt);
            }
        });
        hoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoTenActionPerformed(evt);
            }
        });

        soTienThu.setBackground(new java.awt.Color(98, 204, 254));
        soTienThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        soTienThu.setForeground(new java.awt.Color(0, 49, 64));
        soTienThu.setText("Số tiền thu");
        soTienThu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        soTienThu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                soTienThuFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                soTienThuFocusLost(evt);
            }
        });
        soTienThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soTienThuActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(0, 88, 128));

        jScrollPane1.setBackground(new java.awt.Color(0, 88, 128));
        jScrollPane1.setBorder(null);

        jTable1.setBackground(new java.awt.Color(0, 88, 128));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu thu", "Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại", "Ngày thu", "Tổng tiền thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        XacNhan.setBackground(new java.awt.Color(255, 255, 255));
        XacNhan.setForeground(new java.awt.Color(0, 88, 128));
        XacNhan.setText("THÊM");
        XacNhan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XacNhanActionPerformed(evt);
            }
        });

        Update.setBackground(new java.awt.Color(255, 255, 255));
        Update.setForeground(new java.awt.Color(0, 88, 128));
        Update.setText("Cập nhật");
        Update.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        maKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maKHActionPerformed(evt);
            }
        });

        Xoa.setBackground(new java.awt.Color(255, 255, 255));
        Xoa.setForeground(new java.awt.Color(0, 88, 128));
        Xoa.setText("Xóa");
        Xoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(maKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(179, 179, 179)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(soTienThu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(43, 43, 43))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(maKH))
                .addGap(27, 27, 27)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(soTienThu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void soTienThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soTienThuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soTienThuActionPerformed

    private void soTienThuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_soTienThuFocusLost
        // TODO add your handling code here:
        if("".equals(soTienThu.getText())){
            soTienThu.setText("Số tiền thu");
        }
    }//GEN-LAST:event_soTienThuFocusLost

    private void soTienThuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_soTienThuFocusGained
        // TODO add your handling code here:
        if("Số tiền thu".equals(soTienThu.getText())){
            soTienThu.setText("");
        }
    }//GEN-LAST:event_soTienThuFocusGained

    private void hoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoTenActionPerformed

    private void hoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTenFocusLost
        if("".equals(hoTen.getText())){
            hoTen.setText("Họ và tên");
        }
    }//GEN-LAST:event_hoTenFocusLost

    private void hoTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTenFocusGained
        if("Họ và tên".equals(hoTen.getText())){
            hoTen.setText("");
        }
    }//GEN-LAST:event_hoTenFocusGained

    private void XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XacNhanActionPerformed
        // TODO add your handling code here:
        // Thu thập dữ liệu từ các trường nhập liệu
        String makh = maKH.getSelectedItem().toString();
        String ngayThuInput = "";
        if(jDateChooser1.getDate() != null){
            ngayThuInput = new java.sql.Date(jDateChooser1.getDate().getTime()).toString();
        }
        String soTienThuInputStr = soTienThu.getText().trim();
        double soTienThuInput = 0.0;

        // Kiểm tra tính hợp lệ của dữ liệu
        if("Chọn mã khách hàng".equals(makh)){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(ngayThuInput.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày thu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(soTienThuInputStr.isEmpty() || soTienThuInputStr.equals("Số tiền thu")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền thu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            soTienThuInput = Double.parseDouble(soTienThuInputStr.replaceAll(",", ""));
            if(soTienThuInput <= 0){
                JOptionPane.showMessageDialog(this, "Số tiền thu phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Số tiền thu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // lấy tổng nợ
        String sqlCheckKH = "SELECT TONGNO FROM khachhang WHERE MAKH = ?";
        double tongNoKH = 0.0;
        try {
            ps = conn.prepareStatement(sqlCheckKH);
            ps.setString(1, makh);
            rs = ps.executeQuery();
            if(rs.next()){
                tongNoKH = rs.getDouble("TONGNO");
            } else {
                // Nếu khách hàng chưa tồn tại, hiển thị thông báo lỗi
                JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại. Vui lòng kiểm tra lại thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } finally {
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Lấy giá trị KIEMTRATIENTHU từ bảng TDQD
        int kiemTraTienThu = getKiemTraTienThu();

        // Nếu KIEMTRATIENTHU == 1, kiểm tra SoTienThu <= TongNo
        if(kiemTraTienThu == 1){
            if(soTienThuInput > tongNoKH){
                JOptionPane.showMessageDialog(this, "Số tiền thu vượt quá tổng nợ của khách hàng. Không thể thêm phiếu thu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Chèn dữ liệu vào bảng phieuthutien
        String sqlInsertPTT = "INSERT INTO phieuthutien (MAKH, NGAYTHU, SOTIEN) VALUES (?, ?, ?)";
        try {
            ps = conn.prepareStatement(sqlInsertPTT);
           
            ps.setString(1, makh);
            ps.setDate(2, java.sql.Date.valueOf(ngayThuInput));
            ps.setDouble(3, soTienThuInput);
            ps.executeUpdate();

            //cập nhật TONGNO của khách hàng
          
                String sqlUpdateTongNo = "UPDATE khachhang SET TONGNO = TONGNO - ? WHERE MAKH = ?";
                try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateTongNo)) {
                    psUpdate.setDouble(1, soTienThuInput);
                    psUpdate.setString(2, makh);
                    psUpdate.executeUpdate();
                }
            

        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } finally {
            try {
                if(ps != null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Cập nhật lại bảng
        loadTable1();

        // Reset các trường nhập liệu
        resetInputFields();

        JOptionPane.showMessageDialog(this, "Thêm phiếu thu tiền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_XacNhanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    // Lấy số hàng được chọn
    int rowNo = jTable1.getSelectedRow();
    if(rowNo < 0 ) return;
    Update.setVisible(true);
    XacNhan.setVisible(false);
    Xoa.setVisible(true);
    maKH.setEnabled(false);
    // Lấy dữ liệu từ bảng
    TableModel model = jTable1.getModel();
    String maPhieuThu = model.getValueAt(rowNo, 0).toString(); // MAPHIEUTHU ở cột 0
    String makh = model.getValueAt(rowNo, 1).toString(); // MAKH ở cột 1
    String tenKH = model.getValueAt(rowNo, 2).toString();
    String soDTSelected = model.getValueAt(rowNo, 4).toString();
    String ngayThu = model.getValueAt(rowNo, 5).toString();
    String tongTienThuStr = model.getValueAt(rowNo, 6).toString();
    double tongTienThu = 0.0;
    try {
        tongTienThu = Double.parseDouble(tongTienThuStr.replaceAll(",", ""));
    } catch (NumberFormatException e) {
        tongTienThu = 0.0;
    }

    // Lưu trữ thông tin phiếu thu hiện tại
    selectedMaPhieuThu = maPhieuThu;
    selectedSoTienThu = tongTienThu;

    // Đặt dữ liệu vào các trường nhập liệu
    hoTen.setText(tenKH);
    maKH.setSelectedItem(makh);

    // Đặt ngày thu
    try {
        java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(ngayThu);
        jDateChooser1.setDate(date);
    } catch (java.text.ParseException e) {
        e.printStackTrace();
    }

    // Đặt tổng tiền thu
    soTienThu.setText(tongTienThuStr);
    soTienThu.setForeground(Color.BLACK);
    }//GEN-LAST:event_jTable1MouseClicked

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        // dựa vào sự thay đổi trong số tiền thu => thay đổi tổng nợ
        double thuMoi = Double.parseDouble(soTienThu.getText());
        double thuCu = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
        double changed = thuMoi-thuCu;
        String ngayThuInput = "";
        if(jDateChooser1.getDate() != null){
            ngayThuInput = new java.sql.Date(jDateChooser1.getDate().getTime()).toString();
        }
        String makh = maKH.getSelectedItem().toString();
        String sql = "UPDATE phieuthutien SET NGAYTHU = ?, SOTIEN = ? WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(ngayThuInput));
            ps.setDouble(2, thuMoi);
            ps.setString(3, makh);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "UPDATE khachhang SET TONGNO = TONGNO - ? WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, changed);
            ps.setString(2, makh);
            int result = ps.executeUpdate();
            if(result ==1){
                JOptionPane.showMessageDialog(this, "Cập nhật phiếu thu tiền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Cập nhật lại bảng
        loadTable1();
        // Reset các trường nhập liệu
        resetInputFields();
        // Ẩn nút OK và hiển thị nút Xác Nhận
        Update.setVisible(false);
        XacNhan.setVisible(true);
        Xoa.setVisible(false);
        maKH.setEnabled(true);
        // Reset biến lưu trữ thông tin phiếu thu
        selectedMaPhieuThu = null;
        selectedSoTienThu = 0.0;
    }//GEN-LAST:event_UpdateActionPerformed

    private void maKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maKHActionPerformed
        if("Chọn mã khách hàng".equals(maKH.getSelectedItem().toString())) return;
        String sql = "SELECT TENKH FROM KHACHHANG WHERE MAKH = ?";
        String makh = maKH.getSelectedItem().toString();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            rs = ps.executeQuery();
            while(rs.next()){
                hoTen.setText(rs.getString("TENKH"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_maKHActionPerformed

    private void XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaActionPerformed
        double thuCu = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
        String mapt = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String makh = maKH.getSelectedItem().toString();
        String sql = "DELETE FROM phieuthutien WHERE maphieuthu = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mapt);
            int result = ps.executeUpdate();
            if(result ==1){
                JOptionPane.showMessageDialog(this, "Xóa phiếu thu tiền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "UPDATE khachhang SET TONGNO = TONGNO + ? WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, thuCu);
            ps.setString(2, makh);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable1();
        // Reset các trường nhập liệu
        resetInputFields();
        // Ẩn nút OK và hiển thị nút Xác Nhận
        Update.setVisible(false);
        XacNhan.setVisible(true);
        Xoa.setVisible(false);
        maKH.setEnabled(true);
        // Reset biến lưu trữ thông tin phiếu thu
        selectedMaPhieuThu = null;
        selectedSoTienThu = 0.0;
    }//GEN-LAST:event_XoaActionPerformed

    private int getKiemTraTienThu() {
    int kiemTra = 0; // Mặc định là không cần kiểm tra
    String sql = "SELECT KIEMTRATIENTHU FROM thamso LIMIT 1"; // Giả sử chỉ có một dòng
    try {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.next()){
            kiemTra = rs.getInt("KIEMTRATIENTHU");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return kiemTra;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Update;
    private rojerusan.RSMaterialButtonRectangle XacNhan;
    private rojerusan.RSMaterialButtonRectangle Xoa;
    private com.component.Background background1;
    private javax.swing.JTextField hoTen;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private transient javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> maKH;
    private com.swing.PanelBorder panelBorder1;
    private com.swing.PanelBorder panelBorder2;
    private javax.swing.JTextField soTienThu;
    // End of variables declaration//GEN-END:variables
}
