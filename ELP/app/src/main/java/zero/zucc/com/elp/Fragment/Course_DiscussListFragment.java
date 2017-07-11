package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.LoginActivity;
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
    TextView count;
    DiscussAdapter discussAdapter;
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
        count = (TextView)v.findViewById(R.id.discuss_count);
        init();
        send_user.setText(discuss.getTalkUserName());
        send_content.setText(discuss.getDiscussContent());
        edit_contents.setOnKeyListener(onKey);
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
        edit_contents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    edit_contents.setHint("阁下为何欲言又止，何不畅所欲言");
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
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit_contents.getText().toString().equals("")){
                    Discuss recdissuss = new Discuss();
                    recdissuss.setTalkUserName("小杭同学");
                    recdissuss.setDiscussContent(edit_contents.getText().toString());
                    recdissuss.setTalkUserHead(R.drawable.head_leo);
                    recdissuss.setRecDiscussNum(discuss.getTalkUserNum());
                    listdetial.add(recdissuss);
                    edit_contents.clearFocus();
                    edit_contents.setText("");
                    edit_contents.setHint("发表回复");
                    count.setText("共"+listdetial.size()+"条");
                    discussAdapter.notifyDataSetChanged();
                }
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
        count.setText("共"+listdetial.size()+"条");
        discussAdapter = new DiscussAdapter(getActivity(),listdetial);
        discuss_list.setAdapter(discussAdapter);
    }

    View.OnKeyListener onKey=new View.OnKeyListener() {

        @Override

        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if(keyCode == KeyEvent.KEYCODE_ENTER){
                send.performClick();
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                if(imm.isActive()){
                    send_content.clearFocus();
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0 );

                }

                return true;

            }

            return false;

        }

    };


}
