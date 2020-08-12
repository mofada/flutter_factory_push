package cn.mofada.factory_push.constant

/**
 * @author fada
 * @email fada@mofada.cn
 * @date 2020/8/10
 * @description 方法名称
 */
enum class MethodName {
    /**
     * 获取android版本
     */
    GetPlatformVersion,

    /**
     * 初始化
     * @param xiaomiKey
     * @param xiaomi
     */
    Setup,

    /**
     * 设置调试模式
     */
    SetDebugMode,

    /**
     * 停止推送
     */
    StopPush,

    /**
     * 设置别名
     */
    SetAlias,

    /**
     * 取消别名
     */
    DeleteAlias,

    /**
     * 获取所有的别名
     * 
     */
    GetAllAlias,

    /**
     * 清除所有别名
     */
    CleanAlias,

    /**
     * 设置标签, 小米的叫主题
     *
     */
    AddTag,

    /**
     * 设置标签, 小米的叫主题
     *
     */
    AddTags,

    /**
     * 删除标签, 小米的叫主题
     */
    DeleteTag,

    /**
     * 获取所有标签, 小米的叫主题
     */
    GetAllTag,

    /**
     * 清除所有的tag, 小米的叫主题
     */
    CleanTag,

    /**
     * 清除通知
     */
    ClearNotification,

    /**
     * 清除所有的通知
     */
    ClearAllNotification,

    /**
     * 暂停通知
     */
    PausePush,

    /**
     * 恢复通知
     */
    ResumePush,

    /**
     * 启用推送
     */
    EnablePush,

    /**
     * 禁用推送
     */
    DisablePush,

    /**
     * 获取客户端的RegistrationId
     */
    GetRegistrationId,

    /**
     * 设置推送时间
     */
    SetPushTime,

    /**
     * 是否打开通知
     */
    IsNotificationEnabled,

    /**
     * 前往通知打开界面
     */
    OpenNotification,
}