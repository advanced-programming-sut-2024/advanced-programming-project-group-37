package model.enums.loginMenu;

public enum ConfirmQuestions {
    q1("What is your lucky number?"),
    q2("What is your favorite color?"),
    q3(""),
    q4(""),
    q5("");

    private final String Question;

    ConfirmQuestions(String Question) {
        this.Question = Question;
    }

    public String getQuestion() {
        return Question;
    }
}
