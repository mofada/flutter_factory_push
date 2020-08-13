package cn.mofada.factory_push.bean

import cn.mofada.factory_push.constant.MessageType
import org.json.JSONObject
import java.io.Serializable

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/11 14:53
 * @description: 描述类
 */

data class PushMessageBean(
        //消息id
        var messageId: String?,
        //消息类型
        val messageType: MessageType,
        //messageId, 用来取消通知
        var notifyId: Int = -1,
        //消息标题
        val title: String,
        //消息内容
        val message: String,
        //消息描述
        //分类
        val category: String?,
        //额外字段
        val extra: Map<String, String>
) {
    fun toJson(): String {
        val jsonObject = JSONObject()
        jsonObject.put("messageId", messageId)
        jsonObject.put("messageType", messageType)
        jsonObject.put("notifyId", notifyId)
        jsonObject.put("title", title)
        jsonObject.put("message", message)
        jsonObject.put("category", category)
        jsonObject.put("extra", mapToJson(extra))
        return jsonObject.toString()
    }

    private fun mapToJson(extra: Map<String, String>): JSONObject {
        val jsonObject = JSONObject()
        for (key in extra.keys) {
            jsonObject.put(key, extra[key])
        }
        return jsonObject
    }
}