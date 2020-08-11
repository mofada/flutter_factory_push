package cn.mofada.factory_push.implement

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.xiaomi.channel.commonutils.logger.LoggerInterface
import com.xiaomi.mipush.sdk.Logger
import com.xiaomi.mipush.sdk.MiPushClient


/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 小米推送插件
 */
object XiaoMiPushImplement {
    /**
     * 初始化设置
     * @param context
     * @param appId 在开发者网站上注册时生成的，MiPush推送服务颁发给app的唯一认证标识
     * @param appKey 在开发者网站上注册时生成的，与appID相对应，用于验证appID是否合法
     */
    fun setup(context: Context, appId: String, appKey: String) {
        MiPushClient.registerPush(context.applicationContext, appId, appKey)
    }
}