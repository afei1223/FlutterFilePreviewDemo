import 'dart:async';

import 'package:flutter/services.dart';

class YiyunFilePreview {
  static const MethodChannel _channel =
      const MethodChannel('file_preview_demo');

  static Future<void> fileRead(String path) async {   //接受main.dart的参数
    //下面是把参数传递到底层
    await _channel.invokeMethod('fileRead',<String,dynamic>{
      'path':path,
    });
  }

  static Future<bool> persiomReadFile() async{
    final bool res1 = await _channel.invokeMethod('persiom_write_read');
    return res1;
  }

  static persionReadFileRequest() async{
    await _channel.invokeMethod('persiom_write_read_request');
  }
}