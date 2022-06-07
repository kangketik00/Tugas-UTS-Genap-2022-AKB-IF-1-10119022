package com.mikhlasnr.diaryapp10119022.view.diary;
/*
    nim                 : 10119022
    nama                : Muhammad Ikhlas Naufalsyah Ranau
    kelas               : IF-1
*/
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikhlasnr.diaryapp10119022.R;
import com.mikhlasnr.diaryapp10119022.helper.DatabaseHelper;
import com.mikhlasnr.diaryapp10119022.models.DiaryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView noData;

    DatabaseHelper db;
    List<DiaryModel> diaryList;
    DiaryAdapter diaryAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiaryFragment newInstance(String param1, String param2) {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        add_button = view.findViewById(R.id.fab);
        empty_imageView = view.findViewById(R.id.empty_imageview);
        noData = view.findViewById(R.id.no_data);

        add_button.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), AddDiaryActivity.class);
            startActivity(intent);
        });

        db = new DatabaseHelper(getActivity());
        diaryList = new ArrayList<>();

        storeDataInListModel();

        diaryAdapter = new DiaryAdapter(getActivity(), diaryList);
        recyclerView.setAdapter(diaryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void storeDataInListModel(){
        Cursor cursor = db.getAllData();
        if(cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }else{
            while(cursor.moveToNext()){

//                System.out.println(cursor.getString(0));
//                System.out.println(cursor.getString(1));
//                System.out.println(cursor.getString(2));
//                System.out.println(cursor.getString(3));
//                System.out.println(cursor.getString(4));

                DiaryModel diary = new DiaryModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                diaryList.add(diary);

            }
            empty_imageView.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}