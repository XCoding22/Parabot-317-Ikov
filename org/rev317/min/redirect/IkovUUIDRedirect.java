package org.rev317.min.redirect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import org.parabot.api.io.Directories;
import org.parabot.api.misc.OperatingSystem;
import org.rev317.min.IkovData;
import org.rev317.min.Loader;

public class IkovUUIDRedirect {
	public static final boolean DEBUG_ENABLED = false;
	private static File file;
	public static ClassLoader classLoader;

	public static String a(boolean var0) {
		String var1 = null;
		if (file.exists()) {
			try {
				var1 = readFile(file.getAbsolutePath(), Charset.defaultCharset());
			} catch (IOException var11) {
			}
		}

		if (var1 == null) {
			if (!OperatingSystem.getOS().equals(OperatingSystem.WINDOWS)) {
				var1 = "mac-" + getRandomString(12);
			} else {
				var1 = getRandomString(8) + "-" + getRandomString(4) + "-" + getRandomString(4) + "-"
						+ getRandomString(4) + "-" + getRandomString(20);
			}

			try {
				write(file.getAbsolutePath(), var1);
			} catch (IOException var10) {
			}
		}

		String var2 = null;

		try {
			Class<?> var3 = classLoader.loadClass(IkovData.getUuidClass());
			Method[] var4 = var3.getDeclaredMethods();
			int var5 = var4.length;

			for (int var6 = 0; var6 < var5; ++var6) {
				Method var7 = var4[var6];
				if (var7.getName().equals("a") && var7.getParameterTypes().length == 2
						&& var7.getParameterTypes()[0].getName().equals("java.lang.String")) {
					var7.setAccessible(true);
					Object var8 = var7.invoke((Object) null, var1, var0);
					var2 = (String) var8;
				}
			}
		} catch (Exception var12) {
			var12.printStackTrace();
		}

		return var2;
	}

	private static String getRandomString(int var0) {
		--var0;
		String var1 = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder var2 = new StringBuilder();
		Random var3 = new Random();

		while (var2.length() <= var0) {
			int var4 = (int) (var3.nextFloat() * (float) var1.length());
			var2.append(var1.charAt(var4));
		}

		return var2.toString();
	}

	static {
		try {
			long var0 = Loader.getProcessID();
			file = new File(Directories.getDefaultDirectory() + "/.parabot/uuid-" + var0 + ".txt");
		} catch (NoClassDefFoundError var3) {
			file = new File("uuid.txt");
		}

	}

	private static String readFile(String var0, Charset var1) throws IOException {
		byte[] var2 = Files.readAllBytes(Paths.get(var0));
		return new String(var2, var1);
	}

	private static void write(String var0, String var1) throws IOException {
		BufferedWriter var2 = new BufferedWriter(new FileWriter(var0));
		var2.write(var1);
		var2.close();
	}
}
