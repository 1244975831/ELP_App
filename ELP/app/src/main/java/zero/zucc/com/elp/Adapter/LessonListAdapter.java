package zero.zucc.com.elp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zero.zucc.com.elp.Item.Lesson;
import zero.zucc.com.elp.R;

/**
 * Created by Administrator on 2017/7/5.
 */

public class LessonListAdapter extends BaseAdapter {

    private Context context;
    private List<Lesson> datas;

    public LessonListAdapter(Context context,List<Lesson> datas) {
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
        Lesson data = datas.get(position);
        TextView list_name;
        ImageView list_pic;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.content_lessonlist,null);
        }
        return convertView;
    }
}
