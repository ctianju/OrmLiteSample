package com.example.ctianju.ormlite.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by ctianju on 2018/1/2.
 */

public class UserDao {

    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DataBaseHelper helper;

    public UserDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DataBaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     * @param user
     */
    public void add(User user)
    {
        try
        {
            userDaoOpe.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }



}