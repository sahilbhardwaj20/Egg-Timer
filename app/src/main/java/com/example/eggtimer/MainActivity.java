package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekbar;
    TextView timerTextView;
    Button timerButton;
    Boolean counter=false;
    CountDownTimer countDownTimer;

    public void setTimerButton(View view){

        if(counter){
            resetTimer();
        }else {
            counter = true;
            timerSeekbar.setEnabled(false);
            timerButton.setText("STOP");

            countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();

        }

    }

    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        timerSeekbar.setEnabled(true);
        countDownTimer.cancel();
        counter=false;
        timerButton.setText("GO !");
    }

    public void updateTimer(int progress){
        int minutes = progress/60;
        int seconds = progress - (minutes*60);
        String offSetString= String.valueOf(seconds);

        if(seconds <= 9){
            offSetString = "0" + seconds;
        }

        timerTextView.setText(minutes + ":"+ offSetString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar=(SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        timerButton=(Button) findViewById(R.id.timerButton);

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