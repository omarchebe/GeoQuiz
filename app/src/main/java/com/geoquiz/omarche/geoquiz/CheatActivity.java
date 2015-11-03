package com.geoquiz.omarche.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.geoquiz.omarche.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.geoquiz.omarche.geoquiz.answer_shown";


    private static final String KEY_CHEAT = "cheatValue";
    private static final String KEY_TEXT = "cheatText";

    private boolean mAnswerIsTrue;
    private boolean isCheat;

    private TextView mAnswerTextView;
    private Button mShowAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        setAnswerShownResult(false);
        isCheat = false;

        inicializarComponentesUI();
        if (savedInstanceState != null) {
            isCheat = savedInstanceState.getBoolean(KEY_CHEAT, false);
            mAnswerTextView.setText(savedInstanceState.getString(KEY_TEXT));
            setAnswerShownResult(isCheat);

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putBoolean(KEY_CHEAT, isCheat);
        String value = mAnswerTextView.getText().toString();
        savedState.putString(KEY_TEXT,value);
    }

    private void inicializarComponentesUI() {
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_buton);
                setAnswerShownResult(true);
                isCheat = true;
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }


}
