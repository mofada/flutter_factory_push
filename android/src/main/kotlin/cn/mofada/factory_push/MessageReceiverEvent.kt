package cn.mofada.factory_push

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.annotation.NonNull
import cn.mofada.factory_push.constant.ChannelName
import cn.mofada.factory_push.constant.XiaoMiPushConstant
import cn.mofada.factory_push.receiver.XiaoMiReceiver
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.PluginRegistry.Registrar

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 推送
 */
class MessageReceiverEvent(private val context: Context) : EventChannel.StreamHandler {

    companion object {
        private lateinit var channel: EventChannel

        /**
         * Plugin registration.
         */
        fun registerWith(registrar: Registrar) {
            channel = EventChannel(registrar.messenger(), ChannelName.RECEIVER_PLUGIN.id)
            channel.setStreamHandler(MessageReceiverEvent(registrar.context()))
        }

        /**
         * 初始化
         */
        fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
            val channel = EventChannel(flutterPluginBinding.flutterEngine.dartExecutor, ChannelName.RECEIVER_PLUGIN.id)
            channel.setStreamHandler(MessageReceiverEvent(flutterPluginBinding.applicationContext))
        }

        /**
         * 取消注册
         */
        fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
            channel.setStreamHandler(null)
        }
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        //根据厂商进行判断
        events?.let {
            //创建推送广播
            val tokenEventBroadcastReceiver: BroadcastReceiver = XiaoMiReceiver(it)

            //创建过滤器
            val intentFilter = IntentFilter()
            intentFilter.addAction(XiaoMiPushConstant.ACTION_RECEIVE_MESSAGE)
            intentFilter.addAction(XiaoMiPushConstant.ACTION_MESSAGE_ARRIVED)
            intentFilter.addAction(XiaoMiPushConstant.ACTION_ERROR)

            //创建广播
            context.registerReceiver(
                    tokenEventBroadcastReceiver,
                    intentFilter
            )
        }

    }

    override fun onCancel(arguments: Any?) {
    }

}