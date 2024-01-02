package view;

import controller.DisposeScore;
import controller.MhsToView;
import course.Question;
import course.Quiz;
import java.util.ArrayList;

public class QuizView extends javax.swing.JFrame implements DisposeScore {

    private MhsToView sub;
    private Quiz quiz;
    private ArrayList<Question> questions;
    private int currentQuestion;
    private double nilai;
    private ArrayList<String> answers;
    
    public QuizView(Quiz quiz, ArrayList<Question> questions) {
        initComponents();
        this.quiz = quiz;
        this.currentQuestion = 0;
        this.nilai=0;
        this.questions = new ArrayList();
        this.answers = new ArrayList();
        for(Question q:questions){
            if(q.getIdQuiz()==this.quiz.getQuizId()){
                this.questions.add(q);
            }
        }
        this.jLabel1.setText(this.questions.get(this.currentQuestion).getQuestion());
        this.jRadioButton1.setText("A. "+this.questions.get(this.currentQuestion).getChoice1());
        this.jRadioButton1.setActionCommand("A. "+this.questions.get(this.currentQuestion).getChoice1());
        this.jRadioButton2.setText("B. "+this.questions.get(this.currentQuestion).getChoice2());
        this.jRadioButton2.setActionCommand("B. "+this.questions.get(this.currentQuestion).getChoice2());
        this.jRadioButton3.setText("C. "+this.questions.get(this.currentQuestion).getChoice3());
        this.jRadioButton3.setActionCommand("C. "+this.questions.get(this.currentQuestion).getChoice3());
        this.jRadioButton4.setText("D. "+this.questions.get(this.currentQuestion).getChoice4());
        this.jRadioButton4.setActionCommand("D. "+this.questions.get(this.currentQuestion).getChoice4());
    }

    public void setSub(MhsToView sub) {
        this.sub = sub;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("A");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("B");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("C");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("D");

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void disposing(){
        this.dispose();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.currentQuestion==this.questions.size()-1){
            this.answers.add(this.buttonGroup1.getSelection().getActionCommand().substring(0,1));
            int jumlahSoal = this.questions.size();
            double nilaiMaks = 0;
            double nilaiSekarang = 0;
            for(Question q:this.questions){
                nilaiMaks += q.getBobot();
            }
            for (int i = 0; i < jumlahSoal; i++) {
                System.out.println(this.answers.get(i));
                if(this.answers.get(i).equals(this.questions.get(i).getAnswer())){
                    nilaiSekarang += this.questions.get(i).getBobot();
                }
            }
            double nilaiAkhir = (nilaiSekarang/nilaiMaks)*100;
            ScoreView result = new ScoreView(this, nilaiAkhir);
            this.sub.sendScore(nilaiAkhir);
            result.setVisible(true);
            result.setSub(this);
        }else{
            if(this.buttonGroup1.getSelection()!=null){
                this.answers.add(this.buttonGroup1.getSelection().getActionCommand().substring(0,1));
                this.currentQuestion++;
                this.buttonGroup1.clearSelection();
                this.jLabel1.setText(this.questions.get(this.currentQuestion).getQuestion());
                this.jRadioButton1.setText("A. "+this.questions.get(this.currentQuestion).getChoice1());
                this.jRadioButton1.setActionCommand("A. "+this.questions.get(this.currentQuestion).getChoice1());
                this.jRadioButton2.setText("B. "+this.questions.get(this.currentQuestion).getChoice2());
                this.jRadioButton2.setActionCommand("B. "+this.questions.get(this.currentQuestion).getChoice2());
                this.jRadioButton3.setText("C. "+this.questions.get(this.currentQuestion).getChoice3());
                this.jRadioButton3.setActionCommand("C. "+this.questions.get(this.currentQuestion).getChoice3());
                this.jRadioButton4.setText("D. "+this.questions.get(this.currentQuestion).getChoice4());
                this.jRadioButton4.setActionCommand("D. "+this.questions.get(this.currentQuestion).getChoice4());
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
