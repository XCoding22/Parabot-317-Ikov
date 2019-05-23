package xobot.script;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Manifest {
	public String name() default "Xobot script";

	public String[] authors() default { "Xobot" };

	public String server() default "Ikov";

	public double version() default 1.0;

	public String description() default "From Xobot";
}
