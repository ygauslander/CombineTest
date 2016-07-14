package all.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.cardviewtest.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import all.Fragment.CollectFragment;
import all.Fragment.DayHotFragment;
import all.Fragment.MonthHotFragment;
import all.Fragment.RecommendFragment;
import all.adapter.MyFragmentViewPagerAdapter;

public class MainActivity extends FragmentActivity {
    //混合使用Tablayout作为标题栏
    //使用ViewPager（Fragemnet）保存页面内容
    //使用DrawerLayout设置侧滑菜单(使用了SlidingMenu来替代)
    //使用CardView填充Fragment
    //使用swipeRefreshLayout实现刷新

    //侧滑菜单与ViewPager滑动有冲突

    // 热门推荐
    // 热门收藏
    // 本月热榜
    //今日热榜


    private ViewPager viewPager;

    private MyFragmentViewPagerAdapter mAdapter;

    private List<Fragment> data;
    private List<String> titles;
    private TabLayout tabLayout;

    private String[] items = {"选项1", "选项2", "选项3"};

    private SlidingMenu slidingMenu;

    private RecommendFragment recommendFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setAdapter();
        //initDrawer();
        initSlidingMenu();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                //一下可以消除侧滑菜单与ViewPager的冲突
                if(position>0){

                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

                }else{

                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initSlidingMenu() {
        //这时已经可以对recyclerview进行控制

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindWidth(getWindowManager().getDefaultDisplay().getWidth()/2);
        slidingMenu.attachToActivity(this , SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu_alyout);

    }

    private DrawerLayout.DrawerListener myDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {
            Toast.makeText(MainActivity.this, "侧滑菜单打开", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onDrawerClosed(View drawerView) {
            Toast.makeText(MainActivity.this, "侧滑菜单关闭", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };


    private void setAdapter() {


        mAdapter = new MyFragmentViewPagerAdapter(getSupportFragmentManager(), data);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    private void initData() {
        titles = new ArrayList<>();
        titles.add("热门推荐");
        titles.add("热门收藏");
        titles.add("本月热榜");
        titles.add("今日热榜");

        data = new ArrayList<>();
        recommendFragment = new RecommendFragment(this);
        data.add(recommendFragment);
        data.add(new CollectFragment(this));
        data.add(new MonthHotFragment(this));
        data.add(new DayHotFragment(this));

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
    }
    //以下是侧滑菜单中的三个按钮

    public void changeToListViewMode(View v) {

        recommendFragment.setCurrentLayoutManager(new LinearLayoutManager(this));

    }

    public void changeToGridViewMode(View v) {
        recommendFragment.setCurrentLayoutManager(new GridLayoutManager(this , 2));
    }

    public void changeToPuBuLiuMode(View v) {
        recommendFragment.setCurrentLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }


}
