package com.cpsdbd.kobitaochora.bangla.bangla_fragmant;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.ArrayList;
import java.util.List;

public class BanglaFragmentAdapter extends RecyclerView.Adapter<BanglaFragmentAdapter.BanglaFragmentHolder>{

    private BanglaFragment banglaFragment;
    private LayoutInflater inflater;
    private List<Kobita> kobitaList;

    public BanglaFragmentAdapter(BanglaFragment banglaFragment) {
        this.banglaFragment = banglaFragment;
        this.inflater =  LayoutInflater.from(banglaFragment.getContext());
        this.kobitaList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BanglaFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_kobita_o_chora, parent ,false);
        return new BanglaFragmentAdapter.BanglaFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanglaFragmentHolder holder, int position) {

        Kobita kobita = kobitaList.get(position);
        holder.bind(kobita);
    }

    @Override
    public int getItemCount() {
        return kobitaList.size();
    }

    public  void addKobita(Kobita kobita){
        kobitaList.add(kobita);
        int position = kobitaList.indexOf(kobita);
        notifyItemInserted(position);

    }

    public void removekobita(Kobita kobita) {
        int position = getPosition(kobita);
        if (position != -1) {
            kobitaList.remove(position);
            notifyItemRemoved(position);
        }
    }

    private int getPosition(Kobita kobita) {

        for (Kobita x : kobitaList) {
            if (x.getId().equals(kobita.getId())) {
                return kobitaList.indexOf(x);
            }
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public List<Kobita> getKobitaList(){
        return this.kobitaList;
    }


    public class BanglaFragmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_kobitaName,tv_id_no;

        public BanglaFragmentHolder(@NonNull View itemView) {
            super(itemView);
            tv_kobitaName = itemView.findViewById(R.id.tv_kobit_name);
            tv_id_no = itemView.findViewById(R.id.tv_kobita_number);
            itemView.setOnClickListener(this);

        }

        public void bind(Kobita kobita) {

            tv_kobitaName.setText(kobita.getKobitaName());
            tv_id_no.setText(kobita.getId());
        }


        @Override
        public void onClick(View v) {
            banglaFragment.startSongLyricsActivity(kobitaList.get(getAdapterPosition()));
            Log.d("LogResult","Activity Start");
        }
    }
}
