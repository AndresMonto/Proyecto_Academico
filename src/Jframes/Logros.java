/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//   -------------- falta validar el porcentaje de la nota de las materias  con el metodo *validar_porc* ------------
package Jframes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ander
 */
public class Logros extends javax.swing.JInternalFrame {

    /**
     * Creates new form Logros
     */
    Cone cone;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int Cod_Mat, cod_P;
    String nomMat, nombreP;

    public Logros() {
        initComponents();
        cone = new Cone();
        jButton3.setEnabled(false);
        Cargar_Cur();

    }

    public void Tabla_periodo() {
        Cone cone2 = new Cone();

        String[] titulos = {"CÓDIGO", "DESCRIPCIÓN", "TIPO", "PORCENTAJE"};
        modelo = new DefaultTableModel(null, titulos);

        try {

            rs = cone2.query("select l.id, l.descripcion, l.tipo_logro, l.porcentaje from logro l"
                    + " inner join periodo p on l.periodo_id = p.id "
                    + " where l.materia_id = " + Cod_Mat + " AND p.nombre = '" + nombreP + "'");

            String[] registro = new String[4];
            while (rs.next()) {
                registro[0] = rs.getString("l.id");
                registro[1] = rs.getString("l.descripcion");
                registro[2] = rs.getString("l.tipo_logro");
                registro[3] = rs.getString("l.porcentaje");
                modelo.addRow(registro);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void Cargar_Cur() {
        try {
            rs = cone.query("select *from curso");

            while (rs.next()) {
                String idC = (String) rs.getString("id");
                String nomC = (String) rs.getString("nombre");
                jComboBox4.addItem(idC + " - " + nomC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Logros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Cargar_Mat() {
        Cone cone3 = new Cone();
        jComboBox1.removeAllItems();
        String idC[] = ((String) jComboBox4.getSelectedItem()).split(" - ");

        try {
            ResultSet rs = cone3.query("select m.id, m.nombre "
                    + "from docentexcurso dc, materia m where dc.materia_id = m.id AND dc.curso_id = " + idC[0]);

            while (rs.next()) {
                Cod_Mat = rs.getInt("m.id");
                nomMat = rs.getString("m.nombre");
                jComboBox1.addItem(Cod_Mat + " - " + nomMat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Logros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Cargar_Periodo() {
        Cone cone4 = new Cone();
        jComboBox2.removeAllItems();

        try {
            ResultSet rs = cone4.query("select id, nombre from periodo");

            while (rs.next()) {
                cod_P = rs.getInt("id");
                nombreP = rs.getString("nombre");
                jComboBox2.addItem(cod_P + " - " + nombreP);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Logros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void porcentaje_Completo() {

        int resul = 0;
        try {
            String Mat[] = ((String) jComboBox1.getSelectedItem()).split(" - ");
            Cone cone5 = new Cone();
            rs = cone5.query("SELECT sum(porcentaje) FROM logro where materia_id = " + Mat[0]);

            if (rs.next()) {
                resul = rs.getInt("sum(porcentaje)");
            }

            String total = String.valueOf(resul);
            jLabel12.setText(total + " %");

            if (resul >= 100) {
                JOptionPane.showMessageDialog(rootPane, "se ha completado el 100 % de la nota para la materia "
                        + Mat[1] + ". \nNo se puede agregar mas logros a esta materia en este perido "
                        + "\nSeleccione otro periodo u otra materia para agregar logros");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Logros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void porcentajeXLogro() {

        try {
            String nomL = (String) jComboBox3.getSelectedItem();
            String idMat[] = ((String) jComboBox1.getSelectedItem()).split(" - ");
            rs = cone.query("SELECT sum(porcentaje) FROM logro where tipo_logro = '" + nomL + "' and materia_id = " + idMat[0]);

            if (rs.next()) {
                jLabel9.setText(rs.getString("sum(porcentaje)") + " %");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void Contar_Logros() {
        try {

            String nomL = (String) jComboBox3.getSelectedItem();
            String idMat[] = ((String) jComboBox1.getSelectedItem()).split(" - ");
            rs = cone.query("select count(tipo_logro) from logro where tipo_logro = '" + nomL + "' and materia_id = " + idMat[0]);

            if (rs.next()) {
                String con = rs.getString("count(tipo_logro)");
                jLabel7.setText(con);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Logros.class.getName()).log(Level.SEVERE, null, ex);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        jMenuItem1.setText("Modificar");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("GESTION DE LOGROS");
        setAutoscrolls(true);
        setName("Seccion de "); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(235, 243, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione la materia" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 170, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("MATERIA:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 120, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("PERIODO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el periodo" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 170, 30));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("TIPO:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 30));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el logro", "Academico", "Institucional" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 170, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("PORCENTAJE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 110, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Descripcion");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 120, 30));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 460, 30));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        jPanel1.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 70, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setText("Cantidad de logros");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 110, 30));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 30, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 560, 130));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Descripcion de logros");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 180, 30));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 110, 40));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 40, 30));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("CURSO:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 90, 30));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un curso" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 170, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel11.setText("Total:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 40, 30));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 40, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel13.setText("Total:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 40, 30));

        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 110, 40));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("%");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 20, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        String des, tipoL, cod;
        cod = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        des = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        tipoL = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        String porc[] = ((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3)).split(" ");
        int por = Integer.valueOf(porc[0]);

//        JOptionPane.showMessageDialog(rootPane, "valores \n" + cod + "\n"+ des + "\n" + tipoL + "\n" + porc[0]);
        jComboBox3.setSelectedItem(tipoL);
        jSpinner1.setValue(por);
        jTextField1.setText(des);
        jButton3.setEnabled(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String tip_Logro = "", descrip = "";
        int id_Logro = 0;
        try {
            rs = cone.query("SELECT max(id) FROM logro");
            if (rs.next()) {
                id_Logro = rs.getInt("max(id)") + 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        descrip = jTextField1.getText();
        tip_Logro = (String) jComboBox3.getSelectedItem();
        String materia[] = ((String) jComboBox1.getSelectedItem()).split(" - ");
        String periodo[] = ((String) jComboBox2.getSelectedItem()).split(" - ");
        System.out.println(id_Logro + "\n" + descrip + "\n" + tip_Logro + "\n" + jSpinner1.getValue() + " %" + "\n" + materia[0] + "\n" + periodo[0]);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        Cargar_Mat();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged

        Contar_Logros();
        porcentajeXLogro();
        porcentaje_Completo();

    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        Tabla_periodo();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        Cargar_Periodo();
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
