package com.example.jdurieu.rentapanda.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jdurieu.rentapanda.R;
import com.example.jdurieu.rentapanda.model.Job;
import com.example.jdurieu.rentapanda.model.dao.DaoMaster;
import com.example.jdurieu.rentapanda.model.dao.DaoSession;
import com.example.jdurieu.rentapanda.model.dao.JobDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobDetailActivity extends AppCompatActivity {

    public static final String JOB_ID = "job_order_id";
    private JobDao mJobDao;
    private Job job;
    private String orderId;

    private TextView jobOrderId;
    private TextView jobDateTextView;

/*
    private String __status;
    private String customer_name;
    private String distance;
    private java.util.Date job_date;
    private String extras;
    private String order_duration;
    private String order_id;
    private String order_time;
    private String payment_method;
    private String price;
    private Integer recurrency;
    private String job_city;
    private String job_latitude;
    private String job_longitude;
    private Integer job_postalcode;
    private String job_street;
    private String status;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        orderId = getIntent().getStringExtra(JOB_ID);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"job.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mJobDao = daoSession.getJobDao();

        job = mJobDao.queryBuilder().where(JobDao.Properties.Order_id.eq(orderId)).unique();

        jobOrderId = (TextView) findViewById(R.id.textView_job_order_id);
        jobOrderId.setText(job.getOrder_id());

        jobDateTextView = (TextView) findViewById(R.id.textView_job_date);

        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat DesiredFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = job.getJob_date();
        //Date dateDesiredFormat = sourceFormat.parse(date.toString());
        String formattedDate = DesiredFormat.format(date.getTime());

        jobDateTextView.setText(formattedDate.toString());
}
}

