# oauth_demo

This example demonstates the oauth2 client_credentials workflow using spring oauth2 configured with an inmemory tokenstore and infinite lifetime of a token.

Start the server
```
gradle bootRun
```

Request a protected resource
```
curl -v "http://localhost:8080/secure
```

Request a token
```
curl -X POST -u admin:password http://localhost:8080/oauth/token?grant_type=client_credentials
```

Save the token
```
export TOKEN=FOOOOOOOBAAAARRRRRRR
```

Use the token and access a protected resource
```
curl -v "localhost:8080/secure?access_token=$TOKEN"
```

Destroy the token
```
curl -v -X DELETE "http://localhost:8080/token?access_token=$TOKEN"
```

Start from beginning
