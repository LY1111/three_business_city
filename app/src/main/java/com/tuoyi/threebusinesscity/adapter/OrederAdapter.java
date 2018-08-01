package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.PaymentActivity;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.GetWholeOrderBean;
import com.tuoyi.threebusinesscity.bean.OnLineShopAdBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.vondear.rxtools.view.RxToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

public class OrederAdapter extends RecyclerView.Adapter<OrederAdapter.ViewHolder> {

    private Context context;
    private List<GetWholeOrderBean.DataBean> orderBean;
    private OnItemCancleClickListener mOnCancleClickListener;
    private OnItemConfirmClickListener mOnConfirmClickListener;
    private OnItemPayClickListener onItemPayClickListener;


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
        LogUtils.e(orderBean.size() + "");
        holder.mItem1 = orderBean.get(position);
        holder.itemOrderListOrderNum.setText(holder.mItem1.getOrder().getOrder_num_alias());                    //订单号

        holder.itemOrderListTotalPrice.setText(holder.mItem1.getOrder().getTotal());                            //订单总价格

        //订单状态
        // LogUtils.e("订单状态："+holder.mItem1.getOrder().getOrder_status_id()+"");
        if (holder.mItem1.getOrder().getOrder_status_id() == 1) {
            holder.itemOrderListStateText.setText("待发货");
            //holder.itemOrderListCancel.setVisibility(View.VISIBLE);  //取消订单
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 2) {
            holder.itemOrderListStateText.setText("已完成");
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 3) {
            holder.itemOrderListStateText.setText("待付款");
            holder.itemOrderListCancel.setVisibility(View.VISIBLE);  //取消订单
            holder.itemOrderListPay.setVisibility(View.VISIBLE);     //立即支付
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 4) {
            holder.itemOrderListStateText.setText("已发货");
            holder.itemOrderListCommit.setVisibility(View.VISIBLE);     //确认收货
        } else if (holder.mItem1.getOrder().getOrder_status_id() == 5) {
            holder.itemOrderListStateText.setText("已取消");
        }
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                UserBean.setPosID(context,String.valueOf(holder.mItem.getUid()));
////                RxActivityTool.skipActivity(context, GeneralDetailsActivity.class);
//
//                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
//            }
//        });
        final int pos = holder.getLayoutPosition();
        //取消订单
        if (mOnCancleClickListener != null) {
            holder.itemOrderListCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnCancleClickListener.onItemClick(holder.itemOrderListCancel, pos);
                }
            });
        }

        //确认支付
        if (mOnConfirmClickListener != null) {
            holder.itemOrderListCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnConfirmClickListener.onItemClick(holder.itemOrderListCommit, pos);
                }
            });
        }
        //立即支付
        if (mOnConfirmClickListener != null) {
            holder.itemOrderListPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemPayClickListener.onItemClick(holder.itemOrderListPay, pos);
                }
            });
        }

        if (holder.mRecyclerView.getAdapter() == null) {
            holder.mRecyclerView.setAdapter(new ChildInfoAdapter(context, orderBean.get(position).getOrder_goods(), orderBean.get(position).getOrder().getOrder_id(), orderBean.get(position).getOrder().getOrder_status_id()));
        } else {
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
            RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
            manager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(manager);
        }
    }

    public void setmOnCancleClickListener(OnItemCancleClickListener listener) {
        this.mOnCancleClickListener = listener;
    }

    public void setmOnConfirmClickListener(OnItemConfirmClickListener listener) {
        this.mOnConfirmClickListener = listener;
    }

    public void setOnItemPayClickListener(OnItemPayClickListener listener) {
        this.onItemPayClickListener = listener;
    }

    public interface OnItemCancleClickListener {
        void onItemClick(View view, int positino);
    }

    public interface OnItemConfirmClickListener {
        void onItemClick(View view, int positino);
    }

    public interface OnItemPayClickListener {
        void onItemClick(View view, int positino);
    }
}





