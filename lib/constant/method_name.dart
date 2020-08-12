import 'package:factory_push/constant/argument_name.dart';

/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/11 10:11
/// @description : 方法名称常量

class MethodName {
  ///获取android版本
  static const getPlatformVersion = "GetPlatformVersion";

  ///初始化
  ///@param [ArgumentName.xiaomiAppId]
  ///@param [ArgumentName.xiaomiAppKey]
  static const setup = "Setup";

  ///设置调试模式
  ///@param [ArgumentName.debugMode]
  static const setDebugMode = "SetDebugMode";

  ///注销推送
  static const stopPush = "StopPush";

  ///设置别名
  ///@param [ArgumentName.alias]
  static const setAlias = "SetAlias";

  ///删除别名
  ///@param [ArgumentName.alias]
  static const deleteAlias = "DeleteAlias";

  ///获取所有的别名
  static const getAllAlias = "GetAllAlias";

  ///清除所有别名
  static const cleanAlias = "CleanAlias";

  ///设置标签
  ///@param [ArgumentName.tag]
  static const addTag = "AddTag";

  ///批量设置标签
  ///@param [ArgumentName.tags]
  static const addTags = "AddTags";

  ///删除标签
  ///@param [ArgumentName.tag]
  static const deleteTag = "DeleteTag";

  ///获取所有的标签
  static const getAllTag = "GetAllTag";

  ///清除所有的标签
  static const cleanTag = "CleanTag";

  ///清除指定id的通知
  ///@param [ArgumentName.notify_id]
  static const clearNotification = "ClearNotification";

  ///清除所有的通知
  static const clearAllNotification = "ClearAllNotification";

  ///暂停通知
  static const pausePush = "PausePush";

  ///恢复通知
  static const resumePush = "ResumePush";

  ///启用推送
  static const enablePush = "EnablePush";

  ///禁用推送
  static const disablePush = "DisablePush";

  ///获取注册id
  static const getRegistrationId = "GetRegistrationId";

  ///设置推送时间
  ///@param [ArgumentName.startHour]
  ///@param [ArgumentName.startMinter]
  ///@param [ArgumentName.endHour]
  ///@param [ArgumentName.endMinter]
  static const setPushTime = "SetPushTime";

  ///是否开启了通知
  static const isNotificationEnabled = "IsNotificationEnabled";

  ///打开通知设置界面
  static const openNotification = "OpenNotification";
}
