package com.example.backup;

import android.content.Context;
import android.util.Log;
import java.io.*;

public class BackupUtils {
    private static final String TAG = "BackupUtils";

    public static void backupAppFiles(Context context) {
        File srcDir = context.getFilesDir();
        File backupDir = new File(context.getExternalFilesDir(null), "backup");
        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }
        copyDirectory(srcDir, backupDir);
    }

    private static void copyDirectory(File src, File dest) {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }
            String[] children = src.list();
            if (children != null) {
                for (String child : children) {
                    copyDirectory(new File(src, child), new File(dest, child));
                }
            }
        } else {
            copyFile(src, dest);
        }
    }

    private static void copyFile(File src, File dest) {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dest)) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to copy file: " + src.getAbsolutePath(), e);
        }
    }
}
