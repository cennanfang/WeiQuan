package com.swinfo.weiquan.message.base;

/**
 * Created by redbird on 2017/5/19.
 */

public abstract class AbstractRender {
    public  abstract<T extends AbstractViewHolder> T getReusableComponent();
    public  abstract void bindData(int position);
}
