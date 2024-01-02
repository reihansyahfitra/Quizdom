package view;

import client.Database;
import client.Grade;
import client.Mahasiswa;
import controller.ControllerToView;
import controller.MhsToView;
import course.Question;
import course.Quiz;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DashboardMhs extends javax.swing.JFrame implements ListSelectionListener, MhsToView {
    
    private ArrayList<Quiz> quiz;
    private ArrayList<Grade> grades;
    private ArrayList<Question> questions;
    private Quiz selectedQuiz;
    private Mahasiswa currentUser;
    private ControllerToView sub;
    
    public DashboardMhs(Mahasiswa m, ArrayList<Quiz> quiz, ArrayList<Grade> grade, ArrayList<Question> questions) {
        initComponents();
        this.quiz = quiz;
        this.grades = grade;
        this.currentUser = m;
        this.questions = questions;
        this.setTitle(m.getFullname());
        DefaultListModel tempList = new DefaultListModel();
        for(Quiz q:this.quiz){
            if(m.getDosenSiapa().equals(q.getHeldBy())){
                tempList.addElement(q.getQuizTitle());
            }
        }
        this.jList1.setModel(tempList);
        this.jList1.addListSelectionListener((ListSelectionListener) this);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            for(Quiz q:quiz){
                if(q.getQuizTitle().equals(jList1.getSelectedValue())){
                    this.selectedQuiz = q;
                    double nilai = Database.getNilaiSpecific(this.currentUser.getNim(), this.selectedQuiz.getQuizId());
                    this.jLabel2.setText("Nilai: "+nilai);
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Timeline");

        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Nilai:");

        jButton1.setText("Attempt Quiz");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        QuizView start = new QuizView(this.selectedQuiz, this.questions);
        this.setVisible(false);
        start.setVisible(true);
        start.setSub(this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void sendScore(double nilai) {
        this.sub.addNilai(currentUser, this.selectedQuiz.getQuizId(), nilai);
        this.jList1.clearSelection();
    }

    public void setSub(ControllerToView sub) {
        this.sub = sub;
    }
}
