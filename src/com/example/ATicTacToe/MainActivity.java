package com.example.ATicTacToe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    BoardView m_boardView;
    TicTacToe m_ttt = new TicTacToe();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if ( savedInstanceState != null ) {
            String state = savedInstanceState.getString( "stateTTT" );
            m_ttt.set( state );
        }
        m_boardView = (BoardView) findViewById(R.id.board);
        m_boardView.setBoard( m_ttt.toString() );

        m_boardView.setMoveHandler( new OnMoveEventHandler() {
            @Override
            public void onMove(int col, int row) {
                String str = "(" + col + "," + (2 - row) + ")";
                TicTacToe.Move move = m_ttt.strToMove(str);
                if ( move != null ) {
                    m_ttt.makeMove( move );
                    m_boardView.setBoard(m_ttt.toString());
                    if ( m_ttt.isGameOver() ) {
                        Toast.makeText(getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void buttonNewGame( View view ) {
        m_ttt.reset();
        m_boardView.setBoard( m_ttt.toString() );
    }

    private Random m_random = new Random();

    public void buttonPlay( View view ) {
        List<TicTacToe.Move> actions = m_ttt.getActions();
        if ( !actions.isEmpty() ) {
            int idx = m_random.nextInt( actions.size() );
            TicTacToe.Move move = actions.get(idx);
            m_ttt.makeMove( move );
            m_boardView.setBoard(m_ttt.toString());
            if ( m_ttt.isGameOver() ) {
                Toast.makeText(getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState( Bundle saveInstanceState ) {
        super.onSaveInstanceState( saveInstanceState );
        saveInstanceState.putString("stateTTT", m_ttt.toString() );
    }

}
