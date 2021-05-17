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
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboar
     */
    public Dashboard() {
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

        VentanaCompleta = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        MenuHeader = new javax.swing.JPanel();
        jLabel1_TitleMenu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MenuVENTAS = new javax.swing.JPanel();
        VentasInicio = new javax.swing.JLabel();
        VentasFacturas = new javax.swing.JLabel();
        VentasMotores = new javax.swing.JLabel();
        VentasVehiculos = new javax.swing.JLabel();
        VentasClientes = new javax.swing.JLabel();
        MenuCliente = new javax.swing.JPanel();
        ClienteInicio = new javax.swing.JLabel();
        ClienteAreaPersonal = new javax.swing.JLabel();
        ClienteReservas = new javax.swing.JLabel();
        ClienteFacturas = new javax.swing.JLabel();
        MenuTALLER = new javax.swing.JPanel();
        TallerInicio = new javax.swing.JLabel();
        TallerFacturas = new javax.swing.JLabel();
        TallerReservas = new javax.swing.JLabel();
        TallerClientes = new javax.swing.JLabel();
        PanelBoard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard Concesionario");

        VentanaCompleta.setBackground(new java.awt.Color(255, 255, 255));
        VentanaCompleta.setPreferredSize(new java.awt.Dimension(1280, 720));

        PanelMenu.setBackground(new java.awt.Color(51, 51, 51));

        MenuHeader.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1_TitleMenu.setFont(new java.awt.Font("SansSerif", 3, 24)); // NOI18N
        jLabel1_TitleMenu.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1_TitleMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1_TitleMenu.setText("Concesionario");
        jLabel1_TitleMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("icono");
        jLabel2.setIconTextGap(0);

        javax.swing.GroupLayout MenuHeaderLayout = new javax.swing.GroupLayout(MenuHeader);
        MenuHeader.setLayout(MenuHeaderLayout);
        MenuHeaderLayout.setHorizontalGroup(
            MenuHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuHeaderLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1_TitleMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );
        MenuHeaderLayout.setVerticalGroup(
            MenuHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuHeaderLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1_TitleMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MenuVENTAS.setBackground(new java.awt.Color(51, 51, 51));

        VentasInicio.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        VentasInicio.setForeground(new java.awt.Color(255, 255, 255));
        VentasInicio.setText("Inicio");
        VentasInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        VentasFacturas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        VentasFacturas.setForeground(new java.awt.Color(255, 255, 255));
        VentasFacturas.setText("Facturas");
        VentasFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        VentasMotores.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        VentasMotores.setForeground(new java.awt.Color(255, 255, 255));
        VentasMotores.setText("Motores");
        VentasMotores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        VentasVehiculos.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        VentasVehiculos.setForeground(new java.awt.Color(255, 255, 255));
        VentasVehiculos.setText("Vehiculos");
        VentasVehiculos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        VentasClientes.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        VentasClientes.setForeground(new java.awt.Color(255, 255, 255));
        VentasClientes.setText("Clientes");
        VentasClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout MenuVENTASLayout = new javax.swing.GroupLayout(MenuVENTAS);
        MenuVENTAS.setLayout(MenuVENTASLayout);
        MenuVENTASLayout.setHorizontalGroup(
            MenuVENTASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuVENTASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuVENTASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VentasMotores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(VentasInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(VentasFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(VentasVehiculos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(VentasClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuVENTASLayout.setVerticalGroup(
            MenuVENTASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuVENTASLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VentasInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VentasFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VentasVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VentasMotores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VentasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(363, Short.MAX_VALUE))
        );

        MenuCliente.setBackground(new java.awt.Color(51, 51, 51));

        ClienteInicio.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        ClienteInicio.setForeground(new java.awt.Color(255, 255, 255));
        ClienteInicio.setText("Inicio");
        ClienteInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ClienteAreaPersonal.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        ClienteAreaPersonal.setForeground(new java.awt.Color(255, 255, 255));
        ClienteAreaPersonal.setText("Área personal");
        ClienteAreaPersonal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ClienteReservas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        ClienteReservas.setForeground(new java.awt.Color(255, 255, 255));
        ClienteReservas.setText("Reservas");
        ClienteReservas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ClienteFacturas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        ClienteFacturas.setForeground(new java.awt.Color(255, 255, 255));
        ClienteFacturas.setText("Facturas");
        ClienteFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout MenuClienteLayout = new javax.swing.GroupLayout(MenuCliente);
        MenuCliente.setLayout(MenuClienteLayout);
        MenuClienteLayout.setHorizontalGroup(
            MenuClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClienteReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClienteInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClienteAreaPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(ClienteFacturas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuClienteLayout.setVerticalGroup(
            MenuClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ClienteInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClienteAreaPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClienteFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClienteReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(419, Short.MAX_VALUE))
        );

        MenuTALLER.setBackground(new java.awt.Color(51, 51, 51));

        TallerInicio.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TallerInicio.setForeground(new java.awt.Color(255, 255, 255));
        TallerInicio.setText("Inicio");
        TallerInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TallerFacturas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TallerFacturas.setForeground(new java.awt.Color(255, 255, 255));
        TallerFacturas.setText("Facturas");
        TallerFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TallerReservas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TallerReservas.setForeground(new java.awt.Color(255, 255, 255));
        TallerReservas.setText("Reservas");
        TallerReservas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TallerClientes.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TallerClientes.setForeground(new java.awt.Color(255, 255, 255));
        TallerClientes.setText("Clientes");
        TallerClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout MenuTALLERLayout = new javax.swing.GroupLayout(MenuTALLER);
        MenuTALLER.setLayout(MenuTALLERLayout);
        MenuTALLERLayout.setHorizontalGroup(
            MenuTALLERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuTALLERLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuTALLERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TallerInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TallerFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TallerReservas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(TallerClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuTALLERLayout.setVerticalGroup(
            MenuTALLERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuTALLERLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TallerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TallerFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TallerReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TallerClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(419, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuLayout = new javax.swing.GroupLayout(PanelMenu);
        PanelMenu.setLayout(PanelMenuLayout);
        PanelMenuLayout.setHorizontalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MenuVENTAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MenuTALLER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        PanelMenuLayout.setVerticalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addComponent(MenuHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuLayout.createSequentialGroup()
                    .addGap(0, 77, Short.MAX_VALUE)
                    .addComponent(MenuVENTAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MenuTALLER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
        );

        PanelBoard.setBackground(new java.awt.Color(242, 242, 242));
        PanelBoard.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout PanelBoardLayout = new javax.swing.GroupLayout(PanelBoard);
        PanelBoard.setLayout(PanelBoardLayout);
        PanelBoardLayout.setHorizontalGroup(
            PanelBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 985, Short.MAX_VALUE)
        );
        PanelBoardLayout.setVerticalGroup(
            PanelBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout VentanaCompletaLayout = new javax.swing.GroupLayout(VentanaCompleta);
        VentanaCompleta.setLayout(VentanaCompletaLayout);
        VentanaCompletaLayout.setHorizontalGroup(
            VentanaCompletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaCompletaLayout.createSequentialGroup()
                .addComponent(PanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        VentanaCompletaLayout.setVerticalGroup(
            VentanaCompletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentanaCompletaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VentanaCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VentanaCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        initGui();
    }
    
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard gui = new Dashboard();
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ClienteAreaPersonal;
    private javax.swing.JLabel ClienteFacturas;
    private javax.swing.JLabel ClienteInicio;
    private javax.swing.JLabel ClienteReservas;
    private javax.swing.JPanel MenuCliente;
    private javax.swing.JPanel MenuHeader;
    private javax.swing.JPanel MenuTALLER;
    private javax.swing.JPanel MenuVENTAS;
    private javax.swing.JPanel PanelBoard;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JLabel TallerClientes;
    private javax.swing.JLabel TallerFacturas;
    private javax.swing.JLabel TallerInicio;
    private javax.swing.JLabel TallerReservas;
    private javax.swing.JPanel VentanaCompleta;
    private javax.swing.JLabel VentasClientes;
    private javax.swing.JLabel VentasFacturas;
    private javax.swing.JLabel VentasInicio;
    private javax.swing.JLabel VentasMotores;
    private javax.swing.JLabel VentasVehiculos;
    private javax.swing.JLabel jLabel1_TitleMenu;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}