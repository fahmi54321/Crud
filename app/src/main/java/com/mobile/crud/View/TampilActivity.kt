package com.mobile.crud.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.crud.Adapter.DataAdapter
import com.mobile.crud.Model.ResponseUsers
import com.mobile.crud.Model.ResultItem
import com.mobile.crud.R
import com.mobile.crud.ViewModel.ViewModel
import kotlinx.android.synthetic.main.activity_tampil.*

class TampilActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModel
    lateinit var viewModelHapus: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampil)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        viewModelHapus = ViewModelProviders.of(this).get(ViewModel::class.java)
        pengamatan()

        viewModel.getData()

        tambah.setOnClickListener {
            val intemt = Intent(this, MainActivity::class.java)
            startActivity(intemt)
        }
    }

    private fun pengamatan() {
        viewModel.isSuksessGetData().observe(this, Observer { isSuksessGetData(it) })
        viewModel.isErrorGetData().observe(this, Observer { isErrorGetData(it) })

        viewModelHapus.isSuksessDelete().observe(this, Observer { isSuksessDelete(it) })
        viewModelHapus.isErrorDelete().observe(this, Observer { isErrorDelete(it) })
    }

    private fun isErrorDelete(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun isSuksessDelete(it: ResponseUsers?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun isErrorGetData(it: Throwable?) {
        Toast.makeText(this, it?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun isSuksessGetData(it: ResponseUsers?) {
        Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
        var dataAdapter = DataAdapter(it?.result, object : DataAdapter.onClick {
            override fun hapus(item: ResultItem?) {
                viewModelHapus.hapusData(item?.idUser.toString())
            }

            override fun edit(item: ResultItem?) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)
            }

        })

        rv_data.adapter = dataAdapter
    }
}