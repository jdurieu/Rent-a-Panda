package com.example.jdurieu.rentapanda.model.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.jdurieu.rentapanda.model.Job;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig jobDaoConfig;

    private final JobDao jobDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        jobDaoConfig = daoConfigMap.get(JobDao.class).clone();
        jobDaoConfig.initIdentityScope(type);

        jobDao = new JobDao(jobDaoConfig, this);

        registerDao(Job.class, jobDao);
    }
    
    public void clear() {
        jobDaoConfig.getIdentityScope().clear();
    }

    public JobDao getJobDao() {
        return jobDao;
    }

}
