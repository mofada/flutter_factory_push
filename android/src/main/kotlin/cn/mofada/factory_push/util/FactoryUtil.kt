package cn.mofada.factory_push.util

import android.os.Build

/**
 * @author fada
 * @email fada@mofada.cn 
 * @date 2020/8/10 
 * @description 厂商工具类
 */
object FactoryUtil {
    /**
     * 小米
     */
    const val ROM_XIAOMI = "xiaomi"

    /**
     * 华为
     */
    const val ROM_HUAWEI = "huawei"

    /**
     * oppo
     */
    const val ROM_OPPO = "oppo"

    /**
     * vivo
     */
    const val ROM_VIVO = "vivo"

    /**
     * 魅族
     */
    const val ROM_MEIZU = "meizu"

    /**
     * 判断手机机型是否是小米
     */
    fun isXIAOMI(): Boolean {
        return ROM_XIAOMI.equals(Build.MANUFACTURER, true)
    }


    /**
     * 判断手机机型是否是华为
     */
    fun isHUAWEI(): Boolean {
        return ROM_HUAWEI.equals(Build.MANUFACTURER, true)
    }

    /**
     * 判断手机机型是否是OPPO
     */
    fun isOPPO(): Boolean {
        return ROM_OPPO.equals(Build.MANUFACTURER, true)
    }

    /**
     * 是否是VIVO
     */
    fun isVIVO(): Boolean {
        return ROM_VIVO.equals(Build.MANUFACTURER, true)
    }

    /**
     * 是否是魅族手机
     */
    fun isMEIZU(): Boolean {
        return ROM_MEIZU.equals(Build.MANUFACTURER, true)
    }
}