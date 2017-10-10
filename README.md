# Springfood
Spring Boot RESTful starter with Hibernate and Lombok support

## Consuming the API

The following examples assume you will run the app with localhost as your hostname
and 5001 as the port the app will be running at.

### Adding a user
Endpoint of type POST: http://localhost:5001/api/user/add

```
{
		"username": "ciccio@gmail.com",
		"password": "pippo"
}
```

### Fetching a user by username
Endpoint of type GET: http://localhost:5001/api/user/username/ciccio@gmail.com

### Login

Endpoint of type POST: http://localhost:5001/api/user/login

```
{
    "username": "ciccio@gmail.com",
    "password": "pippo"
}
```

### Get users by role once both after users and roles have been populated

Let's assume that ADMINISTRATOR is the role you are after.

Endpoint of type GET: http://localhost:5001/api/user/role/ADMINISTRATOR


### Adding a post

Endpoint of type POST: http://localhost:5001/api/post/add

Body content:
```
{
		"title": "Ciccio pizzo pizzo",
		"contents": "Lorem Ipsum"
}
```

### Get all posts

Get all posts of type GET: http://localhost:5001/api/post/






