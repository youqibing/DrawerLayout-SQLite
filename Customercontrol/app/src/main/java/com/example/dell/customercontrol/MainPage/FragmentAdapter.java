package com.example.dell.customercontrol.MainPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.customercontrol.FuctionFragmentPage.HotelDetailFragment;
import com.example.dell.customercontrol.FuctionFragmentPage.PersonalFragment;
import com.example.dell.customercontrol.FuctionFragmentPage.ReserveRoomFragment;

/**
 * Created by dell on 2016/7/4.
 */

public class FragmentAdapter extends FragmentPagerAdapter{

    private static final int TAB_COUNT = 3;
    private String[] TabTitle = {"预订房间","宾馆详情","个人主页"};

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MainActivity.RESERVE_ROOM:
                ReserveRoomFragment reserveRoomFragment =new ReserveRoomFragment();
                return reserveRoomFragment;

            case MainActivity.HOTEL_DETAIL:
                HotelDetailFragment hotelDetailFragment=new HotelDetailFragment();
                return hotelDetailFragment;

            case MainActivity.PERSONAL:
                PersonalFragment personalFragment=new PersonalFragment();
                return personalFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitle[position];
    }

}
