/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.Color;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author gerosa_simone
 */
public class Form2 extends javax.swing.JFrame {

    static Form2 f;
    Chat c;
    Comunicazione c1;
    boolean start=false;

    /**
     * Creates new form Form2
     */
    public Form2() throws SocketException {
        initComponents();
        f = this;
        c = Chat.getInstance();

    }

    public static synchronized Form2 Singleton() throws SocketException {
        if (f == null) {
            f = new Form2();
        }

        return f;

    }

    public void setRicezione(Comunicazione a) throws SocketException {
        c = Chat.getInstance();
        this.c1 = a;
        if(start==false){
        c.start();
        start=true;
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

        Nome = new javax.swing.JLabel();
        TxtMessaggio = new javax.swing.JTextField();
        invia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Nome.setBackground(new java.awt.Color(255, 255, 255));
        Nome.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        Nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        invia.setBackground(new java.awt.Color(255, 255, 255));
        invia.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        invia.setText("Invia");
        invia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inviaActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        jButton1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jButton1.setText("quit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(TxtMessaggio, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(invia, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtMessaggio)
                    .addComponent(invia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inviaActionPerformed
        // TODO add your handling code here:
          if (TxtMessaggio.getText() != "") {
            try {
                // TODO add your handling code here:
                c1.InviaMessaggio(TxtMessaggio.getText());
                TxtMessaggio.setText("");
            } catch (IOException ex) {
                Logger.getLogger(Form2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_inviaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            c1.chiudicomunicazione();
            azzera();
        } catch (IOException ex) {
            Logger.getLogger(Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void azzera(){
        this.panel.removeAll();
            this.panel.revalidate();
    }
    /**
     * @param args the command line arguments
     */
    public void setPersona(String s) {
        Nome.setText(s);
    }

    public void AggiungiDestra(String s, int y, int altezza) {
        JLabel l = new JLabel();
        l.setLocation(200, y);
        l.setSize(200, altezza);
        l.setText(s);
        l.setBorder(BorderFactory.createLineBorder(Color.black));
        l.setBackground(new Color(140,186,130));
        l.setOpaque(true);
        panel.add(l);
        this.repaint();
        
    }

    public void AggiungiSinistra(String s, int y, int altezza) {
        JLabel l = new JLabel();
         l.setLocation(0, y);
        l.setSize(200, altezza);
        l.setText(s);
        l.setBorder(BorderFactory.createLineBorder(Color.black));       
        l.setBackground(Color.WHITE);
        l.setOpaque(true);
        panel.add(l);
        this.repaint();
       
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nome;
    private javax.swing.JTextField TxtMessaggio;
    private javax.swing.JButton invia;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
