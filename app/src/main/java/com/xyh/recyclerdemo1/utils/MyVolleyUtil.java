package com.xyh.recyclerdemo1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/8/27.
 */
public class MyVolleyUtil {
    //维护一个唯一的任务队列
    //imageLoader
    //图片缓存

    private Context context;
    private static MyVolleyUtil util;
    private RequestQueue queue;//获取网络数据的请求队列，一般需要一个就行了
    private BitmapCache bitmapCache;//指定图片缩放比例的类，用于处理网络图片加载时的缓存

    private MyVolleyUtil(Context context) {
        this.context = context;
        //在构造方法中实例化，进行单例模式，保证唯一
        queue = Volley.newRequestQueue(context);
        bitmapCache = new BitmapCache();

    }

    public static MyVolleyUtil getInstance(Context context) {
        if (util == null) {
            synchronized (context) {
                if (util == null) {
                    util = new MyVolleyUtil(context);
                }
            }

        }


        return util;
    }


    public RequestQueue getQueue() {
        return queue;
    }

    public ImageLoader getLoader() {
        return new ImageLoader(queue, bitmapCache);
    }

    //实现图片缓存的类
    public static class BitmapCache implements ImageLoader.ImageCache {

        //利用NetworkImageView之前，我们还需要先去实现这个接口，而Volley推荐的也就是LruCache
        private LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(5 * 1024 * 1024) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        //得到图片
        @Override
        public Bitmap getBitmap(String s) {
            return lruCache.get(s);
        }

        //添加图片
        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            lruCache.put(s, bitmap);
        }
    }
}
