package view;

import client.Database;
import client.Dosen;
import client.Grade;
import client.Mahasiswa;
import client.User;
import controller.ControllerToView;
import course.Quiz;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DashboardDosen extends javax.swing.JFrame implements ListSelectionListener{

    private Dosen currentDosen;
    private ArrayList<Mahasiswa> listMhs;
    private ArrayList<Quiz> quizs;
    private ArrayList<Grade> grades;
    private int selectedQuiz;
    private Mahasiswa selectedAttemptedUser;
    private ControllerToView sub;
    
    public DashboardDosen(Dosen d, ArrayList<User> users, ArrayList<Quiz> quizs, ArrayList<Grade> grades) {
        initComponents();
        this.setTitle(d.getFullname());
        this.currentDosen = d;
        this.listMhs = new ArrayList();
        this.quizs = new ArrayList();
        this.grades = grades;
        DefaultListModel tempList = new DefaultListModel();
        for(Quiz q: quizs){
            if(q.getHeldBy().equals(this.currentDosen.getNip())){
                this.quizs.add(q);
                tempList.addElement(q.getQuizTitle());
            }
        }
        this.jList1.setModel(tempList);
        
        for(User u:users){
            if(u instanceof Mahasiswa){
                if(((Mahasiswa) u).getDosenSiapa().equals(this.currentDosen.getNip())){
                    this.listMhs.add((Mahasiswa)u);
                }
            }
        }
        this.jList1.addListSelectionListener(this);
        this.jList2.addListSelectionListener(this);
        this.jList3.addListSelectionListener(this);
    }

    public void setListMhs() {
        DefaultListModel tempList1 = new DefaultListModel();
        DefaultListModel tempList2 = new DefaultListModel();
        for(Mahasiswa m:this.listMhs){
            if(this.grades.size()>0){
                for(Grade g:this.grades){
                    if(m.getNim().equals(g.getNim())){
                        tempList1.addElement(m.getUsername());
                    }else{
                        tempList2.addElement(m.getUsername());
                    }
                }
            }else{
                tempList2.addElement(m.getUsername());
            }

        }
        this.jList2.setModel(tempList1);
        this.jList3.setModel(tempList2);
        this.jList2.clearSelection();
        this.jList3.clearSelection();
        this.repaint();
        this.revalidate();
    }

    public void setQuizs(ArrayList<Quiz> quizs) {
        this.quizs = quizs;
        DefaultListModel tempList = new DefaultListModel();
        for(Quiz q: quizs){
            if(q.getHeldBy().equals(this.currentDosen.getNip())){
                this.quizs.add(q);
                tempList.addElement(q.getQuizTitle());
            }
        }
        this.jList1.setModel(tempList);
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
        this.repaint();
        this.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jList2);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quiz List");

        jLabel2.setText("Student who attempts");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete Attempt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jList3);

        jLabel3.setText("Student who doesn't attempt yet");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(!this.jList2.isSelectionEmpty()){
            this.sub.deleteNilai(this.selectedAttemptedUser.getNim(), this.selectedQuiz);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!this.jList1.isSelectionEmpty()){
            this.sub.deleteQuiz(this.selectedQuiz);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddQuiz add = new AddQuiz(this.currentDosen);
        add.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void setSub(ControllerToView sub) {
        this.sub = sub;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        DefaultListModel tempList1 = new DefaultListModel();
        DefaultListModel tempList2 = new DefaultListModel();
        if(e.getSource()==this.jList1){
            if(!e.getValueIsAdjusting()){
                for(Quiz q:this.quizs){
                    if(this.jList1.getSelectedValue().equals(q.getQuizTitle())){
                        this.selectedQuiz = q.getQuizId();
                    }
                }
                if(this.selectedQuiz>0){
                    for(Mahasiswa m:this.listMhs){
                        if(this.grades.size()>0){
                            for(Grade g:this.grades){
                                System.out.println("asdasd");
                                if(m.getNim().equals(g.getNim())){
                                    tempList1.addElement(m.getUsername());
                                }else{
                                    tempList2.addElement(m.getUsername());
                                }
                            }
                        }else{
                            tempList2.addElement(m.getUsername());
                        }
                        
                    }
                    this.jList2.setModel(tempList1);
                    this.jList3.setModel(tempList2);
                }
            }
        }else if(e.getSource()==this.jList2){
            for(Mahasiswa m:this.listMhs){
                if(m.getUsername().equals(this.jList2.getSelectedValue())){
                    this.selectedAttemptedUser = m;
                    break;
                }
            }
        }
    }
}
