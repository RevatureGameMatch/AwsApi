package com.revature.g2g.aws.services.helpers;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

public class CognitoIdentityProviderClientSingleton {
	private static CognitoIdentityProviderClient client;

	private CognitoIdentityProviderClientSingleton() {
		super();
	}
	public static CognitoIdentityProviderClient getClient() {
		if (client == null) {
			client = CognitoIdentityProviderClient.create();
		}
		return client;
	}
}