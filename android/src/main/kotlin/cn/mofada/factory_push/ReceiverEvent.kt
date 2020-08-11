package cn.mofada.factory_push

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.annotation.NonNull
import cn.mofada.factory_push.constant.ChannelName
import cn.mofada.factory_push.constant.PushIntent
import cn.mofada.factory_push.receiver.MessageReceiver
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.PluginRegistry.Registrar

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 推送
 */
class ReceiverEvent(private val context: Context) : EventChannel.StreamHandler {

    companion object {
        private lateinit var channel: EventChannel

        /**
         * Plugin registration.
         */
        fun registerWith(registrar: Registrar) {
            channel = EventChannel(registrar.messenger(), ChannelName.RECEIVER_PLUGIN.id)
            channel.setStreamHandler(ReceiverEvent(registrar.context()))
        }

        /**
         * 初始化
         */
        fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
            channel = EventChannel(flutterPluginBinding.flutterEngine.dartExecutor, ChannelName.RECEIVER_PLUGIN.id)
            channel.setStreamHandler(ReceiverEvent(flutterPluginBinding.applicationContext))
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
        //创建推送广播
        val broadcast: BroadcastReceiver = MessageReceiver(events)

        //创建过滤器
        val intentFilter = IntentFilter(PushIntent.ACTION_RECEIVER)

        //创建广播
        context.registerReceiver(
                broadcast,
                intentFilter
        )
    }

    /**
     * 取消的时候, 一般来说在这里取消广播注册, 但是考虑离线接收广播, 不取消
     */
    override fun onCancel(arguments: Any?) {

    }

}