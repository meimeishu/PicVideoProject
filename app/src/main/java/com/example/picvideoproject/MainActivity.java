package com.example.picvideoproject;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lake.banner.transformer.DefaultTransformer;
import com.lake.banner.transformer.VerticalPageTransformer;
import com.lake.banner.view.BannerViewPager;
import com.lake.hbanner.HBanner;
import com.lake.hbanner.ImageSubView;
import com.lake.hbanner.SubView;
import com.lake.hbanner.VideoSubView;
import com.lake.hbanner.VideoViewType;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BannerViewPager viewPager;
    private HBanner hBanner;
    private String[] permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissions();
    }

    /**
     * 获取手机权限
     */
    private void getPermissions() {
        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INTERNET};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (AndPermission.hasPermissions(MainActivity.this, permissions)) {
                initViewPager();
                initBanner();
            } else {
                AndPermission.with(MainActivity.this)
                        .runtime()
                        .permission(
                                permissions
                        )
                        .onGranted(permissions -> {
                            Toast.makeText(MainActivity.this, "已经给予权限申请", Toast.LENGTH_SHORT).show();
                            initViewPager();
                            initBanner();
                        })
                        .onDenied(permissions -> {
                            Toast.makeText(MainActivity.this, "请给予权限，并重启应用", Toast.LENGTH_SHORT).show();
                        })
                        .start();
            }
        } else {
            initViewPager();
            initBanner();
        }
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        viewPager = (BannerViewPager) findViewById(R.id.viewpager);
        viewPager.setScrollable(false); //true:滑动，false：禁止滑动
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        if (null == hBanner) {
            hBanner = HBanner.create(viewPager);
        }
        List<SubView> data = new ArrayList<>();
        data.add(new ImageSubView.Builder(getBaseContext())
                .url("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4148675854,1608370142&fm=26&gp=0.jpg")
                .duration(6000)
                .build());
        data.add(new ImageSubView.Builder(getBaseContext())
                .resId(R.drawable.one)
                .duration(5000)
                .build());
        data.add(new ImageSubView.Builder(getBaseContext())
                .resId(R.drawable.two)
                .duration(5000)
                .build());
        data.add(new VideoSubView.Builder(getBaseContext())
                .url("https://v-cdn.zjol.com.cn/123468.mp4")
                .gravity(VideoViewType.FULL)
                .isSub(false)
                .build());

        if (null != hBanner) {
            hBanner.sources(data);
            hBanner.play(true);

        }
        viewPager.setPageTransformer(true, new DefaultTransformer());
//        VideoSubView view = new VideoSubView.Builder(getBaseContext())
//                .url("https://v-cdn.zjol.com.cn/123468.mp4")
//                .gravity(VideoViewType.FULL)
//                .isSub(false)
//                .build();
    }

    private void initImage() {
        ImageSubView imge = new ImageSubView.Builder(getBaseContext())
                .resId(R.mipmap.defalteimage)
                .duration(5000)
                .build();
    }

    @Override
    protected void onResume() {
        if (hBanner != null)
            hBanner.play(true);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (hBanner != null)
            hBanner.pause(0);
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (hBanner != null)
            hBanner = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (hBanner != null)
            hBanner = null;
        super.onDestroy();
    }
}