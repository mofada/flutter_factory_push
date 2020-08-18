package cn.mofada.factory_push.receiver

import android.util.Log
import cn.mofada.factory_push.bean.PushMessageBean
import cn.mofada.factory_push.constant.MessageType
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/17 14:27
 * @description: 华为服务
 */
class HuaWeiPushReceiver : HmsMessageService() {
    /**
     * 接收透传消息方法
     * @param remoteMessage
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        MessageReceiver.sendMessageIntent(this, MessageType.MessageReceiver, messageToPushMessageBean(remoteMessage))
    }

    /**
     * 服务端更新token回调方法
     */
    override fun onNewToken(token: String) {
        MessageReceiver.sendTokenIntent(this, token)
    }

    /**
     * 申请token失败回调方法
     */
    override fun onTokenError(e: Exception) {
    }

    /**
     * 将华为推送消息转成自己的推送实体类
     */
    private fun messageToPushMessageBean(remoteMessage: RemoteMessage): PushMessageBean {
        return PushMessageBean(
                messageId = remoteMessage.messageId,
                messageType = remoteMessage.messageType,
                notifyId = remoteMessage.notification.notifyId,
                title = remoteMessage.notification.title,
                message = remoteMessage.notification.body,
                extra = remoteMessage.dataOfMap,
                category = remoteMessage.messageType
        )
    }
}