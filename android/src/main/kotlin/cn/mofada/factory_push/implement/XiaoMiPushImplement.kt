package cn.mofada.factory_push.implement

import android.content.Context
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
     * @param appId
     * @param appKey
     */
    fun setup(context: Context, appId: String, appKey: String) {
        MiPushClient.registerPush(context, appId, appKey)
    }
}