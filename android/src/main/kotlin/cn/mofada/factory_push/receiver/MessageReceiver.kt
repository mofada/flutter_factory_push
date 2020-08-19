package cn.mofada.factory_push.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import cn.mofada.factory_push.bean.PushMessageBean
import cn.mofada.factory_push.constant.MessageType
import cn.mofada.factory_push.constant.PushIntent
import io.flutter.plugin.common.EventChannel

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 小米推送接收服务
 */
class MessageReceiver(private val events: EventChannel.EventSink?) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val result = HashMap<Any, Any>()

        //获取消息类型
        val messageType = intent?.getStringExtra(PushIntent.EXTRA_TYPE) ?: "MessageReceiver"
        //获取信息
        val message = intent?.getStringExtra(PushIntent.EXTRA_MESSAGE)
        //获取token
        val token = intent?.getStringExtra(PushIntent.EXTRA_TOKEN)

        //设置类型
        result["type"] = messageType

        when (MessageType.valueOf(messageType)) {
            MessageType.MessageReceiver -> result["data"] = message ?: ""
            MessageType.NotificationClicked -> result["data"] = message ?: ""
            MessageType.Token -> result["data"] = token ?: ""
        }

        events?.success(result)
    }

    companion object {
        /**
         * 发送消息实体类
         */
        fun sendMessageIntent(context: Context, messageType: MessageType, pushMessageBean: PushMessageBean) {
            val intent = Intent()
            intent.action = PushIntent.ACTION_RECEIVER
            //接收的结果
            intent.putExtra(PushIntent.EXTRA_MESSAGE, pushMessageBean.toJson())
            //推送类型
            intent.putExtra(PushIntent.EXTRA_TYPE, messageType.toString())
            context.sendBroadcast(intent)
        }

        /**
         * 发送消息实体类
         */
        fun sendTokenIntent(context: Context, token: String) {
            val intent = Intent()
            intent.action = PushIntent.ACTION_RECEIVER
            intent.putExtra(PushIntent.EXTRA_TYPE, MessageType.Token.toString())
            //接收的token
            intent.putExtra(PushIntent.EXTRA_TOKEN, token)
            context.sendBroadcast(intent)
        }

        /**
         * 将{@link Bundle}转称{@link Map}
         */
        fun bundleToMap(bundle: Bundle?): Map<String, String> {
            val result: HashMap<String, String> = HashMap()
            //如果bundle是null, 那么直接返回
            if (bundle == null) return result

            //否则, 转化
            for (key in bundle.keySet()) {
                result[key] = bundle.get(key)!!.toString()
            }

            return result
        }
    }
}