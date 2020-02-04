package com.stpprojects.einscriptionslms.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.adapter.MyCoursesAdapter;
import com.stpprojects.einscriptionslms.bean.MyCourseBean;
import com.stpprojects.einscriptionslms.interfaces.ClickListener;
import com.stpprojects.einscriptionslms.listnerclasses.RecyclerItemClickListener;
import com.stpprojects.einscriptionslms.utils.AppUtil;
import com.stpprojects.einscriptionslms.utils.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCoursesFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.noDataTxt)
    TextView noDataTxt;
    SessionManager sessionManager;
    MyCoursesAdapter myCourseAdapter;
    LinearLayoutManager mLinearLayoutmanager;
    ArrayList<MyCourseBean> mycoursesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_courses, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SetRecyclerView();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (mycoursesList != null || mycoursesList.size() > 0) {
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }


    public void SetRecyclerView() {
        mycoursesList = new ArrayList<>();
        mLinearLayoutmanager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutmanager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myCourseAdapter = new MyCoursesAdapter(getActivity(), mycoursesList);
        recyclerView.setAdapter(myCourseAdapter);
        if (AppUtil.isInternetAvailable(getActivity())) {
        } else {
            AppUtil.showToast(getActivity(), getResources().getString(R.string.PleaseCheckIntenetconection), true);
        }
    }

    public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }
}

