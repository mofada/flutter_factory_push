package cn.mofada.factory_push.implement

import android.content.Context
import androidx.annotation.NonNull
import cn.mofada.factory_push.constant.XiaoMiPushConstant
import cn.mofada.factory_push.util.FactoryUtil
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 推送插件实现
 */
object PushMethodImplement {
    /**
     * 初始化设置
     */
    fun setup(context: Context, @NonNull call: MethodCall, @NonNull result: MethodChannel.Result) {
        //如果是小米手机, 并且有小米的id和key
        if (FactoryUtil.isXIAOMI() ||
                call.hasArgument(XiaoMiPushConstant.ARGUMENT_APP_ID) ||
                call.hasArgument(XiaoMiPushConstant.ARGUMENT_APP_KEY)) {
            //应用id
            val xiaomiAppId = call.argument<String>(XiaoMiPushConstant.ARGUMENT_APP_ID)
            //应用key
            val xiaomiAppKey = call.argument<String>(XiaoMiPushConstant.ARGUMENT_APP_KEY)
            XiaoMiPushImplement.setup(context, xiaomiAppId!!, xiaomiAppKey!!)
        }

    }
}