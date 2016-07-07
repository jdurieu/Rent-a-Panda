package com.example.jdurieu.rentapanda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdurieu.rentapanda.R;
import com.example.jdurieu.rentapanda.model.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JonathanDurieu on 10/06/16.
 */

public class JobAdapter extends ClickableRecyclerViewAdapter<JobViewHolder> {

    private List<Job> listJobs;
    private LayoutInflater layoutInflater;

    public interface JobAdapterOnCLickListener {
        public void onClick(JobViewHolder viewHolder);
    }

    public JobAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        listJobs = new ArrayList<>();
    }

    public void refreshJobs(List<Job> jobs) {
        listJobs.clear();
        listJobs.addAll(jobs);
        this.notifyDataSetChanged();
    }


    @Override
    public void bindCurrentViewHolder(JobViewHolder holder, int position) {

        Job job = listJobs.get(position);
        holder.setJob(job);

    }

    @Override
    public JobViewHolder instantiateViewHolder(ViewGroup parent, int viewType) {

        View rowJob = layoutInflater.inflate(R.layout.row_job,
                parent, false);

        JobViewHolder jobViewHolder = new JobViewHolder(rowJob);
        jobViewHolder.setListener(this);

        return jobViewHolder;
    }

    @Override
    public int getItemCount() {
        return listJobs.size();
    }

    public Job getJob(int position){
        return listJobs.get(position);
    }
}
