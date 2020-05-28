package com.revature.g2g.aws.services.helpers;

import software.amazon.awssdk.services.cognitoidentity.CognitoIdentityClient;

public class CognitoIdentityClientSingleton {
	private CognitoIdentityClientSingleton() {
	}
	private static CognitoIdentityClient client;
	public static CognitoIdentityClient getClient() {
		if(client == null) {
			client = CognitoIdentityClient.create();
		}
		return client;
	}
}
