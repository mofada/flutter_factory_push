import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/13 10:25
/// @description : input your description

class InputDialog extends StatefulWidget {
  @override
  _InputDialogState createState() => _InputDialogState();
}

class _InputDialogState extends State<InputDialog> {
  String _input;

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text("设置别名"),
      content: TextField(
        onChanged: (value) => _input = value,
      ),
      actions: [
        FlatButton(
          onPressed: () => Navigator.of(context).pop(),
          child: Text(
            "取消",
            style: TextStyle(color: Colors.amber),
          ),
        ),
        FlatButton(
          onPressed: () => Navigator.of(context).pop(_input),
          child: Text(
            "确定",
            style: TextStyle(color: Colors.amber),
          ),
        )
      ],
    );
  }
}
