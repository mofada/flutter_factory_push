import 'dart:async';

import 'package:factory_push/constant/argument_name.dart';
import 'package:factory_push/constant/channel_name.dart';
import 'package:factory_push/constant/method_name.dart';
import 'package:flutter/services.dart';

class FactoryPush {
  ///方法通道
  static const MethodChannel _channel =
      const MethodChannel(ChannelName.push_plugin);

  ///消息接收
  static const EventChannel _receiverEvent =
      const EventChannel(ChannelName.receiver_plugin);

  ///默认方法, 接收平台版本
  static Future<String> get platformVersion async {
    final String version =
        await _channel.invokeMethod(MethodName.getPlatformVersion);
    return version;
  }

  ///接收消息
  static void onPushReceiver<T>(void onData(T event),
      {Function onError, void onDone(), bool cancelOnError}) {
    _receiverEvent.receiveBroadcastStream().listen(onData,
        onError: onError, onDone: onDone, cancelOnError: cancelOnError);
  }

  ///初始化设置
  ///[xiaomiAppId] 在开发者网站上注册时生成的，MiPush推送服务颁发给app的唯一认证标识
  ///[xiaomiAppKey] 在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
  ///
  static void setup({String xiaomiAppId, String xiaomiAppKey}) {
    Map<String, dynamic> arguments = <String, dynamic>{};

    ///小米参数
    arguments[ArgumentName.xiaomiAppId] = xiaomiAppId;
    arguments[ArgumentName.xiaomiAppKey] = xiaomiAppKey;

    _channel.invokeMethod(MethodName.setup, arguments);
  }
}
