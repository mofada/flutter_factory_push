package cn.mofada.factory_push.util

import android.content.Context
import android.content.Intent

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/18 15:02
 * @description: 描述类
 */
object ActivityUtil {
    /**
     * 启动朱程序
     */
    fun startMainActivity(context: Context) {
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
}