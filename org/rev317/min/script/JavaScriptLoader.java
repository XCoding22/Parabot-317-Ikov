package org.rev317.min.script;

import java.util.ArrayList;

import org.objectweb.asm.tree.ClassNode;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.classpath.ClassPath;
import org.parabot.environment.scripts.Script;

import xobot.script.ActiveScript;

public class JavaScriptLoader extends ASMClassLoader {
	private ClassPath classpath;

	private boolean isScriptClass(ClassNode clazz) {
		String name = clazz.superName.replace('/', '.');
		return name.equals(Script.class.getName()) || name.equals(ActiveScript.class.getName());
	}

	public final String[] getScriptClassNames() {
		ArrayList<String> list = new ArrayList<String>();

		for (ClassNode b : classpath.classes.values()) {
			if (!isScriptClass(b)) {
				continue;
			}
			list.add(b.name.replace('/', '.'));
		}
		return list.toArray(new String[list.size()]);
	}

	public JavaScriptLoader(ClassPath a) {
		super(a);
		classpath = a;
	}
}
