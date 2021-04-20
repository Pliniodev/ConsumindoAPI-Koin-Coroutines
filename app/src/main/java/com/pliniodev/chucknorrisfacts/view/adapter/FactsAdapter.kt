package com.pliniodev.chucknorrisfacts.view.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pliniodev.chucknorrisfacts.R
import com.pliniodev.chucknorrisfacts.service.model.Fact
import com.pliniodev.chucknorrisfacts.view.listener.FactsListener

class FactsAdapter(
    val context: Context,
    val listener: FactsListener
) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    private var mFactModelList: List<Fact> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.facts_details,
            parent,
            false
        )
        return FactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bindView(mFactModelList[position])
    }

    override fun getItemCount(): Int {
        return mFactModelList.size
    }

    fun setFacts(facts: List<Fact>) {
        this.mFactModelList = facts
        notifyDataSetChanged()
    }

    inner class FactsViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val textValue: TextView = itemView.findViewById(R.id.text_value)
        private val textCategory: TextView = itemView.findViewById(R.id.text_category)
        private val imageShare: ImageView = itemView.findViewById(R.id.image_share)

        fun bindView(fact: Fact) {


            if (fact.isLongText) {
                textValue.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    context.resources.getDimension(R.dimen.smalltext_recyclerview_18sp)
                )
            }
            textValue.text = fact.value
            textCategory.text = fact.getCategory
            imageShare.setOnClickListener {
                listener.onClickShareImage(fact.url)
            }
        }
    }


}