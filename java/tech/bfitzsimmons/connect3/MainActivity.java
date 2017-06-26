package tech.bfitzsimmons.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; //0 is yellow, 1 is red
    boolean isGameOver = false;
    int moves = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //2 means unplayed
    int[][] winningPositions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(isGameOver == false) {
            if (gameState[tappedCounter] == 2) {
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.red);
                    counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
                    activePlayer = 0;
                }
                moves++;
            }
            for (int[] winningPosition : winningPositions) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainDiv);
                TextView message = (TextView) findViewById(R.id.message);

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    String winner = "yellow!";
                    if (gameState[winningPosition[0]] == 1)
                        winner = "red!";
                    message.setText("Winner is " + winner);
                    layout.setScaleX(0f);
                    layout.setScaleY(0f);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().alpha(1f).rotationBy(3600).scaleX(1f).scaleY(1f).setDuration(800);
                    isGameOver = true;
                    moves = 0;
                } else if(moves == gameState.length){
                    message.setText("Tie!");
                    layout.setScaleX(0f);
                    layout.setScaleY(0f);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().alpha(1f).rotationBy(3600).scaleX(1f).scaleY(1f).setDuration(800);
                    isGameOver = true;
                    moves = 0;
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainDiv);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0; //0 is yellow, 1 is red
        isGameOver = false;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
