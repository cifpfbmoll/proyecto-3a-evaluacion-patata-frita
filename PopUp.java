/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fp.concesionario;

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

        FiltroReserva = new javax.swing.JDialog();
        Panel = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        ID_LABEL = new javax.swing.JLabel();
        ESPACIO_LABEL = new javax.swing.JLabel();
        FECHA_LABEL = new javax.swing.JLabel();
        TALLER_LABEL = new javax.swing.JLabel();
        ID_SPINNER = new javax.swing.JSpinner();
        ESPACIO_SPINNER = new javax.swing.JSpinner();
        FECHA_FORMATTED = new javax.swing.JFormattedTextField();
        TALLER_SPINNER = new javax.swing.JSpinner();
        Buscar = new javax.swing.JButton();
        Popup_Panel = new javax.swing.JPanel();
        Popup_Title = new javax.swing.JLabel();
        Popup_Description = new javax.swing.JLabel();
        Popup_Subtext = new javax.swing.JLabel();

        FiltroReserva.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FiltroReserva.setTitle("Filtrar reserva");
        FiltroReserva.setAlwaysOnTop(true);
        FiltroReserva.setMaximumSize(new java.awt.Dimension(397, 240));
        FiltroReserva.setMinimumSize(new java.awt.Dimension(397, 240));
        FiltroReserva.setPreferredSize(new java.awt.Dimension(397, 240));
        FiltroReserva.setResizable(false);

        Panel.setBackground(new java.awt.Color(255, 255, 255));
        Panel.setForeground(new java.awt.Color(255, 255, 255));
        Panel.setMaximumSize(new java.awt.Dimension(397, 240));
        Panel.setMinimumSize(new java.awt.Dimension(397, 240));
        Panel.setPreferredSize(new java.awt.Dimension(397, 240));

        Title.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        Title.setForeground(new java.awt.Color(0, 0, 0));
        Title.setText("Filtrar reservas");

        ID_LABEL.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ID_LABEL.setForeground(new java.awt.Color(0, 0, 0));
        ID_LABEL.setText("ID");

        ESPACIO_LABEL.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ESPACIO_LABEL.setForeground(new java.awt.Color(0, 0, 0));
        ESPACIO_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ESPACIO_LABEL.setText("Espacio reservado");

        FECHA_LABEL.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        FECHA_LABEL.setForeground(new java.awt.Color(0, 0, 0));
        FECHA_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FECHA_LABEL.setText("Fecha");

        TALLER_LABEL.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        TALLER_LABEL.setForeground(new java.awt.Color(0, 0, 0));
        TALLER_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TALLER_LABEL.setText("Taller ID");

        ID_SPINNER.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        ID_SPINNER.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        ID_SPINNER.setToolTipText("Dejar en 0 para buscar todas las ID");

        ESPACIO_SPINNER.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        ESPACIO_SPINNER.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        ESPACIO_SPINNER.setToolTipText("Dejar en 0 para buscar todas las ID");

        FECHA_FORMATTED.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        TALLER_SPINNER.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        TALLER_SPINNER.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        TALLER_SPINNER.setToolTipText("Dejar en 0 para buscar todas las ID");

        Buscar.setText("Buscar");

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TALLER_LABEL)
                            .addComponent(FECHA_LABEL)
                            .addComponent(ID_LABEL)
                            .addComponent(ESPACIO_LABEL))
                        .addGap(18, 18, 18)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FECHA_FORMATTED, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(ID_SPINNER)
                            .addComponent(ESPACIO_SPINNER)
                            .addComponent(TALLER_SPINNER))))
                .addGap(0, 117, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addGap(18, 18, 18)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID_SPINNER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ESPACIO_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ESPACIO_SPINNER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FECHA_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FECHA_FORMATTED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TALLER_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TALLER_SPINNER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Buscar)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FiltroReservaLayout = new javax.swing.GroupLayout(FiltroReserva.getContentPane());
        FiltroReserva.getContentPane().setLayout(FiltroReservaLayout);
        FiltroReservaLayout.setHorizontalGroup(
            FiltroReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiltroReservaLayout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        FiltroReservaLayout.setVerticalGroup(
            FiltroReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiltroReservaLayout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Default Title");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 158));
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
    public static void create(String type, String message) {
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
                    case "f_reserva":
                        pop.FiltroReserva.setTitle("Filtrar reservas");
                        pop.FiltroReserva.setLocationRelativeTo(null);
                        pop.FiltroReserva.setVisible(true);
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
    private javax.swing.JButton Buscar;
    private javax.swing.JLabel ESPACIO_LABEL;
    private javax.swing.JSpinner ESPACIO_SPINNER;
    private javax.swing.JFormattedTextField FECHA_FORMATTED;
    private javax.swing.JLabel FECHA_LABEL;
    private javax.swing.JDialog FiltroReserva;
    private javax.swing.JLabel ID_LABEL;
    private javax.swing.JSpinner ID_SPINNER;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel Popup_Description;
    private javax.swing.JPanel Popup_Panel;
    private javax.swing.JLabel Popup_Subtext;
    private javax.swing.JLabel Popup_Title;
    private javax.swing.JLabel TALLER_LABEL;
    private javax.swing.JSpinner TALLER_SPINNER;
    private javax.swing.JLabel Title;
    // End of variables declaration//GEN-END:variables
}
