package com.geoquiz.omarche.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnTrue;
    private Button btnFalse;
    private Button btnNextButton;
    private Button btnPrevButton;
    private Button btnCheat;

    private TextView txtQuestionTextView;

    private static final String KEY_INDEX = "index";


    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, false)
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(CheatActivity.EXTRA_ANSWER_SHOWN,false);
        }
        updateQuestion();

    }

    @Override
    protected void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putInt(KEY_INDEX, mCurrentIndex);
        savedState.putBoolean(CheatActivity.EXTRA_ANSWER_SHOWN, mIsCheater);
    }

    private void inicializarComponentesUI() {
        btnTrue = (Button) findViewById(R.id.true_button);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        btnFalse = (Button) findViewById(R.id.false_button);
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

            }
        });

        txtQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        txtQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();

        btnNextButton = (Button) findViewById(R.id.next_button);
        btnNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                mIsCheater =  false;
            }
        });

        btnPrevButton = (Button) findViewById(R.id.prev_button);
        btnPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        btnCheat = (Button) findViewById(R.id.cheat_button);
        btnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].getmTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                //startActivity(i);
                startActivityForResult(i, 0);

            }
        });
    }

    private void checkAnswer(boolean userPressed) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].getmTrueQuestion();
        int messageResId = 0;

        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressed == answerIsTrue) {
                messageResId = R.string.correctToas;
            } else {
                messageResId = R.string.incorrectToast;
            }
        }
        Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();


    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        txtQuestionTextView.setText(question);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

}
