package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekbar;
    boolean active=false;
    Button goButton;
    CountDownTimer cntdwn;


    public void reset(){
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        timerSeekbar.setEnabled(true);
        cntdwn.cancel();
        goButton.setText("GO!");
        active=false;

    }
    public void start(View v) {
        if(active){
            reset();
        }
        else{
        active=true;
        timerSeekbar.setEnabled(false);
        goButton.setText("STOP!");

        cntdwn=new CountDownTimer(timerSeekbar.getProgress()*1000+100,1000) {
            @Override
            public void onTick(long l) {
                updateTimer((int)l/1000);

            }

            @Override
            public void onFinish() {
                MediaPlayer snd=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                snd.start();
                reset();
            }
        }.start();
        }

    }


    public void updateTimer(int secsleft){

        int min=secsleft/60,sec=secsleft-min*60;
        String secs=Integer.toString(sec);
        if(secsleft<=9)secs="0"+secs;

        timerTextView.setText(Integer.toString(min)+":"+secs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerSeekbar=findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.countDown);
        goButton=findViewById(R.id.goButton);

        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
