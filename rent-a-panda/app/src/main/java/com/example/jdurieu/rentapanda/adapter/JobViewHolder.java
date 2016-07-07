package com.example.jdurieu.rentapanda.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.jdurieu.rentapanda.R;
import com.example.jdurieu.rentapanda.model.Job;

/**
 * Created by JonathanDurieu on 10/06/16.
 */

public class JobViewHolder extends ClickableViewHolder {


    private Job job;

    private TextView textViewJobName;


    //itemView = layout of the cell = inflate row_job
    public JobViewHolder(View itemView) {
        super(itemView);
        textViewJobName = (TextView) itemView.findViewById(R.id.row_job_textview_name);
    }

    //Refresh UI of the cell
    public void setJob(Job job){
        this.job = job;
        textViewJobName.setText(job.getOrder_id().toString());


    }

}
