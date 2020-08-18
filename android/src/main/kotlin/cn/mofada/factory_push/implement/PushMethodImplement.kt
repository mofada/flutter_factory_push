package cn.mofada.factory_push.implement

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat
import cn.mofada.factory_push.constant.ArgumentName
import cn.mofada.factory_push.util.ManufacturerUtil
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 推送插件实现
 */
object PushMethodImplement {
    const val TAG = "FactoryPushPlugin"

    /**
     * 初始化设置
     * 小米: registerPush(Context context, String appID, String appKey)
     */
    fun setup(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //如果是小米手机, 并且有小米的id和key
        if (ManufacturerUtil.isXIAOMI() &&
                call.hasArgument(ArgumentName.XIAOMI_APP_ID) &&
                call.hasArgument(ArgumentName.XIAOMI_APP_KEY)) {
            //应用id
            val appId = call.argument<String>(ArgumentName.XIAOMI_APP_ID)
            //应用key
            val appKey = call.argument<String>(ArgumentName.XIAOMI_APP_KEY)
            XiaoMiPushImplement.setup(context, appId!!, appKey!!)
        }
        //华为手机, 华为推送
        if (ManufacturerUtil.isHUAWEI() ||
                call.hasArgument(ArgumentName.HUAWEI_APP_ID)) {
            val appid = call.argument<String>(ArgumentName.HUAWEI_APP_ID)
            HuaWeiPushImplement.setup(context, appid!!)
        }
        result.success(null)
    }

