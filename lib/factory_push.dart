import 'dart:async';
import 'dart:convert';

import 'package:factory_push/constant/argument_name.dart';
import 'package:factory_push/constant/channel_name.dart';
import 'package:factory_push/constant/message_type.dart';
import 'package:factory_push/constant/method_name.dart';
import 'package:flutter/services.dart';

part "bean/push_message_bean.dart";

part "constant/manufacturer.dart";

///当接收到消息的时候
typedef OnMessageEvent = void Function(PushMessageBean messageBean);

///华为token事件
typedef OnTokenEvent = void Function(String token);

class FactoryPush {
  /// 方法通道
  static const MethodChannel _channel =
      const MethodChannel(ChannelName.push_plugin);

  /// 消息接收
  static const EventChannel _receiverEvent =
      const EventChannel(ChannelName.receiver_plugin);

  /// 默认方法, 接收平台版本
  static Future<String> get platformVersion async {
    final String version =
        await _channel.invokeMethod(MethodName.getPlatformVersion);
    return version;
  }

  /// 接收消息
  static void onPushReceiver<T>(
      {void onEvent(T event),
      OnMessageEvent onMessageReceiver,
      OnMessageEvent onNotificationClicked,
      OnTokenEvent onTokenReceiver,
      Function onError,
      void onDone(),
      bool cancelOnError}) {
    _receiverEvent.receiveBroadcastStream().listen((event) {
      //推送事件
      if (onEvent != null) onEvent(event);

      //消息类型
      final type = event["type"];
      //数据
      final data = event["data"];

      if (type == MessageType.token && onTokenReceiver != null) {
        onTokenReceiver(data);
      }
      //如果是接收消息并且方法不为空
      if (type == MessageType.messageReceiver && onMessageReceiver != null) {
        final message = PushMessageBean.fromJson(json.decode(data));
        //接收消息
        onMessageReceiver(message);
      }
      //如果接收的是点击消息, 并且点击方法不空
      if (type == MessageType.notificationClicked &&
          onNotificationClicked != null) {
        final message = PushMessageBean.fromJson(json.decode(data));
        //接收消息
        onNotificationClicked(message);
      }
    }, onError: onError, onDone: onDone, cancelOnError: cancelOnError);
  }

  ///初始化设置
  ///
  /// 小米: registerPush
  ///
  ///
  /// @param {String} [xiaomiAppId] 在开发者网站上注册时生成的，MiPush推送服务颁发给app的唯一认证标识
  /// @param {String} [xiaomiAppKey] 在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
  /// @param {String} [huaweiAppId] 华为的 appId
  /// @param {String} [oppoAppKey] oppo 的 oppoAppKey
  /// @param {String} [oppoAppSecret] oppo 的 oppoAppSecret
  /// @param {bool} [debugMode] true: 开启调试模式, false: 关闭调试模式
  static Future setup({
    String xiaomiAppId,
    String xiaomiAppKey,
    String huaweiAppId,
    String oppoAppKey,
    String oppoAppSecret,
    bool debugMode = false,
  }) async {
    Map<String, dynamic> arguments = <String, dynamic>{};

    ///小米参数
    arguments[ArgumentName.xiaomiAppId] = xiaomiAppId;
    arguments[ArgumentName.xiaomiAppKey] = xiaomiAppKey;

    ///华为
    arguments[ArgumentName.huaweiAppId] = huaweiAppId;

    ///Oppo
    arguments[ArgumentName.oppoAppKey] = oppoAppKey;
    arguments[ArgumentName.oppoAppSecret] = oppoAppSecret;

    ///是否调试
    arguments[ArgumentName.debugMode] = debugMode;

    return await _channel.invokeMethod(MethodName.setup, arguments);
  }

  ///
  /// 关闭推送/注销推送
  /// 停止推送服务。
  ///
  /// 调用了本 API 后，推送服务完全被停止。
  ///
  /// 小米: unregisterPush
  static Future stopPush() async {
    return await _channel.invokeMethod(MethodName.stopPush);
  }

  ///
  /// 设置别名
  /// 开发者可以为指定用户设置别名，然后给这个别名推送消息
  ///
  /// 小米: setAlias(Context context, String alias, String category)
  ///
  ///
  /// @param {String} [alias] 别名
  static Future setAlias(String alias) async {
    return await _channel
        .invokeMethod(MethodName.setAlias, {ArgumentName.alias: alias});
  }

  ///
  /// 删除别名
  /// 开发者可以取消指定用户的某个别名，服务器就不会给这个别名推送消息了
  ///
  /// 小米: unsetAlias(Context context, String alias, String category)
  ///
  ///
  /// @param {String} [alias] 别名
  static Future deleteAlias(String alias) async {
    return await _channel
        .invokeMethod(MethodName.deleteAlias, {ArgumentName.alias: alias});
  }

  ///
  /// 获取客户端所有设置的别名
  ///
  /// 小米: getAllAlias(final Context context)
  static Future<List<String>> getAllAlias() async {
    final List<dynamic> alias =
        await _channel.invokeMethod(MethodName.getAllAlias);
    return List<String>.from(alias);
  }

