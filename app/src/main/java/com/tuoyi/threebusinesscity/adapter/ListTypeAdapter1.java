package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andview.refreshview.utils.LogUtils;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.OnLineGoodsDetailsBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/3 14:15
 * @描述 ${商品详情分类适配器}
 */

public class ListTypeAdapter1 extends BaseAdapter {

    private List<OnLineGoodsDetailsBean.DataBean.OptionsBean> list;
    private List<OnLineGoodsDetailsBean.DataBean.OptionsBean.GoodsOptionValueBean> list2;
    private LayoutInflater                                    inflater;
    private Context                                           mContext;
    private RadioButtonClickListener                          radioButtonClickListener;
    private OnLineGoodsDetailsBean.DataBean.OptionsBean mOptionsBean;
    private OnLineGoodsDetailsBean.DataBean.OptionsBean.GoodsOptionValueBean valueBean;
    private int pos;

    public ListTypeAdapter1(Context context, List<OnLineGoodsDetailsBean.DataBean.OptionsBean> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.mContext = context;


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

  /*  @Override
    public int getItemViewType(int position) {
        if ("radio".equals(list.get(position).getType())) {//当前JavaBean对象的类型
            return 0;//单选
        } else if ("checkbox".equals(list.get(position).getType())) {
            return 1;//多选
        } else {
            return 100;
        }


    }*/

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //int currentType = getItemViewType(position);//当前类型
        //if (currentType == 0) {//单选mAddCar
        pos=position;
        final radioViewHolder radioViewHolder;
        if (convertView == null) {
            radioViewHolder = new radioViewHolder();
            convertView = inflater.inflate(R.layout.item_radio, parent, false);
            radioViewHolder.mTextView = convertView.findViewById(R.id.tv_radio);
            //radioViewHolder.radioGroup = convertView.findViewById(R.id.FlowRadioGroup);
            radioViewHolder.mTv_state = convertView.findViewById(R.id.tv_state);
            radioViewHolder.mFlowlayout = convertView.findViewById(R.id.search_page_flowlayout);

            convertView.setTag(radioViewHolder);
        } else {
            radioViewHolder = (radioViewHolder) convertView.getTag();
        }
        LogUtils.e("2222222222" + list.get(position).getRequired());
        if (1 == list.get(position).getRequired()) {
            LogUtils.e("333333333");
            radioViewHolder.mTv_state.setVisibility(View.VISIBLE);
        } else {
            radioViewHolder.mTv_state.setVisibility(View.GONE);
            LogUtils.e("3444444444444");
        }
        radioViewHolder.mTextView.setText(list.get(position).getName());
       // addview(radioViewHolder.radioGroup, position);
        // }else {
        mOptionsBean = new OnLineGoodsDetailsBean.DataBean.OptionsBean();
        mOptionsBean = list.get(pos);
        list2=new ArrayList<>();
        for (int i = 0; i < mOptionsBean.getGoods_option_value().size(); i++) {
            valueBean = new OnLineGoodsDetailsBean.DataBean.OptionsBean.GoodsOptionValueBean();
            list2.add(mOptionsBean.getGoods_option_value().get(i));
        }

        final int i = position;
        radioViewHolder.mFlowlayout.setAdapter(new TagAdapter(list2) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) inflater.inflate(R.layout.item_flowlayout,
                        radioViewHolder.mFlowlayout, false);


                tv.setText(list2.get(position).getName());
                return tv;

            }
        });
        //}
        radioViewHolder.mFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

               Log.e("qqqqqqqq",selectPosSet.iterator().next()+"");

//                for (int j = 0; j < list.size(); j++) {
//
//                }
                radioButtonClickListener.OnRadioButtonClickListener(list.get(position), selectPosSet.iterator().next());
            }
        });
//       final int i = position;
//        radioViewHolder.mFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                radioButtonClickListener.OnRadioButtonClickListener(list.get(i), position);
//                return false;
//            }
//        });


        return convertView;
    }

    /**
     * 单选的Holder
     */
    class radioViewHolder {
        TextView       mTextView;       //分类名称
        TagFlowLayout mFlowlayout;
        TextView       mTv_state;             //是否必选  “*”
    }

    /**
     * 多选的Holder
     */
    class TeacherViewHolder {
        TextView tv1;
    }

    public void setNotfiySmallList(OnLineGoodsDetailsBean.DataBean.OptionsBean smallList){
        this.mOptionsBean = smallList;
        notifyDataSetChanged();
    }


    public void setRadioButtonClickListener(RadioButtonClickListener radioButtonClickListener) {
        this.radioButtonClickListener = radioButtonClickListener;
    }

    public interface RadioButtonClickListener {
        void OnRadioButtonClickListener( OnLineGoodsDetailsBean.DataBean.OptionsBean detail, int position);
    }


}
