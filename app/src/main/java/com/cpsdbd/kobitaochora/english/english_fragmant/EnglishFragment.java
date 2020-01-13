package com.cpsdbd.kobitaochora.english.english_fragmant;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsdbd.kobitaochora.R;
import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.BanglaFragmentAdapter;
import com.cpsdbd.kobitaochora.bangla.bangla_fragmant.BanglaPresenter;
import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment implements EnglishContract.View {

    EnglishPresenter mPresenter;
    private EnglishFragmentAdapter adapter;
    private RecyclerView rv_kobitaList;

    public EnglishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new EnglishPresenter(this);
        adapter = new EnglishFragmentAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_english, container, false);
        View view =  inflater.inflate(R.layout.fragment_english, container, false);
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
}
