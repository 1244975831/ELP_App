package zero.zucc.com.elp.Fragment;

import android.app.Fragment;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    ImageButton btn_start;
    ImageButton btn_pause;
    RelativeLayout Audio;
    private MediaPlayer mediaPlayer;
    String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        discuss = (ListView)v.findViewById(R.id.discuss);
        introView = (VideoView)v.findViewById(R.id.Video);
        pdfView = (com.lidong.pdf.PDFView)v.findViewById(R.id.Pdf);
        fullscreen = (ImageButton)v.findViewById(R.id.course_fullscreen);
        fullscreen_exit = (ImageButton)v.findViewById(R.id.course_fullscreenexit);
        Audio = (RelativeLayout) v.findViewById(R.id.Audio);
        back = (ImageButton)v.findViewById(R.id.course_back);
        btn_start = (ImageButton)v.findViewById(R.id.btn_start);
        btn_pause = (ImageButton) v.findViewById(R.id.btn_pause);
        filelayout = (RelativeLayout)v.findViewById(R.id.filelayout);

        init();
        Typeinit();
        if(type.equals("ppt")) {
            PDFinit();
            pdfView.setVisibility(View.VISIBLE);
            introView.setVisibility(View.GONE);
            Audio.setVisibility(View.GONE);
        }
        else if(type.equals("video")){
            pdfView.setVisibility(View.GONE);
            introView.setVisibility(View.VISIBLE);
            Audio.setVisibility(View.GONE);
            Videoinit();
        }
        else if(type.equals("audio")){
            pdfView.setVisibility(View.GONE);
            introView.setVisibility(View.GONE);
            Audio.setVisibility(View.VISIBLE);
        }

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playerinit();
                mediaPlayer.start();
                btn_pause.setVisibility(View.VISIBLE);
                btn_start.setVisibility(View.GONE);
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                btn_start.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.GONE);
            }
        });


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

    private void Playerinit(){
        String path = "http://www.mobvcasting.com/android/audio/goodmorningandroid.mp3";
        try {

            mediaPlayer = new MediaPlayer();
            // 设置指定的流媒体地址
            mediaPlayer.setDataSource(path);
            // 设置音频流的类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // 通过异步的方式装载媒体资源
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override

                public void onPrepared(MediaPlayer mp) {
                    // 装载完毕 开始播放流媒体
                    mediaPlayer.start();
                    // 避免重复播放，把播放按钮设置为不可用
                    btn_start.setEnabled(false);
                }
            });

            // 设置循环播放
//                 mediaPlayer.setLooping(true);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


                @Override

                public void onCompletion(MediaPlayer mp) {

                    // 在播放完毕被回调
                    btn_start.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.GONE);
                    btn_start.setEnabled(true);

                }

            });



            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {


                @Override

                public boolean onError(MediaPlayer mp, int what, int extra) {

                    // 如果发生错误，重新播放

//                        replay();

                    return false;

                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void Typeinit(){
        Bundle bundle = getArguments();
        type = bundle.getString("type");
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
