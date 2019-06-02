package org.rev317.min.api.util.proxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;
import org.parabot.core.ui.BotUI;
import org.parabot.core.ui.Logger;
import org.rev317.min.IkovData;

public class IPSelector
implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            RefClass refClass = new RefClass(Context.getInstance().getASMClassLoader().loadClass(IkovData.getIPHolderClass()));
            RefClass refClass2 = new RefClass(refClass.getField(IkovData.getIPHolderField()).asObject());
            RefField refField = refClass2.getField(IkovData.getIPField());
            refField.setString(IkovData.getWorkingIP());
            Logger.addMessage((String)"Fixed proxies support for Ikov");
            return;
        }
        catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            Logger.addMessage((String)("Failed to fix proxies support for Ikov (" + classNotFoundException.getException() + ")"));
        }
    }

    public static void addProxySelector() {
        JMenuItem jMenuItem = new JMenuItem("Fix proxies");
        jMenuItem.addActionListener((ActionListener)new IPSelector());
        BotUI.getInstance().getFeatures().add(jMenuItem);
    }
}
