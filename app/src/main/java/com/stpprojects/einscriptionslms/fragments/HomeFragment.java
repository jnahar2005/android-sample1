package com.stpprojects.einscriptionslms.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    @BindView(R.id.videoList)
    RecyclerView recyclerView;
    @BindView(R.id.linearVideo3)
    LinearLayout linearVideo3;
    SessionManager sessionManager;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getActivity());
        return view;
    }

    @OnClick({R.id.linearVideo3, R.id.linearVideo2, R.id.linearVideo1, R.id.linerlayou5, R.id.layoutWebinar, R.id.ll_testseries, R.id.ll_chat, R.id.ll_broadcastvideo})
    public void onClickevent(View view) {
        switch (view.getId()) {
            case R.id.linearVideo3:

                break;
            case R.id.linearVideo2:

                break;
            case R.id.linearVideo1:

                break;
            case R.id.linerlayou5:

                break;
            case R.id.layoutWebinar:

                break;
            case R.id.ll_testseries:

                break;
            case R.id.ll_chat:

                break;
            case R.id.ll_broadcastvideo:

                break;
        }
    }


    public void urlMethod(String urlString) {
        Log.v("urlString", urlString);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            intent.setPackage(null);
            startActivity(intent);
        }
    }
}
