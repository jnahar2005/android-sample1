package com.stpprojects.einscriptionslms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.bean.AllCourseBean;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.ViewHolder> {

    Context mContext;
    ArrayList<AllCourseBean> AllCourseArrayList;

    public AllCourseAdapter(Context context, ArrayList<AllCourseBean> AllCourseList) {
        this.mContext = context;
        this.AllCourseArrayList = AllCourseList;
    }

    @NonNull
    @Override
    public AllCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_allcourse_list2, parent, false);
        return new AllCourseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllCourseAdapter.ViewHolder holder, int position) {
        holder.tv_coursename.setText("Test");
        holder.tv_prize.setText(20 + " Rs/-");

        holder.ratingBar.setRating(3);
        
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
        @BindView(R.id.tv_coursename)
        TextView tv_coursename;
        @BindView(R.id.tv_prize)
        TextView tv_prize;
        @BindView(R.id.tvCourseStatus)
        TextView tvCourseStatus;
        @BindView(R.id.ll_course)
        LinearLayout ll_course;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
