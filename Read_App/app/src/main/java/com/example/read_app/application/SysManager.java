package com.example.read_app.application;

import com.example.read_app.R;
import com.example.read_app.common.APPCONST;
import com.example.read_app.entity.Setting;
import com.example.read_app.enums.Font;
import com.example.read_app.enums.Language;
import com.example.read_app.enums.ReadStyle;
import com.example.read_app.util.CacheHelper;


/**
 * Created by zhao on 2016/11/2.
 */

public class SysManager {

    public static void logout() {

    }

    /**
     * 获取设置
     * @return
     */
    public static Setting getSetting() {
        Setting setting = (Setting) CacheHelper.readObject(APPCONST.FILE_NAME_SETTING);
        if (setting == null){
            setting = getDefaultSetting();
            saveSetting(setting);
        }
        return setting;
    }

    /**
     * 保存设置
     * @param setting
     */
    public static void saveSetting(Setting setting) {
        CacheHelper.saveObject(setting, APPCONST.FILE_NAME_SETTING);
    }


    /**
     * 默认设置
     * @return
     */
    private static Setting getDefaultSetting(){
        Setting setting = new Setting();
        setting.setDayStyle(true);
        setting.setReadBgColor(R.color.sys_protect_eye_bg);
        setting.setReadStyle(ReadStyle.protectedEye);
        setting.setReadWordSize(20);
        setting.setReadWordColor(R.color.sys_protect_eye_word);
        setting.setBrightProgress(50);
        setting.setBrightFollowSystem(true);
        setting.setLanguage(Language.simplified);
        setting.setFont(Font.默认字体);
        setting.setAutoScrollSpeed(50);
        return setting;
    }


}
