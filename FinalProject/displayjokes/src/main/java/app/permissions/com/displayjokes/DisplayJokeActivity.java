package app.permissions.com.displayjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DisplayJokeActivity extends AppCompatActivity {

    public static final String  TEXT_JOKE ="text_joke";

    private TextView txtJoke;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_joke_activity);
        handleIntent();
        txtJoke = (TextView)findViewById(R.id.txtJoke);
        txtJoke.setText(joke);
    }

    private void handleIntent(){
        this.joke = getIntent().getStringExtra(TEXT_JOKE);
    }
}
