import 'package:factory_push/factory_push.dart';
import 'package:factory_push/bean/push_message_bean.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/12 15:53
/// @description : input your description

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  ///当前是否打开通知栏
  bool _isOpenNotification = false;

  GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  List<PushMessageBean> _messages = [];

  @override
  void initState() {
    super.initState();

    initNotificationStatus();

    ///当接收到消息的时候
    FactoryPush.onPushReceiver((message) {
      print(message.toString());
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: const Text('厂商推送插件'),
      ),
      body: ListView.separated(
          itemBuilder: (BuildContext context, int index) {},
          separatorBuilder: (BuildContext context, int index) {},
          itemCount: _messages.length),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          FactoryPush.openNotification();
        },
        tooltip: "根据通知状态显示不同图标, 点击前往设置界面",
        child: Icon(_isOpenNotification
            ? Icons.notifications_active
            : Icons.notifications_off),
      ),
      drawer: Drawer(
        child: ListView(
          children: [
            DrawerHeader(
              decoration: BoxDecoration(
                color: Colors.amber,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Text(
                    "厂商推送插件",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 30,
                    ),
                  ),
                  Text("国内厂商推送（小米/华为/魅族/ vivo / oppo）和极光推送")
                ],
              ),
            ),
            ListTile(
              title: Text("别名设置"),
              leading: Icon(Icons.healing),
              onTap: () => Navigator.of(context).pushNamed("/alias"),
            ),
            ListTile(
              title: Text("标签设置"),
              leading: Icon(Icons.tag_faces),
              onTap: () => Navigator.of(context).pushNamed("/tag"),
            ),
            ListTile(
              title: Text("推送设置"),
              leading: Icon(Icons.settings),
              onTap: () => Navigator.of(context).pushNamed("/alias"),
            ),
            ListTile(
              title: Text("客户端ID"),
              leading: Icon(Icons.account_circle),
              onTap: () async {
                //获取
                var registrationId = await FactoryPush.getRegistrationId();
                Clipboard.setData(ClipboardData(text: registrationId));
                //提示框
                _scaffoldKey.currentState
                    .showSnackBar(SnackBar(content: Text("客户端ID已复制到粘贴板")));
                //关闭抽屉
                Navigator.of(context).pop();
              },
            ),
          ],
        ),
      ),
    );
  }

  ///判断当前是否打开通知
  void initNotificationStatus() async {
    var __isOpenNotification = await FactoryPush.isNotificationEnabled();
    setState(() => _isOpenNotification = __isOpenNotification);
  }
}
