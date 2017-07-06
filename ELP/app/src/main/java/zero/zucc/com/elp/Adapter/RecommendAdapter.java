package zero.zucc.com.elp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.R;

/**
 * Created by Administrator on 2017/7/4.
 */

public class RecommendAdapter extends BaseAdapter {

    private Context context;
    private List<Course> datas;

    public RecommendAdapter(Context context,List<Course> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course data = datas.get(position);
        TextView list_name;
        ImageView list_pic;
        TextView list_info;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.content_recommend,null);
        }
        list_name = (TextView)convertView.findViewById(R.id.course_name);
        list_name.setText(data.getCourseName());
        list_pic = (ImageView) convertView.findViewById(R.id.course_image);
        list_pic.setImageResource(data.getCoursePic());
        list_info = (TextView)convertView.findViewById(R.id.course_info);
        list_info.setText(data.getCourseInfo());
        return convertView;
    }


}
