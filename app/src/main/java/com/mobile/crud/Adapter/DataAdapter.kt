package com.mobile.crud.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.crud.Model.ResultItem
import com.mobile.crud.R

class DataAdapter(
    var data: List<ResultItem?>?,
    var click: onClick

) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    interface onClick {
        fun hapus(item: ResultItem?)
        fun edit(item: ResultItem?)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama = itemView.findViewById<TextView>(R.id.txtNama)
        var alamat = itemView.findViewById<TextView>(R.id.txtAlamat)
        var agama = itemView.findViewById<TextView>(R.id.txtAgama)
        var jk = itemView.findViewById<TextView>(R.id.txtJk)
        var hobi = itemView.findViewById<TextView>(R.id.txtHobi)
        var hapus = itemView.findViewById<ImageButton>(R.id.btnHapus)
        var edit = itemView.findViewById<ImageButton>(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        var item = data?.get(position)
        holder.nama.text = item?.nama
        holder.alamat.text = item?.alamat
        holder.agama.text = item?.agama
        holder.jk.text = item?.jk
        holder.hobi.text = item?.hobi

        holder.hapus.setOnClickListener {
            click.hapus(item)
        }
        holder.edit.setOnClickListener {
            click.edit(item)
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0
}