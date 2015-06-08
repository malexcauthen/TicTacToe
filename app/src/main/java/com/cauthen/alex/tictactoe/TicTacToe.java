package com.cauthen.alex.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TicTacToe extends ActionBarActivity implements View.OnClickListener {


    Button r1b1, r1b2, r1b3, r2b1, r2b2, r2b3, r3b1, r3b2, r3b3, newGame;    // r = row, b = button, row 1 button 1...
    Button[] boardArray;                                                // stores buttons
    TextView textview;

    boolean playerTurn = true;                                      // player 1 = true (X), player 2 = false (O)
    int turnCount = 0;                                              // counts number of buttons pressed
    boolean thereIsAWinner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        textview = (TextView) findViewById(R.id.textview);
        updateTextView();

        r1b1 = (Button) findViewById(R.id.TTT_button1);
        r1b2 = (Button) findViewById(R.id.TTT_button2);
        r1b3 = (Button) findViewById(R.id.TTT_button3);
        r2b1 = (Button) findViewById(R.id.TTT_button4);
        r2b2 = (Button) findViewById(R.id.TTT_button5);
        r2b3 = (Button) findViewById(R.id.TTT_button6);
        r3b1 = (Button) findViewById(R.id.TTT_button7);
        r3b2 = (Button) findViewById(R.id.TTT_button8);
        r3b3 = (Button) findViewById(R.id.TTT_button9);

        boardArray = new Button[]{r1b1, r1b2, r1b3, r2b1, r2b2, r2b3, r3b1, r3b2, r3b3};

        for (Button boardButton : boardArray)
        {
            boardButton.setOnClickListener(this);
        }

        newGame = (Button) findViewById(R.id.newGame_button);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartBoard();
                updateTextView();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button mButton = (Button) v;
        updateBoard(mButton);
    }


    // updates textview to show state of game
    private void updateTextView() {
        //checks to see if a player has won
        if (thereIsAWinner) {
            if (!playerTurn) {
                textview.setText("Player 1 wins!!!");
            }

            else {
                textview.setText("Player 2 wins!!!");
            }
        }

        // updates if there is a tie
        else if (turnCount == 9 && thereIsAWinner == false) {
            textview.setText("There was a tie! Play another game!");
        }

        // updates turn on textview
        else if (playerTurn) {
            textview.setText("Player 1, it is your turn");
        }

        else if (playerTurn == false) {
            textview.setText("Player 2, it is your turn");
        }
    }

    private void winner() {

        // check rows for winner
        // row 1
        if(r1b1.getText() == r1b2.getText() && r1b1.getText() == r1b3.getText() && !r1b1.isClickable()) {
            thereIsAWinner = true;
        }

        // row 2
        else if (r2b1.getText() == r2b2.getText() && r2b1.getText() == r2b3.getText() && !r2b1.isClickable()) {
            thereIsAWinner = true;
        }

        // row 3
        else if(r3b1.getText() == r3b2.getText() && r3b1.getText() == r3b3.getText() && !r3b1.isClickable()) {
            thereIsAWinner = true;
        }

        // check columns for winner
        // column 1
        else if (r1b1.getText() == r2b1.getText() && r1b1.getText() == r3b1.getText() && !r1b1.isClickable()) {
            thereIsAWinner = true;
        }

        // column 2
        else if (r1b2.getText() == r2b2.getText() && r1b2.getText() == r3b2.getText() && !r1b2.isClickable()) {
            thereIsAWinner = true;
        }

        // column 3
        else if (r1b3.getText() == r2b3.getText() && r1b3.getText() == r3b3.getText() && !r1b3.isClickable()) {
            thereIsAWinner = true;
        }

        // check diagonal for winner
        // left to right diagonal
        else if (r1b1.getText() == r2b2.getText() && r1b1.getText() == r3b3.getText() && !r1b1.isClickable()) {
            thereIsAWinner = true;
        }

        // right to left diagonal
        else if (r1b3.getText() == r2b2.getText() && r1b3.getText() == r3b1.getText() && !r1b3.isClickable()) {
            thereIsAWinner = true;
        }



        if (thereIsAWinner) {
            disableBoard();
        }
    }

    private void updateBoard(Button mButton) {

        // updates text in button
        if (playerTurn) {
            mButton.setText("X");
        }

        else {
            mButton.setText("O");
        }

        mButton.getBackground().setColorFilter(Color.parseColor("#8A8A8A"), PorterDuff.Mode.DARKEN);
        mButton.setClickable(false);


        turnCount += 1;
        if (turnCount > 4) {
            winner();
        }

        // changes the players turn
        playerTurn = !playerTurn;

        // updates textview to show game state
        updateTextView();

    }


    private void restartBoard() {
        turnCount = 0;
        playerTurn = true;
        thereIsAWinner = false;

        for (Button boardButton : boardArray) {
            boardButton.setText("");
            boardButton.setClickable(true);
            boardButton.getBackground().setColorFilter(Color.parseColor("#8A8A8A"), PorterDuff.Mode.LIGHTEN);
        }
    }

    private void disableBoard() {
        for (Button boardButton : boardArray) {
            boardButton.setClickable(false);
            boardButton.getBackground().setColorFilter(Color.parseColor("#8A8A8A"), PorterDuff.Mode.DARKEN);
        }
    }

}
