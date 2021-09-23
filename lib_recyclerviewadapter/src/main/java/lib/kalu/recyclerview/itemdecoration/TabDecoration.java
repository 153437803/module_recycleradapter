package lib.kalu.recyclerview.itemdecoration;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import lib.kalu.recyclerview.adapter.BaseCommonAdapter;

/**
 * description: 利用分割线实现悬浮, 配合BaseCommonAdapter使用
 * created by kalu on 2017/6/14 13:48
 */
public abstract class TabDecoration extends RecyclerView.ItemDecoration {


    public TabDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int tabHeight = loadHeight(position);
        if (tabHeight == 0) return;

        if (position == (hasHead() ? 1 : 0)) {
            outRect.top = tabHeight;
        }

        String groupId = loadName(position);
        if (TextUtils.isEmpty(groupId)) return;

        //只有是同一组的第一个才显示悬浮栏
        if (position >= (hasHead() ? 2 : 1)) {

            String preGroupId = loadName(position - 1);
            if (!TextUtils.isEmpty(preGroupId) && !preGroupId.equals(groupId)) {
                outRect.top = tabHeight;
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        // 孩子总个数
        int itemCount = state.getItemCount();
        // 屏幕显示布局个数(布局复用)
        int layoutCount = parent.getChildCount();

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        String preGroupName;
        String currentGroupName = null;

        for (int i = 0; i < layoutCount; i++) {

            // 1.当前遍历临时View
            View sub = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(sub);
            int tabHeight = loadHeight(position);
            if(tabHeight == 0) continue;

            preGroupName = currentGroupName;
            currentGroupName = loadName(position);

            if (TextUtils.isEmpty(currentGroupName) || TextUtils.equals(currentGroupName, preGroupName))
                continue;

            int viewBottom = sub.getBottom();
            int top = Math.max(tabHeight, sub.getTop());//top 决定当前顶部第一个悬浮Group的位置
            if (position + 1 < itemCount) {
                //获取下个GroupName
                String nextGroupName = loadName(position + 1);
                //下一组的第一个View接近头部
                if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom;
                }
            }

            //根据position获取View
            View tab = loadView(position);
            if (null == tab) return;

            // 1.填充TAB内容
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, tabHeight);
            tab.setLayoutParams(layoutParams);
            tab.setDrawingCacheEnabled(true);
            tab.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            // 2.绘制TAB布局
            tab.layout(0, 0, right, tabHeight);
            tab.buildDrawingCache();
            Bitmap bitmap = tab.getDrawingCache();

            if (hasHead() && position == 0) {
                int headBottom = 0;
                RecyclerView.Adapter adapter = parent.getAdapter();
                if (null != adapter && adapter instanceof BaseCommonAdapter) {
                    BaseCommonAdapter temp = (BaseCommonAdapter) adapter;
                    View headerLayout = temp.getHead(0);
                    if (null != headerLayout) {
                        headBottom = headerLayout.getBottom();
                    }
                }
                c.drawBitmap(bitmap, left, top - tabHeight + headBottom, null);
            } else {
                c.drawBitmap(bitmap, left, top - tabHeight, null);
            }
        }
    }

    /**************************************************************************************/

    public boolean hasHead() {
        return false;
    }

    /**
     * Tab高度
     *
     * @return
     */
    public abstract int loadHeight(int position);

    /**
     * Tab名字
     *
     * @param position
     * @return
     */
    public abstract String loadName(int position);

    /**
     * Tab布局文件
     *
     * @param position
     * @return
     */
    public abstract View loadView(int position);
}