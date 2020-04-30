package com.example.file_preview_demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
    /**
     * start
     * */
    //创建和flutter的消息通道
    MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "file_preview_demo");
    channel.setMethodCallHandler(
            new MethodChannel.MethodCallHandler() {
              @Override
              public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                if(call.method.equals("persiom_write_read")){
                  //获取权限
                  if(!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED))
                  {
                    result.success(false);
                  }else {
                    result.success(true);
                  }
                }
                if(call.method.equals("persiom_write_read_request")){
                  ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1101);
                }
                if (call.method.equals("fileRead")) {
                  String path= call.argument("path");
                  Intent intent = new Intent(MainActivity.this,FileReadActivity.class);
                  intent.putExtra("path",path);
                  MainActivity.this.startActivity(intent);
                }else {
                  result.notImplemented();
                }
              }
            }
    );

    /**
     * end
     * */
  }
}
