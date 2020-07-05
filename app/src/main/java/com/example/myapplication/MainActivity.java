package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:round, 1:x
    MediaPlayer m1,m2,m3,m4;
    int[] board={2,2,2,2,2,2,2,2,2};
    int[][] winni={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int player=0;
    boolean gameActive=true;
    @SuppressLint("WrongConstant")
    public void dropIn(View v)
    {

        Log.i("info","pressed");
        ImageView counter=(ImageView) v;
        int pos=Integer.parseInt(counter.getTag().toString());


        if(board[pos]==2 && gameActive==true) {
            counter.setTranslationY(-1500);
            board[pos]=player;
            if (player == 0) {
                player = 1;
                counter.setImageResource(R.drawable.o);
                counter.animate().translationYBy(1500).setDuration(100);
                m1.start();
            } else {
                player = 0;
                m1.start();
                counter.setImageResource(R.drawable.x);
                counter.animate().translationYBy(1500).setDuration(100);
            }
            for (int[] winn : winni) {
                if (board[winn[0]] == board[winn[1]] && board[winn[1]] == board[winn[2]] && board[winn[0]]!=2)
                {
                    gameActive=false;

                    if(board[winn[0]]==0)
                    {
                        m3.start();
                        Toast.makeText(this,"player 1 wins!!!",0).show();
                    }
                    else
                    {
                        m4.start();
                        Toast.makeText(this,"player 2 wins!!!",0).show();
                    }

                    Button btn=(Button)findViewById(R.id.playagain);
                    btn.setVisibility(View.VISIBLE);
                }

            }
            int i=0;
            while(i<9)
            {
                if(board[i]==2)
                {

                    break;
                }

                i++;

            }
            if(i==9)
            {
                Toast.makeText(this,"Match Drawn",0).show();
                Button btn=(Button)findViewById(R.id.playagain);
                btn.setVisibility(View.VISIBLE);

            }
        }
        else
        {
            m2.start();
            Toast.makeText(this, "Illegal move", 0).show();

        }

    }

  //  @SuppressLint("WrongConstant")
    public void playAgain(View v)
    {
        Button btn=(Button)findViewById(R.id.playagain);
        btn.setVisibility(View.INVISIBLE);
        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView counter=(ImageView)grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Log.i("info","Play Again pressed");
    for(int i=0;i<board.length;i++)
       {
           board[i]=2;
       }
        player=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         m1=MediaPlayer.create(this,R.raw.woosh);
       m2=MediaPlayer.create(this,R.raw.wrong);
         m3=MediaPlayer.create(this,R.raw.player1);
         m4=MediaPlayer.create(this,R.raw.player2);


    }
}
