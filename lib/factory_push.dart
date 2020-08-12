import 'dart:async';

import 'package:factory_push/constant/argument_name.dart';
import 'package:factory_push/constant/channel_name.dart';
import 'package:factory_push/constant/method_name.dart';
import 'package:flutter/services.dart';

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
  static void onPushReceiver<T>(void onData(T event),
      {Function onError, void onDone(), bool cancelOnError}) {
    _receiverEvent.receiveBroadcastStream().listen(onData,
        onError: onError, onDone: onDone, cancelOnError: cancelOnError);
  }

  ///初始化设置
  ///
  /// 小米: registerPush
  ///
  ///
  /// @param {String} [xiaomiAppId] 在开发者网站上注册时生成的，MiPush推送服务颁发给app的唯一认证标识
  /// @param {String} [xiaomiAppKey] 在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
  static void setup({String xiaomiAppId, String xiaomiAppKey}) {
    Map<String, dynamic> arguments = <String, dynamic>{};

    ///小米参数
    arguments[ArgumentName.xiaomiAppId] = xiaomiAppId;
    arguments[ArgumentName.xiaomiAppKey] = xiaomiAppKey;

    _channel.invokeMethod(MethodName.setup, arguments);
  }

  ///
  /// 设置调试模式。
  /// 注：该接口需在 setup 接口之前调用，避免出现部分日志没打印的情况
  ///
  ///
  /// @param {bool} [debugMode] true: 开启调试模式, false: 关闭调试模式
  static void setDebugMode(bool debugMode) => _channel.invokeMethod(
      MethodName.setDebugMode, {ArgumentName.debugMode, debugMode});

  ///
  /// 关闭推送/注销推送
  /// 停止推送服务。
  ///
  /// 调用了本 API 后，推送服务完全被停止。
  ///
  /// 小米: unregisterPush
  static void stopPush() => _channel.invokeMethod(MethodName.stopPush);

  ///
  /// 设置别名
  /// 开发者可以为指定用户设置别名，然后给这个别名推送消息
  ///
  /// 小米: setAlias(Context context, String alias, String category)
  ///
  ///
  /// @param {String} [alias] 别名
  static void setAlias(String alias) =>
      _channel.invokeMethod(MethodName.setAlias, {ArgumentName.alias, alias});

  ///
  /// 删除别名
  /// 开发者可以取消指定用户的某个别名，服务器就不会给这个别名推送消息了
  ///
  /// 小米: unsetAlias(Context context, String alias, String category)
  ///
  ///
  /// @param {String} [alias] 别名
  static void deleteAlias(String alias) =>
      _channel.invokeMethod(MethodName.setAlias, {ArgumentName.alias, alias});

  ///
  /// 获取客户端所有设置的别名
  ///
  /// 小米: getAllAlias(final Context context)
  static Future<List<dynamic>> getAllAlias() =>
      _channel.invokeMethod(MethodName.getAllAlias);

  ///
  ///清客户端所设置的别名
  static void cleanAlias() => _channel.invokeMethod(MethodName.cleanAlias);

  ///
  /// 为用户添加标签
  /// 小米中标签叫做主题
  ///
  /// 小米: subscribe(Context context, String topic, String category)
  ///
  ///
  /// @param {String} [tag] 标签/主题
  static void addTag(String tag) =>
      _channel.invokeMethod(MethodName.addTag, {ArgumentName.tag, tag});

  ///
  /// 批量添加标签, 小米中标签叫做主题
  static void addTags(List<String> tags) =>
      _channel.invokeMethod(MethodName.addTags, {ArgumentName.tags, tags});

  ///
  /// 删除标签, 小米中标签叫做主题
  ///
  /// 小米: unsubscribe(Context context, String topic, String category)
  ///
  ///
  /// @param {String} [tag] 标签/主题
  static void deleteTag(String tag) =>
      _channel.invokeMethod(MethodName.deleteTag, {ArgumentName.tag, tag});

  ///
  /// 获取所有的标签, 小米中标签叫做主题
  ///
  /// 小米: getAllTopic(final Context context)
  static Future<List<dynamic>> getAllTag() =>
      _channel.invokeMethod(MethodName.getAllTag);

  ///
  ///清除所有的标签, 小米中标签叫做主题
  static void cleanTag() => _channel.invokeMethod(MethodName.cleanTag);

  ///
  /// 清除通知
  ///
  /// 小米: clearNotification(Context context, int notifyId)
  ///
  ///
  /// @param {int} [notifyId] 通知id
  static void clearNotification(int notifyId) => _channel.invokeMethod(
      MethodName.clearNotification, {ArgumentName.notify_id, notifyId});

  ///
  /// 清除推送弹出的所有通知
  ///
  /// 小米: clearNotification(Context context)
  static void clearAllNotification() =>
      _channel.invokeMethod(MethodName.clearAllNotification);

  ///
  /// 暂停通知
  /// 暂停接收服务推送的消息，app在恢复推送服务之前，不接收任何推送消息
  ///
  /// 小米: pausePush(Context context, String category)
  static void pausePush() => _channel.invokeMethod(MethodName.pausePush);

  ///
  /// 恢复通知
  /// 恢复接收MiPush服务推送的消息,这时服务器会把暂停时期的推送消息重新推送过来
  ///
  /// 小米: resumePush(Context context, String category)
  static void resumePush() => _channel.invokeMethod(MethodName.resumePush);

  ///
  /// 启用推送服务
  ///
  /// 小米: enablePush(final Context context)
  static void enablePush() => _channel.invokeMethod(MethodName.enablePush);

  ///
  /// 禁用推送服务
  ///
  /// 小米: disablePush(final Context context)
  static void disablePush() => _channel.invokeMethod(MethodName.disablePush);

  ///
  /// 获取客户端的RegId
  ///
  /// 小米: getRegId(Context context)
  static Future<String> getRegistrationId() =>
      _channel.invokeMethod(MethodName.getRegistrationId);

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
  static void setPushTime(
      int startHour, int startMinter, int endHour, int endMinter) {
    _channel.invokeMethod(MethodName.getRegistrationId);
  }

  ///
  /// 判断是否开启通知栏
  static Future<bool> isNotificationEnabled() =>
      _channel.invokeMethod(MethodName.isNotificationEnabled);

  ///
  /// 前往设置界面打开通知栏
  static void openNotification() =>
      _channel.invokeMethod(MethodName.openNotification);
}
