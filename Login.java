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
public class Login extends javax.swing.JFrame {

    Empleado user;

    /**
     * Creates new form Gui
     */
    public Login() {
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

        PanelInicio = new javax.swing.JPanel();
        TtitleInicio = new javax.swing.JLabel();
        PanelLogin = new javax.swing.JPanel();
        LabelNIF = new javax.swing.JLabel();
        TextNIF = new javax.swing.JTextField();
        ButtonLOGIN = new javax.swing.JButton();
        LabelPass = new javax.swing.JLabel();
        TextPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Concesionario");
        setMinimumSize(new java.awt.Dimension(484, 391));
        setResizable(false);

        PanelInicio.setBackground(new java.awt.Color(51, 51, 51));
        PanelInicio.setForeground(new java.awt.Color(60, 63, 65));
        PanelInicio.setMinimumSize(new java.awt.Dimension(417, 317));
        PanelInicio.setPreferredSize(new java.awt.Dimension(417, 317));

        TtitleInicio.setBackground(new java.awt.Color(0, 0, 0));
        TtitleInicio.setFont(new java.awt.Font("SansSerif", 3, 36)); // NOI18N
        TtitleInicio.setForeground(new java.awt.Color(255, 255, 0));
        TtitleInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TtitleInicio.setText("Inicio de sesión");

        PanelLogin.setBackground(new java.awt.Color(255, 255, 255));

        LabelNIF.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        LabelNIF.setForeground(new java.awt.Color(0, 0, 0));
        LabelNIF.setText("NIF");

        TextNIF.setBackground(new java.awt.Color(255, 255, 255));
        TextNIF.setFont(LabelNIF.getFont());
        TextNIF.setForeground(new java.awt.Color(51, 51, 51));
        TextNIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLOGINActionPerformed(evt);
            }
        });

        ButtonLOGIN.setBackground(new java.awt.Color(255, 255, 255));
        ButtonLOGIN.setFont(LabelNIF.getFont());
        ButtonLOGIN.setForeground(new java.awt.Color(0, 0, 0));
        ButtonLOGIN.setText("Log in");
        ButtonLOGIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonLOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLOGINActionPerformed(evt);
            }
        });

        LabelPass.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        LabelPass.setForeground(new java.awt.Color(0, 0, 0));
        LabelPass.setText("Contraseña");

        TextPass.setBackground(new java.awt.Color(255, 255, 255));
        TextPass.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        TextPass.setForeground(new java.awt.Color(51, 51, 51));
        TextPass.setPreferredSize(new java.awt.Dimension(64, 36));
        TextPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLOGINActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelLoginLayout.createSequentialGroup()
                        .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLoginLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(LabelNIF))
                            .addGroup(PanelLoginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(LabelPass)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelLoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextNIF))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                .addGap(0, 125, Short.MAX_VALUE)
                .addComponent(ButtonLOGIN, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonLOGIN)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelInicioLayout = new javax.swing.GroupLayout(PanelInicio);
        PanelInicio.setLayout(PanelInicioLayout);
        PanelInicioLayout.setHorizontalGroup(
            PanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInicioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(PanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TtitleInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        PanelInicioLayout.setVerticalGroup(
            PanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInicioLayout.createSequentialGroup()
                .addComponent(TtitleInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void ButtonLOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLOGINActionPerformed
        String nif = TextNIF.getText();
        String pass = String.valueOf(TextPass.getPassword());
        System.out.println(nif);
        System.out.println(pass);

        //Empleado user = Empleado.buscarEmpleadoBBDD(nif); // Coger empleado de la base de datos, el metodo no funciona
        user = new Empleado(new Nomina(), "Administrativo", "Patata", "Frita", nif, 123456789, "Calle de la piruleta", 1, 1);
        System.out.println(user.toString());
        new Dashboard().initGui(user);
        disable();
    }//GEN-LAST:event_ButtonLOGINActionPerformed

    public static void main(String[] args) {
        initGui();
    }

    static Login gui = new Login();

    public static void initGui() {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);
            }
        });
    }

    public void disable() {
        gui.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLOGIN;
    private javax.swing.JLabel LabelNIF;
    private javax.swing.JLabel LabelPass;
    private javax.swing.JPanel PanelInicio;
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JTextField TextNIF;
    private javax.swing.JPasswordField TextPass;
    private javax.swing.JLabel TtitleInicio;
    // End of variables declaration//GEN-END:variables
}
