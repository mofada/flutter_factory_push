package cn.mofada.factory_push.constant

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/11 14:22
 * @description: 推送的意图
 */
object PushIntent {
    /**
     * 推送的action
     */
    const val ACTION_RECEIVER = "cn.mofada.receiver_action"

    /**
     * 接收的结果
     */
    const val EXTRA_MESSAGE = "push_message"

    /**
     * token消息
     */
    const val EXTRA_TOKEN = "push_token"

    /**
     * 类型
     */
    const val EXTRA_TYPE = "push_type"
}