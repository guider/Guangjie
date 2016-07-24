package com.yanyuanquan.model;

import com.yanyuanquan.model.api.Token;
import com.yanyuanquan.model.entity.Branch;
import com.yanyuanquan.model.entity.Empty;
import com.yanyuanquan.model.entity.Event;
import com.yanyuanquan.model.entity.RepoSearch;
import com.yanyuanquan.model.entity.Repository;
import com.yanyuanquan.model.entity.Tree;
import com.yanyuanquan.model.entity.User;
import com.yanyuanquan.model.entity.UserSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * Created by guider on 16/6/29.
 * Email guider@yeah.net
 * github https://github.com/guider
 *
 */
public interface ApiService  {

    // Token
    @POST("authorizations")
    Observable<Response<Token>> login(@Body Token token, @Header("Authorization") String authorization) ;

    @GET("authorizations")
    Observable<Response<List<Token>>> listToken(@Header("Authorization") String authorization) ;

    @DELETE("authorizations/{id}")
    Observable<Response<Empty>> removeToken(@Header("Authorization") String authorization, @Path("id") String id) ;

    //User

    @GET("/user")
    Call<User> authUser();

    @GET("/users/{username}")
    Call<User> user(@Path("username") String username);

    @GET("/users/{user}/following?per_page=10")
    Call<List<User>> follwerings(@Path("user") String user,@Query("page") String page);

    @GET("/users/{user}/followers?per_page=10")
    Call<List<User>> followers(@Path("user") String user,@Query("page") String page);

    @GET("/users/{user}/repos?sort=pushed&per_page=10")
    Call<List<Repository>> userRepo(@Path("user") String user, @Query("page") String page);

    @GET("/users/{user}/starred?per_page=10")
    Call<List<Repository>> starredRepo(@Path("user") String user,@Query("page") String page);

    @GET("/users/{user}/events/public?per_page=10")
    Call<List<Event>> publicEvent(@Path("user") String user, @Query("page") String page);

    @GET("/users/{user}/received_events?per_page=10")
    Call<List<Event>> receivedEvent(@Path("user") String user,@Query("page") String page);


    @GET("/user/starred/{owner}/{repo}")
    Call<Empty> hasStar(@Path("owner") String owner, @Path("repo") String repo);

    @PUT("/user/starred/{owner}/{repo}")
    Call<Empty> star(@Path("owner") String owner, @Path("repo") String repo);

    @DELETE("/user/starred/{owner}/{repo}")
    Call<Empty> unStar(@Path("owner") String owner, @Path("repo") String repo);

    //Get count of starred repo of someone
    @GET("/users/{user}/starred?&per_page=1")
    Call<List<Repository>> starredCount(@Path("user") String user);


    // Repo

    @GET("/repos/{owner}/{repo}/stargazers?&per_page=10")
    Call<List<User>> stargazers(@Path("owner") String owner, @Path("repo") String repo,@Query("page") String page);

    @GET("/repos/{owner}/{repo}/forks?&per_page=10")
    Call<List<User>> forkers(@Path("owner") String owner, @Path("repo") String repo,@Query("page") String page);

    @GET("/repos/{owner}/{repo}/collaborators?&per_page=10")
    Call<List<User>> collaborators(@Path("owner") String owner, @Path("repo") String repo,@Query("page") String page);

    @GET("/repos/{owner}/{repo}/branches")
    Call<List<Branch>> getBranches(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}?&per_page=10")
    Call<Tree> getTree(@Path("owner") String owner, @Path("repo") String repo, @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/forks")
    Call<List<Repository>> fork(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Call<String> getRawContent(@Path("owner") String owner, @Path("repo") String repo,@Path("path") String path);



    //Api about search
    @GET("/search/users?&perpage=10")
    Call<UserSearch> searchUser(@Query("q") String q, @Query("page") String page);

    @GET("/search/repositories?&per_page=10")
    Call<RepoSearch> searchRepo(@Query("q") String q, @Query("page") String page);



    //==========
    //Api about user-user relation
    //==========

    @GET("/user/following/{username}")
    Call<Empty> hasFollow(@Path("username") String username);

    @PUT("/user/following/{username}")
    Call<Empty> follow(@Path("username") String username);

    @DELETE("/user/following/{username}")
    Call<Empty> unFollow(@Path("username") String username);


}
