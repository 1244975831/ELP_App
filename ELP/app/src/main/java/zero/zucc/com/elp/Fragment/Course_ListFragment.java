package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Adapter.LessonListAdapter;
import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.Item.Lesson;
import zero.zucc.com.elp.MainActivity;
import zero.zucc.com.elp.R;
import zero.zucc.com.elp.Tool.DensityUtil;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Course_ListFragment extends Fragment {
    ListView courselist;
    ImageView head_course;
    ImageButton head_back;
    TextView head_info;
    TextView courselist_bottom;
    ArrayList<Course> coursedata = new ArrayList<>();
    ArrayList<Lesson> lessonsdata = new ArrayList<>();
    private ArrayList<Lesson> listdata = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courselist, container, false);
        courselist = (ListView)v.findViewById(R.id.course_list);
        head_course = (ImageView)v.findViewById(R.id.head_course);
        head_back = (ImageButton)v.findViewById(R.id.head_back);
        head_info = (TextView)v.findViewById(R.id.course_info);
        courselist_bottom = (TextView)v.findViewById(R.id.courselist_bottom);
        init();
        courselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course_LessonFragment course_lessonFragment = new Course_LessonFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type",lessonsdata.get(position).getLessonType());
                course_lessonFragment.setArguments(bundle);

                fragmentTransaction.replace(android.R.id.content,course_lessonFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        head_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.back();
                getFragmentManager().popBackStack();

            }
        });
        return v;
    }
    public void init(){
        coursedata.clear();
        lessonsdata.clear();
        Bundle bundle = getArguments();
        ArrayList atransfer = new ArrayList();
        atransfer = bundle.getStringArrayList("course");
        coursedata.addAll(atransfer);
        atransfer = new ArrayList();
        atransfer =  bundle.getParcelableArrayList("lesson");
        for(int i = 0 ; i<atransfer.size() ; i ++){
            Lesson lesson =(Lesson) atransfer.get(i);
            if(lesson.getCourseNum()==coursedata.get(0).getCourseId())
                lessonsdata.add(lesson);
        }

        head_course.setImageResource(coursedata.get(0).getCoursePic());
        head_info.setText(coursedata.get(0).getCourseInfo());

        ViewGroup.LayoutParams layoutParams = this.courselist.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
        // 行高194px
        int height = DensityUtil.dip2px(getActivity(),50);

        layoutParams.height = height*lessonsdata.size();
        courselist.setLayoutParams(layoutParams);
        if(lessonsdata.size()>=7){
            courselist_bottom.setVisibility(View.VISIBLE);
        }else{
            courselist_bottom.setVisibility(View.GONE);
        }
        LessonListAdapter lessonListAdapter = new LessonListAdapter(getActivity(),lessonsdata);
        courselist.setAdapter(lessonListAdapter);
    }

}
