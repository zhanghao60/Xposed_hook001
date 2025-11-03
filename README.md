# Xposed_hook001

一个基于 Xposed Framework 的 Android 摄像头 Hook 模块，用于拦截和监控指定应用的摄像头调用。

## 项目简介

本项目是一个 Xposed 模块，主要用于拦截 Android 应用中摄像头相关的 API 调用。目前主要针对**梦幻西游**游戏进行摄像头调用的监控和拦截。

## 功能特性

- ✅ Hook `android.hardware.Camera.open()` 方法
- ✅ 监控指定应用的摄像头调用
- ✅ 记录摄像头打开事件日志
- ✅ 可扩展的 Hook 框架

## 技术栈

- **开发语言**: Java
- **框架**: Xposed Framework API 82
- **开发工具**: Android Studio
- **最低 SDK**: Android 7.0 (API 24)
- **目标 SDK**: Android 14+ (API 36)

## 环境要求

- Android 设备已 Root
- 已安装 Xposed Framework 或兼容框架（如 LSPosed、EdXposed 等）
- Android 7.0 及以上系统

## 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/zhanghao60/Xposed_hook001.git
   cd Xposed_hook001
   ```

2. **构建项目**
   - 使用 Android Studio 打开项目
   - 等待 Gradle 同步完成
   - 点击 `Build > Build Bundle(s) / APK(s) > Build APK(s)`

3. **安装模块**
   - 将生成的 APK 安装到设备上
   - 在 Xposed Manager 或 LSPosed 中激活模块
   - 选择目标应用（默认：`com.netease.my` - 梦幻西游）
   - 重启设备或应用

## 使用方法

### 默认配置

模块默认 Hook 的应用包名：`com.netease.my`（梦幻西游）

### 自定义目标应用

在 `Hook001.java` 中修改 `target` 变量：

```java
private static final String target = "com.netease.my"; // 修改为你的目标应用包名
```

### 查看日志

Hook 事件会通过以下方式记录：
- Xposed Log（通过 XposedBridge.log）
- Android Logcat（标签：`CameraReplacer`）

查看日志命令：
```bash
adb logcat -s CameraReplacer
```

## 项目结构

```
app/
├── src/main/
│   ├── java/com/app/hookcamera/
│   │   ├── Hook001.java      # 主要 Hook 逻辑（摄像头拦截）
│   │   ├── HookTest.java      # 测试用 Hook 类
│   │   └── MainActivity.java  # 模块主界面
│   ├── assets/
│   │   └── xposed_init       # Xposed 入口配置文件
│   └── res/                   # 资源文件
└── build.gradle.kts          # 构建配置
```

## 核心代码说明

### Hook001.java

主要 Hook 类，用于拦截摄像头调用：

- Hook 方法：`android.hardware.Camera.open(int cameraId)`
- 监控点：摄像头打开时记录日志，包括摄像头 ID

### xposed_init

Xposed 模块入口配置，指定要加载的 Hook 类：
```
com.app.hookcamera.HookTest
com.app.hookcamera.Hook001
```

## 注意事项

⚠️ **重要提示**

1. 此模块仅供学习和研究使用
2. 使用前请确保目标应用允许被 Hook
3. 某些应用可能检测 Xposed 环境，导致无法正常运行
4. 请遵守相关法律法规，不得用于非法用途
5. 使用本模块可能导致目标应用功能异常，请谨慎使用

## 开发说明

### 扩展功能

如需 Hook 其他摄像头相关方法，可以在 `Hook()` 方法中添加：

```java
// Hook 其他方法示例
XposedHelpers.findAndHookMethod(cameraClass, "其他方法名", 参数类型.class, new XC_MethodHook() {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        // 拦截逻辑
    }
});
```

### 测试

`HookTest.java` 是一个测试类，用于测试 Hook 本应用自身的方法。

## 许可证

本项目仅供学习交流使用。

## 联系方式

如有问题或建议，欢迎提交 Issue。

---

**免责声明**: 本工具仅供安全研究和教育目的使用。使用者需自行承担使用本工具的风险和责任。
