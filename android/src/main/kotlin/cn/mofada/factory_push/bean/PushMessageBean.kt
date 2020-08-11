package cn.mofada.factory_push.bean

import cn.mofada.factory_push.constant.MessageType
import org.json.JSONObject

/**
 * @name: fada
 * @email: fada@mofada.cn
 * @date: 2020/8/11 14:53
 * @description: 描述类
 */

data class PushMessageBean(
        val messageType: MessageType,
        //消息标题
        val title: String,
        //消息内容
        val content: String,
        //消息描述
        val description: String?,
        //分类
        val category: String?,
        //别名
        val alias: String?,
        //额外字段
        val extra: Map<String, String>
) {
    fun toJson(): String {
        val jsonObject = JSONObject()
        jsonObject.put("messageType", messageType)
        jsonObject.put("title", title)
        jsonObject.put("content", content)
        jsonObject.put("description", description)
        jsonObject.put("category", category)
        jsonObject.put("alias", alias)
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