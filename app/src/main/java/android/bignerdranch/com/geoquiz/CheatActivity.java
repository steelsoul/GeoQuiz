package android.bignerdranch.com.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Cheat activity :)
 * Created by alex on 27.09.17.
 */

public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";

    public static final String EXTRA_SHOWN_ANSWER =
            "com.bignerdranch.android.geoquiz.answer_shown";

    private static final String KEY_SHOWN_VALUE = "cheater!";

    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private TextView mAPITextView;

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_SHOWN_ANSWER, mAnswerIsShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = findViewById(R.id.ansterTextView);

        mAPITextView = findViewById(R.id.versionAPITextView);
        mAPITextView.setText("API level " + Build.VERSION.SDK_INT);

        mShowAnswerButton = findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerIsShown = true;
                setAnswerShownResult();
            }
        });
        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(KEY_SHOWN_VALUE, false);
        }

        setAnswerShownResult();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstateState) {
        super.onSaveInstanceState(savedInstateState);
        savedInstateState.putBoolean(KEY_SHOWN_VALUE, mAnswerIsShown);
    }
}
