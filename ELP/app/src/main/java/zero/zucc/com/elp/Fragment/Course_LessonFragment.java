package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.util.ArrayList;

import zero.zucc.com.elp.Adapter.DiscussAdapter;
import zero.zucc.com.elp.Adapter.RecommendAdapter;
import zero.zucc.com.elp.Item.Course;
import zero.zucc.com.elp.Item.Discuss;
import zero.zucc.com.elp.LoginActivity;
import zero.zucc.com.elp.R;
import zero.zucc.com.elp.Tool.DensityUtil;

/**
 * Created by Administrator on 2017/7/4.
 */

public class Course_LessonFragment extends Fragment {
    VideoView introView;
    private Handler mHandler = new Handler();
    private boolean bVideoIsBeingTouched = false;
    private ListView discuss;
    private PDFView pdfView;
    private ArrayList<Discuss> listdata = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        discuss = (ListView)v.findViewById(R.id.discuss);
        introView = (VideoView)v.findViewById(R.id.Video);
        pdfView = (PDFView)v.findViewById(R.id.Pdf);
        pdfView.fromAsset("法律与案例期末论文.pdf")   //PDF文件是放在assets文件夹里面的   fromAsset()方法已经写好了从assets问价夹读取文件的方法了  所以不用考虑去其他问题
                .pages(0, 2, 1, 3, 3, 3)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
        init();
        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        introView.setVideoURI(uri);
        MediaController mediaController =  new MediaController(getActivity()) ;
        mediaController.setAnchorView(introView);
        introView.setMediaController(mediaController);
        introView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
//        introView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!bVideoIsBeingTouched) {
//                    bVideoIsBeingTouched = true;
//                    if (introView.isPlaying()) {
//                        introView.pause();
//                    } else {
//                        introView.start();
//                    }
//                    mHandler.postDelayed(new Runnable() {
//                        public void run() {
//                            bVideoIsBeingTouched = false;
//                        }
//                    },500);
//                }
//                return false;
//            }
//        });


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
        discuss.setAdapter(discussAdapter);
    }
}
