
package repaso2018;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class Formulario1 extends javax.swing.JFrame {

    /**
     * Estamos instanciando la clase conexion 
     * y creando una variable de tipo Connection
     * que nos
     * permita conectarnos a la base de datos
     */
    Conexion cn = new Conexion();
    Connection con = cn.conectar();
    
    
    //Creamos el modelo de comboBox
     
    DefaultComboBoxModel modeloComboFECHAS;
    
    //Creamos el modelo de las Listas
    DefaultListModel modeloListaLOCAL = new DefaultListModel();
    DefaultListModel modeloListaVISITA = new DefaultListModel();
    DefaultListModel modeloListaARBITRO = new DefaultListModel();
    
    public Formulario1() {
        
        modeloComboFECHAS = new DefaultComboBoxModel(new String[]{});
        
        initComponents();
        //Mandamos a llamar el metodo para llenar el comboBox
        llenarCombo();
        
    }  
    void llenarCombo(){
        //Limpiamos el comboBox
        
        try {   
            //Escribimos la consulta a la base de datos
            String consulta ="SELECT fecha FROM partidos GROUP BY fecha"; 
            //GROUP BY para que no se repitan valores
            
            //Preparamos y enviamos la consulta a la base
            PreparedStatement verDatos = con.prepareStatement(consulta);
            //Creamos un objeto que pueda recibir la respuesta de la base
            ResultSet ver = verDatos.executeQuery();
            /**
             * Mientras retorne valores la consulta
             * se iran agregando al comboBox
             */
            //Esto serive para darle formato a la fecha que nos retorna sql
            Calendar cal = new GregorianCalendar();
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            
            while(ver.next()){
                //aqui le sumamos 2 dias a la fecha , ya que sql siempre las retorna con 2 dias menos
                cal.setTimeInMillis(ver.getDate("fecha").getTime());
                cal.add(Calendar.DATE, 2);
               //agregamos la fecha con los dias sumados al comboBox
                modeloComboFECHAS.addElement(Formato.format(cal.getTime()));
            }    
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    void llenarListas(){
        modeloListaLOCAL.removeAllElements();
        modeloListaVISITA.removeAllElements();
        modeloListaARBITRO.removeAllElements();
        try {   
            //Escribimos la consulta a la base de datos
            //Utilizamos la consulta que realizamos en el ejercicio 5 de REPASO SQL
            String consulta ="SELECT  E.nombreEquipo,E2.nombreEquipo, A.nombreArbitro "
                    + " FROM partidos as P, equipos as E, equipos as E2, arbitros as A "
                    + " WHERE P.idEquipoLocal=E.idEquipo AND P.idEquipoVisita= E2.idEquipo "
                    + " AND P.idArbitro=A.idArbitro AND fecha  = '"+jComboBox1.getSelectedItem()+"'";
            
            /**
             * Al final de la consulta lo que decimos es que FILTRE los RESULTADOS
             * a partir del valor que tenga seleccionado el comboBOX
            **/
            PreparedStatement verDatos = con.prepareStatement(consulta);
            ResultSet ver = verDatos.executeQuery();
            //Creamos un arreglo de tipo String que guarde los datos en 3 columnas y 5 filas
            Object[][] datos = new Object[3][5];
            int i=0;
            while(ver.next()){
                //en la posicion 0 iran los LOCAL
                datos[0][i]= ver.getObject(1);
                //en la posicion 1 iran los VISITA
                datos[1][i]= ver.getObject(2);
                //en la posicion 2 iran los ARBITROS
                datos[2][i]= ver.getObject(3);
                
                //Ya que tenemos nuestros valores, hay que agregarlos a nuestras listas
                modeloListaLOCAL.addElement(datos[0][i]);
                modeloListaVISITA.addElement(datos[1][i]);
                modeloListaARBITRO.addElement(datos[2][i]);
                
                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }  
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(modeloComboFECHAS);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Fechas de Partidos:");

        jList1.setModel(modeloListaLOCAL);
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(modeloListaVISITA);
        jScrollPane2.setViewportView(jList2);

        jList3.setModel(modeloListaARBITRO);
        jScrollPane3.setViewportView(jList3);

        jLabel2.setText("Equipo Local");

        jLabel3.setText("Equipo Visita");

        jLabel4.setText("Arbitro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel4)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        llenarListas();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
