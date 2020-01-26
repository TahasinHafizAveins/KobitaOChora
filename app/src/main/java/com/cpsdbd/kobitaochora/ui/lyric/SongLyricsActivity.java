package com.cpsdbd.kobitaochora.ui.lyric;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.model.Kobita;
import com.cpsdbd.kobitaochora.ui.main.BaseActivity;
import com.cpsdbd.kobitaochora.utils.Constant;

import java.io.IOException;
import java.util.List;

public class SongLyricsActivity extends BaseActivity implements View.OnClickListener, Runnable {

    private Kobita kobita;
    private Toolbar toolbar;
    private TextView tv_kobitaName, tv_kobitaNumber;
    ImageButton imgPlayPause, imagStop, imgNext, imgPrev;
    SeekBar seekBar;
    private Thread soundThread;
    private List<Kobita> kobitaList;

    MediaPlayer mediaPlayer;

    private LyricsAdapter adapter;
    private int currentPositon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_song_lyrics);
        this.kobita = (Kobita) getIntent().getSerializableExtra(Constant.POEM);
        this.kobitaList = (List<Kobita>) getIntent().getSerializableExtra(Constant.POEM_LIST);

        currentPositon = getPosition();

        adapter = new LyricsAdapter(this, kobita.getKobitaLyrics());

        initView();


    }

    private int getPosition() {

        Log.d("poemList", " " + kobita.getId());
        for (Kobita x : kobitaList) {
            Log.d("poemList", " " + x.getId());
            if (x.getId().equals(kobita.getId())) {
                return kobitaList.indexOf(x);
            }
        }
        return -1;
    }


    private void playKobita() {

        tv_kobitaName.setText(kobita.getKobitaName());
        tv_kobitaNumber.setText(kobita.getId());
        imgPlayPause.setImageResource(R.drawable.player_pause);
        adapter.updateLyrics(kobita.getKobitaLyrics());

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(kobita.getKobitaPlay());

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepare();
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.start();


        } catch (IOException e) {
            Log.d("mediaPlayer", "get error " + e.getMessage());
            e.printStackTrace();
        }

        soundThread = new Thread(this);
        soundThread.start();
    }

    private void pauseMediaPlayer() {
        mediaPlayer.pause();
        imgPlayPause.setImageResource(R.drawable.player_play);
    }

    private void playMediaPlayer() {
        mediaPlayer.start();
        imgPlayPause.setImageResource(R.drawable.player_pause);
    }

    private void stopMediaPlayer() {
        mediaPlayer.stop();
        imgPlayPause.setImageResource(R.drawable.player_play);
    }

    private void resetSeekbar(){
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(kobita.getKobitaPlay());
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.d("mediaPlayer", "get error " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        playKobita();

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_song_lyrics);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        setAds();

        tv_kobitaName = findViewById(R.id.toolbar_kobita_name);
        tv_kobitaNumber = findViewById(R.id.toolbar_kobita_number);
        imgPlayPause = findViewById(R.id.img_play_pause);
        imagStop = findViewById(R.id.img_stop);
        imgPrev = findViewById(R.id.img_prev);
        imgNext = findViewById(R.id.img_next);
        seekBar = findViewById(R.id.seekbar);

        imgPlayPause.setOnClickListener(this);
        imagStop.setOnClickListener(this);
        imgPrev.setOnClickListener(this);
        imgNext.setOnClickListener(this);

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

        RecyclerView recyclerView = findViewById(R.id.rv_list_of_kobita);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_play_pause:
                if (mediaPlayer.isPlaying()) {

                    pauseMediaPlayer();

                } else {
                    playMediaPlayer();
                }
                break;

            case R.id.img_stop:
                stopMediaPlayer();
                resetSeekbar();
                break;


            case R.id.img_next:
                Log.d("currentPs"," next click");
                stopMediaPlayer();
                currentPositon = (currentPositon + 1) % kobitaList.size();
                this.kobita = kobitaList.get(currentPositon);
                playKobita();


                break;

            case R.id.img_prev:
                Log.d("currentPs"," prev click");
                stopMediaPlayer();
                currentPositon = (currentPositon - 1) % kobitaList.size();
                if (currentPositon < 0){
                    currentPositon = kobitaList.size()-1;
                }
                this.kobita = kobitaList.get(currentPositon);
                playKobita();
               Log.d("currentPs"," "+currentPositon);

                break;

        }

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
    public void run() {

        int position = 0;
        int soundTotal = mediaPlayer.getDuration();
        seekBar.setMax(soundTotal);

        while (mediaPlayer != null && position < soundTotal) {
            try {
                Thread.sleep(100);
                position = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException soundException) {
                return;
            } catch (Exception otherException) {
                return;
            }
            seekBar.setProgress(position);
        }

    }


}

