package com.chesnowitz.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // active player 0 = green 1 = red
    int activePlayer = 0;
    boolean gameActtive = true;
    // 2 is slot empty  set a tag on each imageview ()see xml
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};

    int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView chip = (ImageView) view;

        System.out.println(chip.getTag().toString());

        int chipTapped = Integer.parseInt(chip.getTag().toString());

        if (gameState [chipTapped] == 2 && gameActtive) {

            gameState[chipTapped] = activePlayer;

            chip.setTranslationY(-1000f);

            if (activePlayer == 0) {
                chip.setImageResource(R.drawable.green);

                activePlayer = 1;
            } else {
                chip.setImageResource(R.drawable.red);

                activePlayer = 0;
            }
            chip.animate().translationYBy(1000f).rotation(700).setDuration(500);

            for (int[] winningState : winningStates) {
                if(gameState[winningState[0]] == gameState[winningState[1]] &&
                        gameState[winningState[1]] == gameState[winningState[2]] &&
                        gameState[winningState[0]] != 2) {
//                    System.out.println("Winner " + gameState[winningState[0]]);
//                    Toast.makeText(this, "Green Has Won", Toast.LENGTH_SHORT).show();


//                    and the winner is...
                    gameActtive = false;

                    String winningText = "Red";

                    if (gameState[winningState[0]] == 0) {
                    winningText = "Green";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winningText + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameOver = true;
                    for (int chipState : gameState) {
                        if (chipState == 2) gameOver = false;
                    }

                    if (gameOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a Tie Game");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {

        gameActtive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
