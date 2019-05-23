package org.rev317.min.accessors;

public interface Client {
	public boolean isLoggedIn();

	public int getLoopCycle();

	public int getOpenInterfaceId();

	public void setInterface(int var1);

	public boolean walkTo(int var1, int var2, int var3, int var4, int var5, int var6, short var7,
			short var8, int var9, int var10, int var11, boolean var12, int var13, int var14);

	public Interface[] getInterfaceCache();

	public int getPlane();

	public void deleteFriend(long var1);

	public Deque[][][] getGroundItems();

	public CollisionMap[] getCollisionMap();

	public Player[] getPlayers();

	public int getBaseX();

	public int[] getSettings();

	public Npc[] getNpcs();

	public int[] getCurrentExp();

	public long[] getFriendsListAsLong();

	public void addFriend(long var1);

	public Scene getScene();

	public void doAction(MenuActions var1, short var2, long var3);

	public int getBaseY();

	public void dropClient();

	public int getBackDialogId();

	public int getInputDialogState();

	public void setAmountOrNameInput(String var1);

	public void login(String var1, String var2, boolean var3);

	public int[] getCurrentStats();

	public Player getMyPlayer();
}
