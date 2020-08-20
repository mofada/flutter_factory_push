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

    override fun onGetPushStatus(code: Int, status: Int) {
        Log.d(TAG, "onGetPushStatus() called with: p0 = $code, p1 = $status")
    }

    override fun onSetPushTime(code: Int, p1: String?) {
        Log.d(TAG, "onSetPushTime() called with: p0 = $code, p1 = $p1")
    }

    override fun onGetNotificationStatus(code: Int, status: Int) {
        Log.d(TAG, "onGetNotificationStatus() called with: p0 = $code, p1 = $status")
    }

    override fun onUnRegister(code: Int) {
        Log.d(TAG, "onUnRegister() called with: p0 = $code")
    }

    override fun onRegister(code: Int, registerId: String?) {
        Log.d(TAG, "onRegister() called with: p0 = $code, p1 = $registerId")
    }

}