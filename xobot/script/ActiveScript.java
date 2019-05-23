package xobot.script;

import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.LoopTask;

public class ActiveScript extends Script implements LoopTask {

	public int loop() {
		return 0;
	}

	public boolean onExecute() {
		return onStart();
	}

	public boolean onStart() {
		return true;
	}
}
