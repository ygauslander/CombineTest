package all.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MyFragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;
    private String[] str = {"热门推荐", "热门收藏", "本月热榜", "今日热榜"};


    public MyFragmentViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
