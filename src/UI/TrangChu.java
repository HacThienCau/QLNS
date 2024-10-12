/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import static UI.TrangChu.BieuDoCotSachGroupLayout.createChartPanelSach;
import static UI.TrangChu.BieuDoCotdoanhthuGroupLayout.createChartPanel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ACER
 */
public class TrangChu extends javax.swing.JPanel {

    /**
     * Creates new form Home
     */
       public TrangChu() {
        initComponents();
         setOpaque(false);
         jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(createChartPanel(), java.awt.BorderLayout.CENTER);
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#CCF1FF"), 0, getHeight(), Color.decode("#00ABFD"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth()-20, getHeight());
        super.paintComponent(grphcs);
    }
    public class BieuDoCotSachGroupLayout 
    {
        public static void main(String[] args) 
        {
            JFrame frame = new JFrame("Biểu đồ cột trong JPanel7");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

        // Sử dụng BorderLayout cho jPanel7
            JPanel jPanel1 = new JPanel(new java.awt.BorderLayout());

        // Thêm biểu đồ cột vào jPanel7
        jPanel1.add(createChartPanelSach(), java.awt.BorderLayout.CENTER);  // Thêm ChartPanel vào jPanel7
        jPanel1.revalidate();
        jPanel1.repaint();

        // Thêm jPanel7 vào JFrame
        frame.add(jPanel1);
        frame.setVisible(true);
        }
    

    // Hàm tạo biểu đồ cột
    public static JPanel createChartPanelSach() {
        // Tạo dữ liệu cho biểu đồ cột
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(50, "Sách mượn", "January");
        dataset.addValue(40, "Sách trả", "January");
        dataset.addValue(70, "Sách nhập", "January");
        
        dataset.addValue(60, "Sách mượn", "February");
        dataset.addValue(80, "Sách trả", "February");
        dataset.addValue(90, "Sách nhập", "February");
        
        dataset.addValue(30, "Sách mượn", "March");
        dataset.addValue(50, "Sách trả", "March");
        dataset.addValue(40, "Sách nhập", "March");
        
        dataset.addValue(20, "Sách mượn", "April");
        dataset.addValue(60, "Sách trả", "April");
        dataset.addValue(80, "Sách nhập", "April");
        
        dataset.addValue(40, "Sách mượn", "May");
        dataset.addValue(70, "Sách trả", "May");
        dataset.addValue(60, "Sách nhập", "May");
        
        dataset.addValue(30, "Sách mượn", "June");
        dataset.addValue(40, "Sách trả", "June");
        dataset.addValue(90, "Sách nhập", "June");

        // Create chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê sách",
                "Tháng",
                "Số lượng",
                dataset);

        // Tạo biểu đồ cột
        JFreeChart chart = ChartFactory.createBarChart(
            "Doanh thu theo tháng", // Tiêu đề biểu đồ
            "Tháng",                // Trục X
            "Doanh thu (VND)",      // Trục Y
            dataset                 // Dữ liệu
        );

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 391)); // Kích thước phù hợp với jPanel7

        return chartPanel;
    }
    }
    public class BieuDoCotdoanhthuGroupLayout 
    {
        public static void main(String[] args) 
        {
            JFrame frame = new JFrame("Biểu đồ cột trong JPanel7");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

        // Sử dụng BorderLayout cho jPanel7
            JPanel jPanel1 = new JPanel(new java.awt.BorderLayout());

        // Thêm biểu đồ cột vào jPanel7
        jPanel1.add(createChartPanel(), java.awt.BorderLayout.CENTER);  // Thêm ChartPanel vào jPanel7
        jPanel1.revalidate();
        jPanel1.repaint();

        // Thêm jPanel7 vào JFrame
        frame.add(jPanel1);
        frame.setVisible(true);
        }
    

    // Hàm tạo biểu đồ cột
    public static JPanel createChartPanel() {
        // Tạo dữ liệu cho biểu đồ cột
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1000, "Doanh thu", "Tháng 1");
        dataset.addValue(1500, "Doanh thu", "Tháng 2");
        dataset.addValue(2000, "Doanh thu", "Tháng 3");
        dataset.addValue(2500, "Doanh thu", "Tháng 4");
        dataset.addValue(3000, "Doanh thu", "Tháng 5");

        // Tạo biểu đồ cột
        JFreeChart chart = ChartFactory.createBarChart(
            "Doanh thu theo tháng", // Tiêu đề biểu đồ
            "Tháng",                // Trục X
            "Doanh thu (VND)",      // Trục Y
            dataset                 // Dữ liệu
        );

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 391)); // Kích thước phù hợp với jPanel7

        return chartPanel;
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

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(850, 700));
        setVerifyInputWhenFocusTarget(false);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Thông báo hệ thống");

        jList1.setBackground(new java.awt.Color(204, 255, 255));
        jList1.setBorder(new javax.swing.border.MatteBorder(null));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Doanh thu tháng 9", "Đơn hàng nhập", "5 Ngày tới báo cáo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButton2.setText("Đơn hàng");

        jList2.setBackground(new java.awt.Color(204, 255, 255));
        jList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Anh Huy", "Chị Vy", "Anh Long", "Chị Phương", "Chị Hương" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Doanh thu");
        jButton3.setPreferredSize(new java.awt.Dimension(150, 50));

        jButton4.setBackground(new java.awt.Color(153, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Sách nhập");
        jButton4.setPreferredSize(new java.awt.Dimension(150, 50));

        jButton5.setBackground(new java.awt.Color(102, 255, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Sách bán");
        jButton5.setPreferredSize(new java.awt.Dimension(150, 50));

        jButton6.setBackground(new java.awt.Color(51, 255, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Tồn kho");
        jButton6.setPreferredSize(new java.awt.Dimension(150, 50));

        jScrollPane3.setBackground(new java.awt.Color(0, 255, 255));

        jTable1.setBackground(new java.awt.Color(204, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Doanh thu", "Tháng trước", "Tháng này"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(102, 204, 255));
        jScrollPane3.setViewportView(jTable1);

        jScrollPane4.setBackground(new java.awt.Color(0, 255, 255));

        jTable2.setBackground(new java.awt.Color(204, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Khách hàng", "Tiền nợ", "Tiền "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        jButton7.setBackground(new java.awt.Color(204, 204, 255));
        jButton7.setText("Doanh thu");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 204, 255));
        jButton8.setText("Sách");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 34, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8))
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code        jPanel1.removeAll();jPanel1.repaint();  
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(createChartPanel(), java.awt.BorderLayout.CENTER);
        jPanel1.revalidate();jPanel1.repaint();

    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
                jPanel1.removeAll();jPanel1.repaint();  
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(createChartPanelSach(), java.awt.BorderLayout.CENTER);
        jPanel1.revalidate();jPanel1.repaint();

    }//GEN-LAST:event_jButton8MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
