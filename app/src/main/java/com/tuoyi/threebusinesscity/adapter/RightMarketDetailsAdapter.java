package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.SupermarketCateringDetailsActivity;
import com.tuoyi.threebusinesscity.bean.RightMarketDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by md
 * on 2017/12/6.
 */

public class RightMarketDetailsAdapter extends RecyclerView.Adapter<RightMarketDetailsAdapter.ViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    private int secret = 0;
    private String title = "";
    private Context context;
    private List<RightMarketDetailsBean> marketDetailsRightBean = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnAddItemClickListener mOnAddItemClickListener;
    private OnRemoveItemClickListener mOnRemoveItemClickListener;
    private int count = 0;
    public RightMarketDetailsAdapter(Context context) {
        this.context = context;
    }


    public void notifyAdapter(List<RightMarketDetailsBean> marketDetailsRightBean, boolean isAdd) {
        if (!isAdd) {
            this.marketDetailsRightBean = marketDetailsRightBean;
        } else {
            this.marketDetailsRightBean.addAll(marketDetailsRightBean);
        }
        notifyDataSetChanged();
    }

    public List<RightMarketDetailsBean> getMyLiveList() {
        if (marketDetailsRightBean == null) {
            marketDetailsRightBean = new ArrayList<>();
        }
        return marketDetailsRightBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_details_right, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return marketDetailsRightBean.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = marketDetailsRightBean.get(holder.getAdapterPosition());
//        holder.mTvTitle.setText(holder.mItem.getTitle());
        holder.mAccount.setText(holder.mItem.getNum()+"");
        holder.mMoney.setText(holder.mItem.getMoney());
//        Glide.with(context).load(IMGS + holder.mItem.getImg()).error(R.drawable.s_img).placeholder(R.drawable.s_img).into(holder.mImg);

            holder.mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mAccount.setText(holder.mItem.getNum()+"");
                    int mCount = Integer.parseInt(holder.mAccount.getText().toString());
                    count = mCount;
                    count = count + 1;
                    holder.mRemove.setEnabled(true);
                    holder.mAccount.setText(count + "");
                    mOnAddItemClickListener.onAddItemClickListener(holder.getAdapterPosition(), count, holder.mAccount, marketDetailsRightBean);
                }
            });
            holder.mRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mAccount.setText(holder.mItem.getNum()+"");
                    int mCount = Integer.parseInt(holder.mAccount.getText().toString());
                    count = mCount;
                    if (count >= 1) {
                        count = count - 1;
                        holder.mAccount.setText(count + "");
                    }
                    if (count <= 0) {
                        holder.mRemove.setEnabled(false);
                    } else {
                        holder.mRemove.setEnabled(true);
                    }
                    mOnRemoveItemClickListener.onRemoveItemClickListener(holder.getAdapterPosition(), count, holder.mAccount, marketDetailsRightBean);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), marketDetailsRightBean);
                }
            });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<RightMarketDetailsBean> mRightBean);
    }

    public void setOnAddClickListener(OnAddItemClickListener onItemAddClickListener) {
        this.mOnAddItemClickListener = onItemAddClickListener;
    }

    public interface OnAddItemClickListener {
        void onAddItemClickListener(int pos, int count, TextView view, List<RightMarketDetailsBean> mRightBean);
    }

    public void setOnRemoveClickListener(OnRemoveItemClickListener onItemRemoveClickListener) {
        this.mOnRemoveItemClickListener = onItemRemoveClickListener;
    }

    public interface OnRemoveItemClickListener {
        void onRemoveItemClickListener(int pos, int count, TextView view, List<RightMarketDetailsBean> mRightBean);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_market_deyailsRight_img)
        ImageView mImg;
        @BindView(R.id.item_market_deyailsRight_money)
        TextView mMoney;
        @BindView(R.id.item_market_deyailsRight_remove)
        ImageView mRemove;
        @BindView(R.id.item_market_deyailsRight_account)
        TextView mAccount;
        @BindView(R.id.item_market_deyailsRight_add)
        ImageView mAdd;
        @BindView(R.id.item_market_deyailsRight_title)
        TextView mTvTitle;

        private final View mView;
        public RightMarketDetailsBean mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);

        }
    }


}
