import 'package:factory_push/factory_push.dart';
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

  String _manufacturer;

  GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  List<PushMessageBean> _messages = [];

  @override
  void initState() {
    super.initState();

    initNotificationStatus();

    initManufacturer();

    ///当接收到消息的时候
    FactoryPush.onPushReceiver(
        onMessageReceiver: _onEvent, onNotificationClicked: _onEvent);
  }

  void _onEvent(PushMessageBean messageBean) {
    _messages.add(messageBean);
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: const Text('厂商推送插件'),
      ),
      body: _messages.isEmpty
          ? Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Text(
                      "目前暂无消息",
                      style: TextStyle(color: Colors.amber, fontSize: 24),
                    ),
                  ),
                  Text(
                    "请前往开发者后台推送消息吧!",
                    style: TextStyle(color: Colors.amber, fontSize: 20),
                  ),
                ],
              ),
            )
          : ListView.separated(
              itemBuilder: (BuildContext context, int index) => ListTile(
                    title: Text(_messages[index].title),
                  ),
              separatorBuilder: (BuildContext context, int index) => Divider(),
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
                  Row(
                    children: [
                      Container(
                        alignment: Alignment.center,
                        decoration: BoxDecoration(
                          color: Colors.amberAccent,
                          borderRadius: BorderRadius.circular(25),
                        ),
                        width: 50,
                        height: 50,
                        child: Text(
                          _manufacturer == null
                              ? "?"
                              : _manufacturer.substring(0, 1),
                          style: TextStyle(color: Colors.amber, fontSize: 30),
                          strutStyle:
                              StrutStyle(fontSize: 30, forceStrutHeight: true),
                        ),
                      ),
                      SizedBox(
                        width: 15,
                      ),
                      Text(
                        "$_manufacturer手机",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 40,
                            fontWeight: FontWeight.bold),
                        strutStyle:
                            StrutStyle(fontSize: 40, forceStrutHeight: true),
                      ),
                    ],
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
              onTap: () => Navigator.of(context).pushNamed("/setting"),
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

  ///获取手机厂商
  initManufacturer() async {
    var manufacturer = await FactoryPush.manufacturer();
    switch (manufacturer) {
      case Manufacturer.XIAOMI:
        _manufacturer = "小米";
        break;
      case Manufacturer.HUAWEI:
        _manufacturer = "华为";
        break;
      case Manufacturer.OPPO:
        _manufacturer = "OPPO";
        break;
      case Manufacturer.VIVO:
        _manufacturer = "VIVO";
        break;
      case Manufacturer.MEIZU:
        _manufacturer = "魅族";
        break;
      case Manufacturer.OTHER:
        _manufacturer = "其他";
        break;
    }
    setState(() {});
  }

  ///判断当前是否打开通知
  void initNotificationStatus() async {
    var __isOpenNotification = await FactoryPush.isNotificationEnabled();
    setState(() => _isOpenNotification = __isOpenNotification);
  }
}
