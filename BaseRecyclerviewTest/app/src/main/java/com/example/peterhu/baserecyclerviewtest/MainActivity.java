package com.example.peterhu.baserecyclerviewtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.huterpu.baserecyclerview.BaseRecyclerView;
import com.huterpu.baserecyclerview.FlexibleDividerDecoration.HorizontalDividerItemDecoration;
import com.huterpu.baserecyclerview.OnItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, BaseRecyclerView.LFRecyclerViewListener {

        private BaseRecyclerView recycleview;
        private ArrayList<String> list;
        private Main_Adapter adapter;
        private boolean b;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.recycleview = (BaseRecyclerView) findViewById(R.id.myRecycleView);
            this.list = new ArrayList<>();
            list.add(list.size(), "leefeng.me" + "==onLoadMore");
////
//            recycleview.setLoadMore(true);
//            recycleview.setRefresh(true);
            recycleview.setLFRecyclerViewListener(this);
            recycleview.setAutoLoadMore(true);
//            recycleview.addItemDecoration(new GridSpacingItemDecoration(30, new Rect(0,0,0,70)));
//            recycleview.addItemDecoration(new );
//        recycleview.addItemDecoration(new HorizontalDividerItemDecoration().Builder);

        recycleview.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).size(40).color(Color.GREEN).build());
//        recycleview.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));

//        GridLayoutManager manager = new GridLayoutManager(this);
//        recycleview.setLayoutManager(manager);

//        recycleview.setLayoutManager();
            recycleview.setItemAnimator(new DefaultItemAnimator());
            recycleview.setOnItemClickListener(this);
            adapter = new Main_Adapter(list);
            recycleview.setAdapter(adapter);

//        ViewGroup group = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item,null);

            TextView tv = new TextView(MainActivity.this);
            tv.setText("这是头部sdfsfasdfsadfsadfsadfasdfasdfasdfsdafsdafsadfasdf");
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.RED);

            TextView fv = new TextView(MainActivity.this);
            fv.setText("这是底dffvevewvvevewrwerferfwerfwerfwerfwerfwerfwerfwrewrfwfrwerfew部");
            fv.setTextColor(Color.WHITE);
            fv.setGravity(Gravity.CENTER);
            fv.setBackgroundColor(Color.BLUE);
//            recycleview.setHeaderView(tv);
//            recycleview.setFooterView(fv);
//            recycleview.setOrientationAndReverse(RecyclerView.HORIZONTAL,true);
            recycleview.setColumn(1);
//            recycleview.setOrientationAndReverse(RecyclerView.HORIZONTAL,false);
        }


        @Override
        public void onClick(int position) {
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
            list.add(list.size(), "leefeng.me" + "==onLoadMore");
            adapter.notifyItemRangeInserted(list.size()-1,1);
        }

        @Override
        public void onLongClick(int po) {
            Toast.makeText(this, "Long:" + po, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    list.add(0, "leefeng.me" + "==onRefresh");
                    recycleview.stopRefresh(true);
                    adapter.notifyItemInserted(0);
                    adapter.notifyItemRangeChanged(0,list.size());

                }
            }, 2000);
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    list.add(list.size(), "leefeng.me" + "==onLoadMore");
                    list.add(list.size(), "leefeng.me" + "==onLoadMore");

                    if (list.size() < 20) {
                        recycleview.stopLoadMore();
                    }
                    else {
                        recycleview.stopLoadAndNoMoreData();
                    }
//                list.add(list.size(), "leefeng.me" + "==onLoadMore");
                    adapter.notifyItemRangeInserted(list.size()-2,2);

                }
            }, 2000);
        }


    }
