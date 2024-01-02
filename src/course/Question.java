package course;

public class Question {
    private int id;
    private int idQuiz;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String answer;
    private double bobot;

    public Question(int id, int idQuiz, String question, String choice1, String choice2, String choice3, String choice4, String answer, double bobot) {
        this.id = id;
        this.idQuiz = idQuiz;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.answer = answer;
        this.bobot = bobot;
    }

    public int getId() {
        return id;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getAnswer() {
        return answer;
    }

    public double getBobot() {
        return bobot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setBobot(int bobot) {
        this.bobot = bobot;
    }
}