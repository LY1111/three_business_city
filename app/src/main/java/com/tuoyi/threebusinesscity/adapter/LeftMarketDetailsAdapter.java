package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.LeftMarketDetailsBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by md
 * on 2018年3月7日16:54:41
 * 餐饮超市左侧分类适配器
 */
public class LeftMarketDetailsAdapter extends RecyclerView.Adapter<LeftMarketDetailsAdapter.ViewHolder> {


    private Context context;
    private List<LeftMarketDetailsBean> mdatas;
    private Map<Integer, Boolean> map = new HashMap<>();
    private boolean onBind;
    private String feedBackName;
    private int feedBackNum;
    private int selectPos = 0;

    public LeftMarketDetailsAdapter(List<LeftMarketDetailsBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_market_details_left, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);

        if (selectPos == holder.getAdapterPosition()) {
            holder.itemOrderLeftName.setBackgroundColor(context.getResources().getColor(R.color.whiteColor));
//            holder.itemOrderLeftName.setTextColor(context.getResources().getColor(R.color.mainColor));
        } else {
            holder.itemOrderLeftName.setBackgroundColor(context.getResources().getColor(R.color.whiteTwoColor));
//            holder.itemOrderLeftName.setTextColor(context.getResources().getColor(R.color.GrayColor));
        }

        holder.itemOrderLeftName.setText(holder.mItem.getTitle());

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        holder.itemOrderLeftName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    map.clear();
//                    map.put(position, true);
//                    feedBackName = buttonView.getText().toString();
//                    feedBackNum = position;
//                } else {
//                    map.remove(position);
//                }
//                if (!onBind) {
//                    notifyDataSetChanged();
//                }
//            }
//        });
//        onBind = true;
//        if (map != null && map.containsKey(position)) {
//            holder.itemOrderLeftName.setChecked(true);
//        } else {
//            holder.itemOrderLeftName.setChecked(false);
//        }
//        onBind = false;
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_market_deyails_left_title)
        RadioButton itemOrderLeftName;
        public LeftMarketDetailsBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }

}