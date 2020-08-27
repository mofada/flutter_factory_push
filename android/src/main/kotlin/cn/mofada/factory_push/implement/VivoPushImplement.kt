package cn.mofada.factory_push.implement

import android.content.Context
import android.util.Log
import cn.mofada.factory_push.receiver.OppoPushReceiver
import com.heytap.msp.push.HeytapPushManager
import com.vivo.push.PushClient

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/26 11:34
 * @description: VIVO推送实现类
 */
object VivoPushImplement {
    /**
     * 初始化设置
     * @param context
     * @param appId 与AppSecret相对应
     * @param appKey 在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
     */
    fun setup(context: Context, appId: String, appKey: String, debugMode: Boolean) {
        //初始化
        PushClient.getInstance(context).turnOnPush {
            Log.d("TAG", "setup() called")
        }
    }
}