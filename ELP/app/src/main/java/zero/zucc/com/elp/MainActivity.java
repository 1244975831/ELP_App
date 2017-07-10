package zero.zucc.com.elp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import etong.bottomnavigation.lib.BottomNavigationBar;
import zero.zucc.com.elp.Adapter.RecommendAdapter;
import zero.zucc.com.elp.Fragment.Course_ListFragment;
import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.Item.Lesson;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView recommend;
    ListView search_List;
    SearchView searchView;
    Toolbar toolbar;
    TextView title;
    BottomNavigationBar bottomNavigationBar;
    ArrayList<Course> listdata = new ArrayList<>();
    ArrayList<Lesson> lessondata = new ArrayList<>();
    RecommendAdapter recommendAdapter;
    ArrayList<String> data= new ArrayList<>();
//    String [] data = {"JavaScript","Struts2框架"};
    ArrayAdapter<String> search_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomLayout);
        bottomNavigationBar.addTab(R.drawable.history_white, "历史课程", 0xff4a5965);
        bottomNavigationBar.addTab(R.drawable.recommend_white, "推荐课程", R.color.accent);
        bottomNavigationBar.addTab(R.drawable.all_white, "所有课程", R.color.colorPrimary);
        searchView = (SearchView) findViewById(R.id.search);
        title = (TextView)findViewById(R.id.title);
        setSupportActionBar(toolbar);
        search_List = (ListView) findViewById(R.id.search_list);
        searchoperate();
        //加载数据
        initDataHS();
        initDataLesson();
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
                bottomNavigationBar.setVisibility(View.GONE);
                Course_ListFragment course_listFragment = new Course_ListFragment();

                Course atransfer = new Course();
                atransfer = listdata.get(position);
                ArrayList arrayList = new ArrayList();
                Bundle bundle = new Bundle();
                arrayList.add(atransfer);
                bundle.putStringArrayList("course",arrayList);
                arrayList = new ArrayList();
                arrayList.addAll(lessondata);
                bundle.putParcelableArrayList("lesson",arrayList);
                course_listFragment.setArguments(bundle);
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

    private void searchoperate(){
        search_adapter = new ArrayAdapter<String>(this,R.layout.content_search,R.id.search_result,data);
        search_List.setAdapter(search_adapter);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setVisibility(View.GONE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!=""||query!=null){
                    search_List.setVisibility(View.VISIBLE);
                    int flag = 0 ;
                    data.clear();
                    if("JavaScript".indexOf(query)!=-1||"javaScript".indexOf(query)!=-1||"javascript".indexOf(query)!=-1){
                        flag++;
                        data.add("JavaScript");
                    }
                    if("Struts2框架".indexOf(query)!=-1||"struts2框架".indexOf(query)!=-1){
                        flag++;
                        data.add("Struts2框架");
                    }
                    if(flag==0){
                        data.add("没有相关课程哦");
                    }
                    search_adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=""||newText!=null){
                    search_List.setVisibility(View.VISIBLE);
                    int flag = 0 ;
                    data.clear();
                    if("JavaScript".indexOf(newText)!=-1||"javaScript".indexOf(newText)!=-1||"javascript".indexOf(newText)!=-1){
                        flag++;
                        data.add("JavaScript");
                    }
                    if("Struts2框架".indexOf(newText)!=-1||"struts2框架".indexOf(newText)!=-1){
                        flag++;
                        data.add("Struts2框架");
                    }
                    if(flag==0){
                        data.add("没有相关课程哦");
                    }
                    search_adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                title.setVisibility(View.VISIBLE);
                search_List.setVisibility(View.GONE);
                return false;
            }
        });
        search_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data.get(position);
                initDataAL();
                Course atransfer = new Course();
                for(int i = 0 ;i<listdata.size() ; i++ ){
                    if(listdata.get(i).getCourseName().equals( data.get(position))){
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        recommend.setVisibility(View.GONE);
                        toolbar.setVisibility(View.GONE);
                        bottomNavigationBar.setVisibility(View.GONE);
                        Course_ListFragment course_listFragment = new Course_ListFragment();
                        atransfer = listdata.get(i);
                        ArrayList arrayList = new ArrayList();
                        Bundle bundle = new Bundle();
                        arrayList.add(atransfer);
                        bundle.putStringArrayList("course",arrayList);
                        arrayList = new ArrayList();
                        arrayList.addAll(lessondata);
                        bundle.putParcelableArrayList("lesson",arrayList);
                        course_listFragment.setArguments(bundle);
                        transaction.replace(android.R.id.content,course_listFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    }
                }
            }
        });
    }

    private void initDataHS(){
        listdata.clear();
        Course c = new Course() ;
        c.setCourseId("1");
        c.setCourseName("JavaScript");
        c.setCoursePic(R.drawable.js);
        c.setCourseInfo("JavaScript 是互联网上最流行的脚本语言，这门语言可用于 HTML 和 web，更可广泛用于服务器、PC、笔记本电脑、平板电脑和智能手机等设备。");
        listdata.add(c);
    }

    private void initDataRM(){
        listdata.clear();
        Course c = new Course() ;
        c.setCourseId("2");
        c.setCourseName("Struts2框架");
        c.setCoursePic(R.drawable.struts);
        c.setCourseInfo("Struts2以WebWork优秀的设计思想为核心，吸收了 Struts框架的部分优点，提供了一个更加整洁的MVC设计模式实现的Web 应用程序框架");
        listdata.add(c);
    }

    private void initDataAL(){
        listdata.clear();
        Course c = new Course() ;
        c.setCourseId("1");
        c.setCourseName("JavaScript");
        c.setCoursePic(R.drawable.js);
        c.setCourseInfo("JavaScript 是互联网上最流行的脚本语言，这门语言可用于 HTML 和 web，更可广泛用于服务器、PC、笔记本电脑、平板电脑和智能手机等设备。");
        listdata.add(c);
        c = new Course() ;
        c.setCourseId("2");
        c.setCourseName("Struts2框架");
        c.setCoursePic(R.drawable.struts);
        c.setCourseInfo("Struts2以WebWork优秀的设计思想为核心，吸收了 Struts框架的部分优点，提供了一个更加整洁的MVC设计模式实现的Web 应用程序框架");
        listdata.add(c);
    }

    private void initDataLesson(){
        Lesson l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("JavaScript介绍");
        l.setLessonType("ppt");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("变量");
        l.setLessonType("video");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("计算");
        l.setLessonType("audio");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("判断");
        l.setLessonType("ppt");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("循环");
        l.setLessonType("ppt");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("函数");
        l.setLessonType("ppt");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("1");
        l.setLessonName("数组");
        l.setLessonType("ppt");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("2");
        l.setLessonName("介绍Struts 2及Struts 2开发环境的搭建");
        l.setLessonType("video");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("2");
        l.setLessonName("第一个Struts2应用开发");
        l.setLessonType("ppt");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("2");
        l.setLessonName("解决Struts2配置文件无提示问题");
        l.setLessonType("audio");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("2");
        l.setLessonName("Action名称的搜索顺序");
        l.setLessonType("video");
        lessondata.add(l);
        l = new Lesson();
        l.setCourseNum("2");
        l.setLessonName("Action配置的各项默认值");
        l.setLessonType("video");
        lessondata.add(l);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()==1){
            recommend.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            bottomNavigationBar.setVisibility(View.VISIBLE);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        changeconfigPORTRAIT();
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

        if (id == R.id.nav_userinfo) {
            Intent intent = new Intent(MainActivity.this,UserInfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_about_us) {
            Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void back (){
        recommend.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        bottomNavigationBar.setVisibility(View.VISIBLE);
        changeconfigPORTRAIT();
    }

    public void changeconfigLANDSCAPE(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void changeconfigPORTRAIT(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        try {
            super.onConfigurationChanged(newConfig);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            }else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            }
        } catch (Exception ex) {

        }
     }

}
