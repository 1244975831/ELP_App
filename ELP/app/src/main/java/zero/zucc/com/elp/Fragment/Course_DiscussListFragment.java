package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.R;

/**
 * Created by Administrator on 2017/7/8.
 */

public class Course_DiscussListFragment extends Fragment {
    ListView discuss_list;
    private ArrayList<Discuss> listdata = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discussdetial, container, false);
        discuss_list = (ListView)v.findViewById(R.id.discuss_detiallist);
        init();

        return v;
    }
    public void init(){
        Discuss c = new Discuss() ;
        c.setTalkUserName("c");
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);
        listdata.add(c);


        DiscussAdapter discussAdapter = new DiscussAdapter(getActivity(),listdata);
        discuss_list.setAdapter(discussAdapter);
    }
}
