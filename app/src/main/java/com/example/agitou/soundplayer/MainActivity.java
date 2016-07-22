package com.example.agitou.soundplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isPlaying = false;
    SeekBar seekBar;
    MediaPlayer mp;
    ImageButton b1;
    Button submit;
    Handler seekHandler = new Handler();
    EditText value;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declarations
        mp = MediaPlayer.create(this, R.raw.nextep);
        b1 = (ImageButton) this.findViewById(R.id.button);
        //seekBar = (SeekBar) findViewById(R.id.seekBar);
        //seekHandler = new Handler();
        value = (EditText) findViewById(R.id.editText);
        //submit = (Button) findViewById(R.id.button2);

        //Methods and stuff
        b1.setEnabled(false);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isPlaying) {
                    mp.pause();
                    b1.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mp.start();
                    b1.setImageResource(android.R.drawable.ic_media_pause);
                }
                isPlaying = !isPlaying;
            }
        });

        value.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    number = Integer.parseInt(value.getText().toString());
                    if(number >= 10){
                        mp.start();
                    }
                    else{
                        mp.pause();
                    }
                    handled = true;
                }
                return handled;
            }
        });
        /*MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mp != null){
                    int mCurrentPosition = mp.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                seekHandler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mp != null && fromUser){
                    mp.seekTo(progress * 1000);
                }
            }
        });*/

    }
}
