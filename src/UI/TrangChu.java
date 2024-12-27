/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import static UI.TrangChu.BieuDoCotSachGroupLayout.createChartPanelSach;
import static UI.TrangChu.BieuDoCotdoanhthuGroupLayout.createChartPanel;
import com.model.Model_Card;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
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
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/stock.png")), "TỔNG SỐ SÁCH", "2000", "Chi tiết"));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/profit.png")), "DOANH THU", "20.000.000", "Chi tiết"));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/tonkho.png")), "TỒN KHO", "150", "Báo cáo chi tiết"));
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

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);
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

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JLayeredPane();
        card1 = new com.component.Card();
        card2 = new com.component.Card();
        card3 = new com.component.Card();
        Doanhthu = new rojerusan.RSMaterialButtonRectangle();
        Sach = new rojerusan.RSMaterialButtonRectangle();

        setPreferredSize(new java.awt.Dimension(850, 700));
        setVerifyInputWhenFocusTarget(false);

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

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        Doanhthu.setBackground(new java.awt.Color(255, 255, 255));
        Doanhthu.setForeground(new java.awt.Color(0, 88, 128));
        Doanhthu.setText("DOANH THU");
        Doanhthu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Doanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoanhthuActionPerformed(evt);
            }
        });

        Sach.setBackground(new java.awt.Color(255, 255, 255));
        Sach.setForeground(new java.awt.Color(0, 88, 128));
        Sach.setText("SÁCH");
        Sach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(Doanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Doanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DoanhthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoanhthuActionPerformed
        // TODO add your handling code here:
        jPanel1.removeAll();
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(createChartPanel(), java.awt.BorderLayout.CENTER);
        jPanel1.revalidate();jPanel1.repaint();
    }//GEN-LAST:event_DoanhthuActionPerformed

    private void SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SachActionPerformed
        // TODO add your handling code here:
        jPanel1.removeAll();jPanel1.repaint();  
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(createChartPanelSach(), java.awt.BorderLayout.CENTER);
        jPanel1.revalidate();jPanel1.repaint();
    }//GEN-LAST:event_SachActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Doanhthu;
    private rojerusan.RSMaterialButtonRectangle Sach;
    private com.component.Card card1;
    private com.component.Card card2;
    private com.component.Card card3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}
