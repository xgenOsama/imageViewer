/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author gen
 */
public class img extends javax.swing.JFrame {

    /**
     * Creates new form Image
     */
    JFileChooser choose;
    JPanel jp;

    public img() {
        initComponents();
        jp = new JPanel();
        jp.setSize(this.getSize());
        jp.setBackground(Color.BLACK);
        this.add(jp);
        setLocationRelativeTo(null);
        initEventDriven();
        setResizable(false);
        pack();
        revalidate();
        validate();
    }

    ArrayList<String> listdir;
    int counter = 0;
    ImageIcon imageIcon;
    Image image;
    BufferedImage bi;
    JLabel l1 = new JLabel();
    JLabel currentlabel = null;
    int x = 0, y = 0;
    JPanel current;

    public void initEventDriven() {
        jbtExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtBrowes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                choose = new JFileChooser();
                listdir = new ArrayList<>();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg");
                choose.setFileFilter(filter);
                int ret = choose.showDialog(null, "Open file");
                File file = new File("");
//                String currentFile = "";
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = choose.getCurrentDirectory();
//                    currentFile = choose.getSelectedFile().toString();
                    System.out.println(file);
//                    System.out.println(currentFile);
                }
                File[] f = file.listFiles();
                for (File files : f) {
                    if (files != null && files.getName().toLowerCase().endsWith(".jpg")) {
                        try {
                            listdir.add(files.getCanonicalPath());

                        } catch (IOException ex) {
                            Logger.getLogger(img.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
//                for (int i = 0; i < listdir.size(); i++) {
//                    if (listdir.get(i).equals(currentFile)) {
//                        counter = i ;
//                    }
//                }
                System.out.println(counter);
            }
        });

        jbtNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (counter >= 0 && counter < listdir.size()) {
                    counter++;
                    if (current != null) {
                        current.removeAll();
                        current.revalidate();
                        current.repaint();
                    }

                    imageIcon = new ImageIcon(listdir.get(counter));
                    image = imageIcon.getImage();
                    ImageIcon newicon = new ImageIcon(Autoresize(image));
                    current = new JPanel();
                    l1.setSize(jpView.getSize());
                    l1 = new JLabel(newicon);
                    current.setSize(jpView.getSize());
                    l1.setSize(jpView.getSize());
                    l1.setLocation(-jpView.getWidth() * 2, y);
                    current.add(l1);
                    current.setBackground(Color.BLACK);
                    jpView.add(current);
                    initAnime(1);
                    System.out.println(listdir.get(counter));
                }
                current.revalidate();
                jpView.revalidate();
            }
        });

        jbtPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /// prev
//                if (current!= null) {
//                    initAnime(2);
//                }
                if (counter > 0 && counter < listdir.size()) {
                    counter--;
                    if (current != null) {
                        current.removeAll();
                        current.revalidate();
                        current.repaint();
                    }
                    imageIcon = new ImageIcon(listdir.get(counter));
                    image = imageIcon.getImage();
                    ImageIcon newicon = new ImageIcon(Autoresize(image));
                    l1 = new JLabel(newicon);
                    l1.setSize(jpView.getSize());
                    current = new JPanel();
                    current.setSize(jpView.getSize());
                    current.locate(jpView.getWidth(), 0);
                    current.setBackground(Color.BLACK);
                    current.add(l1);
                    jpView.add(current);
                    initAnime(3);
                    System.out.println(listdir.get(counter));
                }
                current.revalidate();
                jpView.revalidate();
            }

        });
    }
    Thread setAnmie;

    public synchronized void initAnime(int i) {

        setAnmie = new Thread(new Runnable() {

            @Override
            public void run() {
                jbtNext.setEnabled(false);
                jbtPrev.setEnabled(false);

                if (i == 1) {
                    for (int i = 0; i < jpView.getWidth() - 300; i++) {

                        System.out.println(i);
                        l1.setLocation((-jpView.getWidth() / 2) + i, 30);
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(img.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    for (int j = jpView.getWidth(); j != 150; j--) {
                        System.out.println(j);
                        l1.setLocation(j, 30);
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(img.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                jbtPrev.setEnabled(true);
                jbtNext.setEnabled(true);

            }
        });
        setAnmie.start();
    }

    public BufferedImage Autoresize(Image im) {
        bi = new BufferedImage(jpView.getWidth(), jpView.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, 600, 700, null);
        return bi;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpView = new javax.swing.JPanel();
        jbtBrowes = new javax.swing.JButton();
        jbtNext = new javax.swing.JButton();
        jbtPrev = new javax.swing.JButton();
        jbtExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpView.setBackground(new java.awt.Color(7, 7, 7));
        jpView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(253, 122, 47)));

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jbtBrowes.setText("Browse");
        jbtBrowes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBrowesActionPerformed(evt);
            }
        });

        jbtNext.setText("next");
        jbtNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextActionPerformed(evt);
            }
        });

        jbtPrev.setText("prev");

        jbtExit.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(jbtPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtNext, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtBrowes, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtExit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtPrev)
                    .addComponent(jbtNext)
                    .addComponent(jbtBrowes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtExit)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtNextActionPerformed

    private void jbtBrowesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBrowesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtBrowesActionPerformed

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
            java.util.logging.Logger.getLogger(img.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(img.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(img.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(img.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new img().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtBrowes;
    private javax.swing.JButton jbtExit;
    private javax.swing.JButton jbtNext;
    private javax.swing.JButton jbtPrev;
    private javax.swing.JPanel jpView;
    // End of variables declaration//GEN-END:variables
}
