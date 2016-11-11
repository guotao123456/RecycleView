package bw.com.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/11.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener{

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    //构造方法
    Context context;
    ArrayList<MyBean.DataBean> list;
    public HomeAdapter(Context context, ArrayList<MyBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_layout, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv.setText(list.get(position).goods_name);
        ImageLoader.getInstance().displayImage(list.get(position).goods_img, holder.imageview);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(list.get(position).goods_name);

    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        ImageView imageview;

        public MyViewHolder(View view)
        {
            super(view);
            imageview = (ImageView)view.findViewById(R.id.imageView);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}


