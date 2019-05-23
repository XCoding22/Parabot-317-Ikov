package org.rev317.min.api.util.randoms;

import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;

public class QuestionSolver implements Random {

	public String getName() {
		return "Question solver";
	}

	private void dropClient() {
		Loader.getClient().dropClient();
	}

	public String getServer() {
		return "Ikov";
	}

	public RandomType getRandomType() {
		return RandomType.SCRIPT;
	}

	@SuppressWarnings("deprecation")
	public boolean activate() {
		return Game.isLoggedIn() && Game.getOpenBackDialogId() == 368;
	}

	public void execute() {
		String question = Loader.getClient().getInterfaceCache()[372].getMessage();
		if (!question.contains("lose items on death, beware")) {
			question = question.replace("@dre@ ", "");
			Logger.addMessage((String) "Contacting server to get an answer", false);
			String answer = getAnswer(question);
			if (answer == null) {
				Logger.addMessage(
						"Could not solve the question, please report this question (and the possible answer) on the forums",
						true);
				Logger.addMessage("Question: " + question, true);
				dropClient();
				Context.getInstance().getRunningScript().setState(2);
			} else {
				Logger.addMessage("Answer found, now trying: " + answer, false);
				Menu.sendAction(679, -1, -1, 373);
				Time.sleep(1000);
				Keyboard.getInstance().sendKeys(answer);
			}
		}
		Time.sleep(1000);
	}

	public String getAnswer(String d) {
		try {
			JSONObject page = (JSONObject) WebUtil.getJsonParser()
					.parse(WebUtil.getContents((String) "http://bdn.parabot.org/api/v2/data/questions/get",
							(String) new StringBuilder().insert(0, "server=ikov&question=")
									.append(URLEncoder.encode(d, "UTF-8")).toString()));
			JSONObject result = (JSONObject) page.get((Object) "result");
			if (result != null) {
				return (String) result.get((Object) "answer");
			}
		} catch (Exception c) {
			c.printStackTrace();
		}
		return null;
	}
}
