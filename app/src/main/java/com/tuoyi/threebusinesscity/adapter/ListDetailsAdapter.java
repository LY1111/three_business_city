package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.OptionListBean;
import com.tuoyi.threebusinesscity.bean.ShoppingCartBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class ListDetailsAdapter extends BaseAdapter {

    private List<ShoppingCartBean.DataBean.GoodsBean> datas=new ArrayList<>();
//    private List<ShoppingCartBean.DataBean.GoodsBean.OptionBean> datas2=new ArrayList<>();;
    private List<OptionListBean> datas2=new ArrayList<>();;
    private LayoutInflater inflater;
    private Context context;

    public static class ViewHolder {
        private TextView tv_name;
        private TextView tv_price;
        private TextView tv_num;
        private TextView tv_integral;
        private ImageView img_title;
        private TagFlowLayout details_flowlayout;
        private LinearLayout linearLayout;

    }

    public ListDetailsAdapter(Context context, List<ShoppingCartBean.DataBean.GoodsBean> datas,List<OptionListBean> datas2) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        this.context=context;
        this.datas2=datas2;

    }
    @Override
    public int getCount() {
        return  datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_listdetails, parent, false);
            holder = new ListDetailsAdapter.ViewHolder();
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.img_title = view.findViewById(R.id.img_title);
            holder.tv_price=view.findViewById(R.id.tv_price);
            holder.tv_num=view.findViewById(R.id.tv_num);
            //holder.details_flowlayout=view.findViewById(R.id.details_flowlayout);
            holder.tv_integral=view.findViewById(R.id.tv_integral);
            holder.linearLayout=view.findViewById(R.id.linearLayout_qqq);
            view.setTag(holder);
        } else {
            holder = (ListDetailsAdapter.ViewHolder) view.getTag();
        }

        holder.tv_name.setText(datas.get(position).getName());
        holder.tv_price.setText("¥："+datas.get(position).getPrice());
        holder.tv_num.setText("x "+datas.get(position).getQuantity());
        holder.tv_integral.setText("积分："+datas.get(position).getPay_points());
        Glide.with(context).load(Config.s+datas.get(position).getImage()).into(holder.img_title);

/*
        holder.details_flowlayout.setAdapter(new TagAdapter(datas2) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) inflater.inflate(R.layout.item_flowlayout,
                        holder.details_flowlayout, false);

                tv.setText(datas2.get(position).getValue());
                return tv;

            }
        });*/

        //final ShoppingCartBean.DataBean.GoodsBean goodsBean=datas.get(position);

        holder.linearLayout.removeAllViews();
        for (int i = 0; i <datas2.get(position).getOptionBeans().size() ; i++) {
            final TextView textView = new TextView(context);
            ShoppingCartBean.DataBean.GoodsBean.OptionBean bean = datas2.get(position).getOptionBeans().get(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 80);
            textView.setLayoutParams(layoutParams);
            textView.setText(bean.getName()+":"+bean.getValue());
            textView.setId(View.generateViewId());
            textView.setTextSize(12);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(10, 10, 10, 10);
            holder.linearLayout.addView(textView);
        }

        return view;
    }
}
