package cn.mofada.factory_push.receiver

import android.util.Log
import com.heytap.msp.push.HeytapPushManager
import com.heytap.msp.push.callback.ICallBackResultService

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/19 15:06
 * @description: Oppo服务接收类
 */
class OppoPushReceiver : ICallBackResultService {
    private val TAG = "OppoPushReceiver"

    override fun onGetPushStatus(p0: Int, p1: Int) {
        Log.d(TAG, "onGetPushStatus() called with: p0 = $p0, p1 = $p1")
    }

    override fun onSetPushTime(p0: Int, p1: String?) {
        Log.d(TAG, "onSetPushTime() called with: p0 = $p0, p1 = $p1")
    }

    override fun onGetNotificationStatus(p0: Int, p1: Int) {
        Log.d(TAG, "onGetNotificationStatus() called with: p0 = $p0, p1 = $p1")
    }

    override fun onUnRegister(p0: Int) {
        Log.d(TAG, "onUnRegister() called with: p0 = $p0")
    }

    override fun onRegister(p0: Int, p1: String?) {
        Log.d(TAG, "onRegister() called with: p0 = $p0, p1 = $p1")
    }

}