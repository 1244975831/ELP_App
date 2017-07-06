package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.barteksc.pdfviewer.PDFView;
import com.lidong.pdf.listener.OnDrawListener;
import com.lidong.pdf.listener.OnLoadCompleteListener;
import com.lidong.pdf.listener.OnPageChangeListener;

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

public class Course_LessonFragment extends Fragment implements OnPageChangeListener

        ,OnLoadCompleteListener, OnDrawListener {
    VideoView introView;
    private Handler mHandler = new Handler();
    private boolean bVideoIsBeingTouched = false;
    private ListView discuss;
    private com.lidong.pdf.PDFView pdfView;
    private ArrayList<Discuss> listdata = new ArrayList<>();
    ImageButton fullscreen;
    ImageButton fullscreen_exit;
    ImageView back;
    RelativeLayout filelayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        discuss = (ListView)v.findViewById(R.id.discuss);
        introView = (VideoView)v.findViewById(R.id.Video);
        pdfView = (com.lidong.pdf.PDFView)v.findViewById(R.id.Pdf);
        fullscreen = (ImageButton)v.findViewById(R.id.course_fullscreen);
        fullscreen_exit = (ImageButton)v.findViewById(R.id.course_fullscreenexit);
        back = (ImageButton)v.findViewById(R.id.course_back);

        filelayout = (RelativeLayout)v.findViewById(R.id.filelayout);
        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreen_exit.setVisibility(View.VISIBLE);
                fullscreen.setVisibility(View.GONE);
                ViewGroup.LayoutParams layoutParams = filelayout.getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.FILL_PARENT;
                filelayout.setLayoutParams(layoutParams);
            }
        });
        fullscreen_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreen_exit.setVisibility(View.GONE);
                fullscreen.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = filelayout.getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
                int height = DensityUtil.dip2px(getActivity(),240);
                layoutParams.height = height;
                filelayout.setLayoutParams(layoutParams);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        init();
        PDFinit();
        Videoinit();
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

    private void PDFinit(){
        displayFromFile1("http://file.chmsp.com.cn/colligate/file/00100000224821.pdf", "00100000224821.pdf");
    }

    private void Videoinit(){
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

    }

    private void displayFromFile1( String fileUrl ,String fileName) {

        pdfView.fileFromLocalStorage(this,this,this,fileUrl,fileName);   //设置pdf文件地址

    }



    /**

     * 翻页回调

     * @param page

     * @param pageCount

     */

    @Override

    public void onPageChanged(int page, int pageCount) {

        Toast.makeText( getActivity() , "page= " + page +

                " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();

    }



    /**

     * 加载完成回调

     * @param nbPages  总共的页数

     */

    @Override

    public void loadComplete(int nbPages) {

        Toast.makeText( getActivity() ,  "加载完成" + nbPages  , Toast.LENGTH_SHORT).show();

    }



    @Override

    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

        // Toast.makeText( MainActivity.this ,  "pageWidth= " + pageWidth + "

        // pageHeight= " + pageHeight + " displayedPage="  + displayedPage , Toast.LENGTH_SHORT).show();

    }


}
