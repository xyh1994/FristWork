package com.xyh.recyclerdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.xyh.recyclerdemo1.adpter.RecyclerAdapter;
import com.xyh.recyclerdemo1.entity.News;
import com.xyh.recyclerdemo1.utils.MyVolleyUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.MyOnItemClickListener, RecyclerAdapter.MyOnItemLongClickListener {

    @BindView(R.id.recyclerview1)

    //安卓5.0组件之一，可用于取代listview,更灵活，在support.v7包下
            RecyclerView recyclerview1;
    RecyclerAdapter adapter;
    String url = "http://c.m.163.com/nc/article/list/T1348648756099/0-20.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new RecyclerAdapter(this);
        adapter.setMyOnItemClickListener(this);
        adapter.setMyOnItemLongClickListener(this);
        recyclerview1.setAdapter(adapter);
        //通过设置布局管理器，设置该控件的显示效果
        //  recyclerview1.setLayoutManager(new LinearLayoutManager(this));
        // recyclerview1.setLayoutManager(new GridLayoutManager(this,3));
        //设置瀑布流布局效果
        recyclerview1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //用volley框架获取网络数据，首先通过工具类得到对网络的请求队列
        MyVolleyUtil.getInstance(this).getQueue().add(new NewsListRsquest(url));
    }

    @Override
    public void onItemClick(View v, int position) {
Toast.makeText(MainActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        Toast.makeText(MainActivity.this,"长安响应",Toast.LENGTH_SHORT).show();
        return true;
    }

    public class NewsListRsquest extends StringRequest {//要发送一条网络请求，必须创建StringRequest对象


        public NewsListRsquest(String url) {
            //第一个参数就是目标服务器的URL地址
            super(url, new Response.Listener<String>() {
                @Override
                //第二个参数是服务器响应成功的回调
                public void onResponse(String s) {
                    //Gson用来在 Java 对象和 JSON 数据之间进行映射的 Java 类库,可以用解析网络数据
                    Gson gson = new Gson();
                    //Gson提供的一个方法。用来将一个Json数据转换为对象
                    News news = gson.fromJson(s, News.class);
                    adapter.setList(news.getT1348648756099());
                    adapter.notifyDataSetChanged();
                }
                // 第三个参数是服务器响应失败的回调
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MainActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
