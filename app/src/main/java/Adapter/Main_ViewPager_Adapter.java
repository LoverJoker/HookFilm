package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by Joker on 2017/1/19.
 */

public class Main_ViewPager_Adapter extends FragmentPagerAdapter{
    private String[] titles ;
    private Fragment[] fragments ;
    public Main_ViewPager_Adapter(FragmentManager fm , String[] titles , Fragment[] fragments) {
        super(fm);
        this.titles = titles ;
        this.fragments= fragments ;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position] ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }


}
