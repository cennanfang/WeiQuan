package com.swinfo.weiquan.mian;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swinfo.weiquan.R;

public class MainListAdapter extends BaseAdapter {
	private List<String> items;
	private Context context;
	private LayoutInflater mInflater;

	public MainListAdapter(Context context, List<String> items) {
		this.context = context;
		this.items = items;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.message_list_layout, null);
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
		return convertView;
	}

	private final class ViewHolder {
		public TextView messageContent;
		public TextView messageCreator;
		public TextView messageCreateTime;
		public ImageView messageImage;
	}

}
