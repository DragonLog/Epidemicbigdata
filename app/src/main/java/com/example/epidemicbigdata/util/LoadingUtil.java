package com.example.epidemicbigdata.util;

import android.content.Context;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

public class LoadingUtil {

    public LoadingDialog initLoadingObj(Context context) {
        LoadingDialog loadingDialog = new LoadingDialog(context);
        loadingDialog.setLoadingText("加载中")
                .setSuccessText("加载成功")
                .setFailedText("加载失败")
                .closeSuccessAnim()
                .closeFailedAnim()
                .show();
        return loadingDialog;
    }

}
