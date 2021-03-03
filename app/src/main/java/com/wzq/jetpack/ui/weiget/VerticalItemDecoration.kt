package com.wzq.jetpack.ui.weiget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration(
    private val mDividerViewTypeMap: SparseArray<Drawable>,
    private val mFirstDrawable: Drawable?,
    private val mLastDrawable: Drawable?
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        // last position
        if (isLastPosition(view, parent)) {
            if (mLastDrawable != null) {
                outRect.bottom = mLastDrawable.intrinsicHeight
            }
            return
        }

        // specific view type
        val childType = parent.layoutManager?.getItemViewType(view)!!
        val drawable = mDividerViewTypeMap[childType]
        if (drawable != null) {
            outRect.bottom = drawable.intrinsicHeight
        }

        // first position
        if (isFirstPosition(view, parent)) {
            if (mFirstDrawable != null) {
                outRect.top = mFirstDrawable.intrinsicHeight
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childViewType = parent.layoutManager?.getItemViewType(child)!!
            val params = child.layoutParams as RecyclerView.LayoutParams

            // last position
            if (isLastPosition(child, parent)) {
                if (mLastDrawable != null) {
                    val top = child.bottom + params.bottomMargin
                    val bottom = top + mLastDrawable.intrinsicHeight
                    mLastDrawable.setBounds(left, top, right, bottom)
                    mLastDrawable.draw(c)
                }
                return
            }

            // specific view type
            val drawable = mDividerViewTypeMap[childViewType]
            if (drawable != null) {
                val top = child.bottom + params.bottomMargin
                val bottom = top + drawable.intrinsicHeight
                drawable.setBounds(left, top, right, bottom)
                drawable.draw(c)
            }

            // first position
            if (isFirstPosition(child, parent)) {
                if (mFirstDrawable != null) {
                    val bottom = child.top - params.topMargin
                    val top = bottom - mFirstDrawable.intrinsicHeight
                    mFirstDrawable.setBounds(left, top, right, bottom)
                    mFirstDrawable.draw(c)
                }
            }
        }
    }

    private fun isFirstPosition(view: View, parent: RecyclerView): Boolean {
        return parent.getChildAdapterPosition(view) == 0
    }

    private fun isLastPosition(view: View, parent: RecyclerView): Boolean {
        return parent.getChildAdapterPosition(view) == parent.adapter?.itemCount!! - 1
    }

    class Builder internal constructor(private val mContext: Context) {
        private val mDividerViewTypeMap = SparseArray<Drawable>()
        private var mFirstDrawable: Drawable? = null
        private var mLastDrawable: Drawable? = null

        fun type(viewType: Int): Builder {
            val a = mContext.obtainStyledAttributes(ATTRS)
            val divider = a.getDrawable(0)!!
            type(viewType, divider)
            a.recycle()
            return this
        }

        fun type(viewType: Int, @DrawableRes drawableResId: Int): Builder {
            mDividerViewTypeMap.put(viewType, ContextCompat.getDrawable(mContext, drawableResId)!!)
            return this
        }

        fun type(viewType: Int, drawable: Drawable): Builder {
            mDividerViewTypeMap.put(viewType, drawable)
            return this
        }

        fun first(@DrawableRes drawableResId: Int): Builder {
            first(ContextCompat.getDrawable(mContext, drawableResId)!!)
            return this
        }

        fun first(drawable: Drawable): Builder {
            mFirstDrawable = drawable
            return this
        }

        fun last(@DrawableRes drawableResId: Int): Builder {
            last(ContextCompat.getDrawable(mContext, drawableResId)!!)
            return this
        }

        fun last(drawable: Drawable): Builder {
            mLastDrawable = drawable
            return this
        }

        fun create(): VerticalItemDecoration {
            return VerticalItemDecoration(mDividerViewTypeMap, mFirstDrawable, mLastDrawable)
        }
    }

    companion object {

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
