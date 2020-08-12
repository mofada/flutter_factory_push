import 'package:factory_push/factory_push.dart';
import 'package:factory_push_example/widgets/tip_button.dart';
import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/12 15:53
/// @description : input your description

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String _platformVersion = 'Unknown';

  ///当前是否打开通知栏
  bool _isOpenNotification = false;

  @override
  void initState() {
    super.initState();

    initPlatformState();

    initNotificationStatus();
  }

  ///初始化平台设置
  void initPlatformState() async {
    var __platformVersion = await FactoryPush.platformVersion;
    setState(() => _platformVersion = __platformVersion);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('厂商推送插件'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('当前运行在: $_platformVersion\n'),
            TipButton(
              tipMessage: "推送别名的增删改查",
              text: "别名设置",
              onPressed: () => Navigator.of(context).pushNamed("/alias"),
            ),
            TipButton(
              tipMessage: "推送标签/主题的增删改查",
              text: "标签设置",
              onPressed: () {},
            ),
            TipButton(
              tipMessage: "推送设置的暂停/恢复",
              text: "推送开关",
              onPressed: () {},
            ),
            TipButton(
              tipMessage: "查看推送过来的消息",
              text: "接收消息",
              onPressed: () {},
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          FactoryPush.openNotification();
        },
        tooltip: "根据通知状态显示不同图标, 点击前往设置界面",
        child: Icon(_isOpenNotification
            ? Icons.notifications_active
            : Icons.notifications_off),
      ),
    );
  }

  ///判断当前是否打开通知
  void initNotificationStatus() async {
    var __isOpenNotification = await FactoryPush.isNotificationEnabled();
    setState(() => _isOpenNotification = __isOpenNotification);
  }
}
