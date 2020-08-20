package cn.mofada.factory_push.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/18 15:02
 * @description: Activity工具
 */
object ActivityUtil {
    private const val TAG = "ActivityUtil"

    /**
     * 启动主程序
     */
    fun startMainActivity(context: Context) {
//        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //获取最近正在运行的任务
//        val runningTasks = activityManager.getRunningTasks(5)
//        val size = runningTasks.filter { it.baseActivity.packageName == context.packageName }.size
//        Log.d(TAG, "startMainActivity() called with: context = $size")
        //如果size > 0, 表示已经在运行了
//        if (size > 0) return

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