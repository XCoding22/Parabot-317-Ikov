package org.rev317.min.api.util.proxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.ui.BotUI;
import org.parabot.core.ui.Logger;
import org.rev317.min.IkovData;

public class IPSelector implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent actionevent) {
		try {
			RefClass a = new RefClass(Context.getInstance().getASMClassLoader().loadClass(IkovData.getI()));
			new RefClass(a.getField(IkovData.getJ()).asObject()).getField(IkovData.getG())
					.setString(IkovData.getServerIP());
			Logger.addMessage((String) "Fixed proxies support for Ikov");
			return;
		} catch (ClassNotFoundException b) {
			b.printStackTrace();
			Logger.addMessage((String) new StringBuilder().insert(0, "Failed to fix proxies support for Ikov (")
					.append(b.getException()).append(")").toString());
			return;
		}
	}

	public static void addProxySelector() {
		JMenuItem jMenuItem = new JMenuItem("Fix proxies");
		jMenuItem.addActionListener(new IPSelector());
		BotUI.getInstance().getFeatures().add(jMenuItem);
	}
}
