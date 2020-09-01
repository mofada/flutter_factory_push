package cn.mofada.factory_push.implement

import android.content.Context
import android.util.Log
import com.vivo.push.PushClient
import io.flutter.plugin.common.MethodChannel

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
        val support = PushClient.getInstance(context).isSupport
        //初始化
        PushClient.getInstance(context).turnOnPush {
            if (debugMode)
                Log.d(PushMethodImplement.TAG, "setup() called $it - $support")
        }
    }

    /**
     * 关闭应用push开关，解除绑定应用，解绑后将收不到当前应用的推送消息。
     */
    fun stopPush(context: Context) {
        PushClient.getInstance(context).turnOffPush(null)
    }

    /**
     * 开发者可以为指定用户设置别名，然后给这个别名推送消息，效果等同于给RegId推送消息。
     * 注: 一个RegId可以被设置多个别名，如果设置的别名已经存在，会覆盖掉之前的别名。
     */
    fun setAlias(context: Context, alias: String) {
        PushClient.getInstance(context).bindAlias(alias) {}
    }

    /**
     * 开发者可以取消指定用户的某个别名，服务器就不会给这个别名推送消息了。
     */
    fun deleteAlias(context: Context, alias: String) {
        PushClient.getInstance(context).unBindAlias(alias, null)
    }

    /**
     * 获取客户端所有设置的别名。
     */
    fun getAllAlias(context: Context) = arrayListOf(PushClient.getInstance(context).alias)


    /**
     * 取消所有的别名
     * 开发者可以取消指定用户的某个别名，服务器就不会给这个别名推送消息了。
     */
    fun cleanAlias(context: Context) {
        getAllAlias(context).forEach {
            deleteAlias(context, it)
        }
    }

    /**
     * 标签, 小米的叫主题
     * 为某个用户设置订阅主题；根据用户订阅的不同主题，开发者可以根据订阅的主题实现分组群发。
     */
    fun addTag(context: Context, tag: String) {
        PushClient.getInstance(context).setTopic( tag, null)
    }

    /**
     * 标签, 小米的叫主题
     * 为某个用户设置订阅主题；根据用户订阅的不同主题，开发者可以根据订阅的主题实现分组群发。
     */
    fun addTags(context: Context, tags: List<String>) {
        tags.forEach { addTag(context, it) }
    }

    /**
     * 删除标签
     * 为某个用户取消某个订阅主题。
     */
    fun deleteTag(context: Context, tag: String) {
        PushClient.getInstance(context).delTopic( tag, null)
    }

    /**
     * 获取所有标签
     * 获取客户端所有订阅的主题。
     */
    fun getAllTag(context: Context) = PushClient.getInstance(context).topics


    /**
     * 清除所有标签
     */
    fun clearTag(context: Context) {
        getAllTag(context).forEach { deleteTag(context, it) }
    }

    /**
     * 获取客户端的RegId
     */
    fun getRegistrationId(context: Context, result: MethodChannel.Result) {
        val regId = PushClient.getInstance(context).regId
        result.success(regId)
    }
}