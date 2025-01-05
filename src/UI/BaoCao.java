/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import static UI.DatabaseConnect.getJDBCConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

class Sach {

    private String maSach;
    private int soLuong;
    
    public Sach(String maSach,int soLuong) {
        this.maSach = maSach;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

   
    public int getSoLuong() {
        return soLuong;
    }
}
class KH {
    private String maKH;
    private double no;
    public KH(String maKH, double no) {
        this.maKH = maKH;
        this.no = no;
    }

    public String getMaKH() {
        return maKH;
    }

    public double getNo() {
        return no;
    }
}

public class BaoCao extends javax.swing.JPanel {
    private boolean mode = true;   //true- tồn ; false - nợ
    private JTextField date;
    Connection conn = getJDBCConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Sach> list = new ArrayList<>();
    List<KH> khList = new ArrayList<>();
    public BaoCao() {
        initComponents();
        setTable1Header();
        
    }
    private void setTable1Header(){
        Table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
    private void layDuLieuTuCoSoDuLieu(int month, int year) {
        String maThang = Integer.toString(month)+"/"+Integer.toString(year);
        DefaultTableModel model = new DefaultTableModel(){
            //không cho chỉnh sửa trong bảng
            @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        }; 
        if(mode){// tồn
            String tieude[] = new String[]{ "STT", "Mã sách","Tên sách", "Tồn đầu", "Phát sinh", "Tồn cuối"};
            model.setColumnIdentifiers(tieude);
            String sql = "SELECT * FROM BAOCAOTONKHO JOIN SACH ON SACH.MASACH = BAOCAOTONKHO.MASACH JOIN DAUSACH ON DAUSACH.MADAUSACH = SACH.MADAUSACH WHERE MABCTK = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1,maThang);
                rs = ps.executeQuery();
                int i = 1;
            while(rs.next()){
                model.addRow(new Object[]{i,rs.getString("MASACH"),rs.getString("TENDAUSACH"), rs.getInt("TONDAU"), rs.getInt("PHATSINH"), rs.getInt("TONCUOI")});
                i++;
            }
            Table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        else{   //nợ
            String tieude[] = new String[]{ "STT", "Mã KH","Tên KH", "Nợ đầu","Phát sinh", "Nợ cuối"};
            model.setColumnIdentifiers(tieude);
            String sql = "SELECT * FROM BAOCAOCONGNO JOIN KHACHHANG ON KHACHHANG.MAKH = BAOCAOCONGNO.MAKH WHERE MABCCN = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1,maThang);
                rs = ps.executeQuery();
            DecimalFormat df = new DecimalFormat("#.##");
            int i = 1;
            while(rs.next()){
                model.addRow(new Object[]{i,rs.getString("MAKH"),rs.getString("TENKH"), df.format(rs.getFloat("NODAU")), df.format(rs.getFloat("PHATSINH")),  df.format(rs.getFloat("NOCUOI"))});
                    i++;
            }
            Table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new com.component.Background();
        panelBorder1 = new com.swing.PanelBorder();
        title = new javax.swing.JLabel();
        panelBorder3 = new com.swing.PanelBorder();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table = new rojerusan.RSTableMetro();
        Tim = new rojerusan.RSMaterialButtonRectangle();
        Thang = new com.toedter.calendar.JMonthChooser();
        Nam = new com.toedter.calendar.JYearChooser();
        Tao = new rojerusan.RSMaterialButtonRectangle();

        background1.setPreferredSize(new java.awt.Dimension(850, 700));

        panelBorder1.setBackground(new java.awt.Color(0, 88, 128));
        panelBorder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder1MouseClicked(evt);
            }
        });

        title.setBackground(new java.awt.Color(0, 88, 128));
        title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("BÁO CÁO TỒN");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelBorder3.setBackground(new java.awt.Color(0, 88, 128));

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
        Table.setFillsViewportHeight(true);
        Table.setFuenteFilas(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Table.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Table.setFuenteHead(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Table.setGridColor(new java.awt.Color(255, 255, 255));
        Table.setRowHeight(40);
        Table.setSelectionBackground(new java.awt.Color(51, 51, 51));
        Table.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(Table);

        Tim.setText("Tìm kiếm");
        Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimActionPerformed(evt);
            }
        });

