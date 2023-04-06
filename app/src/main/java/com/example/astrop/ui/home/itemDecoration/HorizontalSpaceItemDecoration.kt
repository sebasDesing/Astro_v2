package com.example.astrop.ui.home.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(private val horizontalSpaceWidth: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = horizontalSpaceWidth
        outRect.left = horizontalSpaceWidth

        // Si es el primer elemento, no agregamos el espacio a la izquierda
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = 0
        }
    }
}