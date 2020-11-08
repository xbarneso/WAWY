/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author xbarn
 */

import MainClasses.Controller;
import Utilities.Mail;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.chart.ChartUtils;
public class Conexions_graph extends javax.swing.JFrame {
    
    private Object data[][];
    private HashMap<String, Integer> dataH = new HashMap<>();
    private HashMap<String, Integer> dataT = new HashMap<>();
    private Controller contr;
    
    public Conexions_graph() {
        initComponents();
    }

    public Controller getContr() {
        return contr;
    }

    public void setContr(Controller contr) {
        this.contr = contr;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_barres = new javax.swing.JButton();
        btn_pie = new javax.swing.JButton();
        chbx_send = new javax.swing.JCheckBox();

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\xbarn\\Documents\\IOC\\Projecte\\Imatges\\Backgrounds\\fons_conexions_graph.png")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 255));
        setForeground(new java.awt.Color(0, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 255, 255));

        btn_barres.setText("Diagrama Barres");
        btn_barres.setActionCommand("Diagrama Pie");
        btn_barres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_barresActionPerformed(evt);
            }
        });

        btn_pie.setText("Diagrama Pie");
        btn_pie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pieActionPerformed(evt);
            }
        });

        chbx_send.setLabel("Send Pic");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_pie, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_barres)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbx_send)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pie)
                    .addComponent(btn_barres)
                    .addComponent(chbx_send))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     /**
     * Aquest mètode escoltarà el butto per a crear el diagrama en forma de formatge. 
     * Un cop clicat es buscaràn les dades necessaries per a crear-lo. 
     * 
     */
    private void btn_pieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pieActionPerformed
       
        int ample = 1250; 
        int alt = 750;
        
        //Aquí agadarem les dades. 
        get_Data_Diagrams();

        DefaultPieDataset datasetPie = new DefaultPieDataset();
        //Per cada objecte que ens retorna l'api en forma de json que ja hem transformat a map, ho afegirem. 
        dataH.forEach((key, value) -> datasetPie.setValue(key, value));
        
        //creem el chart. 
        JFreeChart chart = ChartFactory.createPieChart3D("Connexions per Country", // titol
            datasetPie, // data
            false, // llegenda
            true,
            false);
        // el passem a 3D
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);
        //Creem el frame pel diagrama i el fem visible amb les mides. 
        ChartFrame CP = new ChartFrame("Diagrama PIE", chart);
        CP.setVisible(true);
        CP.setSize(ample, alt);
        
        //Si han clickat en send, s'enviarà. 
        if (chbx_send.isSelected()) {
            try {
                //Si no existeix el file, res, sino, l'esborrem ja que serà de dades velles. 
                if (new File("C:\\Users\\xbarn\\Documents\\NetBeansProjects\\mavenproject1\\WAWY_Xbarnes_1\\PieChart.jpeg").exists()) {
                    new File("C:\\Users\\xbarn\\Documents\\NetBeansProjects\\mavenproject1\\WAWY_Xbarnes_1\\PieChart.jpeg").delete();
                }
                //El guardem com a imatge el chart. 
                File pieChart = new File("PieChart.jpeg");
                ChartUtils.saveChartAsJPEG(pieChart, chart, ample, alt);
                //Demanem direccio email i l'enviem. 
                Question_Frame jEmailGetter = new Question_Frame("A quina direccio email vols enviar el report?");
                Mail mail = new Mail();
                mail.envia_mail(jEmailGetter.getAnswer(), "C:\\Users\\xbarn\\Documents\\NetBeansProjects\\mavenproject1\\WAWY_Xbarnes_1\\PieChart.jpeg");
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_btn_pieActionPerformed
    /**
     * Aquest mètode escoltarà el butto per a crear el diagrama en forma de
     * barres. Un cop clicat es buscaràn les dades necessaries per a crear-lo.
     *
     */
    private void btn_barresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_barresActionPerformed

        int ample = 1750;
        int alt = 750;

        //Aquí agadarem les dades. 
        get_Data_Diagrams();

        DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
        //Per cada objecte que ens retorna l'api en forma de json que ja hem transformat a map, ho afegirem. 

        dataH.forEach((key, value) -> datasetBar.setValue(value, "", key));
        //creem el chart amb les dades
        JFreeChart chart = ChartFactory.createBarChart("Connexions per país", // chart title
                "Pais",
                "Numero Conexions", // range axis label
                datasetBar, // data
                PlotOrientation.VERTICAL, // orientation
                true,
            true,
            true
        );

        CategoryPlot  plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.black);
        plot.setForegroundAlpha(0.60f);
        ChartFrame CP = new ChartFrame("Diagrama BAR", chart);

        CP.setVisible(true);
        CP.setSize(1750, 750);
        //Si l'hem d'enviar el guardem com a imatge, ... 
        if (chbx_send.isSelected()) {
            try {

                if (new File("C:\\Users\\xbarn\\Documents\\NetBeansProjects\\mavenproject1\\WAWY_Xbarnes_1\\BarChart.jpeg").exists()) {
                    new File("C:\\Users\\xbarn\\Documents\\NetBeansProjects\\mavenproject1\\WAWY_Xbarnes_1\\BarChart.jpeg").delete();
                }
                
                File barChart = new File("BarChart.jpeg");
                ChartUtils.saveChartAsJPEG(barChart, chart, ample, alt);
                Question_Frame jEmailGetter = new Question_Frame("A quina direccio email vols enviar el report?");
                Mail mail = new Mail();
                mail.envia_mail(jEmailGetter.getAnswer(), "C:\\Users\\xbarn\\Documents\\NetBeansProjects\\mavenproject1\\WAWY_Xbarnes_1\\BarChart.jpeg");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btn_barresActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

     /**
     * Aquest mètode omplirà les dades necessaries demnanant les dades al controller qui organitzarà el procés per a obtenir-les de l'api (server)
     *
     */
    
    private void get_Data_Diagrams(){
        
        //data base desde la qual va començar a funcionar el server, aprox. 
        data = this.contr.getConnections("2019-10-01");
        dataT = this.fillData(data);
        dataH = this.filterData(dataT);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Conexions_graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Conexions_graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Conexions_graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Conexions_graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Conexions_graph().setVisible(true);
            }
        });
    }
    
     /**
     * Aquest mètode rebrà unb objecte bidimensional i en crearà un map amb les dades sense duplicitats. 
     * Farà l'agrupació per countries. 
     * @param data objecte bidimensional amb totes les dades retornades pel server. 
     * @return hashmap amb les dades resum per country. 
     */
    
    private HashMap<String, Integer> fillData(Object[][] data) {
        HashMap<String, Integer> returnHash = new HashMap<String, Integer>();
        String key;
        int i;

        for (int row = 0; row < data.length; row++) {

            if (returnHash.containsKey(data[row][1])) {
                key = data[row][1].toString();
                returnHash.put(key, returnHash.get(key) + 1);
            } else {
                if (data[row][1] == null) {

                } else { 
                    key = data[row][1].toString();
                    returnHash.put(key, 1);
                }
            }
        }
        return returnHash;
    }
    
     /**
     * Aquest mètode rebrà un hashmap amb totes les dades i el fltrarà per paisos amb mes de deu conexions (per a fer els diagrames mes bonics. Sino queda tot massa apretat i no ens interessa
     * mostra els païssos que han fet una connexió. 
     * @param data amb el map am,b totes les dades
     * @return hashmap amb les dades filtrades.
     */
    
    private HashMap<String, Integer> filterData(HashMap<String, Integer> data) {
        
        HashMap<String, Integer> returnHash = new HashMap<String, Integer>();
        String key;
        int i;
        Iterator it = data.entrySet().iterator();
        
        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)it.next();
            
            if ((int)pair.getValue()>=10) {
                returnHash.put(pair.getKey().toString(),(int)pair.getValue());
            } 
        }
        return returnHash;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_barres;
    private javax.swing.JButton btn_pie;
    private javax.swing.JCheckBox chbx_send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
