/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import static UI.DatabaseConnect.getJDBCConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TraCuu extends javax.swing.JPanel {
    Connection conn = getJDBCConnection();
    PreparedStatement ps = null;
    ResultSet rs = null; 
    public TraCuu() {
        initComponents();
    }
    public void searchBooks(String madausach, String tentheloai, String tentacgia, String nhaxuatban) {
        
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
  if (!tentheloai.isBlank()) {
    conditions.add("t.TENTHELOAI LIKE ?");
    k++;s++;
  } else {q=0;
  } 
  if (!madausach.isBlank()) {
    conditions.add("d.TENDAUSACH LIKE ?");
    s++;
  } else {k=0;
  } 
  if (!nhaxuatban.isBlank()) {
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

        try {
            ps = conn.prepareStatement(sql.toString());
            if (j!=0){ps.setString(j, "%" + tentacgia + "%");};
            if (q!=0){ps.setString(q, "%" + tentheloai + "%");};
            if (k!=0){ps.setString(k, "%" + madausach + "%");};
            if (s!=0){ps.setString(s, "%" + nhaxuatban + "%");};
            rs = ps.executeQuery();

        

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Mã sách");
    model.addColumn("Đầu sách");
    model.addColumn("Tên tác giả");
    model.addColumn("Số lượng");
    model.addColumn("Thể loại");


    while (rs.next()) {
      Vector<Object> rowData = new Vector<>();
      rowData.add(rs.getString("MASACH"));
      rowData.add(rs.getString("TENDAUSACH"));
      rowData.add(rs.getString("TENTG"));
      rowData.add(rs.getString("SLTON"));
      rowData.add(rs.getString("TENTHELOAI"));
      model.addRow(rowData);
    }

    tb_phieunhap.setModel(model);
    } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
 

    // ... các phương thức khác

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_phieunhap = new javax.swing.JTable();
        lb_masach = new javax.swing.JLabel();
        lb_masach2 = new javax.swing.JLabel();
        lb_masach3 = new javax.swing.JLabel();
        lb_masach4 = new javax.swing.JLabel();
        DS = new javax.swing.JTextField();
        TG = new javax.swing.JTextField();
        TL = new javax.swing.JTextField();
        NXB = new javax.swing.JTextField();

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

        rSMaterialButtonRectangle4.setText("TÌM KIẾM");
        rSMaterialButtonRectangle4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSMaterialButtonRectangle4.setSelected(true);
        rSMaterialButtonRectangle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle4ActionPerformed(evt);
            }
        });

        tb_phieunhap.setBackground(new java.awt.Color(0, 88, 128));
        tb_phieunhap.setForeground(new java.awt.Color(255, 255, 255));
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
        tb_phieunhap.setFillsViewportHeight(true);
        tb_phieunhap.setGridColor(new java.awt.Color(255, 255, 255));
        tb_phieunhap.setRowHeight(40);
        tb_phieunhap.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tb_phieunhap.setSelectionForeground(new java.awt.Color(0, 88, 128));
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
                        .addGap(20, 20, 20)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addComponent(lb_masach)
                                        .addGap(18, 18, 18)
                                        .addComponent(DS, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lb_masach2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TL, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(63, 63, 63)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_masach3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lb_masach4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TG, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 27, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_masach3)
                        .addComponent(TG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_masach)))
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_masach2)
                            .addComponent(TL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_masach4)
                            .addComponent(NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
String madausach = DS.getText();
String tentheloai = TL.getText();
String tentacgia = TG.getText();
String nhaxuatban = NXB.getText();

            searchBooks(madausach, tentheloai, tentacgia, nhaxuatban);
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DS;
    private javax.swing.JTextField NXB;
    private javax.swing.JTextField TG;
    private javax.swing.JTextField TL;
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
