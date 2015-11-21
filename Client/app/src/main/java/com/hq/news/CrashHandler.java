package com.hq.news;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.SystemTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * To catch uncaught exceptions and record it.
 *
 * @author hq
 * @date 20/11/2015
 * @since 1.0
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    // suffix of the log file name
    public static final String FILE_NAME_SUFFIX = "HQNews.log";
    private static CrashHandler sInstance = null;

    private CrashHandler(Context cxt) {
        // 将当前实例设为系统默认的异常处理器
        //make this instance the deafult exception handler of system
        Thread.setDefaultUncaughtExceptionHandler(this);
        // get context
        mContext = cxt;
    }

    public synchronized static CrashHandler create(Context cxt) {
        if (sInstance == null) {
            sInstance = new CrashHandler(cxt);
        }
        return sInstance;
    }

    /**
     * When an uncaught exception occurs, system will call function uncaughtException
     * automatically.
     * thread is the thread where uncaught exception occurs
     * ex is the uncaught exception
     */
    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        //save the information of exception to SD card.
        try {
            saveToSDCard(ex);
        } catch (Exception e) {
        } finally {
            // ex.printStackTrace();// private the information when debug
            System.exit(0);
        }
    }

    public static void sendAppCrashReport(final Context context) {
        ViewInject.create().getExitDialog(context,
                "对不起，发生了未知的错误。请重新启动。", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(-1);
                    }
                });
    }

    private void saveToSDCard(Throwable ex) throws Exception {
        File file = FileUtils.getSaveFile(AppConfig.saveFolder,
                FILE_NAME_SUFFIX);
        boolean append = false;
        if (System.currentTimeMillis() - file.lastModified() > 5000) {
            append = true;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                file, append)));
        // time of the exception
        pw.println(SystemTool.getDataTime("yyyy-MM-dd-HH-mm-ss"));
        // mobile phone information
        dumpPhoneInfo(pw);
        pw.println();
        // the stack trace of exception
        ex.printStackTrace(pw);
        pw.println();
        pw.close();
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        // Version of app
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);
        pw.println();

        // Version of android
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        pw.println();

        // Manufacture of the phone
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        pw.println();

        // Model of the phone
        pw.print("Model: ");
        pw.println(Build.MODEL);
        pw.println();

        // cpu ABI of the phone
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
        pw.println();
    }
}
