package com.stpprojects.einscriptionslms.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.adapter.CourseStatusAdapter;
import com.stpprojects.einscriptionslms.bean.CourseStatusBean;
import com.stpprojects.einscriptionslms.utils.AppUtil;
import com.stpprojects.einscriptionslms.utils.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseStatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.noDataTxt)
    TextView noDataTxt;
    SessionManager sessionManager;
    CourseStatusAdapter courseStatusAdapter;
    ArrayList<CourseStatusBean> courseStatusBeanArrayList;


    public CourseStatusFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CourseStatusFragment newInstance(String param1, String param2) {
        CourseStatusFragment fragment = new CourseStatusFragment();
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
        View view = inflater.inflate(R.layout.fragment_course_status, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getActivity());
        SetRecyclerView();
        return view;
    }

    public void SetRecyclerView() {
        courseStatusBeanArrayList = new ArrayList<>();
        LinearLayoutManager mLinearLayoutmanager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutmanager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        courseStatusAdapter = new CourseStatusAdapter(getActivity(), courseStatusBeanArrayList);
        recyclerView.setAdapter(courseStatusAdapter);
        if (AppUtil.isInternetAvailable(getActivity())) {
        } else {
            AppUtil.showToast(getActivity(), getResources().getString(R.string.PleaseCheckIntenetconection), true);
        }
    }
}
