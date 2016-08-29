package com.xyh.recyclerdemo1.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.xyh.recyclerdemo1.R;
import com.xyh.recyclerdemo1.entity.News;
import com.xyh.recyclerdemo1.utils.MyVolleyUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/27.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private List<News.Data> list;
    private Context context;

    public RecyclerAdapter(List<News.Data> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<News.Data> list) {
        this.list = list;
    }


    //创建内部类，holdel类
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.layout_newsinfo, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        //设置图片，通过NetworkImageView该控件自带的设置方法，需要一个图片网址，和ImageLoader用于网络图片加载的框架
        holder.iv_icon.setImageUrl(list.get(position).getImgsrc(), MyVolleyUtil.getInstance(context).getLoader());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_content.setText(list.get(position).getDigest());
        holder.tv_time.setText(list.get(position).getPtime());
        holder.tv_utl.setText(list.get(position).getUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnItemClickListener != null) {
                    myOnItemClickListener.onItemClick(view, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (myOnItemLongClickListener != null) {
                    myOnItemLongClickListener.onItemLongClick(view, position);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_utl)
        TextView tv_utl;
        @BindView(R.id.iv_icon)
        NetworkImageView iv_icon;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    //自定义布局点击接口
    public MyOnItemClickListener myOnItemClickListener;

    public interface MyOnItemClickListener {
        void onItemClick(View v, int position);
    }

    //自定义布局长安点击接口
    public MyOnItemLongClickListener myOnItemLongClickListener;

    public void setMyOnItemLongClickListener(MyOnItemLongClickListener myOnItemLongClickListener) {
        this.myOnItemLongClickListener = myOnItemLongClickListener;
    }

    public interface MyOnItemLongClickListener {
        boolean onItemLongClick(View v, int position);

    }
}