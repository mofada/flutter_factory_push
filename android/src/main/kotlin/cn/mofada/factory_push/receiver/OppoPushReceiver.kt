package cn.mofada.factory_push.receiver

import cn.mofada.factory_push.FactoryPushPlugin
import com.heytap.msp.push.callback.ICallBackResultService

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/19 15:06
 * @description: Oppo服务接收类
 */
class OppoPushReceiver : ICallBackResultService {

    override fun onGetPushStatus(code: Int, status: Int) {
    }

    override fun onSetPushTime(code: Int, p1: String?) {
    }

    override fun onGetNotificationStatus(code: Int, status: Int) {
    }

    override fun onUnRegister(code: Int) {
    }

    override fun onRegister(code: Int, registerId: String?) {
        registerId?.let {
            MessageReceiver.sendTokenIntent(FactoryPushPlugin.context, it)
        }
    }

}