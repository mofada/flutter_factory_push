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
     * 停止推送
     */
    StopPush,

    /**
     * 设置别名
     * 小米: setAlias(Context context, String alias, String category)
     */
    SetAlias,

    /**
     * 取消别名
     * 小米: unsetAlias(Context context, String alias, String category)
     */
    DeleteAlias,

    /**
     * 清除所有别名
     */
    CleanAlias,

    /**
     * 获取所有的别名
     * 小米: getAllAlias(final Context context)
     */
    GetAlias,

    /**
     * 设置标签, 小米的叫主题
     * 小米: 为某个用户设置订阅主题, subscribe(Context context, String topic, String category)
     *
     */
    SetTag,

    /**
     * 删除标签, 小米的叫主题
     * 小米: unsubscribe(Context context, String topic, String category)
     */
    DeleteTag,

    /**
     * 获取所有标签, 小米的叫主题
     * 小米: getAllTopic(final Context context)
     */
    GetTags,

    /**
     * 清除所有的tag, 小米的叫主题
     */
    CleanTag,

    /**
     * 清除通知
     */
    ClearNotification,

    /**
     * 暂停通知
     * 小米: pausePush(Context context, String category)
     */
    PausePush,

    /**
     * 恢复通知
     * 小米: resumePush(Context context, String category)
     */
    ResumePush,

    /**
     * 启用推送
     * 小米: enablePush(final Context context)
     */
    EnablePush,

    /**
     * 禁用推送
     * 小米: disablePush(final Context context)
     */
    DisablePush,

    /**
     * 获取客户端的RegistrationId
     */
    RegistrationId,

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