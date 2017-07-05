package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Adapter.LessonListAdapter;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.Item.Lesson;
import zero.zucc.com.elp.R;
import zero.zucc.com.elp.Tool.DensityUtil;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Course_ListFragment extends Fragment {
    ListView courselist;
    private ArrayList<Lesson> listdata = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courselist, container, false);
        courselist = (ListView)v.findViewById(R.id.course_list);
        init();
        courselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
        return v;
    }
    public void init(){


        ViewGroup.LayoutParams layoutParams = this.courselist.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
        // 行高194px
        int height = DensityUtil.dip2px(getActivity(),50);
        Lesson c = new Lesson() ;
        c.setLessonName("c");
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);

        layoutParams.height = height*listdata.size();
        courselist.setLayoutParams(layoutParams);


        LessonListAdapter lessonListAdapter = new LessonListAdapter(getActivity(),listdata);
        courselist.setAdapter(lessonListAdapter);
    }
}
