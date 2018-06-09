package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.GetWholeOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrederAdapter extends RecyclerView.Adapter<OrederAdapter.ViewHolder> {

    private Context context;
    private List<GetWholeOrderBean.DataBean> orderBean;

    public OrederAdapter(List<GetWholeOrderBean.DataBean> OrderBean) {
        orderBean = OrderBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_list, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem1 = orderBean.get(position);
        holder.itemOrderListOrderNum.setText(holder.mItem1.getOrder().getOrder_num_alias());                    //订单号

        holder.itemOrderListTotalPrice.setText(holder.mItem1.getOrder().getTotal());                            //订单总价格

        //订单状态
        if (holder.mItem1.getOrder().getOrder_status_id() == 1) {
            holder.itemOrderListStateText.setText("已付款代发货");
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 2) {
            holder.itemOrderListStateText.setText("已完成");
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 3) {
            holder.itemOrderListStateText.setText("待付款");
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 4) {
            holder.itemOrderListStateText.setText("已发货");
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 5) {
            holder.itemOrderListStateText.setText("已取消");
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserBean.setPosID(context,String.valueOf(holder.mItem.getUid()));
//                RxActivityTool.skipActivity(context, GeneralDetailsActivity.class);

                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        /*holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击付款："+holder.mItem.getGoods_id(), Toast.LENGTH_SHORT).show();
                holder.mItem.getGoods_id();
//                UserBean.setPosID(context,String.valueOf(holder.mItem.getUid()));
//                RxActivityTool.skipActivity(context, GeneralDetailsActivity.class);
            }
        });*/

        if (holder.mRecyclerView.getAdapter()==null){
            holder.mRecyclerView.setAdapter(new ChildInfoAdapter(context, orderBean.get(position).getOrder_goods(),orderBean.get(position).getOrder().getOrder_id()));
        }else {
            holder.mRecyclerView.getAdapter().notifyDataSetChanged();
        }



    }

    @Override
    public int getItemCount() {
        return orderBean.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_orderList_orderNum)
        TextView itemOrderListOrderNum;
        @BindView(R.id.item_orderList_stateText)
        TextView itemOrderListStateText;
        @BindView(R.id.item_orderList_Total_quantity)
        TextView itemOrderListTotalQuantity;
        @BindView(R.id.item_orderList_Total_price)
        TextView itemOrderListTotalPrice;
        @BindView(R.id.item_orderList_cancel)       //取消订单
                TextView itemOrderListCancel;
        @BindView(R.id.item_orderList_Pay)          //立即支付
                TextView itemOrderListPay;
        @BindView(R.id.item_orderList_Commit)       //确认收货
                TextView itemOrderListCommit;
        @BindView(R.id.recyclerview)
        RecyclerView mRecyclerView;

        public GetWholeOrderBean.DataBean mItem1;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
            RecyclerView.LayoutManager manager=new LinearLayoutManager(view.getContext());
            manager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(manager);
        }
    }

}





