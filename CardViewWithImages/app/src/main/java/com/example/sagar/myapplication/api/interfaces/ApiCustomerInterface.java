package com.example.sagar.myapplication.api.interfaces;

import com.example.sagar.myapplication.modal.Data;
import com.example.sagar.myapplication.modal.Customer;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiCustomerInterface{

     @GET("api/customer")
     Call<List<Customer>> listCustomer(
             @Header("token") String token
     );

     @Multipart
     @PUT("api/customer/{id}")
     Call<Data> updateCustomer(
            @Part MultipartBody.Part image,
            @Body Customer customer,
            @Path("id") String id,
            @Header("token") String token
     );

     @POST("api/customer")
     Call<Data> createCustomer(
            @Body Customer customer,
            @Header("token") String token
     );

     @DELETE("api/customer/{id}")
     Call<Data> deleteCustomer(
           @Path("id") String id,
           @Header("token") String token
     );

     @Multipart
     @POST("api/customer/image")
     Call<Data> addImage(
             @Header("token") String token ,
             @Part MultipartBody.Part image
     );

     @Multipart
     @PUT("api/cutomer/image/{id}")
     Call<Data> replaceCurrentImage(
        @Part MultipartBody.Part image ,
        @Path("id") String id ,
        @Header("token") String token
     );

     @DELETE("api/customer/image/{id}")
     Call<Data> deleteImageAndSetDefaultOne(
        @Path("id") String id ,
        @Header("token") String token
     );

     @DELETE("api/customer/{id}")
     Call<Data> deleteImage(
           @Path("id") String id,
           @Header("token") String token
      );


}

