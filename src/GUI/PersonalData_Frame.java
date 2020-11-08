
package GUI;

import MainClasses.AdminUser;
import MainClasses.Controller;

/**
 *
 * @author xbarn
 */
public class PersonalData_Frame extends javax.swing.JFrame {
    
    private Controller contr;
    private AdminUser user;
    
    public PersonalData_Frame(){
         initComponents();
    }
    
    public PersonalData_Frame(Controller contr, AdminUser user) {
        initComponents();
        this.contr = contr;
        this.user = user;
    }

    /**
     * Aques mètode es cridat per inicialitzar tots els components del frame
     * 
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tx_Rol = new javax.swing.JTextField();
        tx_Nom = new javax.swing.JTextField();
        tx_Regio = new javax.swing.JTextField();
        tx_Ciutat = new javax.swing.JTextField();
        tx_carrer = new javax.swing.JTextField();
        tx_CP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cb_seleccio_user = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1250, 720));
        setMinimumSize(new java.awt.Dimension(1250, 720));
        setName("DadesUser_Frame"); // NOI18N
        getContentPane().setLayout(null);

        tx_Rol.setBackground(new java.awt.Color(255, 255, 255));
        tx_Rol.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tx_Rol);
        tx_Rol.setBounds(480, 140, 80, 30);

        tx_Nom.setBackground(new java.awt.Color(255, 255, 255));
        tx_Nom.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tx_Nom);
        tx_Nom.setBounds(480, 214, 220, 30);

        tx_Regio.setBackground(new java.awt.Color(255, 255, 255));
        tx_Regio.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tx_Regio);
        tx_Regio.setBounds(480, 250, 300, 30);

        tx_Ciutat.setBackground(new java.awt.Color(255, 255, 255));
        tx_Ciutat.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tx_Ciutat);
        tx_Ciutat.setBounds(480, 290, 300, 30);

        tx_carrer.setBackground(new java.awt.Color(255, 255, 255));
        tx_carrer.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tx_carrer);
        tx_carrer.setBounds(480, 330, 300, 30);

        tx_CP.setBackground(new java.awt.Color(255, 255, 255));
        tx_CP.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tx_CP);
        tx_CP.setBounds(480, 370, 110, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(450, -40, 200, 160);

        cb_seleccio_user.setBackground(new java.awt.Color(255, 255, 255));
        cb_seleccio_user.setForeground(new java.awt.Color(0, 0, 0));
        cb_seleccio_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cristina", "xavier", "jarubioca", "venancio" }));
        cb_seleccio_user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cb_seleccio_user.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_seleccio_userItemStateChanged(evt);
            }
        });
        cb_seleccio_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_seleccio_userActionPerformed(evt);
            }
        });
        getContentPane().add(cb_seleccio_user);
        cb_seleccio_user.setBounds(480, 180, 220, 26);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/3.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1270, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Un cop un usuari sigui seleccionat, mostrare'm les seves dades. Aquesta opció només és disponible pèr a admins.
     * 
     */
    
    private void cb_seleccio_userItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_seleccio_userItemStateChanged
            
            String[] data;
            data = this.contr.getDataUser(this.user, this.cb_seleccio_user.getSelectedItem().toString());
            
            //Recuperem totes les dades, pero les afegim a l''objecte user abans de pintar-les. 
            this.user.setFullName(data[0]);
            this.user.setStreet(data[1]);
            this.user.setCity(data[2]);
            this.user.setRegio(data[3]);
            this.user.setCp(data[4]);
            this.user.setCountry(data[5]);
            //Modifiquem l'user al controller. 
            this.contr.setUser(user);
            //Ara cridem al metode per pintar-les. 
            this.setUser_Data_Screen();    
  
    }//GEN-LAST:event_cb_seleccio_userItemStateChanged

    private void cb_seleccio_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_seleccio_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_seleccio_userActionPerformed

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
            java.util.logging.Logger.getLogger(PersonalData_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonalData_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonalData_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonalData_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonalData_Frame().setVisible(true);
            }
        });
    }
     /**
     * Aquest mètode pintarà totes les dades a l'Screen de l'user.
     * 
     */
    private void setUser_Data_Screen() {

        this.tx_Nom.setText(this.user.getFullName());
        this.tx_CP.setText(this.user.getCp());
        this.tx_Regio.setText(this.user.getRegio());
        this.tx_Ciutat.setText(this.user.getCity());
        this.tx_carrer.setText(this.user.getStreet());
        if (this.user.isAdmin()) {
            this.tx_Rol.setText("ADMIN");
        } else {
            this.tx_Rol.setText("USER");
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_seleccio_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tx_CP;
    private javax.swing.JTextField tx_Ciutat;
    private javax.swing.JTextField tx_Nom;
    private javax.swing.JTextField tx_Regio;
    private javax.swing.JTextField tx_Rol;
    private javax.swing.JTextField tx_carrer;
    // End of variables declaration//GEN-END:variables
}
