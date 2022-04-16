package com.shailey.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class  MainActivity extends AppCompatActivity {

    //0:red, 1:yellow, 2:empty
    int stepCounter = 0;
    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8}, {0,4,8},{2,4,6}};
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter= parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive){
            stepCounter++;
        gameState[tappedCounter]= activePlayer;

        counter.setTranslationY(-1500);

        if(activePlayer==0){

            counter.setImageResource(R.drawable.red);

            activePlayer = 1;

        }

        else if(activePlayer==1){

            counter.setImageResource(R.drawable.yellow);

            activePlayer=0;
        }


        counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);

        for(int[] winningPosition: winningPositions) {
            String message = "";
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {


                gameActive=false;

                if (activePlayer == 1) {

                    message = "Red has Won";
                } else if (activePlayer == 0) {
                    message = "Yellow has won ";
                }

                //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                Button resetButton = (Button) findViewById(R.id.resetButton);
                TextView playerWon = (TextView) findViewById(R.id.playerWon);

                playerWon.setText(message);
                playerWon.setVisibility(View.VISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }else {


                if (stepCounter >= 9 ) {
                    stepCounter=0;
                    message = "It's a draw";
                    Button resetButton = (Button) findViewById(R.id.resetButton);
                    TextView playerWon = (TextView) findViewById(R.id.playerWon);

                    playerWon.setText(message);
                    playerWon.setVisibility(View.VISIBLE);
                    resetButton.setVisibility(View.VISIBLE);



                }


            }



          //  Toast.makeText(this, "as"+stepCounter, Toast.LENGTH_SHORT).show();
        }

            /*for (int element : gameState) {
                if (element !=2 ) {
                    Toast.makeText(this, "It's a Draw", Toast.LENGTH_SHORT).show();;
                }*/
            }


    }

    public void reset(View view) {
        Button resetButton = (Button) findViewById(R.id.resetButton);
        TextView playerWon = (TextView) findViewById(R.id.playerWon);
        playerWon.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout =(GridLayout)findViewById(R.id.gridLayout);
        stepCounter=0;
        for (int i=0 ;i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
             activePlayer = 0;

             for(int i =0 ; i< gameState.length;i++ ){

                 gameState[i]=2;
             }
            //gameState={2,2,2,2,2,2,2,2,2};
            gameActive=true;
        }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
