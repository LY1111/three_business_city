package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BusineseeAdminBean;
import com.tuoyi.threebusinesscity.widget.MyRecyclerViewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/2 9:23
 * @描述 ${TODO}
 */

public class BusinesAdminAdapter extends RecyclerView.Adapter<BusinesAdminAdapter.ViewHolder> {

    private Context context;
    private List<BusineseeAdminBean.DataBean> list; // List 集合（里面是image+text）
    private DeleteInterface deleteInterface;
    /**
     * 构造函数
     *
     * @param context
     * @param list
     */
    public BusinesAdminAdapter(Context context, List<BusineseeAdminBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_businesadmin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("11111111",position+"");
        Log.e("11111111",list.size()+"");
        BusineseeAdminBean.DataBean dataBean = list.get(position);
        holder.tvName.setText(dataBean.getName());
        holder.tvMoney.setText("¥ "+dataBean.getPrice());
        holder.tv_dou.setText(dataBean.getSummary());
        Glide.with(context).load(IMGS + dataBean.getImage()).into(holder.img_content);  //商品图片
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInterface.modify(position);
            }
        });
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInterface.detele(position);
            }
        });

        holder.recyclerViewItem.reset();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_dou)
        TextView tv_dou;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;
        @BindView(R.id.click)
        LinearLayout click;
        @BindView(R.id.img_content)
        ImageView img_content;
        @BindView(R.id.img_edit)
        ImageView img_edit;
        @BindView(R.id.recycler_item)
        MyRecyclerViewItem recyclerViewItem;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * 删除接口
     */
    public interface DeleteInterface {

        void detele(int position);

        void modify(int position);
    }

    public  void setDeleteInterface(DeleteInterface deleteInterface){
        this.deleteInterface=deleteInterface;
    }
}
