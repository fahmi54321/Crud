package com.mobile.crud.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.crud.Model.ResponseUsers
import com.mobile.crud.Model.ResultItem
import com.mobile.crud.R
import com.mobile.crud.ViewModel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModel
    lateinit var viewModelEdit: ViewModel

    var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        viewModelEdit = ViewModelProviders.of(this).get(ViewModel::class.java)
        pengamatan()

        var getData = intent.getParcelableExtra<ResultItem>("data")
        if (getData != null) {
            edtNama.setText(getData.nama)
            edtAlamat.setText(getData.alamat)
            id = getData.idUser
            btnTambah.setText("Update")
        }

        btnTambah.setOnClickListener {

            var nama = edtNama.text.toString()
            var alamat = edtAlamat.text.toString()
            var agama = spinner.selectedItem.toString()
            var jk = ""
            if (rbLaki.isChecked) {
                jk = rbLaki.text.toString()
            }
            if (rbPerempuan.isChecked) {
                jk = rbPerempuan.text.toString()
            }
            var hobi = ""
            if (chkOlahraga.isChecked) {
                hobi += chkOlahraga.text.toString() + ","
            }
            if (chkMemasak.isChecked) {
                hobi += chkMemasak.text.toString() + ","
            }
            if (chkMemancing.isChecked) {
                hobi += chkMemancing.text.toString() + ","
            }
            if (chkLainnya.isChecked) {
                hobi += chkLainnya.text.toString() + ","
            }

            when (btnTambah.text) {
                "Update" -> {
                    viewModelEdit.update(id.toString(), nama, alamat, agama, jk, hobi)
                }
                else -> {
                    viewModel.register(nama, alamat, agama, jk, hobi)
                }
            }


        }
    }

    private fun pengamatan() {
        viewModel.isSuksess().observe(this, Observer { responSukses(it) })
        viewModel.isError().observe(this, Observer { responGagal(it) })
        viewModel.kosong().observe(this, Observer { empty(it) })

        viewModelEdit.isSuksessUpdate().observe(this, Observer { responSukses(it) })
        viewModelEdit.isErrorUpdate().observe(this, Observer { responGagal(it) })
        viewModelEdit.kosong().observe(this, Observer { empty(it) })
    }

    private fun empty(it: Boolean?) {
        if (it == true) {
            Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun responGagal(it: Throwable?) {
        Toast.makeText(this, it?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun responSukses(it: ResponseUsers?) {
        Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, TampilActivity::class.java)
        startActivity(intent)
    }
}