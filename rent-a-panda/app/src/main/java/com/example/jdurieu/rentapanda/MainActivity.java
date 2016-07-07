package com.example.jdurieu.rentapanda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jdurieu.rentapanda.activity.JobDetailActivity;
import com.example.jdurieu.rentapanda.adapter.ClickableRecyclerViewAdapter;
import com.example.jdurieu.rentapanda.adapter.JobAdapter;
import com.example.jdurieu.rentapanda.model.Job;
import com.example.jdurieu.rentapanda.network.JobSearch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener {

    private List<Job> jobList;
    private JobAdapter mJobAdapter;
    private JobSearch mJobSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mainRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mainRecyclerView.setLayoutManager(linearLayoutManager);

        mJobAdapter = new JobAdapter(this);

        //Adapter

        jobList = new ArrayList<>();

        mJobAdapter.setListener(this);
        mJobAdapter.refreshJobs(jobList);

        mainRecyclerView.setAdapter(mJobAdapter);

        mJobSearch = new JobSearch(this);

        mJobSearch.search(new JobSearch.JobSearchListener() {
            @Override
            public void didFindJobs(ArrayList<Job> jobs) {
                mJobAdapter.refreshJobs(jobs);
            }

            @Override
            public void didFailedToFindJobs() {

                alertDidFailedToFindJobs();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        mJobSearch.search(new JobSearch.JobSearchListener() {
            @Override
            public void didFindJobs(ArrayList<Job> jobs) {
                mJobAdapter.refreshJobs(jobs);
            }

            @Override
            public void didFailedToFindJobs() {

                alertDidFailedToFindJobs();
            }
        });

    }

    @Override
    public void onClick(int position) {

        Job job = mJobAdapter.getJob(position);
        Intent intent = new Intent(MainActivity.this, JobDetailActivity.class);
        intent.putExtra(JobDetailActivity.JOB_ID, job.getOrder_id());

        startActivity(intent);

    }

    private void alertDidFailedToFindJobs(){

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle(getString(R.string.alert_dialog_fail_retry_title));

        // Setting Dialog Message
        alertDialog.setMessage(getString(R.string.alert_dialog_fail_retry_message));

        // Setting Positive "Yes" Btn
        alertDialog.setPositiveButton("YES",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                mJobSearch.search(new JobSearch.JobSearchListener() {
                    @Override
                    public void didFindJobs(ArrayList<Job> jobs) {
                        mJobAdapter.refreshJobs(jobs);

                    }

                    @Override
                    public void didFailedToFindJobs() {

                        alertDidFailedToFindJobs();
                    }
                });
                    }
                });

        // Setting Negative "NO" Btn
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Snackbar.make(getWindow().getDecorView().findFocus(), R.string.alert_dialog_fail_retry_no_message, Snackbar.LENGTH_LONG).show();

                dialog.cancel();
                }
            });

            // Showing Alert Dialog
            alertDialog.show();
    }
}
