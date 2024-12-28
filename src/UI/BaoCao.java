/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class BaoCao extends javax.swing.JPanel {

  private JTextField date;

    public BaoCao() {
        initComponents();
        // Lấy JTextField từ JDateChooser
        JTextField date = (JTextField) jDateChooser1.getDateEditor().getUiComponent();
        // Đặt placeholder
        String placeholder = "Ngày nhập";
        date.setText(placeholder);
        date.setForeground(Color.GRAY);
        // Thêm sự kiện Focus để xử lý khi focus vào hoặc ra khỏi trường nhập liệu
        date.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (date.getText().equals(placeholder)) {
                date.setText("");
                date.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (date.getText().isEmpty()) {
                date.setText(placeholder);
                date.setForeground(Color.GRAY);
            }
        }
    });  
    }
    private void layDuLieuTuCoSoDuLieu(String formattedDate) {
        try {
            // Kết nối đến cơ sở dữ liệu (thay thế bằng thông tin kết nối của bạn)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sach", "root", "");
            Statement stmt = conn.createStatement();

            // Câu lệnh SQL để lấy dữ liệu (bạn có thể tùy chỉnh câu lệnh này)
            String sql = "SELECT * FROM BAOCAOTONKHO WHERE NGAYLAP LIKE '%" + formattedDate + "%'";
            ResultSet rs = stmt.executeQuery(sql);

            // Tạo DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("STT");
            model.addColumn("Mã sản phẩm");
            model.addColumn("Tên sản phẩm");
            model.addColumn("Tồn đầu");
            model.addColumn("Phát sinh");
            model.addColumn("Tồn cuối");

            int stt = 1;
            while (rs.next()) {
                Object[] row = {
                        stt++,
                        rs.getString("MABCTK"),
                        rs.getString("MASACH"),
                        rs.getString("NGAYLAP"),
                        rs.getInt("TONDAU"),
                        rs.getInt("PHATSINH"),
                        rs.getInt("TONCUOI")
                };
                model.addRow(row);
            }

            Table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new com.component.Background();
        panelBorder1 = new com.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        panelBorder3 = new com.swing.PanelBorder();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table = new rojerusan.RSTableMetro();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();

        background1.setPreferredSize(new java.awt.Dimension(850, 700));

        panelBorder1.setBackground(new java.awt.Color(0, 88, 128));

        jLabel1.setBackground(new java.awt.Color(0, 88, 128));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BÁO CÁO TỒN");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelBorder3.setBackground(new java.awt.Color(0, 88, 128));

        jDateChooser1.setBackground(new java.awt.Color(0, 88, 128));

        Table.setBackground(new java.awt.Color(0, 88, 128));
        Table.setForeground(new java.awt.Color(255, 255, 255));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Tồn đầu", "Phát sinh", "Tồn cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table.setColorBackgoundHead(new java.awt.Color(0, 88, 128));
        Table.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        Table.setColorBordeHead(new java.awt.Color(255, 255, 255));
        Table.setColorFilasBackgound1(new java.awt.Color(0, 88, 128));
        Table.setColorFilasBackgound2(new java.awt.Color(0, 88, 128));
        Table.setColorFilasForeground1(new java.awt.Color(255, 255, 255));
        Table.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        Table.setColorSelBackgound(new java.awt.Color(51, 51, 51));
        Table.setFuenteFilas(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Table.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Table.setFuenteHead(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Table.setGridColor(new java.awt.Color(255, 255, 255));
        Table.setSelectionBackground(new java.awt.Color(51, 51, 51));
        Table.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(Table);

        rSMaterialButtonRectangle1.setText("Tìm kiếm");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
       // TODO add your handling code here:
Date selectedDate = jDateChooser1.getDate();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = dateFormat.format(selectedDate);
            layDuLieuTuCoSoDuLieu(formattedDate);
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSTableMetro Table;
    private com.component.Background background1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane4;
    private com.swing.PanelBorder panelBorder1;
    private com.swing.PanelBorder panelBorder3;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    // End of variables declaration//GEN-END:variables
}