    /**
     * 设置调试模式
     */
    fun setDebugMode(context: Context, call: MethodCall, result: MethodChannel.Result) {
        val debugMode = call.argument<Boolean>(ArgumentName.DEBUG_MODE) ?: false
        //小米推送
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.setDebugMode(context, debugMode)
        }
        result.success(null)
    }


    /**
     * 停止推送
     * 小米: unregisterPush(Context context)
     */
    fun stopPush(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //小米推送
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.stopPush(context)
        }
        result.success(null)
    }


    /**
     * 设置别名
     */
    fun setAlias(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //检查参数
        if (!call.hasArgument(ArgumentName.ALIAS)) {
            result.error("setAlias no alias argument", "setAlias方法没有alias参数", "Please pass in the alias parameter")
            return
        }
        val alias = call.argument<String>(ArgumentName.ALIAS)
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.setAlias(context, alias!!)
        }
        result.success(null)
    }

    /**
     * 删除别名
     */
    fun deleteAlias(context: Context, call: MethodCall, result: MethodChannel.Result) {
        if (!call.hasArgument(ArgumentName.ALIAS)) {
            result.error("deleteAlias no alias argument", "deleteAlias方法没有alias参数", "Please pass in the alias parameter")
            return
        }
        val alias = call.argument<String>(ArgumentName.ALIAS)
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.deleteAlias(context, alias!!)
        }
        result.success(null)
    }

    /**
     * 获取所有的别名
     */
    fun getAllAlias(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> result.success(XiaoMiPushImplement.getAllAlias(context))
        }
    }

    /**
     * 清除所有别名
     */
    fun cleanAlias(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.cleanAlias(context)
        }
        result.success(null)
    }

    /**
     * 设置标签
     */
    fun addTag(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //检查参数
        if (!call.hasArgument(ArgumentName.TAG)) {
            result.error("setTag no Tag argument", "setTag方法没有tag参数", "Please pass in the tag parameter")
            return
        }
        val tag = call.argument<String>(ArgumentName.TAG)
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.addTag(context, tag!!)
            ManufacturerUtil.isHUAWEI() -> HuaWeiPushImplement.addTag(context, tag!!)
        }
        result.success(null)
    }

    /**
     * 设置标签
     */
    fun addTags(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //检查参数
        if (!call.hasArgument(ArgumentName.TAGS)) {
            result.error("setTags no Tags argument", "setTags方法没有tag参数", "Please pass in the tags parameter")
            return
        }
        val tags = call.argument<List<String>>(ArgumentName.TAGS)
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.addTags(context, tags!!)
        }
        result.success(null)
    }

    /**
     * 删除标签
     */
    fun deleteTag(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //检查参数
        if (!call.hasArgument(ArgumentName.TAG)) {
            result.error("deleteTag no Tags argument", "deleteTag方法没有tag参数", "Please pass in the tag parameter")
            return
        }
        val tag = call.argument<String>(ArgumentName.TAG)
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.deleteTag(context, tag!!)
        }
        result.success(null)
    }

    /**
     * 获取所有标签
     */
    fun getAllTag(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> result.success(XiaoMiPushImplement.getAllTag(context))
        }
    }

    /**
     * 清除所有的标签
     */
    fun cleanTag(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.clearTag(context)
        }
        result.success(null)
    }

    /**
     * 清除指定id的通知
     */
    fun clearNotification(context: Context, call: MethodCall, result: MethodChannel.Result) {
        if (!call.hasArgument(ArgumentName.NOTIFY_ID)) {
            result.error("clearNotification no notifyId argument", "clearNotification 方法没有tag参数", "Please pass in the notifyId parameter")
            return
        }
        val notifyId = call.argument<Int>(ArgumentName.NOTIFY_ID)
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.clearNotification(context, notifyId!!)
        }
        result.success(null)
    }

    /**
     * 清除所有的通知
     */
    fun clearAllNotification(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.clearAllNotification(context)
        }
        result.success(null)
    }

    /**
     * 暂停推送
     */
    fun pausePush(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.pausePush(context)
        }
        result.success(null)
    }

    /**
     * 恢复推送
     */
    fun resumePush(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.resumePush(context)
        }
        result.success(null)
    }

    /**
     * 启用推送服务。
     */
    fun enablePush(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.enablePush(context)
        }
        result.success(null)
    }

    /**
     * 禁用推送服务。
     */
    fun disablePush(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> XiaoMiPushImplement.disablePush(context)
        }
        result.success(null)
    }

    /**
     * 获取注册id
     */
    fun getRegistrationId(context: Context, call: MethodCall, result: MethodChannel.Result) {
        when {
            ManufacturerUtil.isXIAOMI() -> result.success(XiaoMiPushImplement.getRegistrationId(context))
            ManufacturerUtil.isHUAWEI() -> HuaWeiPushImplement.getRegistrationId(context,result)
        }
    }

    /**
     * 设置推送时间
     */
    fun setPushTime(context: Context, call: MethodCall, result: MethodChannel.Result) {
        //开始时间
        if (!call.hasArgument(ArgumentName.START_HOUR)) {
            result.error("setPushTime no startHour argument", "setPushTime 方法没有 startHour 参数", "Please pass in the startHour parameter")
            return
        }
        val startHour = call.argument<Int>(ArgumentName.START_HOUR)
        //开始分钟
        if (!call.hasArgument(ArgumentName.START_MINTER)) {
            result.error("setPushTime no startMinter argument", "setPushTime 方法没有 startMinter 参数", "Please pass in the startMinter parameter")
            return
        }
        val startMinter = call.argument<Int>(ArgumentName.START_MINTER)
        //结束时间
        if (!call.hasArgument(ArgumentName.END_HOUR)) {
            result.error("setPushTime no endHour argument", "setPushTime 方法没有 endHour 参数", "Please pass in the endHour parameter")
            return
        }
        val endHour = call.argument<Int>(ArgumentName.END_HOUR)
        //结束分钟
        if (!call.hasArgument(ArgumentName.END_MINTER)) {
            result.error("setPushTime no endMinter argument", "setPushTime 方法没有 endMinter 参数", "Please pass in the endMinter parameter")
            return
        }
        val endMinter = call.argument<Int>(ArgumentName.END_MINTER)

        when {
            ManufacturerUtil.isXIAOMI() -> result.success(XiaoMiPushImplement.setPushTime(context, startHour!!, startMinter!!, endHour!!, endMinter!!))
        }
    }

    /**
     * 是否开启通知栏
     */
    fun isNotificationEnabled(context: Context, call: MethodCall, result: MethodChannel.Result) {
        result.success(NotificationManagerCompat.from(context).areNotificationsEnabled())
    }

    /**
     * 打开通知栏
     */
    fun openNotification(context: Context, call: MethodCall, result: MethodChannel.Result) {
//        val intent = Intent()
//        when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
//                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
//            }
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
//                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
//                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
//            }
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1 -> {
//                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
//                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
//            }
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
//                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
//                intent.putExtra("app_package", context.packageName)
//                intent.putExtra("app_uid", context.applicationInfo.uid)
//            }
//            Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT -> {
//                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                intent.addCategory(Intent.CATEGORY_DEFAULT)
//                intent.data = Uri.parse("package:" + context.packageName)
//            }
//        }
//        context.startActivity(intent)

        val intent = Intent()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.putExtra("app_package", context.packageName)
                intent.putExtra("app_uid", context.applicationInfo.uid)
            }
            else -> {
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:" + context.packageName)
            }
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

        result.success(null)
    }

    /**
     * 获取手机厂商类型
     */
    fun manufacturer(context: Context, call: MethodCall, result: MethodChannel.Result) {
        val manufacturer = when {
            ManufacturerUtil.isXIAOMI() -> ManufacturerUtil.MANUFACTURER_XIAOMI
            ManufacturerUtil.isHUAWEI() -> ManufacturerUtil.MANUFACTURER_HUAWEI
            ManufacturerUtil.isMEIZU() -> ManufacturerUtil.MANUFACTURER_MEIZU
            ManufacturerUtil.isOPPO() -> ManufacturerUtil.MANUFACTURER_OPPO
            ManufacturerUtil.isVIVO() -> ManufacturerUtil.MANUFACTURER_VIVO
            else -> ManufacturerUtil.MANUFACTURER_OTHER
        }
        result.success(manufacturer)
    }
}