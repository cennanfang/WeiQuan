package com.swinfo.weiquan;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.swinfo.weiquan.location.LocationManager;
import com.swinfo.weiquan.message.MessageEditActivity;
import com.swinfo.weiquan.mian.MainOnListViewItemClickListener;
import com.swinfo.weiquan.mian.MainOnLocateResultListener;
import com.swinfo.weiquan.mian.MainOnNavigationItemSelectedListener;
import com.swinfo.weiquan.search.SearchActivity;
import com.swinfo.weiquan.mian.MainListAdapter;
import com.swinfo.weiquan.mian.MainPullListener;
import com.swinfo.weiquan.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends AppCompatActivity {
    private Context context = MainActivity.this;
    private ListView listView;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.startActivity(context, MessageEditActivity.class);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //左边导航
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new MainOnNavigationItemSelectedListener((DrawerLayout) findViewById(R.id.drawer_layout)));

        //显示列表数据
        ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        ptrl.setOnPullListener(new MainPullListener());
        listView = (ListView) ptrl.getPullableView();
        initListView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            AppUtils.startActivity(context, SearchActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 第一次进入自动刷新
        if (isFirstIn) {
            // 刷新数据
            ptrl.autoRefresh();
            // 获取位置信息
            new LocationManager(context).locate(new MainOnLocateResultListener((TextView) findViewById(R.id.tv_main_location)));
            isFirstIn = false;
        }
    }

    /**
     * ListView初始化方法
     */
    private void initListView() {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            items.add("这里是item " + i);
        }
        MainListAdapter adapter = new MainListAdapter(this, items);
        listView.setAdapter(adapter);
        MainOnListViewItemClickListener mainListViewOnItemClickListener = new MainOnListViewItemClickListener(context);
        listView.setOnItemLongClickListener(mainListViewOnItemClickListener);
        listView.setOnItemClickListener(mainListViewOnItemClickListener);
    }


}
