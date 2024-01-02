package view;

import client.Admin;
import client.Database;
import client.Dosen;
import client.Mahasiswa;
import client.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.ControllerToView;
import controller.AdminToView;

public class DashboardAdmin extends javax.swing.JFrame implements AdminToView,ListSelectionListener {
    private ArrayList<User> users;
    private User selectedUser;
    private ControllerToView sub;
    public DashboardAdmin(String username, ArrayList<User> users) {
        initComponents();
        this.setTitle(username);
        DefaultListModel tempList = new DefaultListModel();
        this.users = users;
        for(User u:this.users){
            if(u instanceof Admin==false){
                tempList.addElement(u.getUsername());
            }
        }
        this.jList1.setModel(tempList);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        this.jList1.addListSelectionListener(this);
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        DefaultListModel tempList = new DefaultListModel();
        for(User u:this.users){
            if(u instanceof Admin==false){
                tempList.addElement(u.getUsername());
            }
        }
        this.jList1.setModel(tempList);
        this.revalidate();
        this.repaint();
    }

    public void setSub(ControllerToView sub) {
        this.sub = sub;
    }
    
    @Override
    public void transfer(String id, String fullname, String username, String role, String lecturerNip){
        this.sub.updateUser(id, fullname, username, role, lecturerNip);
    }
    
    @Override
    public void changePass(String id, String newPass){
        this.sub.updatePass(id, newPass);
    }
    
    @Override
    public void insert(User u, String pass){
        this.sub.addUser(u, pass);
    }
    
    private Dosen findDosen(String nip){
        for(User u:this.users){
            if(u instanceof Dosen){
                if(((Dosen) u).getNip().equals(nip)){
                    return (Dosen)u;
                }
            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PLEASE SELECT A USER!");

        jLabel2.setText("ID::");

        jLabel3.setText("Username:");

        jLabel4.setText("Fullname:");

        jLabel5.setText("Role:");

        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Change Password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Lecturer:");

        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addComponent(jLabel6))
                        .addGap(0, 88, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!this.jList1.isSelectionEmpty()){
            UpdateUsers edit = null;
            if(this.selectedUser instanceof Dosen){
                String id = ((Dosen) selectedUser).getNip();
                String fullname = this.selectedUser.getFullname();
                String username = this.selectedUser.getUsername();
                String role = Database.findRole(selectedUser);
                edit = new UpdateUsers(this, id, fullname, username, role, "");
            }else if(this.selectedUser instanceof Mahasiswa){
                String id = ((Mahasiswa) selectedUser).getNim();
                String fullname = this.selectedUser.getFullname();
                String username = this.selectedUser.getUsername();
                String role = Database.findRole(selectedUser);
                String nip = ((Mahasiswa) selectedUser).getDosenSiapa();
                edit = new UpdateUsers(this, id, fullname, username, role, nip);
            }
            
            if(edit!=null){
                edit.setVisible(true);
                edit.setSub(this);
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        UpdatePassword changePass = null;
        if(this.selectedUser instanceof Dosen){
            String id = ((Dosen) selectedUser).getNip();
            changePass = new UpdatePassword(this, id);
        }else if(this.selectedUser instanceof Mahasiswa){
            String id = ((Mahasiswa) selectedUser).getNim();
            changePass = new UpdatePassword(this, id);
        }
        if(changePass != null){
            changePass.setVisible(true);
            changePass.setSub(this);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(this.selectedUser instanceof Dosen){
            String id = ((Dosen) selectedUser).getNip();
            this.sub.deleteUser(id);
        }else if(this.selectedUser instanceof Mahasiswa){
            String id = ((Mahasiswa) selectedUser).getNim();
            this.sub.deleteUser(id);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        InsertUser insertView = new InsertUser(this);
        insertView.setVisible(true);
        insertView.setSub(this);
    }//GEN-LAST:event_jButton5ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            for(User u:users){
                if(u.getUsername().equals(jList1.getSelectedValue())){
                    jLabel2.setVisible(true);
                    jLabel3.setVisible(true);
                    jLabel4.setVisible(true);
                    jLabel5.setVisible(true);
                    jLabel1.setText("USER INFO");
                    selectedUser = u;
                    if(u instanceof Dosen){
                        jLabel6.setVisible(false);
                        jLabel2.setText("ID: "+((Dosen) u).getNip());
                        jLabel3.setText("Username: "+u.getUsername());
                        jLabel4.setText("Fullname: "+u.getFullname());
                        jLabel5.setText("Role: "+Database.findRole(u));
                    }else if(u instanceof Mahasiswa){
                        jLabel6.setVisible(true);
                        jLabel2.setText("ID: "+((Mahasiswa) u).getNim());
                        jLabel3.setText("Username: "+u.getUsername());
                        jLabel4.setText("Fullname: "+u.getFullname());
                        jLabel5.setText("Role: "+Database.findRole(u));
                        jLabel6.setText("Lecturer: "+findDosen(((Mahasiswa) u).getDosenSiapa()).getFullname());
                    }
                    break;
                }
            }
        }
    }
}
