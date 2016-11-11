package bw.com.relativedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    List<MyBean.Data> list=new ArrayList<MyBean.Data>();
    private  String path="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this) {
            });
             mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
           // mRecyclerView.addItemDecoration(new DividerGridItemDecoration(MainActivity.this));
            mRecyclerView.setAdapter(mAdapter = new HomeAdapter());

         OkHttp.getAsync(path,new OkHttp.DataCallBack()
                {
                    @Override
                    public void requestFailure(Request request, IOException e) {
                    }
                    @Override
                    public void requestSuccess(String result) throws Exception {
                        // Toast.makeText(getApplicationContext(),result,0).show();
                        Gson gson=new Gson();
                        MyBean myBean=gson.fromJson(result,MyBean.class);
                        list.clear();
                list.addAll(myBean.data);
            }
        });
        }
        class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(list.get(position).goods_name);
            holder.con.setText(list.get(position).effiacy);
            ImageLoader.getInstance().displayImage(list.get(position).goods_img,holder.img);
        }
        @Override
        public int getItemCount()
        {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView con;
            TextView tv;
            ImageView img;
            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.title);
                con = (TextView) view.findViewById(R.id.con);
                img = (ImageView) view.findViewById(R.id.img);
            }
        }
    }
    }

