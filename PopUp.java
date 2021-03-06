/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karina
 */
public class PopUp extends javax.swing.JDialog {

    /**
     * Creates new form PopUp
     */
    public PopUp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup_Panel = new javax.swing.JPanel();
        Popup_Title = new javax.swing.JLabel();
        Popup_Description = new javax.swing.JLabel();
        Popup_Subtext = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Default Title");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 158));
        setResizable(false);

        Popup_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Popup_Panel.setForeground(new java.awt.Color(255, 255, 255));

        Popup_Title.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        Popup_Title.setForeground(new java.awt.Color(255, 0, 0));
        Popup_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Popup_Title.setText("Default title ⚠");
        Popup_Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Popup_Description.setForeground(new java.awt.Color(0, 0, 0));
        Popup_Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Popup_Description.setText("Default description");

        Popup_Subtext.setForeground(new java.awt.Color(153, 153, 153));
        Popup_Subtext.setText("Default Subtext");

        javax.swing.GroupLayout Popup_PanelLayout = new javax.swing.GroupLayout(Popup_Panel);
        Popup_Panel.setLayout(Popup_PanelLayout);
        Popup_PanelLayout.setHorizontalGroup(
            Popup_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Popup_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Popup_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Popup_Description, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Popup_Title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(Popup_Subtext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Popup_PanelLayout.setVerticalGroup(
            Popup_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Popup_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Popup_Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Popup_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Popup_Subtext)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Popup_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Popup_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Crear un nuevo PopUp
     *
     * @param type Tipo de popUp {error}
     * @param message Mensaje del PopUp
     */
    public static void createSimple(String type, String message) {
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
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PopUp pop = new PopUp(new javax.swing.JFrame(), true);
                pop.setLocationRelativeTo(null);
                System.out.println("lanzando popup");
                switch (type) {
                    case "error":
                        pop.setTitle("Error");
                        pop.Popup_Title.setText("⚠ Error ⚠");
                        pop.Popup_Title.setForeground(new java.awt.Color(255, 0, 0));
                        pop.Popup_Description.setText(message);
                        pop.Popup_Subtext.setText("Si el problema persiste, contacta con un administrador.");
                        pop.setVisible(true);
                        break;
                    case "success":
                        pop.setTitle("Completado");
                        pop.Popup_Title.setText("✔ Completado ✔");
                        pop.Popup_Title.setForeground(new java.awt.Color(0, 255, 0));
                        pop.Popup_Description.setText(message);
                        pop.Popup_Subtext.setText("Cierra esta pestaña para continuar.");
                        pop.setVisible(true);
                        break;
                    default:
                        pop.setTitle("Default Title");
                        pop.Popup_Title.setText("Default title");
                        pop.Popup_Description.setText("Default description");
                        pop.Popup_Subtext.setText("Default Subtext.");

                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Popup_Description;
    private javax.swing.JPanel Popup_Panel;
    private javax.swing.JLabel Popup_Subtext;
    private javax.swing.JLabel Popup_Title;
    // End of variables declaration//GEN-END:variables
}
