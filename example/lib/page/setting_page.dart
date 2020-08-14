import 'package:factory_push/factory_push.dart';
import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/14 14:41
/// @description : input your description

class SettingPage extends StatefulWidget {
  @override
  _SettingPageState createState() => _SettingPageState();
}

class _SettingPageState extends State<SettingPage> {
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  ///暂停推送, 这里简单示例, 因为厂商没有提供判断的api, 所以当值更改以后, 建议存储 [SharedPreferences]
  bool _pausePush = false;
  bool _resumePush = true;

  ///是否启用推送, 这里简单示例, 因为厂商没有提供判断的api, 所以当值更改以后, 建议存储 [SharedPreferences]
  bool _enablePush = true;
  bool _disablePush = false;

  ///设置推送时间
  TimeOfDay _startTime = TimeOfDay(hour: 0, minute: 0);
  TimeOfDay _endTime = TimeOfDay(hour: 23, minute: 59);

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: Text("推送设置"),
      ),
      body: Column(
        children: [
          SwitchListTile(
            title: Text("恢复推送"),
            value: _resumePush,
            onChanged: (value) {
              if (value) FactoryPush.resumePush();

              setState(() {
                _pausePush = !value;
                _resumePush = value;
              });
            },
          ),
          SwitchListTile(
            title: Text("暂停推送"),
            value: _pausePush,
            onChanged: (value) {
              if (value) FactoryPush.pausePush();

              setState(() {
                _pausePush = value;
                _resumePush = !value;
              });
            },
          ),
          Divider(),
          SwitchListTile(
            title: Text("启用推送"),
            value: _enablePush,
            onChanged: (value) {
              if (value) FactoryPush.pausePush();

              setState(() {
                _enablePush = value;
                _disablePush = !value;
              });
            },
          ),
          SwitchListTile(
            title: Text("禁用推送"),
            value: _disablePush,
            onChanged: (value) {
              if (value) FactoryPush.pausePush();

              setState(() {
                _disablePush = value;
                _enablePush = !value;
              });
            },
          ),
          Divider(),
          ListTile(
            title: Text(
              "推送时间",
            ),
            trailing: Text(
                "${_startTime.format(context)} - ${_endTime.format(context)}"),
            onTap: setPushTime,
          )
        ],
      ),
    );
  }

  ///设置允许推送的时间
  void setPushTime() async {
    final startTime = await showTimePicker(
      context: context,
      initialTime: TimeOfDay.now(),
      builder: (context, child) => MediaQuery(
          data: MediaQuery.of(context).copyWith(alwaysUse24HourFormat: true),
          child: child),
      helpText: "开始时间",
      confirmText: "确定",
      cancelText: "取消",
    );
    if (startTime == null) return;
    final endTime = await showTimePicker(
      builder: (context, child) => MediaQuery(
          data: MediaQuery.of(context).copyWith(alwaysUse24HourFormat: true),
          child: child),
      context: context,
      initialTime: TimeOfDay.now(),
      helpText: "结束时间",
      confirmText: "确定",
      cancelText: "取消",
    );
    if (endTime == null) return;

    //如果开始时间为0, 并且结束时间为0, 实际上就是暂停了推送, 这里只是为了界面, 实际api不需要操作, 参考文档
    if (startTime.hour == 0 &&
        startTime.minute == 0 &&
        endTime.hour == 0 &&
        endTime.minute == 0) {
      _pausePush = true;
      _resumePush = false;
    }

    //如果开始时间为00:00, 结束时间是23:59, 那么就相当于恢复推送, 这里只是为了界面, 实际api不需要操作, 参考文档
    if (startTime.hour == 0 &&
        startTime.minute == 0 &&
        endTime.hour == 23 &&
        endTime.minute == 59) {
      _pausePush = false;
      _resumePush = true;
    }

    setState(() {
      _startTime = startTime;
      _endTime = endTime;
    });

    FactoryPush.setPushTime(
        startTime.hour, startTime.minute, endTime.hour, endTime.minute);
  }
}
