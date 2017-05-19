package com.swinfo.weiquan.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.swinfo.weiquan.R;
import com.swinfo.weiquan.adapter.ImagePickerListAdapter;
import com.swinfo.weiquan.adapter.base.AbstractRenderAdapter;
import com.swinfo.weiquan.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class MessageEditActivity extends AppCompatActivity {
    private Context context = MessageEditActivity.this;
    private final int REQUEST_IMAGE = 1;
    private String TAG = "weiquan";
    private ImagePickerListAdapter mImagePickerAdapter;
    private RecyclerView mImagePickerListView;
    private List<String> mImagePathes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_edit_activity);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("编辑消息");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initImagePicker();
    }

    /**
     * 打开图片选择器
     */
    private void openPickImageGallery() {
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE) {
            mImagePathes = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            replaceImages();
        }
    }

    /**
     * 显示图片
     */
    private void replaceImages(){
        mImagePickerAdapter.replace(mImagePathes);
        if (mImagePathes.size() == 9) {
            mImagePickerAdapter.removeFooterView();
        } else if (!mImagePickerAdapter.hasFooterView()) {
            View footer = getLayoutInflater().inflate(R.layout.message_edit_footer_image_picker, null);
            mImagePickerAdapter.setFooterView(footer);
        }
    }

    /**
     * 初始化图片列表
     */
    private void initImagePicker() {
        //adapter
        mImagePickerAdapter = new ImagePickerListAdapter(new ArrayList<String>());
        mImagePickerAdapter.setOnItemClickListener(new AbstractRenderAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.d(TAG, "clicked");
            }
        });

        //recycler view
        mImagePickerListView = ViewUtils.findViewById(this, R.id.list_image_picker);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);

        mImagePickerListView.setLayoutManager(linearLayoutManager);
        mImagePickerListView.setAdapter(mImagePickerAdapter);

        //footer
        View footer = getLayoutInflater().inflate(R.layout.message_edit_footer_image_picker, null);
        mImagePickerAdapter.setFooterView(footer);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPickImageGallery();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}