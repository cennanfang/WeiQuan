package com.swinfo.weiquan.adapter.render;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.swinfo.weiquan.R;
import com.swinfo.weiquan.adapter.base.AbstractRender;
import com.swinfo.weiquan.adapter.base.AbstractRenderAdapter;
import com.swinfo.weiquan.adapter.base.AbstractViewHolder;
import com.swinfo.weiquan.util.ViewUtils;

/**
 * Created by redbird on 2017/5/19.
 */

public class ImagePickerRender extends AbstractRender {
    private ViewHolder mHolder;
    private AbstractRenderAdapter mAdapter;

    public ImagePickerRender(ViewGroup parent, AbstractRenderAdapter adapter) {
        this.mAdapter = adapter;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_edit_add_pic, parent, false);
        this.mHolder = new ViewHolder(v, adapter);
    }


    @Override
    public void bindData(int position) {
        String data = (String) mAdapter.getItem(position);
        mHolder.mImage.setImageURI(Uri.parse(data));
    }

    @Override
    public AbstractViewHolder getReusableComponent() {
        return this.mHolder;
    }

    public class ViewHolder extends AbstractViewHolder {
        public ImageView mImage;

        public ViewHolder(View v, final AbstractRenderAdapter adapter) {
            super(v);
            mImage = ViewUtils.findViewById(v, R.id.iv_image);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.mOnItemClickListener.onItemClick(view, getLayoutPosition());
                }
            });
        }
    }
}