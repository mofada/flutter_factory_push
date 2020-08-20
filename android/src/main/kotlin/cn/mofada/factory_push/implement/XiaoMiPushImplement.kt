package cn.mofada.factory_push.implement

import android.content.Context
import android.util.Log
import com.xiaomi.channel.commonutils.logger.LoggerInterface
import com.xiaomi.mipush.sdk.Logger
import com.xiaomi.mipush.sdk.MiPushClient
import io.flutter.plugin.common.MethodChannel


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
    fun setup(context: Context, appId: String, appKey: String, debugMode: Boolean) {
        MiPushClient.registerPush(context.applicationContext, appId, appKey)

        //打开Log
        if (debugMode)
            Logger.setLogger(context, object : LoggerInterface {
                override fun setTag(tag: String) {
                    // ignore
                }

                override fun log(content: String, t: Throwable) {
                    Log.d(PushMethodImplement.TAG, content, t)
                }

                override fun log(content: String) {
                    Log.d(PushMethodImplement.TAG, content)
                }
            })
    }

    /**
     * 关闭MiPush推送服务，当用户希望不再使用MiPush推送服务的时候调用，调用成功之后，
     * app将不会接收到任何MiPush服务推送的数据，直到下一次调用registerPush ()。
     * 注: 调用unregisterPush()之后，服务器不会向app发送任何消息。
     */
    fun stopPush(context: Context) {
        MiPushClient.unregisterPush(context)
    }

    /**
     * 开发者可以为指定用户设置别名，然后给这个别名推送消息，效果等同于给RegId推送消息。
     * 注: 一个RegId可以被设置多个别名，如果设置的别名已经存在，会覆盖掉之前的别名。
     */
    fun setAlias(context: Context, alias: String) {
        MiPushClient.setAlias(context, alias, null)
    }

    /**
     * 开发者可以取消指定用户的某个别名，服务器就不会给这个别名推送消息了。
     */
    fun deleteAlias(context: Context, alias: String) {
        MiPushClient.unsetAlias(context, alias, null)
    }

    /**
     * 获取客户端所有设置的别名。
     */
    fun getAllAlias(context: Context) = MiPushClient.getAllAlias(context)


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
        MiPushClient.subscribe(context, tag, null)
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
        MiPushClient.unsubscribe(context, tag, null)
    }

    /**
     * 获取所有标签
     * 获取客户端所有订阅的主题。
     */
    fun getAllTag(context: Context) = MiPushClient.getAllTopic(context)


    /**
     * 清除所有标签
     */
    fun clearTag(context: Context) {
        getAllTag(context).forEach { deleteTag(context, it) }
    }

    /**
     * 清除小米推送弹出的某一个notifyId通知。
     */
    fun clearNotification(context: Context, notifyId: Int) {
        MiPushClient.clearNotification(context, notifyId)
    }

    /**
     * 清除小米推送弹出的所有通知。
     */
    fun clearAllNotification(context: Context) {
        MiPushClient.clearNotification(context)
    }

    /**
     * 获取客户端的RegId
     */
    fun getRegistrationId(context: Context, result: MethodChannel.Result) {
        val regId = MiPushClient.getRegId(context)
        result.success(regId)
    }

    /**
     * 暂停接收MiPush服务推送的消息，app在恢复MiPush推送服务之前，不接收任何推送消息
     * 注: 这里使用与RegId相关联的alias和topic推送消息，也是被暂停的
     */
    fun pausePush(context: Context) {
        MiPushClient.pausePush(context, null)
    }

    /**
     * 恢复接收MiPush服务推送的消息
     * 注: 这里使用与RegId相关联的alias和topic推送消息，也是被恢复的；这时服务器会把暂停时期的推送消息重新推送过来
     */
    fun resumePush(context: Context) {
        MiPushClient.resumePush(context, null)
    }

    /**
     * 启用MiPush推送服务。
     * 调用disablePush和enablePush接口后，不会生成新的regId，regId会和原来的保持一致。
     */
    fun enablePush(context: Context) {
        MiPushClient.enablePush(context)
    }

    /**
     * 禁用MiPush推送服务。
     * 调用disablePush和enablePush接口后，不会生成新的regId，regId会和原来的保持一致。
     */
    fun disablePush(context: Context) {
        MiPushClient.disablePush(context)
    }


    /**
     * 设置推送时间
     * 设置接收MiPush服务推送的时段，不在该时段的推送消息会被缓存起来，到了合适的时段再向app推送原先被缓存的消息。
     * 这里采用24小时制，如果开始时间早于结束时间，则这个时段落在一天内；否则，这个时间将会跨越凌晨0点。
     * 注: 这里使用与regId相关联的alias和topic推送消息，也会受到限制。
     * 如果时间设置为0:00-0:00，就是暂停push推送服务，也可以直接调用pausePush()方法，其本质相同
     * 如果时间设置为0:00-23:59，就是恢复push推送服务，即全天接收push推送消息，也可以直接调用resumePush()方法，其本质相同
     */
    fun setPushTime(context: Context, startHour: Int, startMinter: Int, endHour: Int, endMinter: Int) {
        MiPushClient.setAcceptTime(context, startHour, startMinter, endHour, endMinter, null)
    }
}