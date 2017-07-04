package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zero.zucc.com.elp.R;

/**
 * Created by Administrator on 2017/7/4.
 */

public class Course_LessonFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_course, container, false);

    }
}
