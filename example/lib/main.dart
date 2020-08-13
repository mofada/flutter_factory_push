import 'package:factory_push/factory_push.dart';
import 'package:factory_push_example/page/alias_page.dart';
import 'package:factory_push_example/page/home_page.dart';
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

    ///初始化推送设置
    FactoryPush.setup(
        xiaomiAppId: "2882303761518576392", xiaomiAppKey: "5241857660392");
    FactoryPush.setDebugMode(true);
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
      },
    );
  }
}
