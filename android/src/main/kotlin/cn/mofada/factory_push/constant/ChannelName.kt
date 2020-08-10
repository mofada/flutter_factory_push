package cn.mofada.factory_push.constant

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 通道名称, 定义常量
 */
enum class ChannelName(val id: String) {
    /**
     * 推送插件
     */
    PUSH_PLUGIN("cn.mofada.factory_push"),

    /**
     * 广播接收插件
     */
    RECEIVER_PLUGIN("cn.mofada.factory_push_receiver")
}