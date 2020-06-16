package com.example.shark.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shark.R
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
        private const val TAG = "ListFragment"
    }

    private val viewModel: ListViewModel by lazy { ViewModelProviders.of(this).get(ListViewModel::class.java) }

    private val adapter: ListViewAdaper by lazy { ListViewAdaper(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel.photos.observe(viewLifecycleOwner,  Observer {
            photos -> adapter.addPage(photos!!)
        })
        Log.d(TAG,"zhq.debug onActivityCreated")
    }

    private fun initRecyclerView(){
        recycle_view.layoutManager = GridLayoutManager(context,3)
        recycle_view.itemAnimator = DefaultItemAnimator()
        recycle_view.adapter = adapter
        adapter.loadMore()
    }

}