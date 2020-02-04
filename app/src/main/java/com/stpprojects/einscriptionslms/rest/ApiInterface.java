package com.stpprojects.einscriptionslms.rest;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(" ")
    Call<JsonObject> userRegitration(@Query("action") String action,
                                     @Query("actionMethod") String actionMethod,
                                     @Query("firstname") String firstname,
                                     @Query("lastname") String lastname,
                                     @Query("email") String email,
                                     @Query("password") String password,
                                     @Query("password_2") String password_2,
                                     @Query("city") String city,
                                     @Query("country") String country,
                                     @Query("postalcode") String postalcode,
                                     @Query("source") String source,
                                     @Query("address") String address,
                                     @Query("mobile") String mobile);


    @GET(" ")
    Call<JsonObject> userLogin(@Query("action") String action,
                               @Query("actionMethod") String actionMethod,
                               @Query("email") String email,
                               @Query("password") String password,
                               @Query("loginType") String loginType,
                               @Query("social_id") String social_id,
                               @Query("device_id") String device_id,
                               @Query("device_token") String device_token,
                               @Query("device_type") String device_type,
                               @Query("display_name") String display_name);


    @POST(" ")
    Call<JsonObject> getAllCourse(@Query("action") String action,
                                  @Query("actionMethod") String actionMethod,
                                  @Query("token") String token);

    @POST(" ")
    Call<JsonObject> getCourseDetail(@Query("action") String action,
                                     @Query("actionMethod") String actionMethod,
                                     @Query("token") String token,
                                     @Query("course_id") String course_id);

    @POST(" ")
    Call<JsonObject> getVideoDetails(@Query("action") String action,
                                     @Query("actionMethod") String actionMethod,
                                     @Query("token") String token,
                                     @Query("instructor_id") String parent_id,
                                     @Query("course_id") String course_id,
                                     @Query("phone_number") String phone);


    @POST(" ")
    Call<JsonObject> getMyCourse(@Query("action") String action,
                                 @Query("actionMethod") String actionMethod,
                                 @Query("token") String token);

    @POST(" ")
    Call<JsonObject> saveJoinVideoSession(@Query("action") String action,
                                          @Query("actionMethod") String actionMethod,
                                          @Query("token") String token,
                                          @Query("video_id") String video_id);

    @POST(" ")
    Call<JsonObject> getProductDetailApi(@Query("action") String action,
                                         @Query("actionMethod") String actionMethod,
                                         @Query("token") String token,
                                         @Query("product_id") String video_id);

    @POST(" ")
    Call<JsonObject> createOrder(@Query("action") String action,
                                 @Query("actionMethod") String actionMethod,
                                 @Query("token") String token,
                                 @Query("product_id") String video_id,
                                 @Query("quantity") String quantity);

    @POST(" ")
    Call<JsonObject> getModuleDetails(@Query("action") String action,
                                      @Query("actionMethod") String actionMethod,
                                      @Query("token") String token,
                                      @Query("course_id") String course_id,
                                      @Query("module_id") String module_id);


    @POST(" ")
    Call<JsonObject> markUnitCompleted(@Query("action") String action,
                                       @Query("actionMethod") String actionMethod,
                                       @Query("token") String token,
                                       @Query("course_id") String course_id,
                                       @Query("module_id") String module_id);

    @POST(" ")
    Call<JsonObject> addReview(@Query("action") String action,
                               @Query("actionMethod") String actionMethod,
                               @Query("token") String token,
                               @Query("course_id") String course_id,
                               @Query("comment") String module_id,
                               @Query("review_title") String review_title,
                               @Query("review_rating") String review_rating);

    @POST(" ")
    Call<JsonObject> getQuestionList(@Query("action") String action,
                                     @Query("actionMethod") String actionMethod,
                                     @Query("token") String token,
                                     @Query("course_id") String course_id,
                                     @Query("module_id") String module_id);


    @POST(" ")
    Call<JsonObject> submitQuiz(@Query("action") String action,
                                @Query("actionMethod") String actionMethod,
                                @Query("token") String token,
                                @Query("question_id") String course_id,
                                @Query("option") String module_id);


    @POST(" ")
    Call<JsonObject> submitQuizfinal(@Query("action") String action,
                                     @Query("actionMethod") String actionMethod,
                                     @Query("token") String token,
                                     @Query("module_id") String course_id);

    @POST(" ")
    Call<JsonObject> finishCourse(@Query("action") String action,
                                  @Query("actionMethod") String actionMethod,
                                  @Query("token") String token,
                                  @Query("course_id") String course_id);

    @POST(" ")
    Call<JsonObject> resetPassword(@Query("action") String action,
                                   @Query("actionMethod") String actionMethod,
                                   @Query("email") String email);

    @POST(" ")
    Call<JsonObject> getYoutubeVideo(@Query("action") String action,
                                     @Query("actionMethod") String actionMethod,
                                     @Query("token") String token,
                                     @Query("device_token") String device_token);

    @POST(" ")
    Call<JsonObject> getCourseStatusList(@Query("action") String action,
                                         @Query("actionMethod") String actionMethod,
                                         @Query("token") String token);

    @POST(" ")
    Call<JsonObject> getUserProfile(@Query("action") String action,
                                    @Query("actionMethod") String actionMethod,
                                    @Query("token") String token);

    @POST(" ")
    Call<JsonObject> getFreeCoures(@Query("action") String action,
                                   @Query("actionMethod") String actionMethod,
                                   @Query("token") String token,
                                   @Query("course_id") String course_id);


    @POST(" ")
    Call<JsonObject> getPdfList(@Query("action") String action,
                                @Query("actionMethod") String actionMethod,
                                @Query("token") String token);

    @POST(" ")
    Call<JsonObject> updateUserProfile(@Query("action") String action,
                                       @Query("actionMethod") String actionMethod,
                                       @Query("token") String token,
                                       @Query("firstname") String firstname,
                                       @Query("lastname") String lastname,
                                       @Query("mobile") String mobile,
                                       @Query("address") String address,
                                       @Query("city") String password,
                                       @Query("country") String password_2,
                                       @Query("postalcode") String city,
                                       @Query("email") String email);

    @POST(" ")
    Call<JsonObject> searchCourses(@Query("action") String action,
                                   @Query("actionMethod") String actionMethod,
                                   @Query("token") String token,
                                   @Query("keyword") String keyword);

    @POST(" ")
    Call<JsonObject> searchCoursesBySlug(@Query("action") String action,
                                         @Query("actionMethod") String actionMethod,
                                         @Query("token") String token,
                                         @Query("slug") String keyword);

    @POST(" ")
    Call<JsonObject> userLogout(@Query("action") String action,
                                @Query("actionMethod") String actionMethod,
                                @Query("token") String token);

    @POST(" ")
    Call<JsonObject> getInstructorInfo(@Query("action") String action,
                                       @Query("actionMethod") String actionMethod,
                                       @Query("token") String token,
                                       @Query("instructor_id") String instructor_id);


    @POST(" ")
    Call<JsonObject> addInstructorComment(@Query("action") String action,
                                          @Query("actionMethod") String actionMethod,
                                          @Query("token") String token,
                                          @Query("instructor_id") String instructor_id,
                                          @Query("comment") String comment,
                                          @Query("title") String title,
                                          @Query("rating") String rating);

    @POST(" ")
    Call<JsonObject> joinSessionVideoLink(@Query("action") String action,
                                          @Query("actionMethod") String actionMethod,
                                          @Query("token") String token,
                                          @Query("course_id") String instructor_id,
                                          @Query("instructor_id") String comment);

    @POST(" ")
    Call<JsonObject> getChatOptionList(@Query("action") String action,
                                       @Query("actionMethod") String actionMethod,
                                       @Query("token") String token,
                                       @Query("course_id") String instructor_id);

    @POST(" ")
    Call<JsonObject> getindivisualchats(@Query("action") String action,
                                        @Query("actionMethod") String actionMethod,
                                        @Query("token") String token,
                                        @Query("receiver_id") String receiver_id,
                                        @Query("limit") String limit,
                                        @Query("offset") String offset);


    @POST(" ")
    Call<JsonObject> saveindivisualchats(@Query("action") String action,
                                         @Query("actionMethod") String actionMethod,
                                         @Query("token") String token,
                                         @Query("receiver_id") String receiver_id,
                                         @Query("message") String limit,
                                         @Query("is_quote") String is_quote,
                                         @Query("parents") String parents,
                                         @Query("question") String question,
                                         @Query("quote_name") String quote_name
    );

    @POST(" ")
    Call<JsonObject> saveGropuChat(@Query("action") String action,
                                   @Query("actionMethod") String actionMethod,
                                   @Query("token") String token,
                                   @Query("course_id") String receiver_id,
                                   @Query("message") String limit,
                                   @Query("is_quote") String is_quote,
                                   @Query("parents") String parents,
                                   @Query("question") String question,
                                   @Query("quote_name") String quote_name
    );


    @POST(" ")
    Call<JsonObject> getGroupsChats(@Query("action") String action,
                                    @Query("actionMethod") String actionMethod,
                                    @Query("token") String token,
                                    @Query("course_id") String receiver_id,
                                    @Query("limit") String limit,
                                    @Query("offset") String offset);


    @POST(" ")
    Call<JsonObject> userSessionExpire(@Query("action") String action,
                                       @Query("actionMethod") String actionMethod,
                                       @Query("token") String token,
                                       @Query("app_token") String receiver_id,
                                       @Query("last_login_id") String limit);

    @POST(" ")
    Call<JsonObject> getBroadcastVideo(@Query("action") String action,
                                       @Query("actionMethod") String actionMethod,
                                       @Query("token") String token);



}