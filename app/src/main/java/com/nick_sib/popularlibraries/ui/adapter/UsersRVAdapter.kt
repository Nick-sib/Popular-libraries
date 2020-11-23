package com.nick_sib.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.databinding.ItemUserBinding
import com.nick_sib.popularlibraries.mvp.presenters.list.IUserListPresenter
import com.nick_sib.popularlibraries.mvp.view.image.IImageLoader
import com.nick_sib.popularlibraries.mvp.view.UserItemView
import kotlinx.android.extensions.LayoutContainer

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
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
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, UserItemView {
        private var binding: ItemUserBinding = ItemUserBinding.bind(containerView)

        override var pos = -1
        override fun setLogin(text: String) {
            binding.tvLogin.text = text
        }
        override fun loadAvatar(url: String)  {
            imageLoader.loadInto(url, binding.ivAvatar)
        }
    }
}