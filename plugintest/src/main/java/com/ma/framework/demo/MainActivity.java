package com.ma.framework.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ma.pluginframework.PluginHelper;
import com.ma.pluginframework.domain.event.TroopMsgEvent;
import com.ma.pluginframework.domain.richmsg.PluginMsg;
import com.ma.pluginframework.domain.richmsg.section.PluginImageSection;
import com.ma.pluginframework.domain.richmsg.section.PluginTextSection;
import com.ma.pluginframework.domain.richmsg.section.PluginVoiceSection;
import com.ma.pluginframework.service.HostConnection;
import com.ma.pluginframework.service.WakeupService;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 */
public class MainActivity extends AppCompatActivity implements ServiceConnection {

    HostConnection hostConnection;
    MsgProcessingService mService;

    static {
        PluginHelper.OPEN_LOG = BuildConfig.DEBUG;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setTitle(getResources().getString(R.string.app_name) + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        EditText test_uin = findViewById(R.id.test_uin);
        EditText test_guin = findViewById(R.id.test_guin);

        test_uin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                uin = Long.valueOf(s.toString());
            }
        });

        test_guin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                guin = Long.valueOf(s.toString());
            }
        });

        PluginHelper.log("启动");
        //启动消息处理服务
        WakeupService.startup(this, MsgProcessingService.class);

        //连接消息处理服务 记得unbind
        bindService(new Intent(this, MsgProcessingService.class), this, Context.BIND_AUTO_CREATE);
    }

    long guin = 0;
    long uin = 0;

    //<editor-fold desc="测试方法">
    Bitmap getTestBmp(String text) {
        Bitmap bitmap = PluginHelper.textAsBitmap(text, 25f);
        return bitmap;
    }

    public void ping(View view) throws RemoteException {
        //0 OK    -2 用户未开启本插件 -1需要重新连接主程序 先unbind 再bind
        PluginHelper.log("ping " + hostConnection.ping());
    }

    public void getInfo(View view) throws RemoteException {
        PluginHelper.log("getInfo:" + hostConnection.getInfo());
    }

    public void obtainHostData(View view) throws RemoteException {
        hostConnection.obtainHostData();
    }

    public void obtainHostDataSync(View view) throws RemoteException {
        PluginHelper.log("obtainHostDataSync:" + hostConnection.obtainHostDataSync());

    }

    public void getTroop(View view) throws RemoteException {
        PluginHelper.log("getTroop " + hostConnection.getTroop(PluginHelper.DEFAULT_ACTIVE_UIN, guin));
    }

    public void getBuddy(View view) throws RemoteException {
        PluginHelper.log("getBuddy " + hostConnection.getBuddy(PluginHelper.DEFAULT_ACTIVE_UIN, uin));

    }

    public void getAccountData(View view) throws RemoteException {
        PluginHelper.log("getAccountData " + hostConnection.getAccountData(PluginHelper.DEFAULT_ACTIVE_UIN));

    }

    public void sendBuddyMessage(View view) throws IOException, RemoteException, JSONException {
        PluginMsg msg = new PluginMsg();
        msg.getPluginMsgSections().add(new PluginTextSection("好友消息:你好"));
        File dir = new File(getExternalCacheDir(), "test");
        if (!dir.exists()) dir.mkdirs();
        Bitmap bitmap = getTestBmp("好友图像测试");
        File imageFile = new File(dir, "test.png");
        OutputStream bout = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bout);
        bout.close();
        msg.getPluginMsgSections().add(new PluginImageSection(imageFile.getAbsolutePath()));
        hostConnection.sendBuddyMessage(PluginHelper.DEFAULT_ACTIVE_UIN, uin, msg);
    }

    public void sendBuddyMessageVoice(View view) throws IOException, RemoteException, JSONException {
        File dir = new File(getExternalCacheDir(), "test");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, "test.amr");
        InputStream in = null;
        try {
            in = getAssets().open("test.amr");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "请将语音文件放到assets", Toast.LENGTH_SHORT).show();
            return;
        }
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 10];
        int n;
        while ((n = in.read(buffer)) != -1) {
            outputStream.write(buffer, 0, n);
        }
        outputStream.flush();
        outputStream.close();

        PluginMsg msg = new PluginMsg();
        msg.getPluginMsgSections().add(new PluginVoiceSection(file.getAbsolutePath()));
        hostConnection.sendBuddyMessage(PluginHelper.DEFAULT_ACTIVE_UIN, uin, msg);
    }

    public void sendTroopMessage(View view) throws IOException, RemoteException, JSONException {
        PluginMsg msg = new PluginMsg();
        msg.getPluginMsgSections().add(new PluginTextSection("群消息:你好"));
        File dir = new File(getExternalCacheDir(), "test");
        if (!dir.exists()) dir.mkdirs();
        Bitmap bitmap = getTestBmp("群图像测试");
        File imageFile = new File(dir, "test.png");
        OutputStream bout = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bout);
        bout.close();
        msg.getPluginMsgSections().add(new PluginImageSection(imageFile.getAbsolutePath()));
        hostConnection.sendTroopMessage(PluginHelper.DEFAULT_ACTIVE_UIN, guin, msg);
    }

    public void sendTroopMessageVoice(View view) throws IOException, RemoteException, JSONException {
        File dir = new File(getExternalCacheDir(), "test");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, "test.amr");
        InputStream in = null;
        try {
            in = getAssets().open("test.amr");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "请将语音文件放到assets", Toast.LENGTH_SHORT).show();
            return;
        }
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 10];
        int n;
        while ((n = in.read(buffer)) != -1) {
            outputStream.write(buffer, 0, n);
        }
        outputStream.flush();
        outputStream.close();

        PluginMsg msg = new PluginMsg();
        msg.getPluginMsgSections().add(new PluginVoiceSection(file.getAbsolutePath()));
        hostConnection.sendTroopMessage(PluginHelper.DEFAULT_ACTIVE_UIN, guin, msg);
    }

    public void sendTroopXml(View view) throws RemoteException {
        hostConnection.sendTroopXml(PluginHelper.DEFAULT_ACTIVE_UIN, guin, "<msg serviceID=\"1\" action=\"web\" brief=\"QQ空间 的分享\" url=\"http://baidu.com/\" flag=\"3\" >\n" +
                "<item layout=\"2\">\n" +
                "<picture cover=\"http://wolfxyb.com/images/icon.png\" w=\"0\" h=\"0\" />\n" +
                "<title>hhhhhjjjj</title>\n" +
                "<summary>点击查看</summary>\n" +
                "</item>\n" +
                "</msg>");
    }

    public void sendTroopMemberText(View view) throws RemoteException {
        hostConnection.sendTroopMemberText(PluginHelper.DEFAULT_ACTIVE_UIN, guin, uin, "群成员文本你好哈哈春节了");
    }

    //
    public void leaveTroop(View view) throws RemoteException {
        hostConnection.leaveTroop(PluginHelper.DEFAULT_ACTIVE_UIN, guin);
    }

    public void deleteBuddy(View view) throws RemoteException {
        hostConnection.deleteBuddy(PluginHelper.DEFAULT_ACTIVE_UIN, uin);
    }

    public void removeTroopMember(View view) throws RemoteException {
        hostConnection.removeTroopMember(PluginHelper.DEFAULT_ACTIVE_UIN, guin, uin);
    }

    public void shutupTroop(View view) throws RemoteException {
        hostConnection.shutupTroop(PluginHelper.DEFAULT_ACTIVE_UIN, guin, 0L, 0);
    }

    public void shutupTroopPerson(View view) throws RemoteException {
        hostConnection.shutupTroop(PluginHelper.DEFAULT_ACTIVE_UIN, guin, uin, 60);
    }

    public void restoreShutupTroop(View view) throws RemoteException {
        hostConnection.restoreShutupTroop(PluginHelper.DEFAULT_ACTIVE_UIN, guin, 0L);
    }

    public void restoreShutupTroopPerson(View view) throws RemoteException {
        hostConnection.restoreShutupTroop(PluginHelper.DEFAULT_ACTIVE_UIN, guin, uin);
    }

    public void modifyTroopMemberMark(View view) throws RemoteException {
        hostConnection.modifyTroopMemberMark(PluginHelper.DEFAULT_ACTIVE_UIN, guin, uin, "MARK测试");
    }

    public void getTroopMember(View view) throws RemoteException {
        hostConnection.getTroopMember(PluginHelper.DEFAULT_ACTIVE_UIN, guin);
    }

    TroopMsgEvent event;

    public void withDrawMsg(View view) throws RemoteException {
        hostConnection.withDrawMsg(PluginHelper.DEFAULT_ACTIVE_UIN, guin, event.getMsgId(), event.getRand3());
    }

    //
    public void reqFavorite(View view) throws RemoteException {
        hostConnection.reqFavorite(PluginHelper.DEFAULT_ACTIVE_UIN, uin, 1, 2);
    }

    public void reqFavoriteList(View view) throws RemoteException {
        hostConnection.reqFavoriteList(PluginHelper.DEFAULT_ACTIVE_UIN, true);
    }

    boolean b = true;

    public void cardSwitch(View view) throws RemoteException {
        hostConnection.cardSwitch(PluginHelper.DEFAULT_ACTIVE_UIN, b);
        b = !b;
    }

    public void cardNotifySwitch(View view) throws RemoteException {
        hostConnection.cardNotifySwitch(PluginHelper.DEFAULT_ACTIVE_UIN, b);
        b = !b;
    }

    public void reqPraiseRank(View view) throws RemoteException {
        hostConnection.reqPraiseRank(PluginHelper.DEFAULT_ACTIVE_UIN, true);
    }

    public void queryTag(View view) throws RemoteException {
        hostConnection.queryTag(PluginHelper.DEFAULT_ACTIVE_UIN, uin);
    }

    long tagId = 0L;

    public void reqFavTag(View view) throws RemoteException {
        hostConnection.reqFavTag(PluginHelper.DEFAULT_ACTIVE_UIN, uin, tagId);
    }

    public void reqVoterList(View view) throws RemoteException {
        hostConnection.reqVoterList(PluginHelper.DEFAULT_ACTIVE_UIN, true);
    }

    public void queryRobotOpen(View view) throws RemoteException {
        PluginHelper.log("queryRobotOpen guin:" + hostConnection.queryRobotOpen(PluginHelper.DEFAULT_ACTIVE_UIN, 0, guin));
        PluginHelper.log("queryRobotOpen uin:" + hostConnection.queryRobotOpen(PluginHelper.DEFAULT_ACTIVE_UIN, 1, uin));
    }

    public void querySendOpen(View view) throws RemoteException {
        PluginHelper.log("querySendOpen guin:" + hostConnection.querySendOpen(PluginHelper.DEFAULT_ACTIVE_UIN, 0, guin));
        PluginHelper.log("querySendOpen uin:" + hostConnection.querySendOpen(PluginHelper.DEFAULT_ACTIVE_UIN, 1, uin));
    }

    public void sendBuddyNudge(View view) throws RemoteException {
        hostConnection.sendBuddyNudge(PluginHelper.DEFAULT_ACTIVE_UIN, uin);
    }

    public void sendTroopJSON(View view) throws RemoteException {
        hostConnection.sendTroopJson(PluginHelper.DEFAULT_ACTIVE_UIN, guin, "{\"config\":{\"height\":0,\"forward\":1,\"ctime\":1613996387,\"width\":0,\"type\":\"normal\",\"token\":\"591ea7385984b3c381284bd2f4ba403b\",\"autoSize\":0},\"prompt\":\"[QQ小程序]收集表\",\"app\":\"com.tencent.miniapp_01\",\"ver\":\"0.0.0.1\",\"view\":\"view_8C8E89B49BE609866298ADDFF2DBABA4\",\"meta\":{\"detail_1\":{\"appid\":\"1108961705\",\"preview\":\"pubminishare-30161.picsz.qpic.cn\\/15C2110E-52B6-4B4A-AF8C-E4F86D0CAB91\",\"shareTemplateData\":{},\"gamePointsUrl\":\"\",\"gamePoints\":\"\",\"url\":\"m.q.qq.com\\/a\\/s\\/e832414c6d5b50c7e9414cf988699d75\",\"scene\":0,\"desc\":\"收集表\",\"title\":\"收集表\",\"host\":{\"uin\":3100816665,\"nick\":\"GreyWolf\"},\"shareTemplateId\":\"8C8E89B49BE609866298ADDFF2DBABA4\",\"icon\":\"https:\\/\\/miniapp.gtimg.cn\\/public\\/appicon\\/b29cb47b2471c6b4b652f2b51a3a4120_200.jpg\",\"showLittleTail\":\"\"}},\"desc\":\"\"}");
    }

    public void sendTroopNudge(View view) throws RemoteException {
        hostConnection.sendTroopNudge(PluginHelper.DEFAULT_ACTIVE_UIN, guin, uin);
    }

    public void bind(View view) {
        hostConnection.bindHost();
    }

    public void unbind(View view) {
        hostConnection.unbind();
    }
    //</editor-fold>


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (BuildConfig.DEBUG) {
            MenuItem item = menu.findItem(R.id.menu_test);
            item.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, WebActivity.class));
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a connection to the Service has been established, with
     * the {@link IBinder} of the communication channel to the
     * Service.
     *
     * @param name    The concrete component name of the service that has
     *                been connected.
     * @param service The IBinder of the Service's communication channel,
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MsgProcessingService.LocalBinder binder = (MsgProcessingService.LocalBinder) service;
        mService = binder.getService();
        hostConnection = mService.getHostConnection();
        try {
            int pingRt = hostConnection.ping();
            if (pingRt == -2) {
                Toast.makeText(MainActivity.this, "用户未开启本插件", Toast.LENGTH_SHORT).show();
            } else if (pingRt == -1) {
                Toast.makeText(MainActivity.this, "要重新连接主程序", Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when a connection to the Service has been lost.  This typically
     * happens when the process hosting the service has crashed or been killed.
     * This does <em>not</em> remove the ServiceConnection itself -- this
     * binding to the service will remain active, and you will receive a call
     * to {@link #onServiceConnected} when the Service is next running.
     *
     * @param name The concrete component name of the service whose
     *             connection has been lost.
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        hostConnection = null;
    }

    /**
     * Called when the binding to this connection is dead.  This means the
     * interface will never receive another connection.  The application will
     * need to unbind and rebind the connection to activate it again.  This may
     * happen, for example, if the application hosting the service it is bound to
     * has been updated.
     *
     * @param name The concrete component name of the service whose
     *             connection is dead.
     */
    @Override
    public void onBindingDied(ComponentName name) {
        mService = null;
        hostConnection = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //解除与消息处理服务的绑定
        unbindService(this);
        mService = null;
        hostConnection = null;
    }
}
