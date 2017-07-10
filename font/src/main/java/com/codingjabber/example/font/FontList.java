package com.codingjabber.example.font;

import java.awt.*;

/**
 * 打印本地字体列表
 * Created by 蟹老板 on 2017/7/10.
 */
public class FontList {
    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontList = ge.getAvailableFontFamilyNames();
        for (int i = 0; i < fontList.length; i++) {
            System.out.println(fontList[i]);
        }
    }
}
