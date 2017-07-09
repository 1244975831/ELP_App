package zero.zucc.com.elp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zero.zucc.com.elp.Fragment.Course_DiscussListFragment;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.R;
import zero.zucc.com.elp.Tool.DensityUtil;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DiscussAdapter extends BaseAdapter {

    private Context context;
    private List<Discuss> datas;

    public DiscussAdapter(Context context,List<Discuss> datas) {
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
        Discuss data = datas.get(position);
        TextView send_user;
        TextView send_content;
        ImageView mat;
        ImageView head;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.content_discuss,null);
        }
        send_user = (TextView)convertView.findViewById(R.id.send_user);
        send_content = (TextView)convertView.findViewById(R.id.send_content);
        mat = (ImageView)convertView.findViewById(R.id.mat);
        head = (ImageView)convertView.findViewById(R.id.head);
        send_user.setText(data.getTalkUserName());
        send_content.setText(data.getDiscussContent());
        head.setImageResource(data.getTalkUserHead());
        ViewGroup.LayoutParams layoutParams = mat.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
        // 行高194px
        int height = 100;
        int hg = 0 ;
        if(send_content.length()>20){
            hg=(send_content.length())/20;
        }
        layoutParams.height = height*hg;
        mat.setLayoutParams(layoutParams);
        return convertView;
    }

}
