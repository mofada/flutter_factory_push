import 'package:factory_push/factory_push.dart';
import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/19 9:54
/// @description : input your description
class DetailPage extends StatelessWidget {
  final PushMessageBean messageBean;

  const DetailPage({Key key, @required this.messageBean}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final items = [];
    //标题
    items.add({"title": "消息Id", "trailing": messageBean.messageId});
    items.add({"title": "消息类型", "trailing": messageBean.messageType});
    items.add({"title": "消息标题", "trailing": messageBean.title});
    items.add({"title": "消息内容", "trailing": messageBean.message});
    items.add({"title": "消息类别", "trailing": messageBean.category});
    items.add({"title": "通知id", "trailing": messageBean.notifyId.toString()});
    items.add({"title": "额外字段", "trailing": ""});
    if (messageBean.extra == null)
      items.add({"title": "", "trailing": "--"});
    else
      items.addAll(messageBean.extra
          .map((key, value) =>
              MapEntry(key, {"title": "", "subTitle": key, "trailing": value}))
          .values);

    return Scaffold(
      appBar: AppBar(
        title: Text(messageBean.title ?? "消息详情"),
      ),
      body: ListView.separated(
          itemBuilder: (BuildContext context, int index) => ListTile(
                title: Text(
                    "${items[index]["title"]}  ${items[index]["subTitle"] ?? ""}"),
                trailing: Text(items[index]["trailing"] ?? "--"),
              ),
          separatorBuilder: (BuildContext context, int index) => Divider(),
          itemCount: items.length),
    );
  }
}
