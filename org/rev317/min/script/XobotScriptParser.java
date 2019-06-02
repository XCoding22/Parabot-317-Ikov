package org.rev317.min.script;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import org.parabot.core.Core;
import org.parabot.core.Directories;
import org.parabot.core.classpath.ClassPath;
import org.parabot.core.desc.ScriptDescription;
import org.parabot.core.parsers.scripts.ScriptParser;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.executers.LocalScriptExecuter;

import xobot.script.Manifest;

public class XobotScriptParser extends ScriptParser {
	public void execute() {
		ClassPath var1 = new ClassPath();
		File var2 = new File(Directories.getDefaultDirectory() + "/.parabot/xobot/");
		if (var2.exists()) {
			var1.addClasses(var2);
			JavaScriptLoader var3 = new JavaScriptLoader(var1);
			String[] var4 = (String[]) var3.getScriptClassNames();
			int var5 = var4.length;

			for (int var6 = 0; var6 < var5; ++var6) {
				String var7 = var4[var6];

				try {
					Class<?> var8;
					try {
						var8 = var3.loadClass(var7);
					} catch (NoClassDefFoundError var13) {
						continue;
					}

					Annotation var9 = var8.getAnnotation(Manifest.class);
					if (var9 == null) {
						throw new RuntimeException("Missing manifest at " + var7);
					}

					Manifest var10 = (Manifest) var9;
					Constructor<?> var11 = var8.getConstructor();
					ScriptDescription var12 = new ScriptDescription(var8.getName(), (var10.authors())[0],
							Category.OTHER.toString(), 1.0D, "Script " + var8.getName() + " from Xobot", new String[0],
							"no", "no");
					SCRIPT_CACHE.put(var12, new LocalScriptExecuter(var11));
					Core.verbose("Added Xobot script " + var8.getName());
				} catch (NoClassDefFoundError | ClassNotFoundException var14) {
				} catch (Throwable var15) {
					var15.printStackTrace();
				}
			}

		}
	}
}
