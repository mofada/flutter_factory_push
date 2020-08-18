package cn.mofada.factory_push.activity

import android.app.Activity
import android.os.Bundle
import cn.mofada.factory_push.bean.PushMessageBean
import cn.mofada.factory_push.constant.MessageType
import cn.mofada.factory_push.receiver.MessageReceiver


/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/18
 * @description 华为推送消息接收类
 * 测试 IntentUri:  factory_push://cn.mofada.factory_push_example/message?#Intent;scheme=factory_push;launchFlags=0x4000000;i.age=180;S.name=abc;end
 */
class HuaWeiMessageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MessageReceiver.sendMessageIntent(this, MessageType.NotificationClicked, extraToMessage(intent.extras))

        //关闭界面
        finish()
    }

    /**
     * 将bundle转成消息实体类
     */
    private fun extraToMessage(bundle: Bundle?): PushMessageBean =
            PushMessageBean(null, null, null, null, null, null, MessageReceiver.bundleToMap(bundle))

}