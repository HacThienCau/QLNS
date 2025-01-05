/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import static UI.DatabaseConnect.getJDBCConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;


public class TraCuu extends javax.swing.JPanel {
    Connection conn = getJDBCConnection();
    PreparedStatement ps = null;
    ResultSet rs = null; 
    public TraCuu() {
        initComponents();
        setTable1Header();
    }
    private void setTable1Header(){
        tb_phieunhap.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
    public void searchBooks(String madausach, String tentheloai, String tentacgia, String nhaxuatban) {
        
          StringBuilder sql = new StringBuilder("SELECT s.MASACH, d.TENDAUSACH, a.TENTG , t.TENTHELOAI, s.NXB, s.NAMXB, s.SLTON, s.DONGIANHAP " +
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

        

    DefaultTableModel model = new DefaultTableModel(){
            //không cho chỉnh sửa trong bảng
            @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        };
    model.addColumn("Mã sách");
    model.addColumn("Tên sách");
    model.addColumn("Tên tác giả");
    model.addColumn("NXB");
    model.addColumn("Năm XB");
    model.addColumn("Thể loại");
    model.addColumn("Số lượng");
    model.addColumn("Giá nhập");

    while (rs.next()) {
      Vector<Object> rowData = new Vector<>();
      rowData.add(rs.getString("MASACH"));
      rowData.add(rs.getString("TENDAUSACH"));
      rowData.add(rs.getString("TENTG"));
      rowData.add(rs.getString("NXB"));
      rowData.add(rs.getString("NAMXB"));
      rowData.add(rs.getString("TENTHELOAI"));
      rowData.add(rs.getInt("SLTON"));
      rowData.add(rs.getDouble("DONGIANHAP"));
      model.addRow(rowData);
    }

    tb_phieunhap.setModel(model);
     // Tạo một DefaultTableCellRenderer và thiết lập căn giữa
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
          centerRenderer.setHorizontalAlignment(JLabel.CENTER);

          // Áp dụng renderer cho từng cột
          for (int i = 0; i < tb_phieunhap.getColumnCount(); i++) {
              tb_phieunhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
          }

          // Căn giữa tiêu đề cột (Optional)
          ((DefaultTableCellRenderer)tb_phieunhap.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    
    
    } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
 

    // ... các phương thức khác

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ThemDialog = new javax.swing.JDialog();
        TenSach = new javax.swing.JTextField();
        TenTG = new javax.swing.JTextField();
        NhaXB = new javax.swing.JTextField();
        NamXB = new javax.swing.JTextField();
        TheLoai = new javax.swing.JComboBox<>();
        ThemSach = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SuaDialog = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        TenSach1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TenTG1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        NhaXB1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        NamXB1 = new javax.swing.JTextField();
        CapNhat = new javax.swing.JButton();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        timkiem = new rojerusan.RSMaterialButtonRectangle();
        lb_masach = new javax.swing.JLabel();
        lb_masach2 = new javax.swing.JLabel();
        lb_masach3 = new javax.swing.JLabel();
        lb_masach4 = new javax.swing.JLabel();
        DS = new javax.swing.JTextField();
        TG = new javax.swing.JTextField();
        TL = new javax.swing.JTextField();
        NXB = new javax.swing.JTextField();
        tablePanel = new com.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_phieunhap = new javax.swing.JTable();
        Them = new rojerusan.RSMaterialButtonRectangle();
        Xoa = new rojerusan.RSMaterialButtonRectangle();
        Sua = new rojerusan.RSMaterialButtonRectangle();
        QLTL = new rojerusan.RSMaterialButtonRectangle();

        ThemDialog.setTitle("Thêm sách");
        ThemDialog.setLocation(new java.awt.Point(600, 200));
        ThemDialog.setMinimumSize(new java.awt.Dimension(546, 420));
        ThemDialog.setResizable(false);
        ThemDialog.setSize(new java.awt.Dimension(546, 363));

        ThemSach.setText("Thêm");
        ThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemSachActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên sách");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nhà xuất bản");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên tác giả");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Năm xuất bản");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Thể loại");

        javax.swing.GroupLayout ThemDialogLayout = new javax.swing.GroupLayout(ThemDialog.getContentPane());
        ThemDialog.getContentPane().setLayout(ThemDialogLayout);
        ThemDialogLayout.setHorizontalGroup(
            ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemDialogLayout.createSequentialGroup()
                .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThemDialogLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NamXB, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(NhaXB, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(TenTG, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(TenSach)
                            .addComponent(TheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ThemDialogLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(ThemSach)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        ThemDialogLayout.setVerticalGroup(
            ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemDialogLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TenTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NhaXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NamXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(ThemDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addComponent(ThemSach)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        SuaDialog.setMinimumSize(new java.awt.Dimension(546, 420));
        SuaDialog.setPreferredSize(new java.awt.Dimension(546, 363));
        SuaDialog.setSize(new java.awt.Dimension(546, 363));
        SuaDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên sách");
        SuaDialog.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 71, 59, -1));

        TenSach1.setEditable(false);
        TenSach1.setForeground(new java.awt.Color(153, 153, 153));
        TenSach1.setFocusable(false);
        SuaDialog.getContentPane().add(TenSach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 68, 311, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tên tác giả");
        SuaDialog.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 127, 81, -1));
        SuaDialog.getContentPane().add(TenTG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 121, 332, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Nhà xuất bản");
        SuaDialog.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 175, 81, -1));
        SuaDialog.getContentPane().add(NhaXB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 172, 332, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Năm xuất bản");
        SuaDialog.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 223, 81, -1));
        SuaDialog.getContentPane().add(NamXB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 220, 332, -1));

        CapNhat.setText("Cập nhật");
        CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatActionPerformed(evt);
            }
        });
        SuaDialog.getContentPane().add(CapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 286, 130, -1));

        setPreferredSize(new java.awt.Dimension(850, 700));

        kGradientPanel1.setBackground(new java.awt.Color(205, 241, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(0, 171, 253));
        kGradientPanel1.setkStartColor(new java.awt.Color(205, 241, 255));

        rSMaterialButtonRectangle2.setText("TRA CỨU SÁCH");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 14)); // NOI18N

        timkiem.setText("TÌM KIẾM");
        timkiem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timkiem.setSelected(true);
        timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timkiemActionPerformed(evt);
            }
        });

        lb_masach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach.setText("Tên sách");

        lb_masach2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach2.setText("Thể loại");

        lb_masach3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach3.setText("Tác giả");

        lb_masach4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_masach4.setText("Nhà xuất bản");

        tablePanel.setBackground(new java.awt.Color(0, 88, 128));

        jScrollPane1.setBorder(null);

        tb_phieunhap.setBackground(new java.awt.Color(0, 88, 128));
        tb_phieunhap.setForeground(new java.awt.Color(255, 255, 255));
        tb_phieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên Sách", "Tên tác giả", "NXB", "Năm XB", "Thể loại", "Số lượng", "Giá nhập"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
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
        tb_phieunhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tb_phieunhap);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        Them.setText("THÊM");
        Them.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Them.setSelected(true);
        Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemActionPerformed(evt);
            }
        });

        Xoa.setText("XÓA");
        Xoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Xoa.setSelected(true);
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });

        Sua.setText("SỬA");
        Sua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Sua.setSelected(true);
        Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaActionPerformed(evt);
            }
        });

        QLTL.setText("QUẢN LÝ THỂ lOẠI");
        QLTL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        QLTL.setSelected(true);
        QLTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLTLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(lb_masach)
                                .addGap(18, 18, 18)
                                .addComponent(DS, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, kGradientPanel1Layout.createSequentialGroup()
                                .addContainerGap(7, Short.MAX_VALUE)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addComponent(Them, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lb_masach2)
                                        .addGap(18, 18, 18)
                                        .addComponent(TL, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_masach3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lb_masach4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(25, 25, 25)
                                .addComponent(NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QLTL, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TG, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(150, 150, 150))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(20, 20, 20)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_masach2)
                        .addComponent(TL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_masach4)
                        .addComponent(NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Them, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(QLTL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 867, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(1, 1, 1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timkiemActionPerformed
        String madausach = DS.getText();
        String tentheloai = TL.getText();
        String tentacgia = TG.getText();
        String nhaxuatban = NXB.getText();
        searchBooks(madausach, tentheloai, tentacgia, nhaxuatban);
    }//GEN-LAST:event_timkiemActionPerformed
    private void loadTheLoai(){
        TheLoai.removeAllItems();
        String sql = "SELECT * FROM THELOAI";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TheLoai.addItem(rs.getString("TENTHELOAI"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    private void ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemActionPerformed
        loadTheLoai();
        ThemDialog.setVisible(true);
    }//GEN-LAST:event_ThemActionPerformed

    private void XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaActionPerformed
        int selectedRow = tb_phieunhap.getSelectedRow();
        if(selectedRow < 0 ){
            JOptionPane.showMessageDialog(TraCuu.this, "Vui lòng chọn dòng để xóa");
            return;
        }
        String maSach = tb_phieunhap.getValueAt(selectedRow, 0).toString();
        String sql = "DELETE FROM sach WHERE MASACH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,maSach);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(TraCuu.this, "Xóa sách thành công");
        } catch (SQLException ex) {
            if(ex.getMessage().contains("Cannot delete")){
                JOptionPane.showMessageDialog(TraCuu.this, "Không thể xóa sách do vẫn còn dữ liệu liên quan đến sách này");
            }
        }
    }//GEN-LAST:event_XoaActionPerformed
    private String getMaTacGia(String tentacgia){
        String sql = "SELECT MATG FROM TACGIA WHERE TENTG = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tentacgia);
            rs = ps.executeQuery();
            if(rs.next())
            return rs.getString("MATG");
            // không tìm thấy tên tác giả thì tạo mới
        sql = "INSERT INTO TACGIA (TENTG) VALUES (?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, tentacgia);
        ps.executeUpdate();
        ps.close();
        rs.close();
        sql = "SELECT MATG FROM TACGIA WHERE TENTG = ?";
        ps = conn.prepareStatement(sql);
            ps.setString(1, tentacgia);
            rs = ps.executeQuery();
            if(rs.next())
            return rs.getString("MATG");
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "";
    }
    private void SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaActionPerformed
        int selectedRow = tb_phieunhap.getSelectedRow();
        if(selectedRow < 0 ){
            JOptionPane.showMessageDialog(TraCuu.this, "Vui lòng chọn dòng để sửa");
            return;
        }
        TenSach1.setText(tb_phieunhap.getValueAt(selectedRow, 1).toString());
        TenTG1.setText(tb_phieunhap.getValueAt(selectedRow, 2).toString());
        NhaXB1.setText(tb_phieunhap.getValueAt(selectedRow, 3).toString());
        NamXB1.setText(tb_phieunhap.getValueAt(selectedRow, 4).toString());
        SuaDialog.setVisible(true);
    }//GEN-LAST:event_SuaActionPerformed

    private void QLTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLTLActionPerformed
        JFrame frame = new JFrame();
        QLTL quanlytheloai = new QLTL();
        frame.add(quanlytheloai);
        frame.setSize(882, 597);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }//GEN-LAST:event_QLTLActionPerformed
    private String getMaDauSach(String tensach, String matheloai){
        String sql = "SELECT MADAUSACH FROM DAUSACH WHERE TENDAUSACH =? AND MATHELOAI = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tensach);
            ps.setString(2, matheloai);
            rs = ps.executeQuery();
            if(rs.next())
            return rs.getString("MADAUSACH");
            // không tìm thấy tên đầu sách thì tạo mới
        
        sql = "INSERT INTO DAUSACH (TENDAUSACH, MATHELOAI) VALUES (?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, tensach);
        ps.setString(2, matheloai);
        ps.executeUpdate();
        ps.close();
        rs.close();
        sql = "SELECT MADAUSACH FROM DAUSACH WHERE TENDAUSACH =? AND MATHELOAI = ?";
        ps = conn.prepareStatement(sql);
            ps.setString(1, tensach);
            ps.setString(2, matheloai);
            rs = ps.executeQuery();
            if(rs.next())
            return rs.getString("MADAUSACH");
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "";
    }
    private String getMaTheLoai(String theloai){
        String sql = "SELECT MATHELOAI FROM THELOAI WHERE TENTHELOAI = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, theloai);
            rs = ps.executeQuery();
            if(rs.next())
            return rs.getString("MATHELOAI");
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "";
    }
    private int getCTTG(String madausach, String matacgia){
        String sql = "SELECT * FROM CTTACGIA WHERE MADAUSACH =? AND MATG = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, madausach);
            ps.setString(2, matacgia);
            rs = ps.executeQuery();
            if(rs.next())
            return 1;
            // không tìm thấy đầu sách - tác giả thì tạo mới
        sql = "INSERT INTO CTTACGIA (MATG, MADAUSACH) VALUES (?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, matacgia);
        ps.setString(2, madausach);
        ps.executeUpdate();
        return 1;
        } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return 0;
    }
    private void ThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemSachActionPerformed
        String ts = TenSach.getText();
        String tg = TenTG.getText();
        String nxb = NhaXB.getText();
        String nam = NamXB.getText();
        String tl = TheLoai.getSelectedItem().toString();
        if(ts.isBlank()||tg.isBlank()||nxb.isBlank()||nam.isBlank()){
            JOptionPane.showMessageDialog(TraCuu.this, "Vui lòng điền đầy đủ thông tin");
            return;
        }
        String maTL = getMaTheLoai(tl);
        String maTG = getMaTacGia(tg);
        String maDS = getMaDauSach(ts,maTL);
        getCTTG(maDS,maTG);
        String sql = "INSERT INTO SACH (MADAUSACH,NXB,NAMXB,SLTON,DONGIANHAP) VALUES (?,?,?,0,0)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,maDS);
            ps.setString(2,nxb);
            ps.setString(3, nam);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(TraCuu.this, "Thêm sách thành công");
        } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
        }
        TenSach.setText("");
        TenTG.setText("");
        NhaXB.setText("");
        NamXB.setText("");
        TheLoai.setSelectedIndex(0);
        ThemDialog.setVisible(false);
    }//GEN-LAST:event_ThemSachActionPerformed
    private String getMaDauSachFromMaSach(String maSach){
        String sql = "SELECT MADAUSACH FROM SACH WHERE MASACH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, maSach);
            rs = ps.executeQuery();
            if(rs.next())
            return rs.getString("MADAUSACH");
        } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return "";
    }
    private void updateCTTG(String maDauSach, String maTG, String maTGcu){
        String sql = "UPDATE CTTACGIA SET MATG = ? WHERE MADAUSACH = ? AND MATG = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, maTG);
            ps.setString(2, maDauSach);
            ps.setString(3, maTGcu);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatActionPerformed
        String tg = TenTG1.getText();
        String nxb = NhaXB1.getText();
        String nam = NamXB1.getText();
        if(tg.isBlank()||nxb.isBlank()||nam.isBlank()){
            JOptionPane.showMessageDialog(TraCuu.this, "Vui lòng điền đầy đủ thông tin");
            return;
        }
        String maSach = tb_phieunhap.getValueAt(tb_phieunhap.getSelectedRow(), 0).toString();
        String maTGcu = getMaTacGia(tb_phieunhap.getValueAt(tb_phieunhap.getSelectedRow(), 2).toString());
        String maTG = getMaTacGia(tg);  // tìm, ko có thì tạo tác giả mới
        if(!maTG.equals(maTGcu)){
            String maDS = getMaDauSachFromMaSach(maSach);
            updateCTTG(maDS, maTG, maTGcu);
        }
        String sql = "UPDATE SACH SET NXB = ?, NAMXB = ? WHERE MASACH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,nxb);
            ps.setString(2, nam);
            ps.setString(3,maSach);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(TraCuu.this, "Cập nhật sách thành công");
        } catch (SQLException ex) {
            Logger.getLogger(TraCuu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        TenSach1.setText("");
        TenTG1.setText("");
        NhaXB1.setText("");
        NamXB1.setText("");
        SuaDialog.setVisible(false);
    }//GEN-LAST:event_CapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CapNhat;
    private javax.swing.JTextField DS;
    private javax.swing.JTextField NXB;
    private javax.swing.JTextField NamXB;
    private javax.swing.JTextField NamXB1;
    private javax.swing.JTextField NhaXB;
    private javax.swing.JTextField NhaXB1;
    private rojerusan.RSMaterialButtonRectangle QLTL;
    private rojerusan.RSMaterialButtonRectangle Sua;
    private javax.swing.JDialog SuaDialog;
    private javax.swing.JTextField TG;
    private javax.swing.JTextField TL;
    private javax.swing.JTextField TenSach;
    private javax.swing.JTextField TenSach1;
    private javax.swing.JTextField TenTG;
    private javax.swing.JTextField TenTG1;
    private javax.swing.JComboBox<String> TheLoai;
    private rojerusan.RSMaterialButtonRectangle Them;
    private javax.swing.JDialog ThemDialog;
    private javax.swing.JButton ThemSach;
    private rojerusan.RSMaterialButtonRectangle Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lb_masach;
    private javax.swing.JLabel lb_masach2;
    private javax.swing.JLabel lb_masach3;
    private javax.swing.JLabel lb_masach4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private com.swing.PanelBorder tablePanel;
    private javax.swing.JTable tb_phieunhap;
    private rojerusan.RSMaterialButtonRectangle timkiem;
    // End of variables declaration//GEN-END:variables
}
