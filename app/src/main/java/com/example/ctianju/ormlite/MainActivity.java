package com.example.ctianju.ormlite;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ctianju.ormlite.db.Article;
import com.example.ctianju.ormlite.db.ArticleDao;
import com.example.ctianju.ormlite.db.DataBaseHelper;
import com.example.ctianju.ormlite.db.User;
import com.example.ctianju.ormlite.db.UserDao;

import java.sql.SQLException;
import java.util.List;


//测试OrmLite 数据库创建，并且2张表之间关联等信息，能满足基本需求
public class MainActivity extends AppCompatActivity {
    /**
     * 摄像头、录音权限
     */
    private static final String[] permissionManifest = {

            Manifest.permission.WRITE_EXTERNAL_STORAGE,

    };

    /**
     * 请求权限的结果
     */
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, permissionManifest, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_REQUEST_CODE){
            //得到权限才去创建数据库
            testGetArticleById();
        }
    }

    /**
     * 添加用户表
     */
    public void testAddUser() {

        User u1 = new User("zhy", "2B青年");
        DataBaseHelper helper = DataBaseHelper.getHelper(this);

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
            Log.e("TAG","AddUser");
        }



    }
    //更新一个字段的id=4 的其他信息

    public void testUpdateUser() {
        DataBaseHelper helper = DataBaseHelper.getHelper(this);
        try {
            User u1 = new User("zhy", "2xxxxB青年");
            u1.setId(4);
            helper.getUserDao().update(u1);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 删除一个id = 3 的字段
    public void testDeleteUser() {
        DataBaseHelper helper = DataBaseHelper.getHelper(this);
        try {
            User u1 = new User("zhy", "2B青年");
            u1.setId(3);
            helper.getUserDao().delete(u1);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //罗列显示user，Article表全部信息
    public void testList() {
        DataBaseHelper helper = DataBaseHelper.getHelper(this);
        try {
           /* User u1 = new User("zhy-android", "2B青年");
            u1.setId(2);
            helper.getUserDao().create(u1);*/
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("TAG", "Size= " + users.size());
            for (int i = 0; i < users.size(); i++) {
                Log.e("TAG", users.get(i).toString());
            }

            List<Article> article = helper.getDao(Article.class).queryForAll();
            Log.e("TAG", "Article  Size= " + article.size());
            for (int i = 0; i < article.size(); i++) {
                Log.e("TAG", article.get(i).toString());
            }

        } catch (SQLException e) {
            Log.e("TAG","List");
        }
    }


    //添加Article一个字段，并且用户为张xxxx
    public void testAddArticle()
    {
        User u = new User("张xxx","aaaa");
        new UserDao(this).add(u);
        Article article = new Article();
        article.setTitle("ORMLite的使用");
        article.setUser(u);
        new ArticleDao(this).add(article);
        testList();

    }

    //根据Article得到Article
    public void testGetArticleById()
    {
        Article article = new ArticleDao(this).get(1);
        Log.e("TAG","testGetArticleById= "+article.getId() + " , " + article.getTitle());
        testGetArticleWithUser();
    }

    //根据Article id 得到用户
    public void testGetArticleWithUser()
    {
        Article article = new ArticleDao(this).getArticleWithUser(1);
        Log.e("TAG","User=" + article.getUser().getName()+"testGetArticleWithUser=" + article.getTitle());
        testListArticlesByUserId();
    }

    //根据用户id 得到Article

    public void testListArticlesByUserId()
    {

        List<Article> articles = new ArticleDao(this).listByUserId(7);
        Log.e("TAG","testListArticlesByUserId= "+articles.toString());
    }


}
