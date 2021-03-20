package com.mansi.staquassignment.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mansi.staquassignment.R
import com.mansi.staquassignment.databinding.ActivityMainBinding
import com.mansi.staquassignment.utility.Common

class MainActivity : AppCompatActivity(), UnAnsweredListAdapter.ItemListener{

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var unAnsweredListAdapter: UnAnsweredListAdapter
    private lateinit var unAnsweredListViewModel: UnAnsweredListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.shimmerFrameLayout.visibility = View.VISIBLE
        activityMainBinding.shimmerFrameLayout.startShimmerAnimation()
        //Call methods
        initRecycleView()
        observeListData()

        activityMainBinding.tryAgainText.setOnClickListener {
            // If API is running ---
            initRecycleView()
            observeListData()
        }
    }


    private fun initRecycleView() {

        activityMainBinding.itemsRv.layoutManager = LinearLayoutManager(this@MainActivity)
        unAnsweredListAdapter = UnAnsweredListAdapter(this)
        activityMainBinding.itemsRv.adapter = unAnsweredListAdapter
        activityMainBinding.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(activityMainBinding.itemsRv.context,
                R.color.colorSeaGreen))
        activityMainBinding.swipeRefreshLayout.setColorSchemeColors(Color.WHITE)
        activityMainBinding.swipeRefreshLayout.setOnRefreshListener {
            observeListData()
            unAnsweredListViewModel.getUnAnsweredList()
            activityMainBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeListData(){
        unAnsweredListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(UnAnsweredListViewModel::class.java)
        unAnsweredListViewModel.getObservableList().observe(this){
            if(it!= null){
                unAnsweredListAdapter.setUnAnsweredLIstData(ArrayList(it))
                unAnsweredListAdapter.notifyDataSetChanged()
                activityMainBinding.itemsRv.visibility = View.VISIBLE
                activityMainBinding.shimmerFrameLayout.stopShimmerAnimation()

                activityMainBinding.shimmerFrameLayout.visibility = View.GONE
                activityMainBinding.gifImage.visibility = View.GONE
                activityMainBinding.somethingWentWrongText.visibility = View.GONE
                activityMainBinding.tryAgainText.visibility = View.GONE
            }else{
                activityMainBinding.gifImage.visibility = View.VISIBLE
                activityMainBinding.somethingWentWrongText.visibility = View.VISIBLE
                activityMainBinding.tryAgainText.visibility = View.VISIBLE
                activityMainBinding.progressBar.visibility = View.GONE
                activityMainBinding.itemsRv.visibility = View.GONE
                Glide.with(this).load(R.drawable.caveman).into(activityMainBinding.gifImage)
                activityMainBinding.shimmerFrameLayout.visibility = View.GONE
            }
        }
        unAnsweredListViewModel.getUnAnsweredList()
    }

    override fun onClickedItem(link: String) {
        val intent = Intent(this, WebViewActivity::class.java).apply {
            putExtra(Common.PAGE_LINK, link)
        }
        startActivity(intent)
    }


}

