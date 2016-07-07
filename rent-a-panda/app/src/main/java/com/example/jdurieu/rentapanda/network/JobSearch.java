package com.example.jdurieu.rentapanda.network;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.jdurieu.rentapanda.App;
import com.example.jdurieu.rentapanda.model.Job;
import com.example.jdurieu.rentapanda.model.dao.DaoMaster;
import com.example.jdurieu.rentapanda.model.dao.DaoSession;
import com.example.jdurieu.rentapanda.model.dao.JobDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JonathanDurieu on 10/06/16.
 */

public class JobSearch {


    private JobDao mJobDao;

    public static interface JobSearchListener{
        public void didFindJobs(ArrayList<Job> jobs);
        public void didFailedToFindJobs();
    }

    public static final String GROUP_URL = "http://private-14c693-rentapanda.apiary-mock.com/jobs";

    public JobSearch(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"job.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mJobDao = daoSession.getJobDao();
    }


    public void search(final JobSearchListener listener){

        RequestQueue requestQueue = App.getInstance().getRequestQueue();

        Response.Listener responseListener = new Response.Listener<ResultsJob>(){

            @Override
            public void onResponse(ResultsJob jobs) {

                handleResults(jobs);

                if(null!=listener) {
                    listener.didFindJobs(jobs);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("JobRequest", "error : " + volleyError);

                if(null!=listener) {
                    listener.didFailedToFindJobs();
                }
            }
        };

        // Request factory
        JacksonRequest<ResultsJob> jrequest = new JacksonRequest<ResultsJob>(Request.Method.GET, GROUP_URL, null, ResultsJob.class, new HashMap<String, String>(),
                responseListener, errorListener, 1, 1);

        requestQueue.add(jrequest);
    }

    private void handleResults(ResultsJob resultsJob) {

        for(int i=0;i<resultsJob.size();i++) {
            Job job = resultsJob.get(i);

            List jobs = mJobDao.queryBuilder().where(JobDao.Properties.Order_id.eq(job.getOrder_id())).list();

            boolean jobExistInCache = (jobs.size()>0);

            if(jobExistInCache) {
                mJobDao.update(job);
            }
            else {
                mJobDao.insert(job);
            }
        }
    }



}
