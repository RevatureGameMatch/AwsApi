package com.revature.g2g.aws.api.controllers;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.aws.api.dto.IdDTO;
import com.revature.g2g.aws.api.dto.MessageDTO;
import com.revature.g2g.aws.services.helpers.CognitoIdentityClientSingleton;
import com.revature.g2g.aws.services.helpers.CognitoIdentityProviderClientSingleton;
import com.revature.g2g.aws.services.helpers.CognitoSyncClientSingleton;
import com.revature.g2g.aws.services.helpers.PropertiesSingleton;

import software.amazon.awssdk.services.cognitoidentity.CognitoIdentityClient;
import software.amazon.awssdk.services.cognitoidentity.model.GetIdRequest;
import software.amazon.awssdk.services.cognitoidentity.model.GetIdResponse;
import software.amazon.awssdk.services.cognitoidentity.model.ListIdentitiesRequest;
import software.amazon.awssdk.services.cognitoidentity.model.ListIdentitiesResponse;
import software.amazon.awssdk.services.cognitoidentity.model.ListIdentityPoolsRequest;
import software.amazon.awssdk.services.cognitoidentity.model.ListIdentityPoolsResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
import software.amazon.awssdk.services.cognitosync.CognitoSyncClient;

/**
 * Takes a 
 * @author Philip Lawrence
 *
 */
@RestController
@CrossOrigin
@RequestMapping("token")
public class TokenIdController {
	private CognitoIdentityClient cognitoClient;
	private CognitoIdentityProviderClient cognitoProviderClient;
	private CognitoSyncClient cognitoSyncClient;
	private Properties properties;
	private String poolId;
	private String poolIdShort;
	@Autowired
	public TokenIdController() {
		cognitoClient = CognitoIdentityClientSingleton.getClient();
		cognitoProviderClient = CognitoIdentityProviderClientSingleton.getClient();
		cognitoSyncClient = CognitoSyncClientSingleton.getClient();
		properties = PropertiesSingleton.getPropValues();
		poolId = properties.getProperty("awsIdentityPoolId");
		poolIdShort = properties.getProperty("awsIdentityPoolShortId");
	}
	@GetMapping
	public ResponseEntity<IdDTO> tradeTokenForId(@RequestParam(name = "token") String token) {
		GetIdRequest getIdRequest = GetIdRequest.builder()
				.accountId(token)
				.identityPoolId(poolId)
				.build();
		GetIdResponse getIdResponse = cognitoClient.getId(getIdRequest);
		String id = getIdResponse.identityId();
		IdDTO dto = new IdDTO(id, token);
		return ResponseEntity.ok(dto);
	}
	@GetMapping("/pools")
	public ResponseEntity<MessageDTO> findPools(){
		ListIdentityPoolsRequest listIdentityPoolsRequest = ListIdentityPoolsRequest.builder()
				.maxResults(5)
				.build();
		ListIdentityPoolsResponse listIdentityPoolsResponse = cognitoClient.listIdentityPools(listIdentityPoolsRequest);
		System.out.println(listIdentityPoolsResponse.identityPools().toString());
		MessageDTO message = new MessageDTO("done");
		return ResponseEntity.ok(message);
	}
	@GetMapping("/identities")
	public ResponseEntity<MessageDTO> findIdentities(){
		ListIdentitiesRequest listIdentitiesRequest = ListIdentitiesRequest.builder()
				.maxResults(5)
				.identityPoolId(poolId)
				.build();
		ListIdentitiesResponse listIdentitiesResponse = cognitoClient.listIdentities(listIdentitiesRequest);
		System.out.println(listIdentitiesResponse.identities().toString());
		System.out.println(listIdentitiesResponse.toString());
		MessageDTO message = new MessageDTO("done");
		return ResponseEntity.ok(message);
	}
	@GetMapping("/user")
	public ResponseEntity<MessageDTO> findUser(@RequestParam(name = "token") String token){
		GetUserRequest getUserRequest = GetUserRequest.builder()
				.accessToken("8821e0da-a876-4328-99d4-a4d95cd9e61a")
				.build();
		GetUserResponse getUserResponse = cognitoProviderClient.getUser(getUserRequest);
		System.out.println(getUserResponse.toString());
		MessageDTO message = new MessageDTO("done");
		return ResponseEntity.ok(message);
	}
	@GetMapping("/users")
	public ResponseEntity<MessageDTO> findUsers(){
		ListUsersRequest listUsersRequest = ListUsersRequest.builder()
				.limit(5)
				.userPoolId(poolIdShort)
				.build();
		ListUsersResponse listUsersResponse = cognitoProviderClient.listUsers(listUsersRequest);
		System.out.println(listUsersResponse.toString());
		List<UserType> users = listUsersResponse.users();
		for(UserType user : users) {
			System.out.println(user.username());
			System.out.println(user.attributes().get(0).value());
			System.out.println(user.attributes().get(3).value());
		}
		MessageDTO message = new MessageDTO("done");
		return ResponseEntity.ok(message);
	}
}