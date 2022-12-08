package com.dicoding.tripinaja

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var rvDestination: RecyclerView
    private var list: ArrayList<Destination> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvDestination = view.findViewById(R.id.rv_destination)
        rvDestination.setHasFixedSize(true)
        list.addAll(DestinationData.listData)
        showRecyclerCardView()
    }

    private fun showRecyclerCardView() {
        rvDestination.layoutManager = LinearLayoutManager(activity)
        val cardViewDestinationAdapter = CardViewDestinationAdaptor(list)
        rvDestination.adapter = cardViewDestinationAdapter
    }

}