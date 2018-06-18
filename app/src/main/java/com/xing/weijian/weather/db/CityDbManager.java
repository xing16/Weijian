package com.xing.weijian.weather.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.xing.weijian.application.App;
import com.xing.weijian.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/5/7.
 */

public class CityDbManager {

    private static final String TAG = "CityDbManager";

    private static final String DB_NAME = "city.db";

    public static final String TABLE_PROVINCE = "tb_province";

    public static final String TABLE_CITY = "tb_city";

    public static final String PACKAGE_NAME = "com.xing.weijian";

    public static final String DB_PATH = "/data/data/" +
            PACKAGE_NAME;  //在手机里存放数据库的位置(/data/data/com.xiecc.seeWeather/china_city.db)

    private static CityDbManager instance;

    private SQLiteDatabase database;

    private CityDbManager() {
    }

    public static CityDbManager getInstance() {
        if (instance == null) {
            synchronized (CityDbManager.class) {
                if (instance == null) {
                    instance = new CityDbManager();
                }
            }
        }
        return instance;
    }


    public SQLiteDatabase openDatabase() {
        return openDatabase(DB_PATH + "/" + DB_NAME);
    }


    private SQLiteDatabase openDatabase(String filePath) {
        File dbFile = new File(filePath);
        if (!dbFile.exists()) {  // 数据库文件是否存在，不存在执行导入
            FileOutputStream fos = null;
            InputStream is = null;
            try {
                is = App.getContext().getResources().openRawResource(R.raw.city);
                fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d(TAG, "openDatabase: file not found");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "openDatabase: io error");
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(filePath, null);
    }

    public void closeDatabase() {
        if (database != null) {
            database.close();
        }
    }
}
