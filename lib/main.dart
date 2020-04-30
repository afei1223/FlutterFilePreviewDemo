import 'package:file_preview_demo/yiyun_file_preview.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(new MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
          appBar: new AppBar(
            title: new Text('FilePreviewDemo'),
          ),
          body: new Center(
            child: new Column(
              children: <Widget>[
                new Container(
                  child: new MaterialButton(
                      onPressed: persiomReadFile, child: new Text("filePreview")),
                  padding: const EdgeInsets.all(8.0),
                ),
              ],
            ),
          )),
    );
  }

  //根据路径打开文件。
  Future dialog() async{
    //路径改为自己的路径
    String path2 = "/storage/emulated/0/Download/test.docx";
    await YiyunFilePreview.fileRead(path2);
  }

  //判断是否有文件读取权限，若无，显示文件打开失败
  Future persiomReadFile() async{
//    bool res = await YiyunFilePreview.persiomReadFile();
    if(await YiyunFilePreview.persiomReadFile()){
      dialog();
    }else{
      print("没有文件读取权限");
      await YiyunFilePreview.persionReadFileRequest();
    }
  }



}