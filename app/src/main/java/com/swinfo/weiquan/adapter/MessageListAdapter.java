package com.swinfo.weiquan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swinfo.weiquan.R;

import java.util.List;
import java.util.Map;

/**
 * Created by redbird on 2017/5/13.
 */

public class MessageListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Map<String, Object>> listMessage;

    public MessageListAdapter(Context context, List<Map<String, Object>> listMessage) {
        this.listMessage = listMessage;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listMessage.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.layout_message_list, null);
            holder.messageContent = (TextView) convertView.findViewById(R.id.tv_message_list_content);
            holder.messageCreator = (TextView) convertView.findViewById(R.id.tv_message_list_creator);
            holder.messageCreateTime = (TextView) convertView.findViewById(R.id.tv_message_list_publish_time);
            holder.messageImage = (ImageView) convertView.findViewById(R.id.img_message_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.messageImage.setBackgroundResource(R.mipmap.img001);
        holder.messageContent.setText("新编内容新编内容新编内容" + position);
        holder.messageCreator.setText("新编发布者" + position);
        holder.messageCreateTime.setText("发布时间10:10:1" + position);

//            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    showInfo();
//                }
//            });
        return convertView;
    }

    private final class ViewHolder {
        public TextView messageContent;
        public TextView messageCreator;
        public TextView messageCreateTime;
        public ImageView messageImage;
    }
}
