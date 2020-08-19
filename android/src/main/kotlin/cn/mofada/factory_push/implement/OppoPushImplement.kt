package cn.mofada.factory_push.implement

import android.content.Context
import cn.mofada.factory_push.receiver.OppoPushReceiver
import com.heytap.msp.push.HeytapPushManager

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/19 16:08
 * @description: oppo 推送实现类
 */
object OppoPushImplement {
    /**
     * 初始化设置
     * @param context
     * @param appKey 在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
     * @param appSecret 与AppSecret相对应
     */
    fun setup(context: Context, appKey: String, appSecret: String) {
        HeytapPushManager.register(context,appKey,appSecret,OppoPushReceiver())
    }

    /**
     * 停止推送, 注销推送
     */
    fun stopPush(context: Context) {
        HeytapPushManager.unRegister()
    }
}