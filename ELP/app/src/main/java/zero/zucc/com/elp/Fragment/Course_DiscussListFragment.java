package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.R;

/**
 * Created by Administrator on 2017/7/8.
 */

public class Course_DiscussListFragment extends Fragment {
    ListView discuss_list;
    ArrayList <Discuss> listdetial = new ArrayList <>();
    private ArrayList<Discuss> listdata = new ArrayList<>();
    TextView send_user ;
    Discuss discuss;
    TextView send_content;
    ImageView back;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discussdetial, container, false);
        discuss_list = (ListView)v.findViewById(R.id.discuss_detiallist);
        send_user = (TextView)v.findViewById(R.id.send_user);
        send_content = (TextView)v.findViewById(R.id.send_content);
        back = (ImageButton)v.findViewById(R.id.course_back);
        init();
        send_user.setText(discuss.getTalkUserName());
        send_content.setText(discuss.getDiscussContent());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return v;
    }
    public void init(){
        Bundle bundle = getArguments();
        listdetial .clear();
        ArrayList atransfer = new ArrayList();
        atransfer  = bundle.getParcelableArrayList("discuss");
        listdetial.addAll(atransfer);
        atransfer = new ArrayList();
        atransfer  = bundle.getParcelableArrayList("discussmain");
        discuss =(Discuss) atransfer.get(0);

        DiscussAdapter discussAdapter = new DiscussAdapter(getActivity(),listdetial);
        discuss_list.setAdapter(discussAdapter);
    }


}
