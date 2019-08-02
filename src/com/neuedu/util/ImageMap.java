package com.neuedu.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg04", ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg04.jpg"));
        map.put("my01", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my01.png"));
        map.put("my02", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my02.png"));
        map.put("mb01", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mb01.png"));
        map.put("ep01", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep01.png"));
        map.put("ep02", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep02.png"));
        map.put("ep03", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep03.png"));
        map.put("ep04", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep04.png"));
        map.put("BOSS01", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\BOSS01.png"));
        map.put("BOSS02", ImageUtil.getImage("com\\neuedu\\imgs\\plane\\BOSS02.png"));
        map.put("epb01", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb01.png"));
        map.put("epb02", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb02.png"));
        map.put("epb03", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb03.png"));
        map.put("epb04", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb04.png"));
        map.put("epb05", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb05.png"));
        map.put("epb06", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb06.png"));
        map.put("epb07", ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb07.png"));
        map.put("myheart", ImageUtil.getImage("com\\neuedu\\imgs\\heart\\myheart.png"));
        map.put("HP", ImageUtil.getImage("com\\neuedu\\imgs\\HP\\HP.png"));
    }

    public static Image get(String key) {
        return map.get(key);
    }
}
