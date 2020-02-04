package com.stpprojects.einscriptionslms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.bean.CourseStatusBean;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseStatusAdapter extends  RecyclerView.Adapter<CourseStatusAdapter.ViewHolder> {

    Context mContext;
    ArrayList<CourseStatusBean> MyCourseArrayList;

    public CourseStatusAdapter(Context context, ArrayList<CourseStatusBean> MyCourseList) {
        this.mContext = context;
        this.MyCourseArrayList = MyCourseList;
    }

    @NonNull
    @Override
    public CourseStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_course_status_list, parent, false);
        return new CourseStatusAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseStatusAdapter.ViewHolder holder, int position) {
        holder.tvCourseName.setText("Test");
        holder.tvCourseprice.setText("200 Rs-/");
        holder.tvCoursepercentage.setText("10"+"%");
        holder.tvCourseStatus.setText("status");
        holder.tvCourseDuration.setText("course time");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.tvCourseName)
        TextView tvCourseName;
        @BindView(R.id.tvCourseprice)
        TextView tvCourseprice;
        @BindView(R.id.tvCoursepercentage)
        TextView tvCoursepercentage;
        @BindView(R.id.tvCourseStatus)
        TextView tvCourseStatus;
        @BindView(R.id.tvCourseDuration)
        TextView tvCourseDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}

