package com.example.sathishkumar.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = Red, 1 = yellow, 2 = empty
    int activePlayer = 0;

int[] gameState = {2,2,2,2,2,2,2,2,2};
int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

//Find ing the clicked imageview
    boolean gameActive = true;

    public void click(View view)
    {
        //Assigning that view to a variable counter
        ImageView counter = (ImageView) view;

        //Finding the the tag of which image is tapped.

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //Assigning 0(Red) to tapped posision

        if(gameState[tappedCounter] ==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;

            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;

            }
            counter.animate().rotation(3600).translationYBy(1500).setDuration(200);

            for (int[] position : winningPositions) {

                if ((gameState[position[0]] == gameState[position[1]]) && (gameState[position[1]] == gameState[position[2]]) && (gameState[position[0]] != 2)) {

                    gameActive = false;


//                    Button playAgainBut = (Button) findViewById(R.id.playAgainButton);
//                    playAgainBut.setVisibility(View.VISIBLE);

                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                    winnerText.setVisibility(View.VISIBLE);

                    String message = "";
                    if (activePlayer == 0) {

                        message = "Yellow";
                    } else if (activePlayer == 1) {

                        message = "Red";
                    }

                    winnerText.setText(message + " has won");

                }


                    Button playAgainBut = (Button) findViewById(R.id.playAgainButton);
                    playAgainBut.setVisibility(View.VISIBLE);

            }

        }
    }

    public void playAgain(View view)
    {
        Button playAgainBut =  findViewById(R.id.playAgainButton);
        TextView winnerText =  findViewById(R.id.winnerTextView);

        playAgainBut.setVisibility(View.INVISIBLE);


        winnerText.setVisibility(View.INVISIBLE);


        GridLayout gridLayout =  findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }




        for(int i=0; i<gameState.length; i++ )
        {
            gameState[i] = 2;
        }

        activePlayer = 0;

       gameActive = true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
