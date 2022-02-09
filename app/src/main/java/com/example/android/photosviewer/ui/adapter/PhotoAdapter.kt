package com.example.android.photosviewer.ui.adapter

import com.example.android.photosviewer.BR
import com.example.android.photosviewer.R
import com.example.android.photosviewer.data.model.domain.Photo
import com.example.android.photosviewer.databinding.ItemPhotoBinding
import com.example.android.photosviewer.ui.adapter.base.BaseRecyclerViewAdapter

class PhotoAdapter(onItemClickListener: OnItemClickListener<Photo>) :
    BaseRecyclerViewAdapter<Photo, ItemPhotoBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_photo
    }

    override fun getViewBindingVariableId(): Int {
        return BR.photo
    }

    override fun onViewHolderBinding(
        viewDataBinding: ItemPhotoBinding,
        item: Photo?,
        position: Int
    ) {
    }
}