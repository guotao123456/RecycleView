package bw.com.recycleview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
/**
 * Created by lenovo on 2016/11/10.
 */
public class Fragment1 extends Fragment{
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private Context context=getActivity();
    private View view;
    ArrayList<MyBean.DataBean> list=new ArrayList<MyBean.DataBean>();
    private String path="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        initcode();
        return view;
    }
    private void initcode() {
        mRecyclerView = (RecyclerView)view. findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));// LinearLayoutManager(this)
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                MyBean myBean=gson.fromJson(result,MyBean.class);
                list.clear();
                list.addAll(myBean.data);
                mAdapter= new HomeAdapter(getActivity(),list);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new HomeAdapter.OnRecyclerViewItemClickListener(){
                    @Override
                    public void onItemClick(View view , String data){
                        Toast.makeText(getActivity(), data,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
