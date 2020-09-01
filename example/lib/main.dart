import 'package:factory_push/factory_push.dart';
import 'package:factory_push_example/page/alias_page.dart';
import 'package:factory_push_example/page/home_page.dart';
import 'package:factory_push_example/page/setting_page.dart';
import 'package:factory_push_example/page/tag_page.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    ///尽量保证接收消息在初始化前面, 防止token接收不到, 这里为了demo演示, 接收消息放到home
//    FactoryPush.onPushReceiver(onEvent: (e) => print(e));

    ///初始化推送设置
    FactoryPush.setup(
      xiaomiAppId: "2882303761518576392",
      xiaomiAppKey: "5241857660392",
      huaweiAppId: "102736937",
      oppoAppKey: "8053e8d8bc7142e1aa7dbee0cb708b6f",
      oppoAppSecret: "7c92f5fc05ac4c57b01a50f43b81c6a8",
      vivoAppId: "104304633",
      vivoAppKey: "22ed1558311e52c8a212cbf4772a3acf",
      debugMode: true,
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(primarySwatch: Colors.amber),
      initialRoute: "/",
      routes: <String, WidgetBuilder>{
        "/": (context) => HomePage(),
        "/alias": (context) => AliasPage(),
        "/tag": (context) => TagPage(),
        "/setting": (context) => SettingPage(),
      },
    );
  }
}
