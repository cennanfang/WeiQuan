package com.swinfo.weiquan.title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.swinfo.weiquan.R;

/**
 * Created by redbird on 2017/5/21.
 */

public class CommonTitleBar extends LinearLayout implements View.OnClickListener {
    /**
     * 返回按钮
     */
    private ImageView btnBack;

    /**
     * 进行操作
     */
    private TextView btnDoIt;

    /**
     * 进行操作
     */
    private TextView titleName;

    /**
     * 上下文对象
     */
    private Context mContext;


    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.title_layout, this);
        initViews();
    }

    private void initViews() {
        btnBack = (ImageView) findViewById(R.id.title_btn_back);
        btnDoIt = (TextView) findViewById(R.id.title_btn_do_it);
        titleName = (TextView) findViewById(R.id.title_btn_do_it);

        btnBack.setOnClickListener(this);
        btnDoIt.setOnClickListener(this);
    }

    public void setTitleName(String titleName) {
        this.titleName.setText(titleName);
    }
    public void setBtnDoItName(String btnDoItName) {
        this.btnDoIt.setText(btnDoItName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_btn_do_it:
                Toast.makeText(mContext, "do id", Toast.LENGTH_LONG).show();
                break;
            case R.id.title_btn_back:
                ((Activity) mContext).finish();
                break;
        }
    }
}
