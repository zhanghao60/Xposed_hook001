package com.app.hookcamera;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class Hook001 implements IXposedHookLoadPackage {
    private static final String TAG = "CameraReplacer";
    private static final String target = "com.netease.my";

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals(target)) {
            Log.d(TAG,"已经注入梦幻西游，开始Hook！");
            // 开始Hook
            Hook(loadPackageParam);
        }
    }

    private void Hook(XC_LoadPackage.LoadPackageParam loadPackageParam){
        // Hook Camera.open() 方法（打开摄像头）
        Class<?> cameraClass = XposedHelpers.findClass("android.hardware.Camera", loadPackageParam.classLoader);
        // 开始Hook
        XposedHelpers.findAndHookMethod(cameraClass,"open",int.class,new XC_MethodHook() {
            // 方法调用前拦截,用于修改参数,阻断调用
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                int cameraId = (int)param.args[0];
                Log.d(TAG,"游戏：梦幻西游————尝试打开摄像头，摄像头Id为："+cameraId);
            }
        });
    }
}