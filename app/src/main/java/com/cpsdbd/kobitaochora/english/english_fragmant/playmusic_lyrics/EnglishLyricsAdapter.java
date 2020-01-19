package com.cpsdbd.kobitaochora.english.english_fragmant.playmusic_lyrics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.playmusic_lyrics.LyricsAdapter;

import java.util.List;

public class EnglishLyricsAdapter extends RecyclerView.Adapter<EnglishLyricsAdapter.LyricsHolder> {

    private EnglishLyricsActivity englishLyricsActivity;
    private List<String> lyricList;
    private LayoutInflater inflater;

    public EnglishLyricsAdapter(EnglishLyricsActivity englishLyricsActivity, List<String> lyricList) {
        this.englishLyricsActivity = englishLyricsActivity;
        this.lyricList = lyricList;
        this.inflater = LayoutInflater.from(englishLyricsActivity);
    }

    @NonNull
    @Override
    public LyricsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lyric,parent,false);
        return new LyricsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LyricsHolder holder, int position) {
        String ll = lyricList.get(position);
        holder.bind(ll);
    }

    @Override
    public int getItemCount() {
        return lyricList.size();
    }

    public class LyricsHolder extends RecyclerView.ViewHolder {

        TextView tvLyric;

        public LyricsHolder(@NonNull View itemView) {
            super(itemView);
            tvLyric =  itemView.findViewById(R.id.lyric);
        }

        public void bind(String ll) {
            tvLyric.setText(ll);
        }
    }
}
