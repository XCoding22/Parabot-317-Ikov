package org.rev317.min;

import java.applet.Applet;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import org.parabot.core.Context;
import org.parabot.core.Core;
import org.parabot.core.Directories;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.RedirectClassAdapter;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.hooks.HookFile;
import org.parabot.core.desc.ServerProviderInfo;
import org.parabot.core.io.ProgressListener;
import org.parabot.core.io.SizeInputStream;
import org.parabot.core.network.NetworkInterface;
import org.parabot.core.parsers.scripts.ScriptParser;
import org.parabot.core.ui.Logger;
import org.parabot.core.ui.components.VerboseLoader;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.util.proxy.IPSelector;
import org.rev317.min.api.util.randoms.LogoutDisabler;
import org.rev317.min.api.util.randoms.QuestionSolver;
import org.rev317.min.input.Keyboard;
import org.rev317.min.redirect.IkovClassRedirect;
import org.rev317.min.script.ScriptEngine;
import org.rev317.min.script.XobotScriptParser;
import org.rev317.min.ui.BotMenu;

@ServerManifest(author = "Parabot & Empathy", name = "Ikov", type = Type.INJECTION, version = 2.1)
public class Loader extends ServerProvider {
	private static long processID;

	public void initScript(Script a) {
		ScriptEngine.getInstance().setScript(a);
		ScriptEngine.getInstance().init();
	}

	public void init() {
		Context.getInstance().getRandomHandler()
				.addRandom((org.parabot.environment.randoms.Random) new QuestionSolver());
		Context.getInstance().getRandomHandler()
				.addRandom((org.parabot.environment.randoms.Random) new LogoutDisabler());
		ScriptParser.addParser((ScriptParser) new XobotScriptParser());
	}

	public void injectHooks() {
		AddInterfaceAdapter.setAccessorPackage("org/rev317/min/accessors/");
		super.injectHooks();
	}

	public void unloadScript(Script a) {
		ScriptEngine.getInstance().unload();
	}

	public HookFile getHookFile() {
		File c = new File(Context.getInstance().getServerProviderInfo().getProperties().getProperty("hooks"));
		if (c.exists()) {
			try {
				return new HookFile(c, 0);
			} catch (MalformedURLException a) {
				a.printStackTrace();
				return null;
			}
		}
		try {
			return new HookFile(new URL("http://bdn.parabot.org/api/v2/servers/ikov"), 0);
		} catch (MalformedURLException b) {
			b.printStackTrace();
			return new HookFile(Context.getInstance().getServerProviderInfo().getHookFile(), 0);
		}
	}

	public void addMenuItems(JMenuBar a) {
		new BotMenu(a);
		IPSelector.addProxySelector();
	}

	public URL getJar() {
		downloadUUIDJar();
		ServerProviderInfo serverProvider = Context.getInstance().getServerProviderInfo();
		File target = new File(Directories.getCachePath(), serverProvider.getClientCRC32() + ".jar");
		if (target.exists()) {
			return WebUtil.toURL(target);
		}
		WebUtil.downloadFile(serverProvider.getClient(), target, VerboseLoader.get());
		return WebUtil.toURL(target);
	}

	public static final long getProcessID() {
		if (processID > 0L) {
			return processID;
		}
		processID = ThreadLocalRandom.current().nextLong(10000000L, 10000000000L);
		return processID;
	}

	private void loadLibs(String... libs) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, MalformedURLException {
		for (String lib : libs) {
			File i = new File(
					new StringBuilder(System.getProperty("user.home")).append(File.separator).append(".ikov_cache")
							.append(File.separator).append("libs").append(File.separator).append(lib).toString());
			System.out.println(i.getAbsolutePath());
			if (i.exists()) {
				Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
				method.setAccessible(true);
				method.invoke(ClassLoader.getSystemClassLoader(), i.toURI().toURL());
				Object[] arrobject2 = new Object[1];
				arrobject2[0] = i.getAbsolutePath();
				String a = String.format("Loaded library: %s", arrobject2);
				Logger.addMessage((String) a);
				Core.verbose((String) a);
			} else {
				for (String message : new String[] { "Could not load library: " + i.getAbsolutePath(),
						"First run Ikov using the Launcher of Ikov, to have all libraries loaded" }) {
					Logger.addMessage(message);
					Core.verbose(message);
				}
			}
		}
	}

