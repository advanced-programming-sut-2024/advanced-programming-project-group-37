package message.client;


import message.enums.loginMenu.ConfirmQuestions;

public class PickQuestionMessage extends ClientMessage{
    RegisterMassage registerMassage;
    String answer;
    ConfirmQuestions confirmQuestions;

    public PickQuestionMessage(ConfirmQuestions confirmQuestions, RegisterMassage registerMassage, String answer) {
        this.confirmQuestions = confirmQuestions;
        this.registerMassage =registerMassage;
        this.type = MessageType.PICK_QUESTION;
        this.answer = answer;
    }

    public ConfirmQuestions getQuestions() {
        return confirmQuestions;
    }

    public RegisterMassage getRegisterMassage() {
        return registerMassage;
    }

    public String getAnswer() {
        return answer;
    }
}
