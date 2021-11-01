package com.example.gamechasepicturescatchletters.ui.fragment.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechasepicturescatchletters.R
import com.example.gamechasepicturescatchletters.ui.callback.OnActionCallback
import com.example.gamechasepicturescatchletters.ui.fragment.detail.adapter.ReplyAdapter
import com.example.gamechasepicturescatchletters.ui.fragment.detail.adapter.ResultAdapter
import com.example.gamechasepicturescatchletters.ui.fragment.menu.MenuAndDetailViewModel
import kotlinx.android.synthetic.main.frag_detail.*

class DetailFragment : Fragment(), OnActionCallback {
    private lateinit var mView : View
    private lateinit var menuAndDetailViewModel : MenuAndDetailViewModel

    private lateinit var resultAdapter : ResultAdapter
    private lateinit var replyAdapter: ReplyAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.frag_detail,container,false)
        initViews()
        return mView
    }

    private fun initViews() {
        menuAndDetailViewModel = ViewModelProvider(requireActivity()).get(MenuAndDetailViewModel::class.java)

        menuAndDetailViewModel.currentRuby.observe(viewLifecycleOwner, Observer {
            tv_ruby_detail.setText(it.toString())
        })

        menuAndDetailViewModel.timeQuestion()
        menuAndDetailViewModel.timeQuestion.observe(viewLifecycleOwner, Observer {
            tv_time_detail.setText(it.toString())
        })

//        menuAndDetailViewModel.getListText(requireContext())
        resultAdapter  = ResultAdapter(menuAndDetailViewModel.getListElementRecyclerViewResult(),requireContext())
        replyAdapter = ReplyAdapter(menuAndDetailViewModel.getListElementRecyclerViewReply(),requireContext())
        replyAdapter.setCallback(this)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        imv_back_detail.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                requireActivity().onBackPressed()
            }

        })






    }

    private fun setupRecyclerView() {
        recyclerview_result_detail.layoutManager = GridLayoutManager(requireContext(),8)
        recyclerview_result_detail.adapter = resultAdapter

        recyclerview_reply_detail.layoutManager = GridLayoutManager(requireContext(),7)
        recyclerview_reply_detail.adapter = replyAdapter
    }

    override fun onCallback(position: Int) {

    }
}