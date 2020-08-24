package cn.mofada.factory_push.implement

import android.content.Context
import cn.mofada.factory_push.receiver.OppoPushReceiver
import com.heytap.msp.push.HeytapPushManager
import io.flutter.plugin.common.MethodChannel

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
    fun setup(context: Context, appKey: String, appSecret: String, debugMode: Boolean) {
        //初始化
        HeytapPushManager.init(context, debugMode)
        //注册推送服务
        HeytapPushManager.register(context, appKey, appSecret, OppoPushReceiver())
    }

    /**
     * 停止推送, 注销推送
     */
    fun stopPush(context: Context) {
        HeytapPushManager.unRegister()
    }
    
    /**
     * 暂停推送
     */
    fun pausePush(context: Context) {
        HeytapPushManager.pausePush()
    }

    /**
     * 恢复推送
     */
    fun resumePush(context: Context) {
        HeytapPushManager.resumePush()
    }

    /**
     * 获取注册id
     */
    fun getRegistrationId(context: Context, result: MethodChannel.Result) {
        result.success(HeytapPushManager.getRegisterID())
    }

    /**
     * 设置允许推送时间 API
     *
     * @param weekDays 周日为0,周一为1,以此类推
     * @param startHour 开始时间,24小时制
     * @param endHour 结束时间,24小时制
     */
    fun setPushTime(context: Context, startHour: Int, startMinter: Int, endHour: Int, endMinter: Int, result: MethodChannel.Result) {
        HeytapPushManager.setPushTime(arrayListOf(0, 1, 2, 3, 4, 5, 6), startHour, startMinter, endHour, endMinter)
        result.success(null)
    }

    /**
     * 打开通知栏
     */
    fun openNotification() {
        HeytapPushManager.openNotificationSettings()
    }
}