package com.microservice.ged.utils;

public class SecurityConstants {

	public static final String SECRET = "MySecret@1234";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 JOURS
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_SUFIX = ".RerAeB.";
	public static final String HEADER_STRING = "Authorization";
	public static final String HEADER_STRING2 = "Authentification";
	public static final String ROLES = "roles";
	public static final String REMOVE = "";
	public static final int ITEM_PASS_LOCK_ACCOUNT = 3;
	public static final int INCRIMENT_1 = 1;
	public static final long EXPIRATION_TIME_REFRESH = 864_000_000 * 3 * 12;
	public static final String LOCAL_STORAGE_USER = "Storage/files/Users/";
	public static final String LOCAL_STORAGE_WORKFLOW = "Storage/files/Workflows/";
	

}
