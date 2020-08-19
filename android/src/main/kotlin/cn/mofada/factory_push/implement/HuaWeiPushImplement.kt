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
                HmsInstanceId.getInstance(context).getToken(appId, "HCM")
            } catch (e: Exception) {
//                e.printStackTrace()
            }
        }
    }

    /**
     * 停止推送, 删除token
     */
    fun stopPush(context: Context) {
        HmsInstanceId.getInstance(context).deleteToken(null, null)
    }

    /**
     * 标签, 华为的叫主题
     * 为某个用户设置订阅主题；根据用户订阅的不同主题，开发者可以根据订阅的主题实现分组群发。
     */
    fun addTag(context: Context, tag: String) {
        HmsMessaging.getInstance(context).subscribe(tag)
    }

    /**
     * 批量添加多个标签
     */
    fun addTags(context: Context, tags: List<String>) {
        tags.forEach { addTag(context, it) }
    }

    /**
     * 删除标签
     */
    fun deleteTag(context: Context, tag: String) {
        HmsMessaging.getInstance(context).unsubscribe(tag)
    }

    /**
     * 关闭推送栏消息
     */
    fun pausePush(context: Context) {
        HmsMessaging.getInstance(context).turnOnPush()
    }

    /**
     * 关闭推送栏消息
     */
    fun resumePush(context: Context) {
        HmsMessaging.getInstance(context).turnOffPush()
    }

    /**
     * 获取客户端的RegId
     */
    fun getRegistrationId(context: Context, result: MethodChannel.Result) {
        thread {
            try {
                val token = HmsInstanceId.getInstance(context).getToken(null, null)
                result.success(token)
            } catch (e: Exception) {
//                e.printStackTrace()
//                result.success(null)
            }
        }
    }
}