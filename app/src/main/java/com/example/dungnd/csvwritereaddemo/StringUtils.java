package com.example.dungnd.csvwritereaddemo;

import android.os.Environment;

public class StringUtils {
    public static final String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    public static final String OUTPUT = ROOT_DIR + "/Download/model.csv";
}
