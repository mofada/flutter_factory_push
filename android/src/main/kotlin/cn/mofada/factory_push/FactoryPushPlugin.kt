package cn.mofada.factory_push

import android.content.Context
import android.os.Build
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
        ReceiverEvent.onAttachedToEngine(flutterPluginBinding)
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
            ReceiverEvent.registerWith(registrar)
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (MethodName.valueOf(call.method)) {
            MethodName.GetPlatformVersion -> result.success("Android ${Build.VERSION.RELEASE}")
            MethodName.Setup -> PushMethodImplement.setup(context, call, result)
            MethodName.SetDebugMode -> PushMethodImplement.setDebugMode(context, call, result)
            MethodName.StopPush -> PushMethodImplement.stopPush(context, call, result)
            MethodName.SetAlias -> PushMethodImplement.setAlias(context, call, result)
            MethodName.DeleteAlias -> PushMethodImplement.deleteAlias(context, call, result)
            MethodName.GetAllAlias -> PushMethodImplement.getAllAlias(context, call, result)
            MethodName.CleanAlias -> PushMethodImplement.cleanAlias(context, call, result)
            MethodName.AddTag -> PushMethodImplement.addTag(context, call, result)
            MethodName.AddTags -> PushMethodImplement.addTags(context, call, result)
            MethodName.DeleteTag -> PushMethodImplement.deleteTag(context, call, result)
            MethodName.GetAllTag -> PushMethodImplement.getAllTag(context, call, result)
            MethodName.CleanTag -> PushMethodImplement.cleanTag(context, call, result)
            MethodName.ClearNotification -> PushMethodImplement.clearNotification(context, call, result)
            MethodName.ClearAllNotification -> PushMethodImplement.clearAllNotification(context, call, result)
            MethodName.PausePush -> PushMethodImplement.pausePush(context, call, result)
            MethodName.ResumePush -> PushMethodImplement.resumePush(context, call, result)
            MethodName.EnablePush -> PushMethodImplement.enablePush(context, call, result)
            MethodName.DisablePush -> PushMethodImplement.disablePush(context, call, result)
            MethodName.GetRegistrationId -> PushMethodImplement.getRegistrationId(context, call, result)
            MethodName.SetPushTime -> PushMethodImplement.setPushTime(context, call, result)
            MethodName.IsNotificationEnabled -> PushMethodImplement.isNotificationEnabled(context, call, result)
            MethodName.OpenNotification -> PushMethodImplement.openNotification(context, call, result)
        }
    }

    /**
     * 销毁的方法
     */
    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)

        //插件销毁
        ReceiverEvent.onDetachedFromEngine(binding)
    }
}
