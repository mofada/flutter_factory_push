import 'package:flutter/material.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/12 17:59
/// @description : input your description
class TipButton extends StatelessWidget {
  final String tipMessage;
  final String text;
  final VoidCallback onPressed;

  const TipButton({Key key, this.tipMessage, this.text, this.onPressed})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Tooltip(
      message: tipMessage,
      child: FlatButton(
        onPressed: onPressed ?? null,
        color: Colors.amber,
        child: Text(text),
        materialTapTargetSize: MaterialTapTargetSize.padded,
      ),
    );
  }
}
