package org.sungshin.lnk.learningnorthkorean.object;

public class Problem {
    String qusetion;
    boolean inputAnswer;
    String rightAnswer;
    String detail;

    public Problem(String question, boolean inputAnswer, String rightAnswer, String detail) {
        this.qusetion = question;
        this.inputAnswer = inputAnswer;
        this.rightAnswer = rightAnswer;
        this.detail = detail;
    }

    public String getQusetion() {
        return qusetion;
    }

    public void setQusetion(String qusetion) {
        this.qusetion = qusetion;
    }

    public boolean isInputAnswer() {
        return inputAnswer;
    }

    public void setInputAnswer(boolean inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
