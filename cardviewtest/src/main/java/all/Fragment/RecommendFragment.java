package all.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.administrator.cardviewtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import all.View.CustomeRefreshFooterView;
import all.View.CustomeRefreshHeaderView;
import all.adapter.MyRecyclerAdapter;
import all.domain.CardItem;


/**
 * Created by Administrator on 2016/7/13.
 */
public class RecommendFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {


    //实现上拉，下拉刷新
    //演技一下toolbar，actionbar
    private List<Integer> res;//图片资源
    private List<String> des;//描述字符串
    private  RecyclerView recyclerView;
    private Context context;
    private MyRecyclerAdapter mAdapter;
    private List<CardItem> data;
    private RecyclerView.LayoutManager currentLayoutManager;

    private SwipeToLoadLayout swipeToLoadLayout;
    //使用这个框架要注意
//1.头部，底部及中间包含的视图（例如listview , Recyclerview）的id是固定的
//              <item name="swipe_target" type="id" />刷新目标
//            <item name="swipe_refresh_header" type="id" />刷新头部
//            <item name="swipe_load_more_footer" type="id" />刷新尾部
    //2.需要自己来实现头部和底部


    private List<CardItem> add_data;

    private CustomeRefreshHeaderView headerview;
    private CustomeRefreshFooterView footerView;


    public RecommendFragment(Context context) {
        this.context = context;
    }
public  void setCurrentLayoutManager(RecyclerView.LayoutManager currentLayoutManager){
    if(currentLayoutManager!=null) {
        this.currentLayoutManager = currentLayoutManager;
        recyclerView.setLayoutManager(currentLayoutManager);
    }
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initAddData();//添加在刷新时加入的数据

        View view = initView();

        initSwipeReToloadLayout();
        initCardItems();
        initRecyclerView();

        return view;
    }

    @NonNull
    private View initView() {
        View view = View.inflate(context, R.layout.recomment_item_layout, null);
        headerview = (CustomeRefreshHeaderView) view.findViewById(R.id.swipe_refresh_header);
        footerView = (CustomeRefreshFooterView) view.findViewById(R.id.swipe_load_more_footer);

        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipetoloadlayout);

        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);

        return view;
    }

    private void initSwipeReToloadLayout() {

        //对头部，底部设置布局

        headerview.setBackgroundResource(R.drawable.bg);
        headerview.setPadding(5, 5, 5, 5);
        headerview.setGravity(Gravity.CENTER);
        headerview.setTextColor(Color.WHITE);
        footerView.setTextSize(25);
        headerview.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));

        footerView.setBackgroundResource(R.drawable.bg);
        footerView.setPadding(5, 5, 5, 5);
        footerView.setTextSize(25);
        footerView.setTextColor(Color.WHITE);
        footerView.setGravity(Gravity.CENTER);
        footerView.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        //设置自定义头部视图
        swipeToLoadLayout.setRefreshHeaderView(headerview);
        //设置自定义底部视图
        swipeToLoadLayout.setLoadMoreFooterView(footerView);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }


    private void initAddData() {
        add_data = new ArrayList<>();
        add_data.add(new CardItem("图片", R.drawable.d));
        add_data.add(new CardItem("图片", R.drawable.e));
        add_data.add(new CardItem("图片", R.drawable.f));
    }

    private void initCardItems() {

        data = new ArrayList<>();
        data.add(new CardItem("小黄人一号", R.drawable.a));
        data.add(new CardItem("小黄人二号", R.drawable.b));
        data.add(new CardItem("小黄人三号", R.drawable.c));
        data.add(new CardItem("小黄人一号", R.drawable.a));
        data.add(new CardItem("小黄人二号", R.drawable.b));
        data.add(new CardItem("小黄人三号", R.drawable.c));
        data.add(new CardItem("小黄人一号", R.drawable.a));
        data.add(new CardItem("小黄人二号", R.drawable.b));
        data.add(new CardItem("小黄人三号", R.drawable.c));
        data.add(new CardItem("小黄人一号", R.drawable.a));
        data.add(new CardItem("小黄人二号", R.drawable.b));
        data.add(new CardItem("小黄人三号", R.drawable.c));
        data.add(new CardItem("小黄人一号", R.drawable.a));
        data.add(new CardItem("小黄人二号", R.drawable.b));
        data.add(new CardItem("小黄人三号", R.drawable.c));
        data.add(new CardItem("小黄人一号", R.drawable.a));
        data.add(new CardItem("小黄人二号", R.drawable.b));
        data.add(new CardItem("小黄人三号", R.drawable.c));

    }

    private void initRecyclerView() {

        mAdapter = new MyRecyclerAdapter(context, data);
        currentLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(currentLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(10);

        recyclerView.addItemDecoration(spacesItemDecoration);

        autoRefresh();



    }

    private void autoRefresh() {

        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });

    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getRandomCardItem();//向data中添加一个随机数据
                swipeToLoadLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
            }
        }, 1500);

    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getRandomCardItem();//向data中添加一个随机数据
                swipeToLoadLayout.setLoadingMore(false);
                mAdapter.notifyDataSetChanged();

            }
        }, 1500);
    }

    private void getRandomCardItem() {
        Random random = new Random();
        int num = random.nextInt(add_data.size());
        data.add(add_data.get(num));


    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
