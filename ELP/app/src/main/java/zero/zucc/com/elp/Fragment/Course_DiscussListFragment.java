package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.MainActivity;
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
    ImageView mat;
    ImageView main_head;
    EditText edit_contents;
    Button send;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discussdetial, container, false);
        discuss_list = (ListView)v.findViewById(R.id.discuss_detiallist);
        send_user = (TextView)v.findViewById(R.id.send_user);
        send_content = (TextView)v.findViewById(R.id.send_content);
        back = (ImageButton)v.findViewById(R.id.course_back);
        mat = (ImageView)v.findViewById(R.id.mat);
        edit_contents = (EditText)v.findViewById(R.id.discuss_editcontent);
        send = (Button)v.findViewById(R.id.discuss_send) ;
        main_head = (ImageView) v.findViewById(R.id.main_head);

        init();
        send_user.setText(discuss.getTalkUserName());
        send_content.setText(discuss.getDiscussContent());
        main_head.setImageResource(discuss.getTalkUserHead());
        send.setVisibility(View.GONE);
        edit_contents.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    send.setVisibility(View.VISIBLE);
                }else {
                    send.setVisibility(View.GONE);
                }
            }
        });

        ViewGroup.LayoutParams layoutParams = mat.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
        // 行高194px
        int height = 100;
        int hg = 0;
        if(send_content.length()>20){
            hg=(send_content.length())/20;
        }
        layoutParams.height = height*hg;
        mat.setLayoutParams(layoutParams);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeconfigPORTRAIT();
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
