# All my rest api code I might use

```java
@GET
@Path("/all")
public Response allRegesteredUsers() {

    final List<Map<String, User>> users = Database.getAllData();
    return Response.status(Response.Status.OK)
    .entity(users)
    .build();

    }

    @GET
    @Path("/allusers")
    public Response cantGetAllUsers() {
    return Response.status(Response.Status.UNAUTHORIZED)
    .entity("Unauthorized")
    .build();
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") final int userId) {
        if (userId <= 0 || userId > Database.databaseSize()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User Not Found")
                    .build();
        } else {
            // Retrieve the user from the database based on the userId
            final Map<String, User> user = userServices.getUserInfo(userId);
            return Response.status(Response.Status.OK)
                    .entity(user)
                    .build();
        }
    }

if (userServices.loggedIn(user) && user != null && user.getUserListToDoList()
!= null
&& todo.getWhatTodo() != null) {

todo.setId(user.getUserListToDoList().size() + 1);
user.getUserListToDoList().add(todo);
return Response.status(Response.Status.ACCEPTED)
.entity(userListDoServices.userToDoList(user))
.build();
}


```
