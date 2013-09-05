package net.anotheria.marsnews.shared.config;

public class ConfigFactory {
	private static Config instance = new Config();
	
	public static Config getConfig(){
		return instance;
	}
}
