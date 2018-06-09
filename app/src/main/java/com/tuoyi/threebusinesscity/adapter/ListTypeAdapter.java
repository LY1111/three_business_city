package com.tuoyi.threebusinesscity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.andview.refreshview.utils.LogUtils;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.OnLineGoodsDetailsBean;
import com.tuoyi.threebusinesscity.widget.FlowRadioGroup;

import java.util.List;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/3 14:15
 * @描述 ${商品详情分类适配器}
 */

public class ListTypeAdapter extends BaseAdapter {

    private List<OnLineGoodsDetailsBean.DataBean.OptionsBean> list;
    private LayoutInflater                                    inflater;
    private Context                                           mContext;
    private RadioButtonClickListener                          radioButtonClickListener;
    private OnLineGoodsDetailsBean.DataBean.OptionsBean mOptionsBean;
    private OnLineGoodsDetailsBean.DataBean.OptionsBean.GoodsOptionValueBean valueBean;

    public ListTypeAdapter(Context context, List<OnLineGoodsDetailsBean.DataBean.OptionsBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //int currentType = getItemViewType(position);//当前类型
        //if (currentType == 0) {//单选mAddCar
        radioViewHolder radioViewHolder;
        if (convertView == null) {
            radioViewHolder = new radioViewHolder();
            convertView = inflater.inflate(R.layout.item_radio, parent, false);
            radioViewHolder.mTextView = convertView.findViewById(R.id.tv_radio);
          //  radioViewHolder.radioGroup = convertView.findViewById(R.id.FlowRadioGroup);
            radioViewHolder.mTv_state = convertView.findViewById(R.id.tv_state);

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
        addview(radioViewHolder.radioGroup, position);
        // }else {

        //}
        return convertView;
    }

    /**
     * 单选的Holder
     */
    class radioViewHolder {
        TextView       mTextView;       //分类名称
        FlowRadioGroup radioGroup;
        TextView       mTv_state;             //是否必选  “*”
    }

    /**
     * 多选的Holder
     */
    class TeacherViewHolder {
        TextView tv1;
    }

    //动态添加视图
    @SuppressLint("ResourceAsColor")
    public void addview(FlowRadioGroup radiogroup, final int postion) {
        radiogroup.removeAllViews();
        radiogroup.setOrientation(LinearLayout.HORIZONTAL);
        mOptionsBean = new OnLineGoodsDetailsBean.DataBean.OptionsBean();
        mOptionsBean = list.get(postion);
        for (int i = 0; i < mOptionsBean.getGoods_option_value().size(); i++) {
            valueBean = new OnLineGoodsDetailsBean.DataBean.OptionsBean.GoodsOptionValueBean();
            valueBean = mOptionsBean.getGoods_option_value().get(i);
            final RadioButton radioButton = new RadioButton(mContext);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, 50);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setButtonDrawable(null);
            radioButton.setText(valueBean.getName());
            radioButton.setId(View.generateViewId());
            radioButton.setTextSize(12);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setPadding(10, 10, 10, 10);
            radioButton.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.goods_radio_select));
            radioButton.setTextColor(mContext.getResources().getColor(R.color.select_goods_radio_color));
            final int finalI = i;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonView.setTextColor(0xffffffff);
                    }else {
                        buttonView.setTextColor(0xff000000);
                    }
                }
            });
            radioButton.setChecked(list.get(postion).getGoods_option_value().get(i).isCheck());
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButtonClickListener.OnRadioButtonClickListener(radioButton,list.get(postion), finalI);
                }
            });
            radiogroup.addView(radioButton);//将单选按钮添加到RadioGroup中

        }
    }
    public void setNotfiySmallList(OnLineGoodsDetailsBean.DataBean.OptionsBean smallList){
        this.mOptionsBean = smallList;
        notifyDataSetChanged();
    }


    public void setRadioButtonClickListener(RadioButtonClickListener radioButtonClickListener) {
        this.radioButtonClickListener = radioButtonClickListener;
    }

    public interface RadioButtonClickListener {
        void OnRadioButtonClickListener(RadioButton radioButton, OnLineGoodsDetailsBean.DataBean.OptionsBean detail, int position);
    }


}