  ///
  ///清客户端所设置的别名
  static Future cleanAlias() async {
    return await _channel.invokeMethod(MethodName.cleanAlias);
  }

  ///
  /// 为用户添加标签
  /// 小米中标签叫做主题
  ///
  /// 小米: subscribe(Context context, String topic, String category)
  ///
  ///
  /// @param {String} [tag] 标签/主题
  static Future addTag(String tag) async {
    return await _channel
        .invokeMethod(MethodName.addTag, {ArgumentName.tag: tag});
  }

  ///
  /// 批量添加标签, 小米中标签叫做主题
  static Future addTags(List<String> tags) async {
    return await _channel
        .invokeMethod(MethodName.addTags, {ArgumentName.tags: tags});
  }

  ///
  /// 删除标签, 小米中标签叫做主题
  ///
  /// 小米: unsubscribe(Context context, String topic, String category)
  ///
  ///
  /// @param {String} [tag] 标签/主题
  static Future deleteTag(String tag) async {
    return await _channel
        .invokeMethod(MethodName.deleteTag, {ArgumentName.tag: tag});
  }

  ///
  /// 获取所有的标签, 小米中标签叫做主题
  ///
  /// 小米: getAllTopic(final Context context)
  static Future<List<String>> getAllTag() async {
    final List<dynamic> tags =
        await _channel.invokeMethod(MethodName.getAllTag);
    return List<String>.from(tags);
  }

  ///
  ///清除所有的标签, 小米中标签叫做主题
  static Future cleanTag() async {
    return await _channel.invokeMethod(MethodName.cleanTag);
  }

  ///
  /// 清除通知
  ///
  /// 小米: clearNotification(Context context, int notifyId)
  ///
  ///
  /// @param {int} [notifyId] 通知id
  static Future clearNotification(int notifyId) async {
    return await _channel.invokeMethod(
        MethodName.clearNotification, {ArgumentName.notify_id: notifyId});
  }

  ///
  /// 清除推送弹出的所有通知
  ///
  /// 小米: clearNotification(Context context)
  static Future clearAllNotification() async {
    return await _channel.invokeMethod(MethodName.clearAllNotification);
  }

  ///
  /// 暂停通知
  /// 暂停接收服务推送的消息，app在恢复推送服务之前，不接收任何推送消息
  ///
  /// 小米: pausePush(Context context, String category)
  static Future pausePush() async {
    return await _channel.invokeMethod(MethodName.pausePush);
  }

  ///
  /// 恢复通知
  /// 恢复接收MiPush服务推送的消息,这时服务器会把暂停时期的推送消息重新推送过来
  ///
  /// 小米: resumePush(Context context, String category)
  static Future resumePush() async {
    return await _channel.invokeMethod(MethodName.resumePush);
  }

  ///
  /// 启用推送服务
  ///
  /// 小米: enablePush(final Context context)
  static Future enablePush() async {
    return await _channel.invokeMethod(MethodName.enablePush);
  }

  ///
  /// 禁用推送服务
  ///
  /// 小米: disablePush(final Context context)
  static Future disablePush() async {
    return await _channel.invokeMethod(MethodName.disablePush);
  }

  ///
  /// 获取客户端的RegId
  ///
  /// 小米: getRegId(Context context)
  static Future<String> getRegistrationId() async {
    final String registrationId =
        await _channel.invokeMethod(MethodName.getRegistrationId);
    return registrationId;
  }

  ///
  /// 设置推送时间
  /// 设置接收服务推送的时段，不在该时段的推送消息会被缓存起来，到了合适的时段再向app推送原先被缓存的消息。
  /// 这里采用24小时制，如果开始时间早于结束时间，则这个时段落在一天内；否则，这个时间将会跨越凌晨0点。
  ///
  /// 小米: setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin, String category)
  ///
  /// @param {int} [startHour] 接收时段开始时间的小时
  /// @param {int} [startMinter] 接收时段开始时间的分钟
  /// @param {int} [endHour] 接收时段结束时间的分钟
  /// @param {int} [endMinter] 接收时段开始时间的小时
  static Future setPushTime(
      int startHour, int startMinter, int endHour, int endMinter) async {
    return await _channel.invokeMethod(MethodName.getRegistrationId);
  }

  ///
  /// 判断是否开启通知栏
  static Future<bool> isNotificationEnabled() async {
    final bool isNotificationEnable =
        await _channel.invokeMethod(MethodName.isNotificationEnabled);
    return isNotificationEnable;
  }

  ///
  /// 前往设置界面打开通知栏
  static Future openNotification() async {
    return await _channel.invokeMethod(MethodName.openNotification);
  }

  ///
  /// 判断当前手机厂商
  static Future<Manufacturer> manufacturer() async {
    final String manufacturer =
        await _channel.invokeMethod(MethodName.manufacturer);
    switch (manufacturer) {
      case "xiaomi":
        return Manufacturer.XIAOMI;
      case "huawei":
        return Manufacturer.HUAWEI;
      case "oppo":
        return Manufacturer.OPPO;
      case "vivo":
        return Manufacturer.VIVO;
      case "meizu":
        return Manufacturer.MEIZU;
      default:
        return Manufacturer.OTHER;
    }
  }
}
