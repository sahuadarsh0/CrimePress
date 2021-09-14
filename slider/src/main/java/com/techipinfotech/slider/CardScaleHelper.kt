package com.techipinfotech.slider

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.techipinfotech.slider.CardLinearSnapHelper
import com.techipinfotech.slider.ScreenUtil

class CardScaleHelper {
    private var mRecyclerView: RecyclerView? = null
    private var mContext: Context? = null
    private var mScale = 0.9f
    private var mPagePadding = 15
    private var mShowLeftCardWidth = 15
    private var mCardWidth = 0
    private var mOnePageWidth = 0
    private var mCardGalleryWidth = 0
    var currentItemPos = 0
    private var mCurrentItemOffset = 0
    private val mLinearSnapHelper = CardLinearSnapHelper()
    fun attachToRecyclerView(mRecyclerView: RecyclerView) {
        this.mRecyclerView = mRecyclerView
        mContext = mRecyclerView.context
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLinearSnapHelper.mNoNeedToScroll = mCurrentItemOffset == 0 || mCurrentItemOffset == getDestItemOffset(
                        mRecyclerView.adapter!!.itemCount - 1
                    )
                } else {
                    mLinearSnapHelper.mNoNeedToScroll = false
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx != 0) {
                    mCurrentItemOffset += dx
                    computeCurrentItemPos()
                    //                    LogUtils.v(String.format("dx=%s, dy=%s, mScrolledX=%s", dx, dy, mCurrentItemOffset));
                    onScrolledChangedCallback()
                }
            }
        })
        initWidth()
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView)
    }

    private fun initWidth() {
        mRecyclerView!!.post {
            mCardGalleryWidth = mRecyclerView!!.width
            mCardWidth =
                mCardGalleryWidth - ScreenUtil.dip2px(mContext!!, (2 * (mPagePadding + mShowLeftCardWidth)).toFloat())
            mOnePageWidth = mCardWidth
            mRecyclerView!!.smoothScrollToPosition(currentItemPos)
            onScrolledChangedCallback()
        }
    }

    private fun getDestItemOffset(destPos: Int): Int {
        return mOnePageWidth * destPos
    }

    private fun computeCurrentItemPos() {
        if (mOnePageWidth <= 0) return
        var pageChanged = false
        if (Math.abs(mCurrentItemOffset - currentItemPos * mOnePageWidth) >= mOnePageWidth) {
            pageChanged = true
        }
        if (pageChanged) {
            val tempPos = currentItemPos
            currentItemPos = mCurrentItemOffset / mOnePageWidth
            //            LogUtils.d(String.format("=======onCurrentItemPos Changed======= tempPos=%s, mCurrentItemPos=%s", tempPos, mCurrentItemPos));
        }
    }

    private fun onScrolledChangedCallback() {
        val offset = mCurrentItemOffset - currentItemPos * mOnePageWidth
        val percent = Math.max(Math.abs(offset) * 1.0 / mOnePageWidth, 0.0001).toFloat()

//        LogUtils.d(String.format("offset=%s, percent=%s", offset, percent));
        var leftView: View? = null
        val currentView: View?
        var rightView: View? = null
        if (currentItemPos > 0) {
            leftView = mRecyclerView!!.layoutManager!!.findViewByPosition(currentItemPos - 1)
        }
        currentView = mRecyclerView!!.layoutManager!!.findViewByPosition(currentItemPos)
        if (currentItemPos < mRecyclerView!!.adapter!!.itemCount - 1) {
            rightView = mRecyclerView!!.layoutManager!!.findViewByPosition(currentItemPos + 1)
        }
        if (leftView != null) {
            // y = (1 - mScale)x + mScale
            leftView.scaleY = (1 - mScale) * percent + mScale
        }
        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.scaleY = (mScale - 1) * percent + 1
        }
        if (rightView != null) {
            // y = (1 - mScale)x + mScale
            rightView.scaleY = (1 - mScale) * percent + mScale
        }
    }

    fun setScale(scale: Float) {
        mScale = scale
    }

    fun setPagePadding(pagePadding: Int) {
        mPagePadding = pagePadding
    }

    fun setShowLeftCardWidth(showLeftCardWidth: Int) {
        mShowLeftCardWidth = showLeftCardWidth
    }
}