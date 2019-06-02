package org.rev317.min.callback;

public class DebugCallback {

	/**
	 * Callback to get the string encryption number because the makers have no clue
	 * how to get them without running the jar
	 */
	public static void walkTo(int n, int m, int l, int k, int j, int i, short h, short g, int f, int e, int d,
			boolean c, int b, int a) {
		System.out.println("[walkTo] var7: " + h + ", var8: " + g + ", var14: " + a);
	}
	

    public static void debug(Object ... arrobject) {
        for(Object object : arrobject) {
            System.out.println(object);
            System.out.println(object.getClass());
        }
    }

	
}
