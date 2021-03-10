package com.mobile.crud.Repository

import com.mobile.crud.Model.ResponseUsers
import com.mobile.inkadosumbar.network.kotlin.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository {

    fun register(
        nama:String,
        alamat:String,
        agama:String,
        jk:String,
        hobi:String,
        responHandler: (ResponseUsers)->Unit,
        errorHandler: (Throwable)->Unit
    ){
        ConfigNetwork.getRetrofit().register(nama, alamat, agama, jk, hobi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun getData(
        responHandler: (ResponseUsers) -> Unit,
        errorHandler: (Throwable) -> Unit
    ){
        ConfigNetwork.getRetrofit().selectUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun hapus(
        idUser:String,
        responHandler: (ResponseUsers) -> Unit,
        errorHandler: (Throwable) -> Unit
    ){
        ConfigNetwork.getRetrofit().hapusUsers(idUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun edit(
        idUser:String,
        nama:String,
        alamat:String,
        agama:String,
        jk:String,
        hobi:String,
        responHandler: (ResponseUsers)->Unit,
        errorHandler: (Throwable)->Unit
    ){
        ConfigNetwork.getRetrofit().editUser(idUser,nama, alamat, agama, jk, hobi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            },{
                errorHandler(it)
            })
    }
}