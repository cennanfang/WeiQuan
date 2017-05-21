package com.swinfo.weiquan.message;

import android.annotation.TargetApi;
import android.view.ViewGroup;

import com.swinfo.weiquan.message.base.AbstractRender;
import com.swinfo.weiquan.message.base.AbstractRenderAdapter;
import com.swinfo.weiquan.message.base.AbstractViewHolder;
import com.swinfo.weiquan.message.render.ImagePickerRender;

import java.util.List;

/**
 * Created by redbird on 2017/5/19.
 */

public class ImagePickerListAdapter extends AbstractRenderAdapter<String> {

    public ImagePickerListAdapter(List<String> data) {
        this.mDataset = data;
    }

    public AbstractViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //header view
        AbstractViewHolder holder = super.onCreateViewHolder(viewGroup, viewType);
        if (holder != null) {
            return holder;
        }

        ImagePickerRender render = new ImagePickerRender(viewGroup, this);
        AbstractViewHolder articleHolder = render.getReusableComponent();
        articleHolder.itemView.setTag(android.support.design.R.id.list_item, render);
        return articleHolder;
    }

    @TargetApi(4)
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        AbstractRender render = (AbstractRender) holder.itemView.getTag(android.support.design.R.id.list_item);
        render.bindData(position);
    }
}
