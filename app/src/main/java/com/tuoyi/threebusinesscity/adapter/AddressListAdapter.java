package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.AdressListBen;

import java.util.List;

public class AddressListAdapter extends BaseAdapter {
    private List<AdressListBen.DataBean> dataBeans;
    private LayoutInflater inflater;
    private Context mContext;
    private CheckInterface checkInterface;
    private EditInterface editInterface;
    private DeleteInterface deleteInterface;
    private boolean flag;

    public AddressListAdapter(Context context, List<AdressListBen.DataBean> list) {
        inflater = LayoutInflater.from(context);
        this.dataBeans = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_addresslist, null);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_phone = convertView.findViewById(R.id.tv_phone);
            viewHolder.tv_address = convertView.findViewById(R.id.tv_address);
            //viewHolder.default_address = convertView.findViewById(R.id.default_address);
            viewHolder.ll_delete = convertView.findViewById(R.id.ll_delete);
            viewHolder.ll_edit = convertView.findViewById(R.id.ll_edit);
            //viewHolder.select_img=convertView.findViewById(R.id.select_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(dataBeans.get(position).getName());
        viewHolder.tv_phone.setText(dataBeans.get(position).getTelephone());
        viewHolder.tv_address.setText(dataBeans.get(position).getProvince()+ dataBeans.get(position).getCity()+dataBeans.get(position).getCountry() +dataBeans.get(position).getAddress());
       /* if (position==0){
            viewHolder. select_img.setImageResource(R.mipmap.weixuanzhong);
        }*/
        viewHolder.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteInterface.delete(position);
            }
        });
        viewHolder.ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editInterface.edit(position);
            }
        });
       /* viewHolder.default_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInterface.check(position);
                if(flag){
                    viewHolder. select_img.setImageResource(R.mipmap.weixuanzhong);
                }else{
                    viewHolder.select_img.setImageResource(R.mipmap.xuanzhong);
                }
                flag = !flag;
            }
        });*/
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
       // LinearLayout default_address;
        LinearLayout ll_delete;
        LinearLayout ll_edit;
        //ImageView select_img;
    }

    public void setDeleteClickListener(DeleteInterface deleteClickListener) {
        this.deleteInterface = deleteClickListener;
    }
    public void setCheckClickListener(CheckInterface checkClickListener) {
        this.checkInterface = checkClickListener;
    }
    public void setEditClickListener(EditInterface editClickListener) {
        this.editInterface = editClickListener;
    }

    /**
     * 默认地址
     */
    public interface CheckInterface {
        void check(int position);
    }

    /**
     * 编辑
     */
    public interface EditInterface {
        void edit(int position);
    }

    /**
     * 删除
     */
    public interface DeleteInterface {
        void delete(int position);
    }
}
