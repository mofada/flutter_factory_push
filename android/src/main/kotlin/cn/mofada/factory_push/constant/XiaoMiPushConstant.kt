package cn.mofada.factory_push.constant

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 小米推送的常量
 */
object XiaoMiPushConstant {
    /**
     * 接收推送
     */
    val ACTION_RECEIVE_MESSAGE = "com.xiaomi.mipush.RECEIVE_MESSAGE"

    /**
     * 消息到达
     */
    val ACTION_MESSAGE_ARRIVED = "com.xiaomi.mipush.MESSAGE_ARRIVED"

    /**
     * 发生错误
     */
    val ACTION_ERROR = "com.xiaomi.mipush.ERROR"

    /**
     * 小米的应用id
     */
    val PARAMS_APP_ID = "xiaomiAppId";

    /**
     * 小米的应用key
     */
    val PARAMS_APP_KEY = "xiaomiAppKey";
}