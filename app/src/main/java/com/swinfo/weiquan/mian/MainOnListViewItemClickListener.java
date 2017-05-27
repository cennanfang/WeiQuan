package com.swinfo.weiquan.mian;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.swinfo.weiquan.message.MessageDetailActivity;
import com.swinfo.weiquan.util.AppUtils;

/**
 * Created by redbird on 2017/5/27.
 */

public class MainOnListViewItemClickListener implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private Context context;

    public MainOnListViewItemClickListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        Toast.makeText(
                context,
                "LongClick on "
                        + parent.getAdapter().getItemId(position),
                Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        AppUtils.startActivity(context, MessageDetailActivity.class);
        Toast.makeText(context,
                " Click on " + parent.getAdapter().getItemId(position),
                Toast.LENGTH_SHORT).show();
    }
}

