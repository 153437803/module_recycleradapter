package com.demo.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import lib.kalu.recyclerview.adapter.BaseCommonAdapter;
import lib.kalu.recyclerview.itemdecoration.TabDecoration;
import lib.kalu.recyclerview.viewholder.RecyclerHolder;

/**
 * description: 悬浮菜单
 * created by kalu on 2017/12/5 4:31
 */
public class StickyActivity1 extends AppCompatActivity {
    private RecyclerView mRv;

    BaseCommonAdapter<City> mAdapter;
    List<City> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_1);
        initView();
    }

    private void initView() {

        mRv = (RecyclerView) findViewById(R.id.rv);
        //模拟数据
        dataList.addAll(CityUtil.getCityList());
        dataList.addAll(CityUtil.getCityList());

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(manager);

        TabDecoration decoration = new TabDecoration() {

            @Override
            public boolean hasHead() {
                return true;
            }

            @Override
            public int loadHeight(int position) {
                return DensityUtil.dip2px(getApplicationContext(), 40);
            }

            @Override
            public String loadName(int position) {
                if (dataList.size() > position) {
                    return dataList.get(position).getProvince();
                }
                return null;
            }

            @Override
            public View loadView(int position) {
                //获取自定定义的组View
                if (dataList.size() > position) {
                    View view = getLayoutInflater().inflate(R.layout.city_group, null, false);
                    ((TextView) view.findViewById(R.id.tv)).setText("tab");
                    ((ImageView) view.findViewById(R.id.iv)).setImageResource(dataList.get(position).getIcon());
                    return view;
                } else {
                    return null;
                }
            }
        };

        mRv.addItemDecoration(decoration);
        mAdapter = new BaseCommonAdapter<City>() {
            @Override
            protected int onView() {
                return R.layout.activity_sticky_1_item;
            }

            @NonNull
            @Override
            protected List<City> onData() {
                return dataList;
            }

            @Override
            protected void onNext(RecyclerHolder holder, City result, int position) {
                int i = position % 5 + 1;
                if (i == 1) {
                    holder.setImageResource(R.id.iv_city, R.mipmap.ic_launcher);
                } else if (i == 2) {
                    holder.setImageResource(R.id.iv_city, R.mipmap.ic_launcher);
                } else if (i == 3) {
                    holder.setImageResource(R.id.iv_city, R.mipmap.ic_launcher);
                } else if (i == 4) {
                    holder.setImageResource(R.id.iv_city, R.mipmap.ic_launcher);
                } else {
                    holder.setImageResource(R.id.iv_city, R.mipmap.ic_launcher);
                }
                holder.setText(R.id.tv_city, position + "");
            }
        };

        TextView textView = new TextView(getApplicationContext());
        textView.setText("HEAD");
        textView.setBackgroundColor(Color.GRAY);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getApplicationContext(), 250));
        textView.setLayoutParams(layoutParams);

        mAdapter.addHead(textView);

        mRv.setAdapter(mAdapter);
    }
}