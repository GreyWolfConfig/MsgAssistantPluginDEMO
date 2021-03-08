# [MsgAssistant](http://portal.52bestsoft.com/) 插件DEMO

## [MsgAssistant](http://portal.52bestsoft.com/) 简介
        MsgAssistant是一款运行在手机上的高性能机器人框架,支持多账号同时操作
    具备完善的插件交互API,通过这些API,您可以做一些有趣的插件,每个插件都是一个完整的App,开发流程简单,拥有无限的可能性
    欢迎来加入我们!

### 优势

1. 主程序核心由C++编写,性能有保障
1. 支持多账号同时在线,支持批量
1. 插件API功能丰富,满足绝大部分使用场景
1. 具有一定数量的用户群

### 如果你使用AIDE开发插件 [AIDE开发Demo下载](https://gitee.com/GreyWolf007/MAPluginDemo/raw/master/MAPluginAIDEQuickStart.zip) 
    
### 插件开发流程
1. 在 AndroidManifest.xml 声明插件
    ```
        <meta-data
            android:name="ma_plgin_author"
            android:value="*作者" />
        <meta-data
            android:name="ma_plgin_desc"
            android:value="简介" />

        <meta-data
            android:name="ma_plgin_website"
            android:value="官网URL-在插件列表长按菜单里面可以跳转官网" />

        <meta-data
            android:name="ma_plgin_icon"
            android:value="图标URL-可选-留空则取apk的图标" />
    ```
    ps:plugin拼写错误是主程序历史问题(可能当时手抖),请淡定~,使用时直接复制以上代码片段就好

2. 添加依赖
    ```
    implementation  project(':pluginlib')
    ```
3. 创建消息处理服务

    + AndroidManifest.xml中声明服务
        ```
        <service
            android:name=".MsgProcessingService"
            android:enabled="true"
            android:exported="true">
            <!--这个很重要,确保主程序可以唤醒该插件-->
            <intent-filter>
                <action android:name="com.ma.pluginframework.WAKEUP" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </service>
        ```
    + 创建服务Java文件
        ```
        public class MsgProcessingService extends WakeupService {
            public MsgProcessingService() {
            }

            public class LocalBinder extends Binder {
                MsgProcessingService getService() {
                    return MsgProcessingService.this;
                }
            }

            private final IBinder binder = new LocalBinder();

            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return binder;
            }

            @Override
            protected PluginAppInterface getAppInterface() {
                return new AppInterface(this, new PluginInfo("", "", "", ""));
            }

            /**
            * 这个类是监听主程序的各种消息
            */
            class AppInterface extends AbstractAppInterface {

                protected AppInterface(Context context, PluginInfo pluginInfo) {
                    super(context, pluginInfo);
                }
                //可以重写父类的方法从而监听感兴趣的消息
            }
        }
        ```


## 插件API支持的功能截止到2021-02-15

* 获取数据
    - 账号数据
    - 群数据
    - 群成员数据
    - 好友数据
* 发送群消息
    - [长]文本 
    - 图片 
    - 图文混合
    - 艾特 
    - 语音
    - XML/JSON
    - 戳一戳
    - 以上任意形式的组合
* 发送好友消息/群成员消息
    - [长]文本
    - 图片
    - 图文混合
    - 语音
    - 戳一戳
    - 以上任意形式的组合
* 接收群消息
    - 文本
    - 图片
    - 闪照
    - XML/JSON
    - 成员变动
    - 管理变动
    - 群主变动
    - 消息撤回
    - 其他
* 接收好友消息
    - 图片
    - 文本
* 好友管理
    - 删除好友
* 群管理
    - 退群
    - 删除群成员
    - 禁言
    - 解除禁言
    - 修改成员昵称
    - 撤回消息
* 点赞相关
    - 点赞
    - 查询已赞列表
    - 切换是否允许附近人赞我
    - 切换收到赞时是否通知
    - 查询获赞排行榜
    - 查询个性标签
    - 赞个性标签
* 需要主程序授权才能用的功能
    * 发送语音
    * 发送图片

### 上传您的插件到主程序
[点我加群](https://qm.qq.com/cgi-bin/qm/qr?k=3gtkZJEKCjYbTi6LjLXcmADPNxUINSnJ&jump_from=webapi)

文档依然在不断完善中...
