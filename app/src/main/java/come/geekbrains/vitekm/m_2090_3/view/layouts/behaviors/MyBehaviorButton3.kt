package come.geekbrains.vitekm.m_2090_3.view.layouts.behaviors

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import come.geekbrains.vitekm.m_2090_3.R

class MyBehaviorButton3(context: Context, attrs: AttributeSet?=null): CoordinatorLayout.Behavior<Button>(context,attrs)  {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        return (dependency.id == R.id.btn2)
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        if(dependency.id ==R.id.btn2){
            Log.d("@@@","${dependency.y} ${dependency.height}")
            child.x = dependency.y +child.width
            child.y = dependency.y
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}