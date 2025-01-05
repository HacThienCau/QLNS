/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;
import static UI.DatabaseConnect.getJDBCConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

class CTBan {

    private String maSach;
    private String tenSach;
    private int soLuong;
    private double giaban;

    private String theloai;
    
    public CTBan(String maSach, String tenSach, int soLuong, double giaban,  String theloai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.giaban = giaban;
        this.theloai = theloai;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGia() {
        return giaban;
    }

    public String getTheloai() {
        return theloai;
    }
}

   
public class BanSach extends javax.swing.JPanel {
    Connection conn = getJDBCConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    java.util.List<CTBan> danhsachsanpham = new ArrayList<>();
    java.util.List<CTBan> chitiethoadon = new ArrayList<>();
    private int mode = 0; //0 - chế độ lập phiếu, 1- chế độ tra cứu
    /**
     * Creates new form BanSach
     */
    public BanSach() {
        initComponents();
        ThemDialog.setVisible(false);
        setTable1Header();
        setTable2Header();
        loadTable1();
        loadTable2("");
        loadComboBox();
        loadComboBoxSach();
        hoTen.setFocusable(false);
        setMode(0);
    }
    private void setMode(int x){
        if(x==0){   //thêm hóa đơn
            ThemSP.setVisible(true);
            XoaSP.setVisible(true);
            InHoaDon.setVisible(true);
            TraCuu.setVisible(true);
            Tim.setVisible(true);
            TraCuuPanel.setVisible(true);
            KTTC.setVisible(false);
            maKH.setEnabled(true);
            NLHD.setFocusable(true);
            TienTraField.setFocusable(true);
            jTable1.setFocusable(true);
        } else{ // đang tra cứu
            ThemSP.setVisible(false);
            XoaSP.setVisible(false);
            InHoaDon.setVisible(false);
            TraCuu.setVisible(false);
            Tim.setVisible(false);
            TraCuuPanel.setVisible(false);
            KTTC.setVisible(true);
            hoTen.setFocusable(false);
            maKH.setEnabled(false);
            NLHD.setFocusable(false);
            TienTraField.setFocusable(false);
            jTable1.setFocusable(false);
        }
        mode = x;
    }
     private void loadComboBoxSach(){
        MS.removeAllItems();
        String sql = "SELECT * FROM SACH";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            MS.addItem("Chọn mã sách");
            while(rs.next()){
                MS.addItem(rs.getString("MASACH"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
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
    private void setTable2Header(){
        jTable2.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
    private void loadTable1(){
        // create jTable1 columns
        
        DefaultTableModel model = new DefaultTableModel(){
            //không cho chỉnh sửa trong bảng
            @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        };       
        String tieude[] = new String[]{ "STT", "Tên sách","Thể loại", "Số lượng", "Đơn giá bán"};
        model.setColumnIdentifiers(tieude);
        if(mode==0){ // thêm hóa đơn    
            int i = 1 ;
            for(CTBan sach : danhsachsanpham){
                model.addRow(new Object[]{i, sach.getTenSach(), sach.getTheloai(), sach.getSoLuong() ,sach.getGia()});
                i++;
            }
        }
        else{
            int i = 1 ;
            for(CTBan sach : chitiethoadon){
                model.addRow(new Object[]{i, sach.getTenSach(), sach.getTheloai(), sach.getSoLuong() ,sach.getGia()});
                i++;
            }        
        }
        double tongtien = 0;
        for(int i = 0 ;i< model.getRowCount();i++){
            tongtien += (Integer.parseInt(model.getValueAt(i, 3).toString())*Double.parseDouble(model.getValueAt(i, 4).toString()));
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
        TongTienField.setText(Double.toString(tongtien));
        // set columns width
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);     
    }
    private void loadTable2(String maHD){  //danh sách các hóa đơn
        // create jTable1 columns
        DefaultTableModel model = new DefaultTableModel(){           
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Không cho phép chỉnh sửa các ô
        }       
        };        
        String tieude[] = new String[]{ "STT", "Mã hóa đơn","Họ tên khách hàng","Ngày lập hóa đơn", "Chi tiết hóa đơn"};
        model.setColumnIdentifiers(tieude);
        String sql = "SELECT MAHD,TENKH,NGAYLAPHD FROM hoadon JOIN khachhang ON hoadon.MAKH = khachhang.MAKH WHERE MAHD = ?";
        if("".equals(maHD)) {
            sql = "SELECT MAHD,TENKH,NGAYLAPHD FROM hoadon JOIN khachhang ON hoadon.MAKH = khachhang.MAKH";
        }
        try {
            ps = conn.prepareStatement(sql);
            if(!"".equals(maHD)) ps.setString(1, maHD);
            rs = ps.executeQuery();
            if(!rs.isBeforeFirst() ) {
                JOptionPane.showMessageDialog(BanSach.this, "Không có kết quả");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            int j = 1;
            while(rs.next()){
                model.addRow(new Object[]{j, rs.getString("MAHD") ,rs.getString("TENKH"), sdf.format(rs.getDate("NGAYLAPHD")), "Bấm đúp để xem"});
                j++;
            }
            jTable2.setModel(model);
            // Tạo một DefaultTableCellRenderer và thiết lập căn giữa
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            
            // Áp dụng renderer cho từng cột
            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
            // Căn giữa tiêu đề cột (Optional)
            ((DefaultTableCellRenderer)jTable2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
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

        ThemDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ThemButton = new javax.swing.JButton();
        SL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TL = new javax.swing.JTextField();
        MS = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        SLT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        DGN = new javax.swing.JTextField();
        panelBorder1 = new com.swing.PanelBorder();
        background1 = new com.component.Background();
        TitlePanel = new com.swing.PanelBorder();
        title = new javax.swing.JLabel();
        tablePanel = new com.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        TongTien = new javax.swing.JLabel();
        TienTra = new javax.swing.JLabel();
        ConLai = new javax.swing.JLabel();
        TongTienField = new javax.swing.JTextField();
        TienTraField = new javax.swing.JTextField();
        ConLaiField = new javax.swing.JTextField();
        ThemSP = new javax.swing.JButton();
        XoaSP = new javax.swing.JButton();
        InHoaDon = new javax.swing.JButton();
        TraCuuPanel = new com.swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        TraCuu = new javax.swing.JTextField();
        Tim = new javax.swing.JButton();
        KTTC = new javax.swing.JButton();
        maKH = new javax.swing.JComboBox<>();
        hoTen = new javax.swing.JTextField();
        NLHD = new javax.swing.JTextField();

        ThemDialog.setLocation(new java.awt.Point(600, 200));
        ThemDialog.setMinimumSize(new java.awt.Dimension(407, 350));
        ThemDialog.setPreferredSize(new java.awt.Dimension(407, 300));
        ThemDialog.setSize(new java.awt.Dimension(407, 300));
        ThemDialog.setType(java.awt.Window.Type.POPUP);
        ThemDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã sách:");
        ThemDialog.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setText("Số lượng:");
        ThemDialog.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        ThemButton.setText("Thêm");
        ThemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemButtonActionPerformed(evt);
            }
        });
        ThemDialog.getContentPane().add(ThemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));
        ThemDialog.getContentPane().add(SL, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 230, -1));

        jLabel3.setText("Tên sách:");
        ThemDialog.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        TS.setEnabled(false);
        ThemDialog.getContentPane().add(TS, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 230, -1));

        jLabel4.setText("Thể loại");
        ThemDialog.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        TL.setEnabled(false);
        ThemDialog.getContentPane().add(TL, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 230, -1));

        MS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MSActionPerformed(evt);
            }
        });
        ThemDialog.getContentPane().add(MS, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 230, -1));

        jLabel5.setText("Số lượng tồn:");
        ThemDialog.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        SLT.setEnabled(false);
        ThemDialog.getContentPane().add(SLT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 230, -1));

        jLabel6.setText("Đơn giá nhập:");
        ThemDialog.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        DGN.setEnabled(false);
        ThemDialog.getContentPane().add(DGN, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 230, -1));

        setBackground(new java.awt.Color(205, 241, 255));

        TitlePanel.setBackground(new java.awt.Color(0, 88, 128));
        TitlePanel.setPreferredSize(new java.awt.Dimension(144, 34));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("HÓA ĐƠN");

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
            .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TitlePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(title)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
            .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TitlePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(title)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tablePanel.setBackground(new java.awt.Color(0, 88, 128));

        jScrollPane1.setBackground(new java.awt.Color(0, 88, 128));
        jScrollPane1.setBorder(null);

        jTable1.setBackground(new java.awt.Color(0, 88, 128));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sách", "Thể loại", "Số lượng", "Đơn giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

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
        jScrollPane1.setViewportView(jTable1);

        TongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TongTien.setForeground(new java.awt.Color(255, 255, 255));
        TongTien.setText("Tổng tiền:");

        TienTra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TienTra.setForeground(new java.awt.Color(255, 255, 255));
        TienTra.setText("Số tiền trả:");

        ConLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ConLai.setForeground(new java.awt.Color(255, 255, 255));
        ConLai.setText("Còn lại:");

        TongTienField.setBackground(new java.awt.Color(0, 88, 128));
        TongTienField.setForeground(new java.awt.Color(255, 255, 255));
        TongTienField.setBorder(null);
        TongTienField.setSelectedTextColor(new java.awt.Color(0, 88, 128));
        TongTienField.setSelectionColor(new java.awt.Color(255, 255, 255));

        TienTraField.setBackground(new java.awt.Color(0, 88, 128));
        TienTraField.setForeground(new java.awt.Color(255, 255, 255));
        TienTraField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        TienTraField.setSelectedTextColor(new java.awt.Color(0, 88, 128));
        TienTraField.setSelectionColor(new java.awt.Color(255, 255, 255));
        TienTraField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TienTraFieldActionPerformed(evt);
            }
        });

        ConLaiField.setEditable(false);
        ConLaiField.setBackground(new java.awt.Color(0, 88, 128));
        ConLaiField.setForeground(new java.awt.Color(255, 255, 255));
        ConLaiField.setBorder(null);
        ConLaiField.setSelectedTextColor(new java.awt.Color(0, 88, 128));
        ConLaiField.setSelectionColor(new java.awt.Color(255, 255, 255));

        ThemSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ThemSP.setForeground(new java.awt.Color(0, 88, 128));
        ThemSP.setText("Thêm sản phẩm");
        ThemSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 88, 128), 3, true));
        ThemSP.setPreferredSize(new java.awt.Dimension(140, 40));
        ThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemSPActionPerformed(evt);
            }
        });

        XoaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        XoaSP.setForeground(new java.awt.Color(0, 88, 128));
        XoaSP.setText("Xóa sản phẩm");
        XoaSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 88, 128), 3, true));
        XoaSP.setPreferredSize(new java.awt.Dimension(140, 40));
        XoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaSPActionPerformed(evt);
            }
        });

        InHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        InHoaDon.setForeground(new java.awt.Color(0, 88, 128));
        InHoaDon.setText("In hóa đơn");
        InHoaDon.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 88, 128), 3, true));
        InHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(ThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(XoaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(InHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(ConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConLaiField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(TienTra, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TienTraField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TongTienField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(XoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TongTienField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TienTraField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TienTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ConLaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(122, Short.MAX_VALUE)))
        );

        TraCuuPanel.setBackground(new java.awt.Color(0, 88, 128));

        jScrollPane2.setBackground(new java.awt.Color(0, 88, 128));
        jScrollPane2.setBorder(null);

        jTable2.setBackground(new java.awt.Color(0, 88, 128));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ tên khách hàng", "Ngày lập hóa đơn", "Chi Tiết Hóa Đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.setFillsViewportHeight(true);
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.setRowHeight(40);
        jTable2.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable2.setSelectionForeground(new java.awt.Color(0, 88, 128));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowGrid(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout TraCuuPanelLayout = new javax.swing.GroupLayout(TraCuuPanel);
        TraCuuPanel.setLayout(TraCuuPanelLayout);
        TraCuuPanelLayout.setHorizontalGroup(
            TraCuuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TraCuuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        TraCuuPanelLayout.setVerticalGroup(
            TraCuuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TraCuuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TraCuu.setBackground(new java.awt.Color(82, 199, 253));
        TraCuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TraCuu.setForeground(new java.awt.Color(0, 49, 64));
        TraCuu.setText("Nhập mã hóa đơn cần tra cứu");
        TraCuu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 88, 128)));
        TraCuu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TraCuuFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TraCuuFocusLost(evt);
            }
        });

        Tim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Tim.setForeground(new java.awt.Color(0, 88, 128));
        Tim.setText("Tìm");
        Tim.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 88, 128), 3, true));
        Tim.setPreferredSize(new java.awt.Dimension(140, 40));
        Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimActionPerformed(evt);
            }
        });

        KTTC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        KTTC.setForeground(new java.awt.Color(0, 88, 128));
        KTTC.setText("Kết thúc tra cứu");
        KTTC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 88, 128), 3, true));
        KTTC.setPreferredSize(new java.awt.Dimension(140, 40));
        KTTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KTTCActionPerformed(evt);
            }
        });

        maKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maKHActionPerformed(evt);
            }
        });

        hoTen.setBackground(new java.awt.Color(138, 218, 254));
        hoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hoTen.setForeground(new java.awt.Color(0, 49, 64));
        hoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        hoTen.setFocusable(false);

        NLHD.setBackground(new java.awt.Color(138, 218, 254));
        NLHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NLHD.setForeground(new java.awt.Color(0, 49, 64));
        NLHD.setText("Ngày lập hóa đơn (dd/MM/yyyy)");
        NLHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 49, 64)));
        NLHD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NLHDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NLHDFocusLost(evt);
            }
        });

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(TitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(TraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(KTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NLHD))
                            .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TraCuuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(TitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(KTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TraCuuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemSPActionPerformed
        ThemDialog.setVisible(true);
    }//GEN-LAST:event_ThemSPActionPerformed
    private double getTLGB(){
        String sql = "SELECT TILETINHGIABAN FROM THAMSO";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            return rs.getDouble("TILETINHGIABAN");
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return -1;
    }
    private int getSLTTTSB(){
        String sql = "SELECT SOLUONGTONTOITHIEUSAUBAN FROM THAMSO";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            return rs.getInt("SOLUONGTONTOITHIEUSAUBAN");
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return -1;
    }
    private void addToDSSP(String masach, String ts, String tl, String slt, String soluong, String dgn) {
        
            double gianhap = Double.parseDouble(dgn);
            System.out.println("Ma sach: "+masach);
            int slton = Integer.parseInt(slt);
            int sl = Integer.parseInt(soluong);
            int sltttsb = getSLTTTSB();
            if(slton-sl < sltttsb){
                JOptionPane.showMessageDialog(BanSach.this, "Số lượng tồn phải lớn hơn số lượng tối thiểu tồn tối thiểu");
                return;
            }                
            double tlgb = getTLGB();
            if(tlgb==-1){
                JOptionPane.showMessageDialog(BanSach.this, "Đã xảy ra lỗi");
                return;
            }
            danhsachsanpham.add(new CTBan(masach,ts,sl,(gianhap*tlgb),tl));
            loadTable1();
        
    }
    private void ThemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemButtonActionPerformed
        String sl = SL.getText();
        if(sl.isBlank()||Integer.parseInt(sl)<1){
            JOptionPane.showMessageDialog(BanSach.this, "Số lượng phải lớn hơn 0");
            return;
        }
        addToDSSP(MS.getSelectedItem().toString(),TS.getText(),TL.getText(),SLT.getText(),SL.getText(),DGN.getText());
        MS.setSelectedIndex(0);
        SL.setText("");
        ThemDialog.setVisible(false); 
    }//GEN-LAST:event_ThemButtonActionPerformed

    private void XoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaSPActionPerformed
        int rowNo = jTable1.getSelectedRow();
        if(rowNo<0){
            return;
        }
        danhsachsanpham.remove(rowNo);
        loadTable1();      
    }//GEN-LAST:event_XoaSPActionPerformed
    private String getMaKH(String hoten, String sdt){
        String sql = "SELECT MAKH FROM KHACHHANG WHERE tenkh = ? AND SODT = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hoten);
            ps.setString(2, sdt);
            rs = ps.executeQuery();
            if(rs.next()) 
            return rs.getString("MAKH");
            //khong tim thay makh
            
        sql = "SELECT * FROM KHACHHANG";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
        String maKH = "KH"+rowCount;
        // insert khachhang
        sql = "INSERT INTO khachhang (MAKH, TENKH,SODT,TONGNO) VALUES (?,?,?,0)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, maKH);
        ps.setString(2, hoten);
        ps.setString(3, sdt);
        ps.executeUpdate();
        return maKH;
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private double getSNTD(){
        String sql = "SELECT SONOTOIDA FROM THAMSO";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            return rs.getDouble("SONOTOIDA");
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return -1;
    }
    private double getTongNo(String maKH){
        String sql = "SELECT TONGNO FROM KHACHHANG WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,maKH);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("TONGNO");
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;        
    }
    private String getLastestMHD(){
        String sql = "SELECT * FROM HOADON";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            String mhd = "HD"+rowCount;
            return mhd;
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "";
    }
    private String createHD(String maKH, double tongtien, double tientra, double conlai){
        String sql = "INSERT INTO hoadon (MAKH, NGAYLAPHD, TONGTIEN, DATRA, CONLAI) VALUES (?,?,?,?,?)";
        String ngay = NLHD.getText(); //dd/MM/yyyy
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(ngay, inputFormatter);
        String formattedNgay = date.format(outputFormatter);
        Date nn = Date.valueOf(formattedNgay);
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setDate(2, nn);
            ps.setDouble(3, tongtien);
            ps.setDouble(4, tientra);
            ps.setDouble(5, conlai);            
            ps.executeUpdate();
            ps.close();
            sql = "select last_insert_id()";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String mahd = null;
            if(rs.next()){
                mahd = rs.getString(1);
                JOptionPane.showMessageDialog(BanSach.this, "Thêm hóa đơn thành công");
            }
            return mahd;
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private void updateCTHD(String maHD){
        String sql = "INSERT INTO CHITIETHOADON (MAHD, MASACH, SLBAN, ThanhTien) VALUES (?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            for(CTBan sach : danhsachsanpham){
                ps.setString(1, maHD);
                ps.setString(2, sach.getMaSach());
                ps.setInt(3, sach.getSoLuong());
                ps.setDouble(4, sach.getGia());
                ps.executeUpdate();            }
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateSach(){
        String sql = "UPDATE sach SET SLTON = SLTON-? WHERE MASACH = ?";
        try {
            ps = conn.prepareStatement(sql);
            for(CTBan sach : danhsachsanpham){
                ps.setInt(1, sach.getSoLuong());
                ps.setString(2, sach.getMaSach());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateTongNoKH(String maKH, double conlai){
        String sql = "UPDATE khachhang SET TONGNO = TONGNO-? WHERE MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            
                ps.setDouble(1, conlai);
                ps.setString(2, maKH);
                ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void InHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InHoaDonActionPerformed
        if("Chọn mã khách hàng".equals(maKH.getSelectedItem().toString())||TienTraField.getText().isBlank()||danhsachsanpham.isEmpty()||ConLaiField.getText().isBlank()||"Ngày lập hóa đơn (dd/MM/yyyy)".equals(NLHD.getText())){
            JOptionPane.showMessageDialog(BanSach.this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        String makh = maKH.getSelectedItem().toString();
        double sntd = getSNTD(); //số nợ tối đa
        double tongno = getTongNo(makh);
        double conlai = Double.parseDouble(ConLaiField.getText());
        if(conlai < 0 && tongno-conlai > sntd){
            JOptionPane.showMessageDialog(BanSach.this, "Khách hàng không được nợ quá số nợ tối đa");
            return;
        }
        double tongtien  = Double.parseDouble(TongTienField.getText());
        double tientra = Double.parseDouble(TienTraField.getText());
        String mahd = createHD(makh, tongtien, tientra, conlai);
        updateCTHD(mahd);
        updateSach();
        if (conlai<0) updateTongNoKH(makh,conlai);
        danhsachsanpham.clear();
        TongTienField.setText("");
        TienTraField.setText("");
        ConLaiField.setText("");
        hoTen.setText("");
        maKH.setSelectedItem("Chọn mã khách hàng");
        NLHD.setText("Ngày lập hóa đơn (dd/MM/yyyy)");
        loadTable1();
        loadTable2("");
    }//GEN-LAST:event_InHoaDonActionPerformed
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
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void TraCuuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TraCuuFocusGained
        if("Nhập mã hóa đơn cần tra cứu".equals(TraCuu.getText())){
            TraCuu.setText("");
        }
    }//GEN-LAST:event_TraCuuFocusGained
    private void TraCuuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TraCuuFocusLost
        if("".equals(TraCuu.getText())){
            TraCuu.setText("Nhập mã hóa đơn cần tra cứu");
        }
    }//GEN-LAST:event_TraCuuFocusLost
    private void TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimActionPerformed
        String maHD = TraCuu.getText();
        if ("Nhập mã hóa đơn cần tra cứu".equals(maHD)){
            JOptionPane.showMessageDialog(BanSach.this, "Vui lòng nhập mã hóa đơn");
            return;
        }
        TraCuu.setText("Nhập mã hóa đơn cần tra cứu");
        loadTable2(maHD);
    }//GEN-LAST:event_TimActionPerformed
    private void getGeneral(String mahd){
        String sql  = "SELECT * FROM HOADON JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH WHERE MAHD = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            rs = ps.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(rs.next()){
                hoTen.setText(rs.getString("TENKH"));
                TienTraField.setText(Double.toString(rs.getDouble("DATRA")));
                ConLaiField.setText(Double.toString(rs.getDouble("CONLAI")));
                NLHD.setText(sdf.format(rs.getDate("NGAYLAPHD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void getDetail(String mahd){
        String sql = "SELECT * FROM CHITIETHOADON JOIN SACH ON CHITIETHOADON.MASACH = SACH.MASACH JOIN DAUSACH ON DAUSACH.MADAUSACH = SACH.MADAUSACH JOIN THELOAI ON THELOAI.MATHELOAI = DAUSACH.MATHELOAI WHERE MAHD = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            rs = ps.executeQuery();
            while(rs.next()){
                chitiethoadon.add(new CTBan(rs.getString("MASACH"),rs.getString("TENDAUSACH"),rs.getInt("SLBAN"),rs.getDouble("THANHTIEN"),rs.getString("TENTHELOAI")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if(evt.getClickCount()==2){ //bấm đúp chuột
            if(jTable2.getSelectedColumn()==4 ){ // cột CTHD
                if(!danhsachsanpham.isEmpty()){
                    JOptionPane.showMessageDialog(BanSach.this, "Vui lòng hoàn tất hóa đơn đang ghi");
                    return;
                }
                setMode(1);
                danhsachsanpham.clear();
            }
            String maHD = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString();
            getGeneral(maHD); // chỉnh tên KH, ngày, tổng tiền, tiền trả, còn lại
            getDetail(maHD); //chỉnh danh sách hàng trong hóa đơn
            loadTable1();        
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void KTTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KTTCActionPerformed
        setMode(0);
        hoTen.setText("");
        maKH.setSelectedItem("Chọn mã khách hàng");
        TongTienField.setText("");
        TienTraField.setText("");
        ConLaiField.setText("");
        NLHD.setText("Ngày lập hóa đơn (dd/MM/yyyy)");
        chitiethoadon.clear();
        loadTable1();
        loadTable2("");
    }//GEN-LAST:event_KTTCActionPerformed

    private void TienTraFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TienTraFieldActionPerformed
        double tongtien  = Double.parseDouble(TongTienField.getText());
        double tientra = Double.parseDouble(TienTraField.getText());
        double conlai = tientra-tongtien;
        ConLaiField.setText(String.valueOf(conlai));
    }//GEN-LAST:event_TienTraFieldActionPerformed

    private void maKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maKHActionPerformed
        if("Chọn mã khách hàng".equals(maKH.getSelectedItem().toString())){
            hoTen.setText("Họ và tên khách hàng");
            return;
        }
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

    private void MSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MSActionPerformed
        if("Chọn mã sách".equals(MS.getSelectedItem().toString())){
            TS.setText("");
            TL.setText("");
            SLT.setText("");
            DGN.setText("");
            return;
        }
        String sql = "SELECT * FROM SACH JOIN DAUSACH ON DAUSACH.MADAUSACH = SACH.MADAUSACH JOIN THELOAI ON THELOAI.MATHELOAI = DAUSACH.MATHELOAI WHERE MASACH = ?";
        String masach = MS.getSelectedItem().toString();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, masach);
            rs = ps.executeQuery();
            while(rs.next()){
                TS.setText(rs.getString("TENDAUSACH"));
                TL.setText(rs.getString("TENTHELOAI"));
                SLT.setText(rs.getString("SLTON"));
                DGN.setText(rs.getString("DONGIANHAP"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuTien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MSActionPerformed

    private void NLHDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NLHDFocusGained
        if("Ngày lập hóa đơn (dd/MM/yyyy)".equals(NLHD.getText())){
            NLHD.setText("");
        }
    }//GEN-LAST:event_NLHDFocusGained

    private void NLHDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NLHDFocusLost
        if("".equals(NLHD.getText())){
            NLHD.setText("Ngày lập hóa đơn (dd/MM/yyyy)");
        }
    }//GEN-LAST:event_NLHDFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ConLai;
    private javax.swing.JTextField ConLaiField;
    private javax.swing.JTextField DGN;
    private javax.swing.JButton InHoaDon;
    private javax.swing.JButton KTTC;
    private javax.swing.JComboBox<String> MS;
    private javax.swing.JTextField NLHD;
    private javax.swing.JTextField SL;
    private javax.swing.JTextField SLT;
    private javax.swing.JTextField TL;
    private javax.swing.JTextField TS;
    private javax.swing.JButton ThemButton;
    private javax.swing.JDialog ThemDialog;
    private javax.swing.JButton ThemSP;
    private javax.swing.JLabel TienTra;
    private javax.swing.JTextField TienTraField;
    private javax.swing.JButton Tim;
    private com.swing.PanelBorder TitlePanel;
    private javax.swing.JLabel TongTien;
    private javax.swing.JTextField TongTienField;
    private javax.swing.JTextField TraCuu;
    private com.swing.PanelBorder TraCuuPanel;
    private javax.swing.JButton XoaSP;
    private com.component.Background background1;
    private javax.swing.JTextField hoTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> maKH;
    private com.swing.PanelBorder panelBorder1;
    private com.swing.PanelBorder tablePanel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    
}
