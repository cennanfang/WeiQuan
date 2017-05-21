package com.swinfo.weiquan.title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    /**
     * 函数回调
     */
    private CommonTitleListener titleListener;


    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.title_layout, this);
        initViews();
    }

    private void initViews() {
        btnBack = (ImageView) findViewById(R.id.title_btn_back);
        btnDoIt = (TextView) findViewById(R.id.title_btn_do_it);
        titleName = (TextView) findViewById(R.id.title_name);

        btnBack.setOnClickListener(this);
        btnDoIt.setOnClickListener(this);
    }

    /**
     * 设置标题
     * @param titleName
     */
    public void setTitleName(String titleName) {
        this.titleName.setText(titleName);
    }

    /**
     * 设置标题
     * @param doItName
     */
    public void setDoItName(String doItName) {
        this.btnDoIt.setText(doItName);
    }
    /**
     * 设置标题
     * @param doItName
     */
    public void setDoItVisibility(int visibility) {
        this.btnDoIt.setVisibility(visibility);
    }



    public void setTitleListener(CommonTitleListener titleListener) {
        this.titleListener = titleListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_btn_do_it:
                titleListener.doIt();
                break;
            case R.id.title_btn_back:
                ((Activity) mContext).finish();
                break;
        }
    }



    public interface CommonTitleListener {
        /**
         * 实现按钮功能
         */
        public void doIt();

    }
}
