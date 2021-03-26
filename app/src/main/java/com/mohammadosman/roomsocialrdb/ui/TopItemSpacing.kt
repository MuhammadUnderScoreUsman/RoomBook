package com.mohammadosman.roomsocialrdb.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TopItemSpacing(
    private val padding: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = padding
    }
}