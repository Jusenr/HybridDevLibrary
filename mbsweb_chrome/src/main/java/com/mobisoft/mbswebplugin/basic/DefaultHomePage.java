package com.mobisoft.mbswebplugin.basic;

import android.content.Context;
import android.os.Bundle;

import com.mobisoft.mbswebplugin.cmd.HomePage;
import com.mobisoft.mbswebplugin.helper.CoreConfig;
import com.mobisoft.mbswebplugin.helper.FunctionConfig;
import com.mobisoft.mbswebplugin.helper.ThemeConfig;
import com.mobisoft.mbswebplugin.utils.Preconditions;

/**
 * Author：Created by fan.xd on 2017/3/27.
 * Email：fang.xd@mobisoft.com.cn
 * Description：默认的返回首页
 */

public class DefaultHomePage implements Recycler.Recycleable, HomePage {

    @Override
    public void goHomePage(Context context, Bundle bundle, String url, String action) {
        CoreConfig coreConfig = new CoreConfig.Builder(context, ThemeConfig.DEFAULT, FunctionConfig.DEFAULT_ACTIVITY)
                .setURL(Preconditions.checkNotNull(url))
                .setAccount("8100458")//
                .setNoAnimcation(false)
                .setHideNavigation(true)
                .build();
//        HybridWebApp.init(coreConfig).startHomeActivity(context, MbsWebActivity.class,null);

    }

    @Override
    public void release() {

    }


}
