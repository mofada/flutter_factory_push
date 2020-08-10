package cn.mofada.factory_push.receiver

import android.content.Context
import com.xiaomi.mipush.sdk.MiPushCommandMessage
import com.xiaomi.mipush.sdk.MiPushMessage
import com.xiaomi.mipush.sdk.PushMessageReceiver
import io.flutter.plugin.common.EventChannel

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 小米推送接收服务
 */
class XiaoMiReceiver(val events: EventChannel.EventSink) : PushMessageReceiver() {

    /**
     * 接收服务器推送的透传消息，消息封装在 MiPushMessage类中。详细说明请参照3.4.21。
     */
    override fun onReceivePassThroughMessage(context: Context?, message: MiPushMessage?) {
    }

    /**
     * 接收服务器推送的通知消息，用户点击后触发，消息封装在 MiPushMessage类中。详细说明请参照3.4.22。
     */
    override fun onNotificationMessageClicked(context: Context?, message: MiPushMessage?) {
    }

    /**
     * 接收服务器推送的通知消息，消息到达客户端时触发，还可以接受应用在前台时不弹出通知的通知消息，
     * 消息封装在 MiPushMessage类中。在MIUI上，只有应用处于启动状态，或者自启动白名单中，
     * 才可以通过此方法接受到该消息。详细说明请参照3.4.23。
     */
    override fun onNotificationMessageArrived(context: Context?, message: MiPushMessage?) {

    }

    /**
     * 获取给服务器发送命令的结果，结果封装在MiPushCommandMessage类中。详细说明请参照3.4.24
     */
    override fun onCommandResult(context: Context?, message: MiPushCommandMessage?) {
    }

    /**
     * 获取给服务器发送注册命令的结果，结果封装在MiPushCommandMessage类中。详细说明请参照3.4.25
     */
    override fun onReceiveRegisterResult(context: Context?, message: MiPushCommandMessage?) {
    }

    /**
     * 当所需要的权限未获取到的时候会回调该接口。详细说明请参照3.4.26。
     */
    override fun onRequirePermissions(context: Context?, permissions: Array<out String>?) {
    }

    override fun onReceiveMessage(context: Context?, message: MiPushMessage?) {
    }
}