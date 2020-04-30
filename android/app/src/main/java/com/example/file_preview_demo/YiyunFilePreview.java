package com.example.file_preview_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tencent.tbs.reader.TbsFileInterfaceImpl;
import com.tencent.tbs.reader.TbsReaderView;

import java.io.File;

import io.flutter.Log;

public class YiyunFilePreview {
    private static TbsFileInterfaceImpl tbs;
    private static TbsReaderView tbsReaderView1;

    public static void openFileReader(String path, Activity mActivity) {
        if(IfFileExists(path,mActivity)){

            tbs.initEngine(mActivity);
            //        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() ;
            //        Log.i("TAG",sdcard);
            String[] qwe = path.split("\\.");
            String last = "";
            if(qwe.length>1){
                last = qwe[qwe.length-1];
                if(!tbs.canOpenFile(last)){
                    Toast.makeText(mActivity, "暂不支持 "+last+" 文件 ", Toast.LENGTH_SHORT).show();
                    Log.i("TAG","cannot");
                    return;
                }else{
                    Bundle param = new Bundle();
                    param.putString("filePath", path);
                    param.putString("fileExt",last);
                    Log.i("TAG","path  "+path+" "+last);
                    FrameLayout rootlayout = (FrameLayout) mActivity.getWindow().getDecorView();
                    loadReaderView(path,rootlayout,mActivity);
                }
            }
        }

    }

    private static boolean IfFileExists(String path, Activity mActivity) {
        try{
            File f = new File(path);
            if(f.exists()){
                return true;
            }else{
                Toast.makeText(mActivity, "文件不存在或路径错误", Toast.LENGTH_SHORT).show();
                return false;
            }

        }catch (Exception e){
            Log.i("TAG", String.valueOf(e));
            Toast.makeText(mActivity, "异常操作("+e+")", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    static void loadReaderView(String fileName, FrameLayout rootlayout,Activity mActivity)
    {
        String[] items = fileName.split("\\.") ;
        String extName = "";
        if(items.length > 1)
        {
            extName = items[items.length - 1].toLowerCase();
            if(!TbsReaderView.isSupportExt(mActivity, extName))
            {
                Toast.makeText(mActivity, "ext "+extName+" not supported", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        lp.topMargin = 70;
        TbsReaderView readerView = new TbsReaderView(mActivity, new TbsReaderView.ReaderCallback()
        {
            @Override
            public void onCallBackAction(Integer type, Object o, Object o1)
            {
                Log.i("TAG","back?");
            }
        });


        Bundle param = new Bundle();
        param.putString("fileExt",extName);
        param.putString("filePath",fileName);
        if (readerView.preOpen(extName, false))
        {
            readerView.openFile(param);
        }
        tbsReaderView1 = readerView;
        rootlayout.addView(tbsReaderView1, lp);
    }

    public static void destory() {
        Log.i("TAG","des");
        tbsReaderView1.onStop();
    }
}
