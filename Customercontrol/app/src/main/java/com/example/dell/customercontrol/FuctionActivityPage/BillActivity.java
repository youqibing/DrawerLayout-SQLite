package com.example.dell.customercontrol.FuctionActivityPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.customercontrol.R;
import com.example.dell.customercontrol.SQLite.AccountOpenFunction;

/**
 * Created by dell on 2016/7/6.
 */

public class BillActivity extends Activity {
    private TextView time_tx;
    private TextView money_tx;
    private TextView fool_tx;
    private TextView room_tx;

    private Button button;

    public String time;
    public Float money;
    public Float fool;
    public Float room;
    public Boolean isReserve;

    AccountOpenFunction accountDBOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_layout);

        accountDBOpen = new AccountOpenFunction(getApplicationContext());

        time_tx=(TextView)findViewById(R.id.textView2);
        money_tx=(TextView)findViewById((R.id.textView4));
        fool_tx=(TextView)findViewById(R.id.textView6);
        room_tx=(TextView)findViewById(R.id.textView8);

        button=(Button)findViewById(R.id.button3);

        Intent intent =getIntent();
        Bundle bundle =intent.getExtras();
        time = bundle.getString("time");
        money=bundle.getFloat("money");
        fool =bundle.getFloat("fool");
        room = bundle.getFloat("room");
        isReserve =bundle.getBoolean("isReserve");

        time_tx.setText(time);
        money_tx.setText(money.toString());
        fool_tx.setText(fool.toString());
        room_tx.setText(room.toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountDBOpen.add(time, money, fool, room, isReserve);
                Toast.makeText(BillActivity.this, "添加账单成功", Toast.LENGTH_LONG).show();

                finish();
            }
        });


    }
}
