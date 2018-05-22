package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.MainGridMenuBean;
import com.tuoyi.threebusinesscity.fragment.MainFragment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * Created by md
 * on 2018/3/3 0003.
 * 线下首页菜单适配器
 */

public class MainGridMenuAdapter extends RecyclerView.Adapter<MainGridMenuAdapter.ViewHolder> {

    private Context context;
    private List<MainGridMenuBean.DataBean> mdatas;
    private int selectPos = 0;
    private boolean selectType;

    private Map<Integer, Boolean> map = new HashMap<>();
    private boolean onBind;
    private MyOnItemClickListener itemClickListener;

    public MainGridMenuAdapter(List<MainGridMenuBean.DataBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_grid_menu, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.itemTitle.setText(holder.mItem.getName());
        Glide.with(context).load(IMGS + holder.mItem.getImage()).into(holder.itemIcon);
        if (selectType) {
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (map != null && map.containsKey(position)) {
//                    map.remove(position);
                    } else {
                        map.clear();
                        map.put(position, true);
                        itemClickListener.OnItemClickListener(position);
                    }
                    if (!onBind) {
                        notifyDataSetChanged();
                    }

                }
            });
        }else {
            map.clear();
            map.put(0, true);
            itemClickListener.OnItemClickListener(0);
            selectType = true;
        }

        onBind = true;

        if (map != null && map.containsKey(position)) {
//            holder.itemTitle.setChecked(true);
            selectPos = position;
            holder.itemTitle.setTextColor(context.getResources().getColor(R.color.colorPrimary));

        } else {
            map.remove(position);
//            holder.itemTitle.setChecked(false);
            holder.itemTitle.setTextColor(context.getResources().getColor(R.color.gray_2));
        }
        onBind = false;

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }

    public int getSelectPos() {
        return selectPos;
    }

    /**
     * 列表点击事件
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * item点击接口
     */
    public interface MyOnItemClickListener {
        void OnItemClickListener(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_main_grid_menu_name)
        TextView itemTitle;
        @BindView(R.id.item_main_grid_menu_icon)
        ImageView itemIcon;
        private MainGridMenuBean.DataBean mItem;
        private final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
