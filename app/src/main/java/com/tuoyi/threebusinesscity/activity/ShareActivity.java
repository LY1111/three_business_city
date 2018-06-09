package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.ShareBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.QRCodeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tuoyi.threebusinesscity.util.MyUtil.saveImageToGallery;

/**
 * 分享推荐
 */
public class ShareActivity extends AppCompatActivity {

    @BindView(R.id.share_back)
    ImageView shareBack;
    @BindView(R.id.share_title)
    TextView shareTitle;
    @BindView(R.id.share_img)
    ImageView shareImg;
    @BindView(R.id.share_bt)
    Button shareBt;
    @BindView(R.id.share_btB)
    Button shareBtb;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        GetQRCodeLink();
    }


    //   获取二维码链接
    private void GetQRCodeLink() {
        OkGo.<String>post(Config.s + "api/AppProve/sharing_links")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        ShareBean shareBean = gson.fromJson(response.body(), ShareBean.class);
                        if (shareBean.getCode() == 200 ) {
                            String s = shareBean.getData().getSharing_links();
//                            bitmap = QRCodeUtil.createQRCodeBitmap(s, 50, myColor, Color.WHITE);
                            bitmap = QRCodeUtil.createQRCodeBitmap(s, 300);
                            shareImg.setImageBitmap(bitmap);
                        } else if (shareBean.getCode() == 200 ) {
                            Toast.makeText(ShareActivity.this, shareBean.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    @OnClick({R.id.share_back, R.id.share_bt, R.id.share_btB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_back:
                break;
            case R.id.share_bt:
                if (bitmap != null) {
                    saveImageToGallery(this, bitmap);
                } else {
                    Toast.makeText(this, "保存失败！请重新打开此页面", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.share_btB:
                initGetFile();
                break;
        }
    }

    private void initGetFile() {
        //获取外部存储
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        //判断sd卡是否挂载
        if (externalStorageDirectory.equals(Environment.MEDIA_MOUNTED)) {
            //如果有sd卡，则获取存放图片的文件夹路径
            String imageDirPath = Environment.getExternalStorageDirectory().toString() + File.separator
                    + "sszl";
            Log.d("share", imageDirPath);
            //
            File imgFile = new File(imageDirPath);
            //获取并分享二维码
            getQrAndShare(imgFile);
        } else {
            //如果没有sd卡，则获取内部存储路径
            File filesDir = getFilesDir();
            File imgFile = new File(filesDir.getAbsolutePath());
            getQrAndShare(imgFile);
        }
    }

    //这样会弹出系统的分享界面，然后进行分享就ok了
    private void getQrAndShare(File imgFile) {
        //获取到文件夹中所有的文件
        File[] files = imgFile.listFiles();
        //如果文件夹下没有文件，则提示去生成
        if (files.length == 0) {
            Toast.makeText(this, "请先在侧边栏二维码处生成二维码", Toast.LENGTH_SHORT).show();
        } else {
            //创建一个存放图片路径的集合
            List<String> imagePathList = new ArrayList<>();
            // 将所有的文件存入ArrayList中
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                imagePathList.add(file.getPath());
            }
            //获取图片路径,获取的永远是最后一张二维码
            int size = imagePathList.size();
            Log.d("share", "size:" + size);
            if (size != 0) {
                //获取最后一张图片的路径
                String mPath = imagePathList.get(size - 1);
                Log.d("share", "path:" + mPath);
                //转化为uri
                Uri imageUri = Uri.fromFile(new File(mPath));
                Log.d("share", "uri:" + imageUri);
                //创建Intent设置Action为ACTION_SEND，传入uri，设置type为image/*，开启Intent
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
            } else {
                //由文件得到uri
                Toast.makeText(this, "请先在侧边栏二维码处生成二维码", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
