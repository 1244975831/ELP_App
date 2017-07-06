package zero.zucc.com.elp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import etong.bottomnavigation.lib.BottomNavigationBar;
import zero.zucc.com.elp.Adapter.RecommendAdapter;
import zero.zucc.com.elp.Fragment.Course_ListFragment;
import zero.zucc.com.elp.Item.Course;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView recommend;
    SearchView searchView;
    Toolbar toolbar;
    BottomNavigationBar bottomNavigationBar;
    ArrayList<Course> listdata = new ArrayList<>();
    RecommendAdapter recommendAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomLayout);
        bottomNavigationBar.addTab(R.drawable.history_white, "历史课程", 0xff4a5965);
        bottomNavigationBar.addTab(R.drawable.recommend_white, "推荐课程", R.color.accent);
        bottomNavigationBar.addTab(R.drawable.all_white, "所有课程", R.color.colorPrimary);
        setSupportActionBar(toolbar);
        //加载数据
        initDataHS();

        recommend = (ListView)findViewById(R.id.recommend) ;
        recommendAdapter = new RecommendAdapter(this,listdata);
        recommend.setAdapter(recommendAdapter);
        recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                recommend.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
                Course_ListFragment course_listFragment = new Course_ListFragment();
                transaction.replace(android.R.id.content,course_listFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        bottomNavigationBar.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(int position) {
                if(position==0){
                    initDataHS();
                    recommendAdapter.notifyDataSetChanged();
                }else if(position==1){
                    initDataRM();
                    recommendAdapter.notifyDataSetChanged();
                }else{
                    initDataAL();
                    recommendAdapter.notifyDataSetChanged();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initDataHS(){
        listdata.clear();
        Course c = new Course() ;
        c.setCourseName("JavaScript");
        c.setCoursePic(R.drawable.js);
        c.setCourseInfo("JavaScript 是互联网上最流行的脚本语言，这门语言可用于 HTML 和 web，更可广泛用于服务器、PC、笔记本电脑、平板电脑和智能手机等设备。");
        listdata.add(c);
    }

    private void initDataRM(){
        listdata.clear();
        Course c = new Course() ;
        c.setCourseName("Struts2框架");
        c.setCoursePic(R.drawable.struts);
        c.setCourseInfo("Struts2以WebWork优秀的设计思想为核心，吸收了 Struts框架的部分优点，提供了一个更加整洁的MVC设计模式实现的Web 应用程序框架");
        listdata.add(c);
    }

    private void initDataAL(){
        listdata.clear();
        Course c = new Course() ;
        c.setCourseName("JavaScript");
        c.setCoursePic(R.drawable.js);
        c.setCourseInfo("JavaScript 是互联网上最流行的脚本语言，这门语言可用于 HTML 和 web，更可广泛用于服务器、PC、笔记本电脑、平板电脑和智能手机等设备。");
        listdata.add(c);
        c = new Course() ;
        c.setCourseName("Struts2框架");
        c.setCoursePic(R.drawable.struts);
        c.setCourseInfo("Struts2以WebWork优秀的设计思想为核心，吸收了 Struts框架的部分优点，提供了一个更加整洁的MVC设计模式实现的Web 应用程序框架");
        listdata.add(c);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void back (){
        recommend.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
    }
}
