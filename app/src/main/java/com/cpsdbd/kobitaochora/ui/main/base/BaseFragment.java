package com.cpsdbd.kobitaochora.ui.main.base;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.ui.lyric.SongLyricsActivity;
import com.cpsdbd.kobitaochora.model.Kobita;
import com.cpsdbd.kobitaochora.utils.Constant;

import java.io.Serializable;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment  {


    private BaseFragmentAdapter adapter;
    private RecyclerView rv_kobitaList;


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BaseFragmentAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_base, container, false);

        initView(view);
        return view;

    }

    private void initView(View view) {
        rv_kobitaList = view.findViewById(R.id.rv_list_of_kobita);
        rv_kobitaList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv_kobitaList.setAdapter(adapter);

    }



    public void updateKobita(List<Kobita> kobitaList) {

        for (Kobita kobita : kobitaList)
        {
            if (kobita != null) {
                adapter.addKobita(kobita);
            }
        }
    }


    public void startSongLyricsActivity(Kobita kobita) {
        Intent intent = new Intent(getContext(), SongLyricsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.POEM, kobita);
        bundle.putSerializable(Constant.POEM_LIST, (Serializable) adapter.getKobitaList());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
