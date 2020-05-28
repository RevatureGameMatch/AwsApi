package com.revature.g2g.aws.services.helpers;

import software.amazon.awssdk.services.cognitosync.CognitoSyncClient;

public class CognitoSyncClientSingleton {
	private static CognitoSyncClient cognitoSyncClient;

	private CognitoSyncClientSingleton() {
	}
	
	public static CognitoSyncClient getClient() {
		if (cognitoSyncClient == null) {
			cognitoSyncClient = CognitoSyncClient.create();
		}
		return cognitoSyncClient;
	}
}