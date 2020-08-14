/// @author : fada
/// @email : fada@mofada.cn
/// @date : 2020/8/13 17:06
/// @description : 消息封装实体类

part of "../factory_push.dart";

class PushMessageBean {
  ///消息id
  final String messageId;

  ///消息类型
  final String messageType;

  ///通知id
  final int notifyId;

  ///标题
  final String title;

  ///消息
  final String message;

  ///类别
  final String category;

  ///额外字段
  final Map<String, String> extra;

  PushMessageBean({
    this.messageId,
    this.notifyId,
    this.messageType,
    this.title,
    this.message,
    this.category,
    this.extra,
  });

  factory PushMessageBean.fromJson(Map<String, dynamic> json) =>
      PushMessageBean(
        messageId: json["messageId"],
        notifyId: json["notifyId"],
        messageType: json["messageType"],
        title: json["title"],
        message: json["message"],
        category: json["category"],
        extra: Map<String, String>.from(json["extra"]),
      );

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['messageId'] = this.messageId;
    data['notifyId'] = this.notifyId;
    data['messageType'] = this.messageType;
    data['title'] = this.title;
    data['message'] = this.message;
    data['category'] = this.category;
    data['extra'] = this.extra;
    return data;
  }

  @override
  String toString() {
    return 'PushMessageBean{messageId: $messageId, messageType: $messageType, notifyId: $notifyId, title: $title, message: $message, category: $category, extra: $extra}';
  }
}
