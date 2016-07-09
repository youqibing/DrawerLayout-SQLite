package com.example.dell.customercontrol.FuctionFragmentPage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.customercontrol.FuctionActivityPage.BillActivity;
import com.example.dell.customercontrol.FuctionActivityPage.IsReserveActivity;
import com.example.dell.customercontrol.R;
import com.example.dell.customercontrol.SQLite.AccountOpenFunction;

import java.util.Calendar;

/**
 * Created by dell on 2016/7/5.
 *
 */

public class ReserveRoomFragment extends Fragment implements View.OnClickListener{
    private View layoutView;

    private String time;
    private float fool;
    private float room;
    private float money;
    private boolean isReserve ;

    private ImageView srue;

    private EditText timebilled;  //账单时间
    private EditText billedmoney;  //账单金额
    private Spinner foolNumber;   //楼层
    private Spinner roomNumber;    //房号

    private static final String[] fools ={"01","02","03","04","05", "06","07","08","09","10"};
    private static final String[] rooms ={"01","02","03","04","05", "06","07","08","09","10"};

    private final Calendar calendar = Calendar.getInstance();
    AccountOpenFunction accountDBOpen;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.reserve_layout,container,false);

        accountDBOpen = new AccountOpenFunction(getActivity().getApplicationContext());  //实例化数据库对象,这里不能直接getApplicationContext()，因为这是Fragment,必须先getActivty()

        initView();  //绑定相应控件函数

        DatePickerchoice();  //时期选择函数
        spinneradapter();   //两个Spinner下拉菜单适配函数。

        return layoutView;
    }

    public void initView(){

        srue = (ImageView)layoutView.findViewById(R.id.srue);
        srue.setOnClickListener(this);

        foolNumber = (Spinner)layoutView.findViewById(R.id.louceng);
        roomNumber = (Spinner)layoutView.findViewById(R.id.fanghao);


        billedmoney = (EditText)layoutView.findViewById(R.id.zhangdandingjin);
        billedmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billedmoney.setText("");
            }
        });

        timebilled = (EditText)layoutView.findViewById(R.id.yudingriqi);

    }

    public void DatePickerchoice() {
        timebilled.setInputType(InputType.TYPE_NULL);  //让EditText始终不显示系统键盘。
        timebilled.setOnClickListener(new View.OnClickListener() {
            int mmonth, mday, myear;
            String mm, dd;
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
                    //注意这里的getActivity()不能换成this,因为this指代的是上面的 View.OnClickListener()这个接口，但这里要求是一个context.
                    //利用fragment的getActivity()可以取得其所依赖的Activty，即环境。
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear <= 9) {
                            mmonth = monthOfYear + 1;
                            mm = "0" + mmonth;  //字符串+整数强制转换为字符串。
                        } else {
                            mmonth = monthOfYear + 1;
                            mm = String.valueOf(mmonth);
                        }
                        if (dayOfMonth <= 9) {
                            mday = dayOfMonth;
                            dd = "0" + mday;
                        } else {
                            mday = dayOfMonth;
                            dd = String.valueOf(mday);
                        }
                        myear = year;
                        timebilled.setText(String.valueOf(myear) + "-" + mm + "-" + dd);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    public void spinneradapter(){

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getActivity(), R.layout.addnotes_earing,fools); //将types这个可选择的数组同下拉菜单联系起来。
        adapterType.setDropDownViewResource(android.R.layout.select_dialog_item); //设置下拉菜单风格
        foolNumber.setAdapter(adapterType);

        ArrayAdapter<String> adapterEaring =new ArrayAdapter<String>(getActivity(),R.layout.addnotes_earing,rooms);
        adapterEaring.setDropDownViewResource(android.R.layout.select_dialog_item);
        roomNumber.setAdapter(adapterEaring);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.srue:
                if (billedmoney.getText().toString().trim().equals("账单定金") ) {
                    Toast.makeText(getActivity(), "金额不能为空!", Toast.LENGTH_LONG).show();
                    break;
                } else if(billedmoney.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(), "金额不能为空!", Toast.LENGTH_LONG).show();
                    break;
                }
                else {
                    money = Float.parseFloat(billedmoney.getText().toString().trim());  //trim()用来去除空格。
                }

                time = timebilled.getText().toString().trim();
                fool = Float.parseFloat(foolNumber.getSelectedItem().toString());
                room = Float.parseFloat(roomNumber.getSelectedItem().toString());

                isReserve = true;

                Intent billIntent = new Intent(getActivity(), BillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("time",time);
                bundle.putFloat("money",money);
                bundle.putFloat("fool",fool);
                bundle.putFloat("room",room);
                bundle.putBoolean("isReserve",isReserve);
                billIntent.putExtras(bundle);
                startActivity(billIntent);

                billedmoney.setText("预订金额");
                timebilled.setText("预订日期");

                break;
        }
    }

}
