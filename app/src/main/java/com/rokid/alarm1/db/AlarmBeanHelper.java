package com.rokid.alarm1.db;

import android.content.Context;

import com.rokid.alarm1.beans.AlarmBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * Created by ZY on 2016/6/16.
 * Description : 数据库帮助类
 */
public class AlarmBeanHelper {
    AlarmBeanDao alarmBean;
    private static AlarmBeanHelper instance;
    private List<AlarmBean> emptyList;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;


    private AlarmBeanHelper(Context context) {
        emptyList = new ArrayList<>();
        try {
            alarmBean = getDaoSession(context, "alarm.db").getAlarmBeanDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AlarmBeanHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (AlarmBeanHelper.class) {
                if (instance == null) {
                    instance = new AlarmBeanHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private DaoSession getDaoSession(Context context, String DATABASE_NAME) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        if (daoSession == null) {
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public <T> void addData(T data) {
        if (null != alarmBean && null != data) {
            alarmBean.insertOrReplace((AlarmBean) data);
        }
    }



    public void deleteAll() {
        if (alarmBean != null) {
            alarmBean.deleteAll();
        }
    }
    /**根据id删除数据*/
    public void deleteData(Long id) {
        if (null != alarmBean && null != id) {
            alarmBean.deleteByKey(id);
        }
    }


    public List<AlarmBean> getAllData() {
        if (null != alarmBean) {
            Query query = alarmBean.queryBuilder()
                    .build();
            List<AlarmBean> notes = query.list();

            return notes;
        }
        return emptyList;
    }


    /**
     * 根据闹钟响铃时间查找
     *
     * @param startDate
     * @return
     */
    public List<AlarmBean> getAlarmBytime(String startDate) {
        if (alarmBean != null) {
            Query query = null;
            query = alarmBean.queryBuilder().
                    where(AlarmBeanDao.Properties.StartDate.eq(startDate)).
                    orderDesc(AlarmBeanDao.Properties.StartDate).
                    build();
            List<AlarmBean> list = query.list();
            return list;
        }
        return emptyList;
    }
}
