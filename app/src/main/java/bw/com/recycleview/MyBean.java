package bw.com.recycleview;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/11.
 */
public class MyBean {
    public String code;
    public String msg;
    public ArrayList<DataBean> data;

    public class DataBean {
        public String goods_name;
        public  String goods_img;
    }
}
