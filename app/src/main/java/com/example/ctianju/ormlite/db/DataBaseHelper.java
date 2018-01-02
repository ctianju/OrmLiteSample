package com.example.ctianju.ormlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.icu.lang.UScript;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ctianju on 2018/1/2.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "pp_db";
    private static final int VERSION = 6;
    private static DataBaseHelper instance;
    private Dao<User, Integer> userDao;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.e("TAG", "onCreate");
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.e("TAG", "onUpgrade");
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);//上面删除了数据库表记得重新创建表


        } catch (SQLException e) {
            Log.e("TAG", "onUpgradeSQLException");
        }
    }

    //单例模式得到helper
    public synchronized static DataBaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null) {
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    //模版模式得到Dao
    public synchronized Dao getDao(Class classz) throws SQLException {

        Dao dao = null;
        String className = classz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);

        }
        if (dao == null) {
            dao = super.getDao(classz);
            daos.put(className, dao);
        }
        return dao;

    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
