package com.cpsdbd.kobitaochora.bangla.bangla_fragmant;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.playmusic_lyrics.SongLyricsActivity;
import com.cpsdbd.kobitaochora.model.Kobita;
import com.cpsdbd.kobitaochora.utils.Constant;

import java.io.Serializable;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BanglaFragment extends Fragment implements BanglaContract.View {

    BanglaPresenter mPresenter;
    private BanglaFragmentAdapter adapter;
    private RecyclerView rv_kobitaList;


    public BanglaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new BanglaPresenter(this);
        adapter = new BanglaFragmentAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bangla, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        rv_kobitaList = view.findViewById(R.id.rv_list_of_kobita);
        rv_kobitaList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv_kobitaList.setAdapter(adapter);

        mPresenter.getAllKobita();
    }


    @Override
    public void updateKobita(List<Kobita> kobitaList) {

        for (Kobita kobita : kobitaList)
        {
            adapter.addKobita(kobita);
        }
    }

    @Override
    public void startSongLyricsActivity(Kobita kobita) {
        Intent intent = new Intent(getContext(), SongLyricsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.POEM, kobita);
        bundle.putSerializable(Constant.POEM_LIST, (Serializable) adapter.getKobitaList());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
