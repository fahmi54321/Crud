package com.mobile.crud.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.crud.Model.ResponseUsers
import com.mobile.crud.Repository.Repository
import okhttp3.Response

class ViewModel:ViewModel() {

    // register
    var repository = Repository()
    var responRegisterSukses = MutableLiveData<ResponseUsers>()
    var responRegisterError = MutableLiveData<Throwable>()
    var kosong = MutableLiveData<Boolean>()

    //get data
    var responSuksesGetData = MutableLiveData<ResponseUsers>()
    var responErrorGetData = MutableLiveData<Throwable>()

    //edit data
    var responUpdateSukses = MutableLiveData<ResponseUsers>()
    var responUpdateError = MutableLiveData<Throwable>()

    //hapusData
    var responHapusSukses = MutableLiveData<ResponseUsers>()
    var responHapusError = MutableLiveData<Throwable>()

    fun register(
        nama:String,
        alamat:String,
        agama:String,
        jk:String,
        hobi:String,
    ){
        if (nama.isNullOrEmpty()){
            kosong.value = true
        }else if (alamat.isNullOrEmpty()){
            kosong.value = true
        }else if (agama.isNullOrEmpty()){
            kosong.value = true
        }else if (jk.isNullOrEmpty()){
            kosong.value = true
        }else if (hobi.isNullOrEmpty()){
            kosong.value = true
        }else {
            repository.register(nama, alamat, agama, jk, hobi, {
                responRegisterSukses.value = it
            }, {
                responRegisterError.value = it
            })
        }
    }

    fun getData(){
        repository.getData({
            responSuksesGetData.value = it
        },{
            responErrorGetData.value = it
        })
    }

    fun update(
        id:String,
        nama:String,
        alamat:String,
        agama:String,
        jk:String,
        hobi:String,
    ){
        if (nama.isNullOrEmpty()){
            kosong.value = true
        }else if (alamat.isNullOrEmpty()){
            kosong.value = true
        }else if (agama.isNullOrEmpty()){
            kosong.value = true
        }else if (jk.isNullOrEmpty()){
            kosong.value = true
        }else if (hobi.isNullOrEmpty()){
            kosong.value = true
        }else {
            repository.edit(id,nama, alamat, agama, jk, hobi, {
                responUpdateSukses.value = it
            }, {
                responUpdateError.value = it
            })
        }
    }

    fun hapusData(
        id:String
    ){
        repository.hapus(id,{
            responHapusSukses.value = it
        },{
            responHapusError.value = it
        })
    }

    //register
    fun isSuksess():LiveData<ResponseUsers>{
        return responRegisterSukses
    }
    fun isError():LiveData<Throwable>{
        return responRegisterError
    }
    fun kosong():LiveData<Boolean>{

        return kosong
    }

    //update
    fun isSuksessUpdate():LiveData<ResponseUsers>{
        return responUpdateSukses
    }
    fun isErrorUpdate():LiveData<Throwable>{
        return responUpdateError
    }

    //hapus
    fun isSuksessDelete():LiveData<ResponseUsers>{
        return responHapusSukses
    }
    fun isErrorDelete():LiveData<Throwable>{
        return responHapusError
    }

    //getData
    fun isSuksessGetData():LiveData<ResponseUsers>{
        return responSuksesGetData
    }
    fun isErrorGetData():LiveData<Throwable>{
        return responErrorGetData
    }
}