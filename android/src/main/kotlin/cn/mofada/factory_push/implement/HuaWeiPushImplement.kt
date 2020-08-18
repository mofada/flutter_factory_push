package cn.mofada.factory_push.implement

import android.content.Context
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.push.HmsMessaging
import io.flutter.plugin.common.MethodChannel
import kotlin.concurrent.thread

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/14 16:39
 * @description: 华为推送实现类
 */
object HuaWeiPushImplement {

    /**
     * 初始化设置
     * @param context
     * @param appId 在开发者网站上注册时生成的，MiPush推送服务颁发给app的唯一认证标识
     */
    fun setup(context: Context, appId: String) {
        thread {
            try {
                HmsInstanceId.getInstance(context).getToken(null, null)
            } catch (e: Exception) {
//                e.printStackTrace()
            }
        }
    }

    /**
     * 标签, 华为的叫主题
     * 为某个用户设置订阅主题；根据用户订阅的不同主题，开发者可以根据订阅的主题实现分组群发。
     */
    fun addTag(context: Context, tag: String) {
        HmsMessaging.getInstance(context).subscribe(tag)
    }

    /**
     * 获取客户端的RegId
     */
    fun getRegistrationId(context: Context, result: MethodChannel.Result) {
        thread {
            try {
                result.success(HmsInstanceId.getInstance(context).getToken(null, null))
            } catch (e: Exception) {
//                e.printStackTrace()
//                result.success(null)
            }
        }
    }
}