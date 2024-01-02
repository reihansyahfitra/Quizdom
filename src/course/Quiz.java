package course;

import java.util.ArrayList;

public class Quiz {
    private String heldBy;
    private int quizId;
    private String quizTitle;

    public Quiz(String heldBy, int quizId, String quizTitle) {
        this.heldBy = heldBy;
        this.quizId = quizId;
        this.quizTitle = quizTitle;
    }

    public int getQuizId() {
        return quizId;
    }    

    public String getHeldBy() {
        return heldBy;
    }

    public String getQuizTitle() {
        return quizTitle;
    }
    
    
}