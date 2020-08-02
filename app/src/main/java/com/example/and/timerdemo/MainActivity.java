package com.example.and.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    TextView display;
    String message;
    int minute,second;
    SeekBar seekBar;
    boolean counting=false;
    Button button;
    CountDownTimer yourCountDownTimer;

    public void countDown(View view){

        if (counting){
            seekBar.setEnabled(true);
            button.setText("GO!");
            counting=false;
            yourCountDownTimer.cancel();

        } else {
            seekBar.setEnabled(false);
            button.setText("STOP!");
            counting=true;
            int seconds = seekBar.getProgress();
            Log.i("from seeker", String.valueOf(seconds));
            yourCountDownTimer=new CountDownTimer(seconds * 1000, 1000) {
                public void onTick(long milissecondsUntildone) {
                    setClock((int) (milissecondsUntildone / 1000));
                }
                public void onFinish() {
                    seekBar.setEnabled(true);
                    button.setText("GO!");
                    counting=false;
                }
            }.start();
        }
    }

    public void setClock(int seconds){
        minute=seconds/60;
        second=seconds%60;
        message=String.format("%02d:%02d", minute, second);
        display.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button);
        button.setText("GO!");
        display=(TextView) findViewById(R.id.textView);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(300);
        seekBar.setProgress(30);
        setClock(30);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setClock(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /* every second prints a message in logs
        new CountDownTimer(10000,100){
            public void onTick(long milissecondsUntildone){
                Log.i("seconds left",String.valueOf(milissecondsUntildone/1000));
            }

            public void onFinish(){
                Log.i("We are done","No more coundown");
            }
        }.start();
        */

        /* every second prints a message in logs
        final Handler handler=new Handler();
        Runnable run=new Runnable() {
            @Override
            public void run() {
                Log.i("Testing output","A second passed by");
                handler.postDelayed(this,1000);
            }
        };
        handler.post(run);
        */
    }
}
