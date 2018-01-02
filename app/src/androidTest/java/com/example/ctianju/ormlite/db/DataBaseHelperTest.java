package com.example.ctianju.ormlite.db;

import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ctianju on 2018/1/2.
 */
public class DataBaseHelperTest extends AndroidTestCase {
    @Test
    public void testAddUser() {

        User u1 = new User("zhy", "2B青年");
        DataBaseHelper helper = DataBaseHelper.getHelper(getContext());

        try {
            helper.getUserDao().create(u1);
            u1 = new User("zhy2", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy3", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy4", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy5", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy6", "2B青年");
            helper.getUserDao().create(u1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        testList();


    }

    public void testUpdateUser() {
        DataBaseHelper helper = DataBaseHelper.getHelper(getContext());
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(3);
            helper.getUserDao().update(u1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void testDeleteUser() {
        DataBaseHelper helper = DataBaseHelper.getHelper(getContext());
        try {
            helper.getUserDao().deleteById(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testList() {
        DataBaseHelper helper = DataBaseHelper.getHelper(getContext());
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(2);
            helper.getUserDao().create(u1);
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("TAG", "Size= " + users.size());
            for (int i = 0; i < users.size(); i++) {
                Log.e("TAG", users.get(i).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}