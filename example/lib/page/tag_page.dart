import 'package:factory_push/factory_push.dart';
import 'package:factory_push_example/widgets/input_dialog.dart';
import 'package:factory_push_example/widgets/tip_button.dart';
import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/12 15:57
/// @description : input your description

class TagPage extends StatefulWidget {
  @override
  _TagPageState createState() => _TagPageState();
}

class _TagPageState extends State<TagPage> {
  List<String> _tags;

  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  @override
  void initState() {
    super.initState();

    refreshTag();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: Text("标签设置"),
        actions: [
          IconButton(
            tooltip: "清除所有设置的标签",
            icon: Icon(Icons.clear_all),
            onPressed: () async {
              await FactoryPush.cleanTag();

              refreshTag();
            },
          )
        ],
      ),
      body: _tags == null || _tags.isEmpty
          ? Center(
              child: Text("暂未设置标签"),
            )
          : RefreshIndicator(
              onRefresh: refreshTag,
              child: ListView.separated(
                itemBuilder: (BuildContext context, int index) => Dismissible(
                  key: UniqueKey(),
                  onDismissed: (DismissDirection direction) async {
                    //删除标签
                    await FactoryPush.deleteTag(_tags[index]);
                    //移除列表
                    _tags.removeAt(index);
                    setState(() {});
                  },
                  confirmDismiss: (DismissDirection direction) async =>
                      direction != DismissDirection.startToEnd,
                  child: ListTile(
                    title: Text(
                      _tags[index],
                      style: TextStyle(color: Colors.amber),
                    ),
                  ),
                  background: Container(
                    color: Colors.green,
                    padding: EdgeInsets.only(left: 16),
                    alignment: Alignment.centerLeft,
                    child: Icon(
                      Icons.cancel,
                      color: Colors.white,
                    ),
                  ),
                  secondaryBackground: Container(
                    color: Colors.red,
                    padding: EdgeInsets.only(right: 16),
                    alignment: Alignment.centerRight,
                    child: Icon(
                      Icons.delete_forever,
                      color: Colors.white,
                    ),
                  ),
                ),
                separatorBuilder: (BuildContext context, int index) => Divider(
                  height: 1,
                ),
                itemCount: _tags.length,
              ),
            ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: FloatingActionButton(
        elevation: 0,
        onPressed: setAlias,
        child: Icon(Icons.add),
      ),
    );
  }

  ///设置别名
  setAlias() async {
    var alias =
        await showDialog(context: context, builder: (context) => InputDialog());
    if (alias == null) return;
    //设置别名
    await FactoryPush.addTag(alias);

    refreshTag();
  }

  ///初始化别名
  Future refreshTag() async {
    List<dynamic> alias = await FactoryPush.getAllTag();
    //List<dynamic> to List<String>
    /// 方式一
    //alias.map((e) => e as String).toList()
    /// 方式二 DON’T use cast() when a nearby operation will do.
    /// https://dart.dev/guides/language/effective-dart/usage#dont-use-cast-when-a-nearby-operation-will-do
    //List<String>.from(alias)/alias.cast<String>()

    setState(() => _tags = List<String>.from(alias));
  }
}
