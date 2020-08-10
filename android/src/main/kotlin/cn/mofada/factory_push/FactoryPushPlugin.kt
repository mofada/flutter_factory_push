package cn.mofada.factory_push

import android.content.Context
import androidx.annotation.NonNull;
import cn.mofada.factory_push.constant.ChannelName
import cn.mofada.factory_push.constant.MethodName
import cn.mofada.factory_push.implement.PushMethodImplement

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 推送插件
 */
public class FactoryPushPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel

    private lateinit var context: Context

    /**
     * 初始化
     */
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, ChannelName.PUSH_PLUGIN.id)
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext

        //初始化
        MessageReceiverEvent.onAttachedToEngine(flutterPluginBinding)
    }

    companion object {

        /**
         * 老版本的初始化
         */
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), ChannelName.PUSH_PLUGIN.id)
            val pushPlugin = FactoryPushPlugin()

            channel.setMethodCallHandler(pushPlugin)
            pushPlugin.context = registrar.context().applicationContext

            //消息接收注册
            MessageReceiverEvent.registerWith(registrar)
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (MethodName.valueOf(call.method)) {
            MethodName.getPlatformVersion -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            MethodName.setup -> PushMethodImplement.setup(context, call, result)
            else -> result.notImplemented()
        }
    }

    /**
     * 销毁的方法
     */
    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)

        //插件销毁
        MessageReceiverEvent.onDetachedFromEngine(binding)
    }
}
