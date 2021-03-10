package com.mobile.inkadosumbar.network.kotlin

import com.mobile.crud.Model.ResponseUsers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


//todo 2.1 Config API
interface ConfigApi {

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("nama") nama:String,
        @Field("alamat") alamat:String,
        @Field("agama") agama:String,
        @Field("jk") jk:String,
        @Field("hobi") hobi:String
    ): Single<ResponseUsers>

    @FormUrlEncoded
    @POST("editUser.php")
    fun editUser(
        @Field("id_user") id_user:String,
        @Field("nama") nama:String,
        @Field("alamat") alamat:String,
        @Field("agama") agama:String,
        @Field("jk") jk:String,
        @Field("hobi") hobi:String
    ): Single<ResponseUsers>

    @GET("hapusUsers.php")
    fun hapusUsers(
        @Query("id_user") id_user:String,
    ): Single<ResponseUsers>

    @GET("selectUsers.php")
    fun selectUsers(): Single<ResponseUsers>
}