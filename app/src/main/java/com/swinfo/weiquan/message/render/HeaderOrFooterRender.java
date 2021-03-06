package com.swinfo.weiquan.message.render;

import android.view.View;

import com.swinfo.weiquan.message.base.AbstractRender;
import com.swinfo.weiquan.message.base.AbstractViewHolder;

/**
 * Created by redbird on 2017/5/19.
 */

public class HeaderOrFooterRender extends AbstractRender {

    private ViewHolder mHolder;

    public HeaderOrFooterRender(View view) {
        this.mHolder = new ViewHolder(view);
    }

    @Override
    public void bindData(int position) {
    }

    @Override
    public AbstractViewHolder getReusableComponent() {
        return this.mHolder;
    }

    public class ViewHolder extends AbstractViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }
}
