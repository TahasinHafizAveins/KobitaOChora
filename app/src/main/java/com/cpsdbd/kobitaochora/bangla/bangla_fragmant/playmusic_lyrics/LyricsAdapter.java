package com.cpsdbd.kobitaochora.bangla.bangla_fragmant.playmusic_lyrics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;

import java.util.List;

public class LyricsAdapter extends RecyclerView.Adapter<LyricsAdapter.LyricsHolder> {


    private SongLyricsActivity activity;
    private List<String> lyricList;
    private LayoutInflater inflater;

    public LyricsAdapter(SongLyricsActivity activity, List<String> lyricList) {
        this.activity = activity;
        this.lyricList = lyricList;
        this.inflater = LayoutInflater.from(activity);
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

    public void updateLyrics(List<String> lyricList){
       this.lyricList = lyricList;
       notifyDataSetChanged();
    }

    public static class LyricsHolder extends RecyclerView.ViewHolder{

        TextView tvLyric;

        public LyricsHolder(@NonNull View itemView) {
            super(itemView);
            tvLyric =  itemView.findViewById(R.id.lyric);
        }

        public void bind(String lyric){
            tvLyric.setText(lyric);
        }
    }
}
