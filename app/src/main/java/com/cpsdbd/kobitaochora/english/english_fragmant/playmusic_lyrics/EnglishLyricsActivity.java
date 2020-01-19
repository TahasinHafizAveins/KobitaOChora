package com.cpsdbd.kobitaochora.english.english_fragmant.playmusic_lyrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.playmusic_lyrics.LyricsAdapter;
import com.cpsdbd.kobitaochora.model.Kobita;

import java.io.IOException;
import java.util.List;

public class EnglishLyricsActivity extends AppCompatActivity implements  View.OnClickListener,Runnable, EnglishSongLyricsContract.View {

    private EnglishSongLyricsPresenter mPresenter;
    private Kobita kobita;
    private Toolbar toolbar;
    private TextView tv_kobitaName, tv_kobitaNumber;
    ImageButton imgPlayPause, imagStop;
    SeekBar seekBar;
    private Handler handler;
    private Thread soundThread;


    private String musicUrl;

    MediaPlayer mediaPlayer;

    private EnglishLyricsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_song_lyrics);

        this.kobita = (Kobita) getIntent().getSerializableExtra("kobita");
        mPresenter = new EnglishSongLyricsPresenter(this);
//        mPresenter.getLyrics(kobita.getKobitaName());

        adapter = new EnglishLyricsAdapter(this,kobita.getKobitaLyrics());

        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_song_lyrics);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        tv_kobitaName = findViewById(R.id.toolbar_kobita_name);
        tv_kobitaNumber = findViewById(R.id.toolbar_kobita_number);
        imgPlayPause = findViewById(R.id.img_play_pause);
        imagStop = findViewById(R.id.img_stop);
        seekBar = findViewById(R.id.seekbar);

        tv_kobitaName.setText(kobita.getKobitaName());
        tv_kobitaNumber.setText(kobita.getId());
        musicUrl = kobita.getKobitaPlay();

        imgPlayPause.setOnClickListener(this);
        imagStop.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.rv_list_of_kobita);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(musicUrl);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepare();
            mediaPlayer.start();



        } catch (IOException e) {
            Log.d("mediaPlayer", "get error " + e.getMessage());
            e.printStackTrace();
        }



        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();

        soundThread = new Thread(this);
        soundThread.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }


    @Override
    public void lyrics(List<Kobita> kobitaList) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_play_pause:
                if (mediaPlayer.isPlaying()) {

                    mediaPlayer.pause();
                    imgPlayPause.setImageResource(R.drawable.player_play);
                } else {

                    mediaPlayer.start();
                    imgPlayPause.setImageResource(R.drawable.player_pause);
                }
                break;

            case R.id.img_stop:
                mediaPlayer.stop();
                imgPlayPause.setImageResource(R.drawable.player_play);
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(musicUrl);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    Log.d("mediaPlayer", "get error " + e.getMessage());
                    e.printStackTrace();
                }

        }
    }

    @Override
    public void run() {

        int currentPosition = 0;
        int soundTotal = mediaPlayer.getDuration();
        seekBar.setMax(soundTotal);

        while (mediaPlayer != null && currentPosition < soundTotal) {
            try {
                Thread.sleep(300);
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException soundException) {
                return;
            } catch (Exception otherException) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }

    }
}