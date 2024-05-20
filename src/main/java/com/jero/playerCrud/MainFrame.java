package com.jero.playerCrud;

import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


public class MainFrame extends javax.swing.JFrame {

    private static JButton btnSave;
    private static JLabel lblId3;
    private static JLabel lblJuegof;
    private static JLabel lblLevel;
    private static JLabel lblNombre;
    private static JLabel lblScore;
    private static JTextField txtId;
    private static JTextField txtJuego;
    private static JTextField txtLevel;
    private static JTextField txtNombre;
    private static JTextField txtScore;
    private static JTextArea txtArea;
    private static JScrollPane jScrollPane1;
    private static JButton btnBuscar;
    private static JButton btnEliminar;
    private static JButton btnClear;
    private static JButton btnEdit;

    static ArrayList<Jugadores> jugadores = new ArrayList<>();
    static ArrayList<Object> saveBoat = new ArrayList<>();
    static ArrayList<Object> copyBoat = new ArrayList<>();
        
    public MainFrame() {
        
        initComponents();
    }

    public static void writeJugador() {
        
        String name = txtNombre.getText();
        String game = txtJuego.getText();
        char level = txtLevel.getText().charAt(0);
        int score = Integer.parseInt(txtScore.getText());
        
        saveBoat.add(name);
        saveBoat.add(game);
        saveBoat.add(level);
        saveBoat.add(score);
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Data.txt"));
            for (Object obj : saveBoat) {
                writer.write(obj.toString() + "\n");
            }
            writer.close();
        }
        catch (IOException e) {}
    }
    
    public static void erasePlayer() {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Data.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Data_temp.txt"))){

            int index = Integer.parseInt(txtId.getText());
            index = (index * 4) - 4;
            String line;
            int lineN = 0;

            while ((line = reader.readLine()) != null) {
   
                if (lineN < index || lineN >= index + 4) {
                    writer.write(line);
                    writer.newLine();
                }
                lineN++;
            }
            writer.close();
            reader.close();
            
        } catch (IOException e) {}

        try {
            Files.move(Paths.get("Data_temp.txt"), Paths.get("Data.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {}
    }
    
    public static void rewritePlayer() {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Data.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Data_temp.txt"))) {

            int index = Integer.parseInt(txtId.getText());
            index = (index * 4) - 4;

            String line;
            int lineN = 0;
            
            String name = txtNombre.getText();
            String game = txtJuego.getText();
            char level = txtLevel.getText().charAt(0);
            int score = Integer.parseInt(txtScore.getText());

            copyBoat.add(name);
            copyBoat.add(game);
            copyBoat.add(level);
            copyBoat.add(score);

            while ((line = reader.readLine()) != null) {

                if (lineN < index || lineN >= index + 3) {
                    writer.write(line);
                    writer.newLine();
                    lineN++;
                } else {
                    for (Object obj: copyBoat) {
                        writer.write(obj.toString());
                        writer.newLine();
                        lineN++;
                    }
                    copyBoat.clear();
                }
                
            }
            writer.close();
            reader.close();
            
        } catch (IOException e) {}

        try {
            Files.move(Paths.get("Data_temp.txt"), Paths.get("Data.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {}
    }
        
    public static void addJugador() {
       
        Jugadores jugador = new Jugadores(txtNombre.getText(), txtJuego.getText(), txtLevel.getText().charAt(0), Integer.parseInt(txtScore.getText()));
        jugadores.add(jugador);
        listarJugadores();
    }

    static void listarJugadores(){
       
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("==*******== Listado de Jugadores ==**********==\n");
       
       for (Jugadores jugador: jugadores) {
        stringBuilder.append("Id. ").append(jugador.getId()).append(" JF: ").append(jugador.getJuegoFavorito()).append(" Nombre del Jugador: ").append(jugador.getNombre()).append(" Puntaje: ").append(jugador.getScore()).append("\n");
        txtArea.setText(stringBuilder.toString());
       }
    }

    static void buscarJugador() {
        
        int id = Integer.parseInt(txtId.getText());
        for (Jugadores jugador : jugadores) {
            if (jugador.getId() == id) {
                txtNombre.setText(jugador.getNombre());
                txtJuego.setText(jugador.getJuegoFavorito());
                txtScore.setText(String.valueOf(jugador.getScore()));
                txtLevel.setText(Character.toString(jugador.getLevel()));
            }
        }
    }
    
    static void eliminarJugador() {

    int id = Integer.parseInt(txtId.getText());
    for (Jugadores jugador : jugadores) {
        if (jugador.getId() == id) {
            jugadores.remove(jugador);
            JOptionPane.showInternalMessageDialog(null,"Se ha eliminado el jugador.");
            System.out.println("Se ha eliminado el jugador " + jugador.getId());
        }
        listarJugadores();
        }
    }
    
        
    static void editPlayer() {
        
    int id = Integer.parseInt(txtId.getText());
    Iterator<Jugadores> iterator = jugadores.iterator();
    
    while (iterator.hasNext()) {
        
        Jugadores editor = iterator.next();
        
        if (editor.getId() == id) {
            editor.setNombre(txtNombre.getText());
            editor.setLevel((char) Integer.parseInt(txtLevel.getText()));
            editor.setJuegoFavorito(txtJuego.getText());
            editor.setScore(Integer.parseInt(txtScore.getText()));
            
            JOptionPane.showInternalMessageDialog(null, "Se ha editado el jugador.");
            System.out.println("Se ha editado el jugador " + editor.getId());
        }
    }
    listarJugadores();
}

    static void clear(){

        txtId.setText("");
        txtJuego.setText("");
        txtLevel.setText("");
        txtNombre.setText("");
        txtScore.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblJuegof = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblId3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtLevel = new javax.swing.JTextField();
        txtJuego = new javax.swing.JTextField();
        txtScore = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombre.setText("Nombre del jugador:");

        lblLevel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblLevel.setText("Nivel:");
        lblLevel.setToolTipText("'");

        lblJuegof.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblJuegof.setText("Juego Favorito:");

        lblScore.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblScore.setText("Score:");

        lblId3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblId3.setText("ID:");

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtLevel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLevel.setToolTipText("");

        txtJuego.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtScore.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnSave.setText("Guardar");
        btnSave.setToolTipText("Guardar el Jugador Actual");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Guardar el Jugador Actual");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Guardar el Jugador Actual");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setToolTipText("Guardar el Jugador Actual");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(btnEdit)
                .addGap(44, 44, 44)
                .addComponent(btnSave)
                .addGap(41, 41, 41)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJuegof, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombre)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnBuscar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtScore, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(lblId3)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel)
                    .addComponent(txtLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJuegof)
                    .addComponent(txtJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblScore)
                    .addComponent(txtScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        addJugador();
        writeJugador();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarJugador();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminarJugador();
        erasePlayer();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        editPlayer();
        rewritePlayer();
    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    /*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblId3;
    private javax.swing.JLabel lblJuegof;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblScore;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtJuego;
    private javax.swing.JTextField txtLevel;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtScore;
    // End of variables declaration//GEN-END:variables
*/
}