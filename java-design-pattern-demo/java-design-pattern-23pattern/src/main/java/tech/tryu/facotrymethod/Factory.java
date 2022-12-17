package tech.tryu.facotrymethod;

import tech.tryu.facotrymethod.creator.Dialog;
import tech.tryu.facotrymethod.creator.WindowsDialog;
import tech.tryu.facotrymethod.creator.IosDialog;
import tech.tryu.facotrymethod.creator.WebDialog;

/**
 * 用于创建对应的对象，为了将创建过程和client代码解耦
 *
 * @author YU
 * @date 2022-04-30 19:56
 */
public class Factory {

    /**
     * 获取创建商品的工厂 ： 简单工厂模式
     *
     * @author YU
     * @return   {@link Dialog}
     */
    public static Dialog createDialog(String deviceType) {
        Dialog dialog = null;
        // 一种是单纯的返回对象创建（存入到缓存中，获取到，内有对象的初始化）; 这部分应该单独写一个Factory类调用
        switch (deviceType) {
            case "windows": dialog = new WindowsDialog(); break;
            case "ios": dialog = new IosDialog(); break;
            case "web": dialog = new WebDialog(); break;
            default: throw new IllegalArgumentException("the param is illegal");
        }
        return dialog;
    }

}