        Thang.setOpaque(false);

        Nam.setOpaque(false);

        Tao.setBackground(new java.awt.Color(255, 255, 255));
        Tao.setForeground(new java.awt.Color(0, 112, 192));
        Tao.setText("TẠO BÁO CÁO THÁNG NÀY");
        Tao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                        .addComponent(Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Tao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tao, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimActionPerformed
       // TODO add your handling code here:
        int selectedMonth = Thang.getMonth()+1;
        int selectedYear = Nam.getYear();
        layDuLieuTuCoSoDuLieu(selectedMonth, selectedYear);
    }//GEN-LAST:event_TimActionPerformed

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked
        if(!mode){
            title.setText("BÁO CÁO TỒN");
            mode = true;
        }               
        else{
            title.setText("BÁO CÁO CÔNG NỢ");
            mode = false;
        }
        DefaultTableModel model = new DefaultTableModel(){
            //không cho chỉnh sửa trong bảng
            @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        }; 
        if(mode){// tồn
            String tieude[] = new String[]{ "STT", "Mã sách","Tên sách", "Tồn đầu", "Phát sinh", "Tồn cuối"};
            model.setColumnIdentifiers(tieude);
        }
        else{   //nợ
             String tieude[] = new String[]{ "STT", "Mã KH","Tên KH", "Nợ đầu","Phát sinh", "Nợ cuối"};
            model.setColumnIdentifiers(tieude);
        }
        Table.setModel(model);
    }//GEN-LAST:event_panelBorder1MouseClicked
    private boolean isExist(String maThang){
        if(mode){// tồn

            String sql = "SELECT * FROM BAOCAOTONKHO WHERE MABCTK = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1,maThang);
                rs = ps.executeQuery();
     
                if(rs.next())return true;
            } catch (SQLException ex) {
                Logger.getLogger(QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        else{   //nợ
           
            String sql = "SELECT * FROM BAOCAOCONGNO WHERE MABCCN = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1,maThang);
                rs = ps.executeQuery();
                if(rs.next())return true;
            } catch (SQLException ex) {
                Logger.getLogger(QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        return false;
    }
    private int getTonCuoiThangTruoc(String maSach, String maThangTruoc){
            String sql = "SELECT TONCUOI FROM BAOCAOTONKHO WHERE MABCTK = ? AND MASACH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,maThangTruoc);
            ps.setString(2,maSach);
            rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("TONCUOI");
        } catch (SQLException ex) {
            Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return 0;
    }
    private int addSach(String maThang, String maSach, int tondau, int phatsinh, int toncuoi){
        String sql = "INSERT INTO BAOCAOTONKHO (MABCTK, MASACH, TONDAU, PHATSINH, TONCUOI) VALUES (?,?,?,?,?)";
        try {
            Connection conn2 = DatabaseConnect.getJDBCConnection(); 
            PreparedStatement ps2 = conn2.prepareStatement(sql);
            ps2.setString(1, maThang);
            ps2.setString(2, maSach);
            ps2.setInt(3, tondau);
            ps2.setInt(4, phatsinh);
            ps2.setInt(5,toncuoi);
            int result = ps2.executeUpdate();
            ps2.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private double getNoCuoiThangTruoc(String maKH, String maThangTruoc){
        String sql = "SELECT NOCUOI FROM BAOCAOCONGNO WHERE MABCCN = ? AND MAKH = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,maThangTruoc);
            ps.setString(2,maKH);
            rs = ps.executeQuery();
            if(rs.next()) return rs.getDouble("NOCUOI");
        } catch (SQLException ex) {
            Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return 0.0;
    }
    private int addKH(String maThang, String maKH, double nodau, double phatsinh, double nocuoi){
        String sql = "INSERT INTO BAOCAOCONGNO (MABCCN, MAKH, NODAU, PHATSINH, NOCUOI) VALUES (?,?,?,?,?)";
        try {
            Connection conn2 = DatabaseConnect.getJDBCConnection(); 
            PreparedStatement ps2 = conn2.prepareStatement(sql);
            ps2.setString(1, maThang);
            ps2.setString(2, maKH);
            ps2.setDouble(3, nodau);
            ps2.setDouble(4, phatsinh);
            ps2.setDouble(5,nocuoi);
            int result = ps2.executeUpdate();
            ps2.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private void lapBaoCaoThangNay(String maThang){
        LocalDate currentDate = LocalDate.now(); 
        int month = currentDate.getMonthValue()-1;
        int year = currentDate.getYear();
        if(month==0){
            month=12;
            year-=year;
        }
        String maThangTruoc = Integer.toString(month)+"/"+Integer.toString(year);
        if(mode){       //tồn
            String sql = "SELECT MASACH,SLTON FROM SACH";
            try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            list.clear();
            while (rs.next()){
                list.add(new Sach(rs.getString("MASACH"),rs.getInt("SLTON")));
            }
            for(Sach index : list){
                String maSach = index.getMaSach();
                int toncuoi = index.getSoLuong();
                int tondau = getTonCuoiThangTruoc(maSach,maThangTruoc); 
                int phatsinh = toncuoi-tondau;
                int result = addSach(maThang,maSach,tondau,phatsinh,toncuoi);
                if(result==1){
                    System.out.println("Them thanh cong:"+maSach);
                }else {
                    System.out.println("Thêm thất bại: " + maSach);
                }
            }
            JOptionPane.showMessageDialog(BaoCao.this, "Thêm báo cáo tồn kho thành công");
            rs.close();
            ps.close();
            } catch (SQLException ex) {
                System.err.println("Lỗi khi thực thi truy vấn: " + ex.getMessage());
            }           
        }
        else{   //nợ
            String sql = "SELECT MAKH,TONGNO FROM KHACHHANG";
            try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            list.clear();
            while (rs.next()){
                khList.add(new KH(rs.getString("MAKH"),rs.getDouble("TONGNO")));
            }
            for(KH index : khList){
                String maKH = index.getMaKH();
                double nocuoi = index.getNo();
                double nodau = getNoCuoiThangTruoc(maKH,maThangTruoc); 
                double phatsinh = nocuoi-nodau;
                int result = addKH(maThang,maKH,nodau,phatsinh,nocuoi);
                if(result==1){
                    System.out.println("Them thanh cong:"+maKH);
                }else {
                    System.out.println("Thêm thất bại: " + maKH);
                }
            }
            JOptionPane.showMessageDialog(BaoCao.this, "Thêm báo cáo công nợ thành công");
            rs.close();
            ps.close();
            } catch (SQLException ex) {
                System.err.println("Lỗi khi thực thi truy vấn: " + ex.getMessage());
            }           
        }
    }
    private void TaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaoActionPerformed
        LocalDate currentDate = LocalDate.now(); 
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        String maThang = Integer.toString(month)+"/"+Integer.toString(year);
        if(isExist(maThang)){
            JOptionPane.showMessageDialog(BaoCao.this, "Báo cáo tháng này đã tồn tại");
            return;
        }
        lapBaoCaoThangNay(maThang);
    }//GEN-LAST:event_TaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser Nam;
    private rojerusan.RSTableMetro Table;
    private rojerusan.RSMaterialButtonRectangle Tao;
    private com.toedter.calendar.JMonthChooser Thang;
    private rojerusan.RSMaterialButtonRectangle Tim;
    private com.component.Background background1;
    private javax.swing.JScrollPane jScrollPane4;
    private com.swing.PanelBorder panelBorder1;
    private com.swing.PanelBorder panelBorder3;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}