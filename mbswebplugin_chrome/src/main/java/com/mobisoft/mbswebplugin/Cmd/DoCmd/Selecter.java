package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.Entity.SelectEntity;
import com.mobisoft.mbswebplugin.Entity.SelectItem;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.R;
import com.mobisoft.mbswebplugin.utils.UrlUtil;

import java.util.List;

/**
 * Author：Created by fan.xd on 2017/8/7.
 * Email：fang.xd@mobisoft.com.cn
 * Description：选项卡页面
 */

public class Selecter extends DoCmdMethod {
    @Override
    public String doMethod(HybridWebView webView, final Context context, final MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, final String callBack) {
//       String json = "{\"selectItemList\":[{\"line\":\"前处理线1#\",\"name\":\"内层前处理/涂布\"},{\"line\":\"前处理线2#\",\"name\":\"内层前处理/涂布\"},{\"line\":\"前处理线3#\",\"name\":\"内层前处理/涂布\"},{\"line\":\"前处理线4#\",\"name\":\"内层前处理/涂布\"}]}";
        SelectEntity entity = JSON.parseObject(params, SelectEntity.class);
        final List<SelectItem> list = entity.getSelectItemList();

        final OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                SelectItem selectItem = list.get(options1);
                selectItem.setIndex(options1);
                String item = JSON.toJSONString(selectItem);
                view.loadUrl(UrlUtil.getFormatJs(callBack, item));
            }
        }).setSubmitText(context.getString(R.string.sure))
                .setCancelText(context.getString(R.string.cancel))
                .setSubmitColor(Color.parseColor("#333333"))
                .setCancelColor(Color.parseColor("#333333"))
                .build();
        pvOptions.setPicker(list);
        pvOptions.show();

        return null;
    }
}
