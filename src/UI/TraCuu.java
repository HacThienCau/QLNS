/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Vector;


public class TraCuu extends javax.swing.JPanel {

    public TraCuu() {
        initComponents();
    }
    public void searchBooks(String madausach, String tentheloai, String tentacgia, String nhaxuatban) {
  try {
          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sach", "root", "");
          
          StringBuilder sql = new StringBuilder("SELECT s.MASACH, d.TENDAUSACH, a.TENTG , t.TENTHELOAI AS tentheloai, s.NXB, s.NAMXB, s.SLTON " +
    "FROM sach s " +
    "JOIN dausach d ON s.MADAUSACH = d.MADAUSACH " +
    "JOIN cttacgia ct ON s.MADAUSACH = ct.MADAUSACH " +
    "JOIN tacgia a ON ct.MATG = a.MATG " +
    "JOIN theloai t ON d.MATHELOAI = t.MATHELOAI ");
  List<String> conditions = new ArrayList<>();
 int j = 1;
 int q = 1;
 int k = 1;
 int s = 1;
  if (!tentacgia.equals("Chon")) {
    conditions.add("a.TENTG LIKE ?");
    q++;k++;s++;
  } else {j=0;
  }
  if (!tentheloai.equals("Chon")) {
    conditions.add("t.TENTHELOAI LIKE ?");
    k++;s++;
  } else {q=0;
  }
  if (!madausach.equals("Chon")) {
    conditions.add("d.TENDAUSACH LIKE ?");
    s++;
  } else {k=0;
  }
  if (!nhaxuatban.equals("Chon")) {
    conditions.add("s.NXB LIKE ?");
  } else {s=0;
  }
if (!conditions.isEmpty()) {
        sql.append(" WHERE ");
        for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                sql.append(" AND ");
            }
            sql.append(conditions.get(i));
        }
    }

PreparedStatement statement = connection.prepareStatement(sql.toString());
if (j!=0){statement.setString(j, "%" + tentacgia + "%");};
if (q!=0){statement.setString(q, "%" + tentheloai + "%");};
if (k!=0){statement.setString(k, "%" + madausach + "%");};
if (s!=0){statement.setString(s, "%" + nhaxuatban + "%");};
ResultSet resultSet = statement.executeQuery();

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Mã sách");
    model.addColumn("Đầu sách");
    model.addColumn("Tên tác giả");
    model.addColumn("Số lượng");
    model.addColumn("Thể loại");


    while (resultSet.next()) {
      Vector<Object> rowData = new Vector<>();
      rowData.add(resultSet.getString("MASACH"));
      rowData.add(resultSet.getString("TENDAUSACH"));
      rowData.add(resultSet.getString("TENTG"));
      rowData.add(resultSet.getString("SLTON"));
      rowData.add(resultSet.getString("TENTHELOAI"));
      model.addRow(rowData);
    }

    tb_phieunhap.setModel(model);
  } catch (SQLException e) {
    e.printStackTrace();
  }
}

    
 

    // ... các phương thức khác

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_phieunhap = new javax.swing.JTable();
        lb_masach = new javax.swing.JLabel();
        lb_masach2 = new javax.swing.JLabel();
        lb_masach3 = new javax.swing.JLabel();
        lb_masach4 = new javax.swing.JLabel();

        kGradientPanel1.setBackground(new java.awt.Color(205, 241, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(0, 171, 253));
        kGradientPanel1.setkStartColor(new java.awt.Color(205, 241, 255));

        rSMaterialButtonRectangle2.setText("TRA CỨU SÁCH");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(205, 241, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon", "Tam Cam", "Thanh Giong", "Harry Porter", "Shin Cau Be But Chi" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(205, 241, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon", "Co Tich", "Huyen Bi", "Manga", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox4.setBackground(new java.awt.Color(205, 241, 255));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon", "Usui Yoshito", "Pham Thoai", "Joanne Rowling" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle4.setText("TÌM KIẾM");
        rSMaterialButtonRectangle4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSMaterialButtonRectangle4.setSelected(true);
        rSMaterialButtonRectangle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle4ActionPerformed(evt);
            }
        });

        jComboBox5.setBackground(new java.awt.Color(205, 241, 255));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon", "Kim Dong", "Thanh Nien", "Thieu Nhi" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        tb_phieunhap.setBackground(new java.awt.Color(0, 88, 128));
        tb_phieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Đầu Sách", "Tên tác giả", "Số lượng", "Thể loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_phieunhap.setGridColor(new java.awt.Color(255, 255, 255));
        tb_phieunhap.setRowHeight(40);
        tb_phieunhap.setSelectionBackground(new java.awt.Color(153, 255, 255));
        jScrollPane1.setViewportView(tb_phieunhap);

        lb_masach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach.setText("Đầu sách");

        lb_masach2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach2.setText("Thể loại");

        lb_masach3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach3.setText("Tác giả");

        lb_masach4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach4.setText("Nhà xuất bản");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lb_masach2)
                        .addGap(23, 23, 23)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addComponent(lb_masach)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(99, 99, 99)
                                        .addComponent(lb_masach3))
                                    .addComponent(lb_masach4))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_masach))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lb_masach3)))
                .addGap(20, 20, 20)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lb_masach2))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_masach4)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 874, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
String madausach = (String) jComboBox1.getSelectedItem();
String tentheloai = (String) jComboBox2.getSelectedItem();
String tentacgia = (String) jComboBox4.getSelectedItem();
String nhaxuatban = (String) jComboBox5.getSelectedItem();

            searchBooks(madausach, tentheloai, tentacgia, nhaxuatban);
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lb_masach;
    private javax.swing.JLabel lb_masach2;
    private javax.swing.JLabel lb_masach3;
    private javax.swing.JLabel lb_masach4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle4;
    private javax.swing.JTable tb_phieunhap;
    // End of variables declaration//GEN-END:variables
}
