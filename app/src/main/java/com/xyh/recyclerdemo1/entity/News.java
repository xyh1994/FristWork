package com.xyh.recyclerdemo1.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */
public class News {

private ArrayList<Data> T1348648756099;

    public News(ArrayList<Data> t1348648756099) {
        T1348648756099 = t1348648756099;
    }

    public ArrayList<Data> getT1348648756099() {
        return T1348648756099;
    }

    public void setT1348648756099(ArrayList<Data> t1348648756099) {
        T1348648756099 = t1348648756099;
    }

    public  static class  Data {

    private String title;
    private String digest;
    private String imgsrc;
    private String ptime;
    private String url;

        public Data(String title, String digest, String imgsrc, String ptime, String url) {
            this.title = title;
            this.digest = digest;
            this.imgsrc = imgsrc;
            this.ptime = ptime;
            this.url = url;
        }

        //右键 Genenrate 里面toString
        @Override
        public String toString() {
            return "Data{" +
                    "title='" + title + '\'' +
                    ", digest='" + digest + '\'' +
                    ", imgsrc='" + imgsrc + '\'' +
                    ", ptime='" + ptime + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
