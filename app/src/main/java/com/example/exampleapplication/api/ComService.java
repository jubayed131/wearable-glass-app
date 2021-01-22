package com.example.exampleapplication.api;

import com.example.exampleapplication.pojos.Task;
import com.example.exampleapplication.pojos.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComService {

    //>>>>>> Tasks
    @GET("/task/<flight_id>/<state_id>")
    Single<Task> getTaskByFlight(@Path("flight_id") int flightID, @Path("state_id") int stateID);

    @GET("/task/<identifier>")
    Single<Task> getTaskByID(@Path("identifier") int id);

    @GET("/flight/<identifier>")
    Single<Task> getFlightkByID(@Path("identifier") int id);

    @POST("/task/")
    Single<Task> createTask(@Body Task task);

    @DELETE("task/{identifier}")
    Single<Task> deleteTask(@Path("id") int taskId);
    //<<<<<< Tasks

    // >>>>>>> Subtasks
    @GET("/subtask/")
    Single<List<Task>> getSubtasks();

    @GET("/subtask/<identifier>")
    Single<Task> getSubtaskByID(@Path("identifier") int id);
    // <<<<<<<<< Subtasks

    // >>>>>>>> User
    @POST("/user/")
    Single<User> createUser(@Body User user);
    @GET("/user/<identifier>")
    Single<User> getUserByID(@Path("identifier") int id);
    // <<<<<<< User


}
