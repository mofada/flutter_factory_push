package cn.mofada.factory_push.receiver

import android.content.Context
import android.content.Intent
import cn.mofada.factory_push.bean.PushMessageBean
import cn.mofada.factory_push.constant.MessageType
import com.xiaomi.mipush.sdk.MiPushMessage
import com.xiaomi.mipush.sdk.PushMessageReceiver

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/11 11:01
 * @description 描述类
 */
class XiaoMiPushReceiver : PushMessageReceiver() {
    /**
     * 接收服务器推送的通知消息，用户点击后触发，消息封装在 MiPushMessage类中。详细说明请参照3.4.22。
     */
    override fun onNotificationMessageClicked(context: Context, miPushMessage: MiPushMessage) {
        MessageReceiver.sendIntent(context, messageToPushMessageBean(MessageType.NotificationClicked, miPushMessage))

        //启动主程序
        val intent = context
                .packageManager
                .getLaunchIntentForPackage(context.packageName)
                ?.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        val build = FlutterActivity
//                .withNewEngine()
//                .initialRoute("/message")
//                .build(context)
        context.startActivity(intent)
    }

    /**
     * 接收服务器推送的通知消息，消息到达客户端时触发，还可以接受应用在前台时不弹出通知的通知消息，
     * 消息封装在 MiPushMessage类中。在MIUI上，只有应用处于启动状态，
     * 或者自启动白名单中，才可以通过此方法接受到该消息。详细说明请参照3.4.23。
     */
    override fun onNotificationMessageArrived(context: Context, miPushMessage: MiPushMessage) {
        MessageReceiver.sendIntent(context, messageToPushMessageBean(MessageType.MessageReceiver, miPushMessage))
    }

    /**
     * 将小米推送消息转成自己的推送实体类
     */
    private fun messageToPushMessageBean(method: MessageType, miPushMessage: MiPushMessage): PushMessageBean {
        return PushMessageBean(
                messageId = miPushMessage.messageId,
                messageType = method,
                notifyId = miPushMessage.notifyId,
                title = miPushMessage.title,
                message = miPushMessage.description,
                extra = miPushMessage.extra,
                category = miPushMessage.category
        )
    }
}