	public static Class<?> getActionClass() {
		ASMClassLoader b = Context.getInstance().getASMClassLoader();
		try {
			return b.loadClass(IkovData.getA());
		} catch (ClassNotFoundException a) {
			a.printStackTrace();
			return null;
		}
	}

	public void parseJar() {
		RedirectClassAdapter.getRedirects().put("java/lang/Class", IkovClassRedirect.class);
		super.parseJar();
	}

	public static Client getClient() {
		return (Client) Context.getInstance().getClient();
	}

	public static void refreshProcess() {
		processID = -1L;
	}

	private void downloadUUIDJar() {
		try {
			URL o = new URL("http://bot.parabot.org/servers/newest/data/ikov/uuid.jar");
			File anothershittypbfolder = new File(Directories.getDefaultDirectory() + "/.parabot/");
			if (anothershittypbfolder.exists()) {
				File jarCache = new File(anothershittypbfolder, "uuid.jar");
				if (jarCache.exists()) {
					jarCache.delete();
				}
			} else {
				anothershittypbfolder.mkdir();
			}

			File uuidJar = new File(anothershittypbfolder, "uuid.jar");
			URLConnection connection = WebUtil.getConnection(o);
			;
			int connectionLength = connection.getContentLength();
			SizeInputStream sis = new SizeInputStream(connection.getInputStream(), connectionLength,
					(ProgressListener) null);
			OutputStream out = new FileOutputStream(uuidJar);
			BufferedOutputStream bufOut = new BufferedOutputStream(out);
			byte buffer[] = new byte[4086];
			while (true) {
				int nRead = sis.read(buffer, 0, buffer.length);
				if (nRead <= 0)
					break;
				bufOut.write(buffer, 0, nRead);
			}

			bufOut.flush();
			out.close();
			sis.close();

		} catch (IOException var44) {
			var44.printStackTrace();
		}

	}

	public void initKeyboard() {
		Context context = Context.getInstance();
		Applet applet = context.getApplet();
		Keyboard keyboard = new Keyboard(applet);
		applet.addKeyListener(keyboard);
		context.setKeyboard(keyboard);
	}

	public Applet fetchApplet() {
		try {
			Object[] arrobject2 = { "Exit Parabot", "Continue botting" };
			if (JOptionPane.showOptionDialog(null,
					"Ikov has aggressive anti-botting methods.\nTherefore we recommend using a Proxy or a VPN when starting up the client with Parabot.\nWe also recommend to not bot your main-account and babysit your bot.",
					"Avoid getting banned", -1, 1, null, arrobject2, arrobject2[1]) == 0) {
				System.err.println("User selected to quit Parabot");
				System.exit(0);
			}
			if (Arrays.equals(NetworkInterface.getRealHardwareAddress(),
					NetworkInterface.getByInetAddress((InetAddress) InetAddress.getLocalHost()).getHardwareAddress())) {
				byte[] byteAllocation = new byte[6];
				new Random().nextBytes(byteAllocation);
				NetworkInterface.setMac(byteAllocation);
			}
			String[] libs = { "gson.jar", "jna.jar", "discord-rpc-release.jar", "java-discord-rpc.jar" };
			loadLibs(libs);
			return (Applet) Context.getInstance().getASMClassLoader()
					.loadClass(Context.getInstance().getServerProviderInfo().getClientClass()).newInstance();
		} catch (Exception d) {
			d.printStackTrace();
			return null;
		}
	}
}
