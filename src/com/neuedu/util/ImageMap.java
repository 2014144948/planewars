package com.neuedu.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01", ImageUtil.getImage("com/neuedu/imgs/bg/bg01.jpg"));
        map.put("bg02", ImageUtil.getImage("com/neuedu/imgs/bg/bg02.jpg"));
        map.put("bg03", ImageUtil.getImage("com/neuedu/imgs/bg/bg03.jpg"));
        map.put("bg04", ImageUtil.getImage("com/neuedu/imgs/bg/bg04.jpg"));
        map.put("bg05", ImageUtil.getImage("com/neuedu/imgs/bg/bg05.jpg"));
        map.put("bg06", ImageUtil.getImage("com/neuedu/imgs/bg/bg06.jpg"));
        map.put("bg07", ImageUtil.getImage("com/neuedu/imgs/bg/bg07.jpg"));
        map.put("bg08", ImageUtil.getImage("com/neuedu/imgs/bg/bg08.jpg"));
        map.put("bg09", ImageUtil.getImage("com/neuedu/imgs/bg/bg09.jpg"));

        map.put("my01", ImageUtil.getImage("com/neuedu/imgs/plane/my01.png"));
        map.put("my02", ImageUtil.getImage("com/neuedu/imgs/plane/my02.png"));
        map.put("mb01", ImageUtil.getImage("com/neuedu/imgs/bullet/mb01.png"));

        map.put("epA1", ImageUtil.getImage("com/neuedu/imgs/plane/epA1.png"));
        map.put("epA2", ImageUtil.getImage("com/neuedu/imgs/plane/epA2.png"));
        map.put("epA3", ImageUtil.getImage("com/neuedu/imgs/plane/epA3.png"));
        map.put("epA4", ImageUtil.getImage("com/neuedu/imgs/plane/epA4.png"));
        map.put("epB1", ImageUtil.getImage("com/neuedu/imgs/plane/epB1.png"));
        map.put("epB2", ImageUtil.getImage("com/neuedu/imgs/plane/epB2.png"));
        map.put("epB3", ImageUtil.getImage("com/neuedu/imgs/plane/epB3.png"));
        map.put("epB4", ImageUtil.getImage("com/neuedu/imgs/plane/epB4.png"));
        map.put("epC1", ImageUtil.getImage("com/neuedu/imgs/plane/epC1.png"));
        map.put("epC2", ImageUtil.getImage("com/neuedu/imgs/plane/epC2.png"));
        map.put("epC3", ImageUtil.getImage("com/neuedu/imgs/plane/epC3.png"));
        map.put("epC4", ImageUtil.getImage("com/neuedu/imgs/plane/epC4.png"));
        map.put("epD1", ImageUtil.getImage("com/neuedu/imgs/plane/epD1.png"));
        map.put("epD2", ImageUtil.getImage("com/neuedu/imgs/plane/epD2.png"));
        map.put("epD3", ImageUtil.getImage("com/neuedu/imgs/plane/epD3.png"));
        map.put("epD4", ImageUtil.getImage("com/neuedu/imgs/plane/epD4.png"));
        map.put("epE1", ImageUtil.getImage("com/neuedu/imgs/plane/epE1.png"));
        map.put("epE2", ImageUtil.getImage("com/neuedu/imgs/plane/epE2.png"));
        map.put("epE3", ImageUtil.getImage("com/neuedu/imgs/plane/epE3.png"));
        map.put("epE4", ImageUtil.getImage("com/neuedu/imgs/plane/epE4.png"));
        map.put("epE5", ImageUtil.getImage("com/neuedu/imgs/plane/epE5.png"));
        map.put("epE6", ImageUtil.getImage("com/neuedu/imgs/plane/epE6.png"));

        map.put("BOSS01", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS01.png"));
        map.put("BOSS02", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS02.png"));
        map.put("BOSS03", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS03.png"));
        map.put("BOSS04", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS04.png"));
        map.put("BOSS05", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS05.png"));
        map.put("BOSS06", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS06.png"));
        map.put("BOSS07", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS07.png"));
        map.put("BOSS08", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS08.png"));
        map.put("BOSS09", ImageUtil.getImage("com/neuedu/imgs/boss/BOSS09.png"));

        map.put("epb01", ImageUtil.getImage("com/neuedu/imgs/bullet/epb01.png"));
        map.put("epb02", ImageUtil.getImage("com/neuedu/imgs/bullet/epb02.png"));
        map.put("epb03", ImageUtil.getImage("com/neuedu/imgs/bullet/epb03.png"));
        map.put("epb04", ImageUtil.getImage("com/neuedu/imgs/bullet/epb04.png"));
        map.put("epb05", ImageUtil.getImage("com/neuedu/imgs/bullet/epb05.png"));
        map.put("epb06", ImageUtil.getImage("com/neuedu/imgs/bullet/epb06.png"));
        map.put("epb07", ImageUtil.getImage("com/neuedu/imgs/bullet/epb07.png"));
        map.put("myheart", ImageUtil.getImage("com/neuedu/imgs/heart/myheart.png"));
        map.put("stone",ImageUtil.getImage("com/neuedu/imgs/others/stone.png"));
        map.put("light",ImageUtil.getImage("com/neuedu/imgs/others/light.png"));

        map.put("s01",ImageUtil.getImage("com/neuedu/imgs/bullet/skill01.png"));
    }

    public static Image get(String key) {
        return map.get(key);
    }
}
