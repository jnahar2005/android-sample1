package com.stpprojects.einscriptionslms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.bean.MyCourseBean;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.ViewHolder> {

    Context mContext;
    ArrayList<MyCourseBean> MyCourseArrayList;

    public MyCoursesAdapter(Context context, ArrayList<MyCourseBean> MyCourseList) {
        this.mContext = context;
        this.MyCourseArrayList = MyCourseList;
    }

    @NonNull
    @Override
    public MyCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mycourse_list2, parent, false);
        return new MyCoursesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCoursesAdapter.ViewHolder holder, int position) {

        holder.tv_coursename.setText("Test");
        holder.tv_prize.setText("400" + " Rs/-");
        holder.tvInstructoreName.setText("Instructor name");
        holder.tvCourseStatus.setText("status");

        holder.ll_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.iv_course)
        ImageView iv_course;
        @BindView(R.id.imagechat)
        ImageView imagechat;
        @BindView(R.id.tv_coursename)
        TextView tv_coursename;
        @BindView(R.id.tv_prize)
        TextView tv_prize;
        @BindView(R.id.tvCourseStatus)
        TextView tvCourseStatus;
        @BindView(R.id.tvInstructoreName)
        TextView tvInstructoreName;
        @BindView(R.id.tvInstroctore)
        LinearLayout tvInstroctore;
        @BindView(R.id.layoutAddReview)
        LinearLayout layoutAddReview;
        @BindView(R.id.iv_next)
        ImageView iv_next;
        @BindView(R.id.ll_course)
        LinearLayout ll_course;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}

