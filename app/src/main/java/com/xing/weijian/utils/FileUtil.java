package com.xing.weijian.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/7/15.
 */

public class FileUtil {

    private FileUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean saveFileToSdcard(Context context, InputStream is, String name) {
        if (is == null) {
            return false;
        }
        String parentPath;
        if (SdcardUtil.isSdcardAvailable()) {
            parentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            parentPath = context.getFilesDir().getAbsolutePath();
        }
        File destFile = new File(parentPath, name);
        File parentFile = destFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fos = null;
        int len = 0;
        try {
            fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[3 * 1024];
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                    destFile = null;
                }
                is.close();
            } catch (Exception e) {

            }


        }
        return false;
    }


}
