package com.example.shark.ui.list

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shark.R
import com.example.shark.data.model.Photo

class ListViewAdaper(private val viewModel: ListViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var page = 1;
    private var photos = mutableListOf<Photo>()
    private var hasMore = false


    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_REGULAR) {
            RegularViewHolder(
                LayoutInflater.from(group.context).inflate(R.layout.item_view_layout, null)
            )
        } else {
            FootViewHolder(
                LayoutInflater.from(group.context).inflate(R.layout.foot_view_layout, null)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) TYPE_FOOT else TYPE_REGULAR
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if( viewHolder is RegularViewHolder ){
            val context = viewHolder.imageView.context
            val photo = photos[position]
            val url = photo.url_t
            Log.d(TAG,"zhq.debug url: $url ")
            Glide.with(context)
                .load(url)
                .into(viewHolder.imageView)
        }
    }

    class RegularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    class FootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val footTextView: TextView = itemView.findViewById(R.id.foot_text_view)
    }

    fun loadMore() {
        viewModel.loadPhotos("shark", page)
        page++
    }

    fun addPage(photos: List<Photo>) {
        this.photos.addAll(photos)
        hasMore = photos.isNotEmpty()
        notifyDataSetChanged()
        Log.d("addPage","zhq.debug onActivityCreated")
    }

    companion object {
        private const val TYPE_REGULAR = 1
        private const val TYPE_FOOT = 2
        private const val TAG = "ListViewAdaper"
    }
}

