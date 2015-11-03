package com.geoquiz.omarche.geoquiz;

/**
 * Created by Omar Che on 27/10/2015.
 */
public class TrueFalse {

    private int mQuestion;
    private Boolean mTrueQuestion;

    public TrueFalse(int mQuestion, Boolean mTrueQuestion) {
        this.mQuestion = mQuestion;
        this.mTrueQuestion = mTrueQuestion;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public Boolean getmTrueQuestion() {
        return mTrueQuestion;
    }


    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setmTrueQuestion(Boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
}
