package com.example.dell.customercontrol.FuctionActivityPage;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.customercontrol.R;
import com.example.dell.customercontrol.SQLite.Account;
import com.example.dell.customercontrol.SQLite.AccountOpenFunction;

import java.util.List;

/**
 * Created by dell on 2016/7/5.
 */

public class IsReserveActivity extends Activity{
    private ListView listView;
    private List<Account> accounts;

    private AccountOpenFunction accountDBOpen;
    private Account account;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserved_layout);

        listView = (ListView)findViewById(R.id.listView);
        accountDBOpen = new AccountOpenFunction(getApplicationContext());

        inflater =LayoutInflater.from(this);

        accounts = accountDBOpen.fillAllReserve();
        listView.setAdapter(new MyAdpater());
    }

    private class MyAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            int size =0;
            if(accounts!=null){
                size = accounts.size();
            }
            return size;
        }

        @Override
        public Object getItem(int position) {
            return accounts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = inflater.inflate(R.layout.specific_data,null);
            account = accounts.get(position);    //accounts是这个ListView的对象，account是数据库中中条数据的对象,
            // 这句代码就是把数据库中的数据和这个ListView联系起来的,即每一个ListView中的数据对应一个数据库中的数据。

            TextView txtime =(TextView)view.findViewById(R.id.ls_sp_tv_time);
            TextView txfool = (TextView)view.findViewById(R.id.ls_sp_tv_louceng);
            TextView txroom = (TextView)view.findViewById(R.id.ls_sp_tv_fanghao);
            TextView txmoney = (TextView)view.findViewById(R.id.ls_sp_tv_dingjin);

            txtime.setText("预订"+account.getTime()+"日");
            txfool.setText(account.getFoolNumber().toString()+"楼");
            txroom.setText(account.getRoomNmber().toString()+"房");
            txmoney.setText(account.getMoney().toString()+"定金");

            return view;
        }
    }


}
