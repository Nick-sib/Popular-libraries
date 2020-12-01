package com.nick_sib.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.databinding.ItemSimpleTextBinding
import com.nick_sib.popularlibraries.mvp.presenters.list.IForkListPresenter
import com.nick_sib.popularlibraries.mvp.view.ForkItemView
import kotlinx.android.extensions.LayoutContainer

class ForksRVAdapter(
    private val presenter: IForkListPresenter
) : RecyclerView.Adapter<ForksRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_simple_text, parent, false)
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, ForkItemView {
        private var binding: ItemSimpleTextBinding = ItemSimpleTextBinding.bind(containerView)

        override var pos = -1

        override fun setForkName(text: String) {
            binding.tvName.text = text
        }
    }
}