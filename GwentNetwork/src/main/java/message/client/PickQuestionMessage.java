package message.client;


import message.enums.loginMenu.ConfirmQuestions;

public class PickQuestionMessage extends ClientMessage{
    RegisterMassage registerMassage;
    ConfirmQuestions confirmQuestions;

    public PickQuestionMessage(ConfirmQuestions confirmQuestions, RegisterMassage registerMassage) {
        this.confirmQuestions = confirmQuestions;
        this.registerMassage =registerMassage;
        this.type = MessageType.PICK_QUESTION;
    }

    public ConfirmQuestions getQuestions() {
        return confirmQuestions;
    }
}
