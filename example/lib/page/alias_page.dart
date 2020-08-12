import 'package:factory_push/factory_push.dart';
import 'package:factory_push_example/widgets/tip_button.dart';
import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/12 15:57
/// @description : input your description

class AliasPage extends StatefulWidget {
  @override
  _AliasPageState createState() => _AliasPageState();
}

class _AliasPageState extends State<AliasPage> {
  String _alias;

  @override
  void initState() {
    super.initState();

    initAlias();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("别名设置"),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text("推送别名的增删改查"),
            Text(
              _alias ?? "暂未设置别名",
              style: TextStyle(color: Colors.amber),
            ),
            TipButton(
              text: "添加别名",
              tipMessage: "设置推送别名",
              onPressed: () {},
            )
          ],
        ),
      ),
    );
  }

  void initAlias() async {
    List<dynamic> alias = await FactoryPush.getAllAlias();
    setState(() {
      _alias = alias.isEmpty ? null : alias[0];
    });
  }
}
