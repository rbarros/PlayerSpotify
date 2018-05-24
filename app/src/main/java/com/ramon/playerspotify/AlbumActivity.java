package com.ramon.playerspotify;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PARAM_NOME = "PARAM_NOME";
    private Button fecharButton;
    private TextView nomeTextView;

    private Button playBtn;
    private SeekBar posicaoSeekBar;
    private SeekBar volumeSeekBar;
    private TextView tempoDecorridoTextView;
    private TextView tempoTotalTextView;
    private MediaPlayer mp;
    private int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        nomeTextView = findViewById(R.id.activity_album_nome_text_view);
        fecharButton = findViewById(R.id.activity_album_fechar_button);
        fecharButton.setOnClickListener(this);

        String nome = getIntent().getStringExtra(PARAM_NOME);
        nomeTextView.setText(nome);

        playBtn = (Button) findViewById(R.id.activity_album_play_button);
        tempoDecorridoTextView = (TextView) findViewById(R.id.activity_album_tempo_decorrido_text_view);
        tempoTotalTextView = (TextView) findViewById(R.id.activity_album_tempo_total_text_view);

        // Media Player
        mp = MediaPlayer.create(this, R.raw.music);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        // Position Bar
        posicaoSeekBar = (SeekBar) findViewById(R.id.activity_album_posicao_seek_bar);
        posicaoSeekBar.setMax(totalTime);
        posicaoSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            posicaoSeekBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


        // Volume Bar
        volumeSeekBar = (SeekBar) findViewById(R.id.activity_album_volume_seek_bar);
        volumeSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float volumeNum = progress / 100f;
                        mp.setVolume(volumeNum, volumeNum);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // Thread (Update positionBar & timeLabel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            posicaoSeekBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            tempoDecorridoTextView.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            tempoTotalTextView.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick(View view) {

        if (!mp.isPlaying()) {
            // Stopping
            mp.start();
            playBtn.setBackgroundResource(R.drawable.stop);

        } else {
            // Playing
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }

    }

    @Override
    public void onClick(View view) {
        if (view == fecharButton) {
            finish();
        }
    }
}
