package com.yh.coordinatordemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

class FooterBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    var sinceDirectionChange:Int=0//总偏移量
//
//    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
//        var translationY=Math.abs(dependency.top)
//        child.translationY= translationY.toFloat()
//        return true
//    }
//
//    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
//        return dependency is AppBarLayout
//    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        //滑动方向变化时，偏移量要置0，并取消动画
        if ((dy>0&&sinceDirectionChange<0)||(dy<0&&sinceDirectionChange>0)){
            child.animate().cancel()
            sinceDirectionChange=0
        }

        sinceDirectionChange+=dy

        var visible=child.visibility

        if (sinceDirectionChange > child.height && visible == View.VISIBLE) {
            hide(child)
        }else if (sinceDirectionChange<0&&visible==View.INVISIBLE){
            show(child)
        }
        View.MeasureSpec.EXACTLY
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun hide(view: View) {
        var animator=view.animate().translationY(view.height.toFloat()).setDuration(200)

        animator.setListener (object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                view.visibility=View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
                show(view)
            }
        })

        animator.start()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun show(view: View) {
        var animator=view.animate().translationY(0f).setDuration(200)

        animator.setListener (object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                view.visibility=View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
                hide(view)
            }
        })

        animator.start()
    }

}