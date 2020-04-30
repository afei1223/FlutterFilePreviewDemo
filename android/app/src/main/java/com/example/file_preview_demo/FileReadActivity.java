package com.example.file_preview_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class FileReadActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        YiyunFilePreview.openFileReader(path,FileReadActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        YiyunFilePreview.destory();
    }
}
