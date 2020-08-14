package cn.mofada.factory_push.util

import android.os.Build

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 厂商工具类
 */
object ManufacturerUtil {
    /**
     * 小米
     */
    const val MANUFACTURER_XIAOMI = "xiaomi"

    /**
     * 华为
     */
    const val MANUFACTURER_HUAWEI = "huawei"

    /**
     * oppo
     */
    const val MANUFACTURER_OPPO = "oppo"

    /**
     * vivo
     */
    const val MANUFACTURER_VIVO = "vivo"

    /**
     * 魅族
     */
    const val MANUFACTURER_MEIZU = "meizu"

    /**
     * 其他厂商
     */
    const val MANUFACTURER_OTHER = "other"

    /**
     * 判断手机机型是否是小米
     */
    fun isXIAOMI(): Boolean {
        return MANUFACTURER_XIAOMI.equals(Build.MANUFACTURER, true)
    }


    /**
     * 判断手机机型是否是华为
     */
    fun isHUAWEI(): Boolean {
        return MANUFACTURER_HUAWEI.equals(Build.MANUFACTURER, true)
    }

    /**
     * 判断手机机型是否是OPPO
     */
    fun isOPPO(): Boolean {
        return MANUFACTURER_OPPO.equals(Build.MANUFACTURER, true)
    }

    /**
     * 是否是VIVO
     */
    fun isVIVO(): Boolean {
        return MANUFACTURER_VIVO.equals(Build.MANUFACTURER, true)
    }

    /**
     * 是否是魅族手机
     */
    fun isMEIZU(): Boolean {
        return MANUFACTURER_MEIZU.equals(Build.MANUFACTURER, true)
    }
}