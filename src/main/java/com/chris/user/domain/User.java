package com.chris.user.domain;

public class User {
	String id;
	String name;
	String passwd;
	String email;
	Level lvl;
	int login;
	int recommend;
	
	public User() {
	}
	
	public User(String id, String name, String passwd, String email,
			Level lvl, int login, int recommend) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.email = email;
		this.lvl = lvl;
		this.login = login;
		this.recommend = recommend;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void upgradeLevel() {
		Level nextLevel = this.lvl.nextLevel();	
		if (nextLevel == null) { 								
			throw new IllegalStateException(this.lvl + "은  업그레이드가 불가능합니다");
		}
		else {
			this.lvl = nextLevel;
		}	
	}
}
