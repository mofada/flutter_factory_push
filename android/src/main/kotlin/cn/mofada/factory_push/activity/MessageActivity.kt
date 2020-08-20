package cn.mofada.factory_push.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import cn.mofada.factory_push.bean.PushMessageBean
import cn.mofada.factory_push.constant.MessageType
import cn.mofada.factory_push.receiver.MessageReceiver
import cn.mofada.factory_push.util.ActivityUtil


/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/18
 * @description 华为推送消息接收类
 * 华为测试 IntentUri:  factory_push://cn.mofada.factory_push_example/message?#Intent;scheme=factory_push;launchFlags=0x4000000;i.age=180;S.name=abc;end
 * oppo推送 Activity: cn.mofada.factory_push.activity.MessageActivity
 */
class MessageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //关闭界面
        finish()

        MessageReceiver.sendMessageIntent(this, MessageType.NotificationClicked, extraToMessage(intent.extras))

        //启动主程序
        ActivityUtil.startMainActivity(this)
    }

    /**
     * 将bundle转成消息实体类

     */
    private fun extraToMessage(bundle: Bundle?): PushMessageBean =
            PushMessageBean(null, null, null, null, null, null, MessageReceiver.bundleToMap(bundle))

    companion object {
        /**
         * 生成IntentUri参数
         * 参考华为文档:
         * https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/android-client-dev-0000001050042041#ZH-CN_TOPIC_0000001050042041__section132431123150
         */
        private fun generateIntentUri(): String {
            val intent = Intent(Intent.ACTION_VIEW)
            // Scheme协议（例如：pushscheme://com.huawei.codelabpush/deeplink?）需要开发者自定义
            intent.data = Uri.parse("factory_push://cn.mofada.factory_push_example/message?")
            // 往intent中添加参数，用户可以根据自己的需求进行添加参数：
            intent.putExtra("name", "abc")
            intent.putExtra("age", 180)
            // 应用必须带上该Flag，如果不添加该选项有可能会显示重复的消息
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            // 打印出的intentUri值就是设置到推送消息中intent字段的值
            return intent.toUri(Intent.URI_INTENT_SCHEME)
        }
    }

}