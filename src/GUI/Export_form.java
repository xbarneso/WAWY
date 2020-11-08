
package GUI;

import Utilities.Mail;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author xbarn
 */
public class Export_form extends javax.swing.JFrame {

    private String[] nomColumnes = {"IP", "Country", "City", "Date"};
    private Object[][] data;
    private String fileName;
    /**
     * Creates new form Export_form
     */
    public Export_form() {
        initComponents();
    }
    
    public Export_form(Object[][]data){
        this.data = data;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_email = new javax.swing.JButton();
        btn_CSV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btn_email.setIcon(new javax.swing.ImageIcon("C:\\Users\\xbarn\\Downloads\\email.png")); // NOI18N
        btn_email.setBorderPainted(false);
        btn_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_emailActionPerformed(evt);
            }
        });

        btn_CSV.setIcon(new javax.swing.ImageIcon("C:\\Users\\xbarn\\Downloads\\carpeta.png")); // NOI18N
        btn_CSV.setBorderPainted(false);
        btn_CSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_CSV))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_CSV)
            .addComponent(btn_email)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
     /**
     * Aquest mètode s'emncarregarà de cridar a guardar csv
     */
    private void btn_CSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CSVActionPerformed

        try {

            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);
            fc.showDialog(this, "Select Path");
            String path = fc.getSelectedFile().getAbsolutePath();

            createCSV(path, data);

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_CSVActionPerformed

     /**
     * Aquest mètode s'encarregarà de fer les crides pertinents per a enviar el file per email. 
     */
    
    private void btn_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_emailActionPerformed
        Question_Frame jEmailGetter = new Question_Frame("A quina direccio email vols enviar el report?");
        Mail mail = new Mail();
        createCSV("C:\\Users\\xbarn\\Documents\\", data);
        mail.envia_mail(jEmailGetter.getAnswer(), "C:\\Users\\xbarn\\Documents\\" + fileName);

    }//GEN-LAST:event_btn_emailActionPerformed
    /**
     * Aquest mètode agafarà les dades de les conexions i crearà un fitxer.
     * @param data amb les dades pel file. 
     * @param pathDestination amb la path a la que guardar el fitxer.
     */
    public void createCSV(String pathDestination, Object[][] data) {

        fileName = getFilename();
        System.out.println(fileName);
        try (PrintWriter writer = new PrintWriter(new File(pathDestination + "\\" + fileName))) {

            StringBuilder sb = new StringBuilder();
            for (String element : nomColumnes) {
                sb.append(element);
                sb.append(',');
            }
            sb.append('\n');

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {

                    sb.append(data[i][j]);
                }
                sb.append('\n');
            }
            writer.write(sb.toString());

            System.out.println("done!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(Export_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Export_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Export_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Export_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Export_form().setVisible(true);
            }
        });
    }

    public String getFilename() {

        return "Export_" + (new Date().toString().replaceAll(":", "")).replaceAll(" ", "") + ".csv";

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CSV;
    private javax.swing.JButton btn_email;
    // End of variables declaration//GEN-END:variables
}
