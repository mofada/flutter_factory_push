package cn.mofada.factory_push.constant

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/11 15:56
 * @description: 消息类型枚举
 */
enum class MessageType {
    /**
     * 接收消息
     */
    MessageReceiver,

    /**
     * 通知被点击
     */
    NotificationClicked,

    /**
     * 华为Token
     */
    Token,
}