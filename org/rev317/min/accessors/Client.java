package org.rev317.min.accessors;

public interface Client {
    public int[] getSettings();

    public void doAction(MenuActions var1, short var2, long var3);

    public int getLoopCycle();

    public Scene getScene();

    public int getBaseY();

    public void login(String var1, String var2, boolean var3);

    public int getPlane();

    public CollisionMap[] getCollisionMap();

    public int[] getCurrentExp();

    public int getBackDialogId();

    public int getOpenInterfaceId();

    public void addFriend(long var1);

    public Player[] getPlayers();

    public void deleteFriend(long var1);

    public int getBaseX();

    public boolean walkTo(int var1, int var2, int var3, int var4, char var5, int var6, int var7, int var8, int var9, int var10, int var11, boolean var12, int var13, short var14);

    public Deque[][][] getGroundItems();

    public boolean isLoggedIn();

    public void dropClient();

    public int[] getCurrentStats();

    public long[] getFriendsListAsLong();

    public Player getMyPlayer();

    public void setInterface(int var1);

    public Interface[] getInterfaceCache();

    public void setAmountOrNameInput(String var1);

    public Npc[] getNpcs();

    public int getInputDialogState();
}

