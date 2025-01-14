package com.miniapp.talks.activity.message;

import android.widget.EditText;

import java.util.List;
import java.util.ListIterator;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.model.Conversation;

/**
 * 作者:sgm
 * 描述:删除聊天得扩展区域
 */
public class LiaoBaExtensionModule extends DefaultExtensionModule {
    private EditText mEditText;

    @Override
    public void onAttachedToExtension(RongExtension extension) {
        super.onAttachedToExtension(extension);
        mEditText = extension.getInputEditText();
    }

    @Override
    public void onDetachedFromExtension() {
        super.onDetachedFromExtension();
        mEditText = null;
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules = super.getPluginModules(conversationType);
        ListIterator<IPluginModule> iterator = pluginModules.listIterator();
        while (iterator.hasNext()) {
            IPluginModule integer = iterator.next();
            if (integer instanceof FilePlugin) {
                iterator.remove();
            }
        }
//        pluginModules.add(new ConectionPlugin());
        return pluginModules;
    }
}
