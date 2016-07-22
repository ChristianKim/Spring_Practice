package com.chris.user.service;

import com.chris.user.domain.User;

public interface UserService {
	void add(User user);
	void upgradeLevels();
}