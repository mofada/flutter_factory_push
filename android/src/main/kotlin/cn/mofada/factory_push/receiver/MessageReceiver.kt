package cn.mofada.factory_push.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.mofada.factory_push.bean.PushMessageBean
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
        val result = intent?.getStringExtra(PushIntent.EXTRA_MESSAGE)

        events?.success(result)
    }

    companion object {
        fun sendIntent(context: Context, pushMessageBean: PushMessageBean) {
            val intent = Intent()
            intent.action = PushIntent.ACTION_RECEIVER
            //接收的结果
            intent.putExtra(PushIntent.EXTRA_MESSAGE, pushMessageBean.toJson())
            context.sendBroadcast(intent)
        }
    }
}