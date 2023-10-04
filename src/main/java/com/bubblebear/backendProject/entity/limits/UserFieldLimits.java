package com.bubblebear.backendProject.entity.limits;

public interface UserFieldLimits {
	
	int FULLNAME_MIN_LENGTH = 5;
	int FULLNAME_MAX_LENGTH = 200;
	int FULLNAME_DB_LENGTH = 200;
	
	int EMAIL_MIN_LENGTH = 5;
	int EMAIL_MAX_LENGTH = 90;
	int EMAIL_DB_LENGTH = 90;

	int PASSWORD_MIN_LENGTH = 5;
	int PASSWORD_MAX_LENGTH = 20;
	int PASSWORD_DB_LENGTH = 90;
	
	int PHONE_NUMBER_MIN_LENGTH = 10;
	int PHONE_NUMBER_MAX_LENGTH = 10;
	int PHONE_NUMBER_DB_LENGTH = 10;
}
