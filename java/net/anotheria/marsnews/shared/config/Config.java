package net.anotheria.marsnews.shared.config;

import net.anotheria.marsnews.marsconnector.MarsConstants;

public class Config {
	private boolean clanSupportActivated;
	private int serverId;
	
	public static final boolean DEF_CLAN_SUPPORT_ACTIVATED = false;
	public static final int DEF_SERVER_ID = MarsConstants.SERVER_ALLIANCE;//express
	
	Config(){
		serverId = DEF_SERVER_ID;
		clanSupportActivated = DEF_CLAN_SUPPORT_ACTIVATED;
	}
	
	public boolean isClanSupportActivated() {
		return clanSupportActivated;
	}
	public void setClanSupportActivated(boolean clanSupportActivated) {
		this.clanSupportActivated = clanSupportActivated;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
}
