package com.mansi.staquassignment.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.chip.Chip
import com.mansi.staquassignment.databinding.ItemUnansweredlistBinding
import com.mansi.staquassignment.model.Items
import com.mansi.staquassignment.utility.Common
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UnAnsweredListAdapter(private val listener: ItemListener) : RecyclerView.Adapter<UnAnsweredListAdapter.MyViewHolder>(){

    interface ItemListener {
        fun onClickedItem(link: String)
    }

    private var items = ArrayList<Items>()
    lateinit var binding: ItemUnansweredlistBinding

    private val diffCallback = object : DiffUtil.ItemCallback<Items>(){
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.questionId == newItem.questionId
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun setUnAnsweredLIstData(data : ArrayList<Items>){
        differ.submitList(data)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemUnansweredlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MyViewHolder(private val binding: ItemUnansweredlistBinding, private val listener: ItemListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private lateinit var item: Items
        init {
            binding.root.setOnClickListener(this)
        }

        fun onBind(item : Items){
            this.item = item
            binding.title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
           binding.timeText.text = setTimeFormat(item.creationDate* 1000)

            Glide.with(binding.root)
                .load(item.owner.profileImage)
                .transform(CircleCrop())
                .into(binding.profileImage)
            binding.tagsText.removeAllViews()
            for (i in item.tags) {
                val chip = Chip(binding.tagsText.context)
                chip.text = i
                // Make the chip clickable
                chip.isClickable = false
                chip.isCheckable = false
                chip.isCloseIconVisible = false
                // Finally, add the chip to chip group
                binding.tagsText.addView(chip)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun setTimeFormat(dateInLong: Long) : String{
            val date = Date(dateInLong)
            val format = SimpleDateFormat(Common.TIME_FORMAT)
            return format.format(date)
        }


        override fun onClick(v: View?) {
            listener.onClickedItem(item.link)
        }
    }


}