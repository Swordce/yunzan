package com.liaoda.yunzan.yzFlowLayout;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowTagLayout extends ViewGroup {
    public static final int FLOW_TAG_CHECKED_MULTI = 2;
    public static final int FLOW_TAG_CHECKED_NONE = 0;
    public static final int FLOW_TAG_CHECKED_SINGLE = 1;
    private static final String TAG = FlowTagLayout.class.getSimpleName();
    public boolean MUST_SELECT_ONE_MODE = false;
    private List<View> childViewList = new ArrayList();
    ListAdapter mAdapter;
    /* access modifiers changed from: private */
    public SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();
    AdapterDataSetObserver mDataSetObserver;
    OnTagClickListener mOnTagClickListener;
    OnTagSelectListener mOnTagSelectListener;
    /* access modifiers changed from: private */
    public int mTagCheckMode = 0;
    /* access modifiers changed from: private */
    public boolean selectedLock = false;

    class AdapterDataSetObserver extends DataSetObserver {
        AdapterDataSetObserver() {
        }

        public void onChanged() {
            super.onChanged();
            FlowTagLayout.this.reloadData();
        }

        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public void setSelectedLock(boolean selectedLock) {
        this.selectedLock = selectedLock;
    }

    public void setMustSelectOneMode(boolean mustSelectOneMode) {
        this.MUST_SELECT_ONE_MODE = mustSelectOneMode;
    }

    public FlowTagLayout(Context context) {
        super(context);
    }

    public FlowTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int resultWidth = 0;
        int resultHeight = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int i2;
            int i3;
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = (mlp.leftMargin + childWidth) + mlp.rightMargin;
            int realChildHeight = (mlp.topMargin + childView.getMeasuredHeight()) + mlp.bottomMargin;
            if (lineWidth + realChildWidth > sizeWidth) {
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += realChildHeight;
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;
            } else {
                lineWidth += realChildWidth;
                lineHeight = Math.max(lineHeight, realChildHeight);
            }
            if (i == childCount - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }
            if (modeWidth == 1073741824) {
                i2 = sizeWidth;
            } else {
                i2 = resultWidth;
            }
            if (modeHeight == 1073741824) {
                i3 = sizeHeight;
            } else {
                i3 = resultHeight;
            }
            setMeasuredDimension(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        int flowWidth = getWidth();
        int childLeft = 0;
        int childTop = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != GONE) {
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
                if (((mlp.leftMargin + childLeft) + childWidth) + mlp.rightMargin > flowWidth) {
                    childTop += (mlp.topMargin + childHeight) + mlp.bottomMargin;
                    childLeft = 0;
                }
                childView.layout(childLeft + mlp.leftMargin, childTop + mlp.topMargin, (mlp.leftMargin + childLeft) + childWidth, (mlp.topMargin + childTop) + childHeight);
                childLeft += (mlp.leftMargin + childWidth) + mlp.rightMargin;
            }
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void clearPosition(int position) {
        if (this.mCheckedTagArray.get(position, false)) {
            ((View) this.childViewList.get(position)).performClick();
        }
    }

    public void reloadData() {
        removeAllViews();
        this.childViewList.clear();
        boolean isSetted = false;
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            final int j = i;
            this.mCheckedTagArray.put(i, false);
            final View childView = this.mAdapter.getView(i, null, this);
            addView(childView, new MarginLayoutParams(new LayoutParams(-2, -2)));
            if (this.mAdapter instanceof OnInitSelectedPosition) {
                boolean isSelected = ((OnInitSelectedPosition) this.mAdapter).isSelectedPosition(i);
                if (this.mTagCheckMode == 1) {
                    if (isSelected && !isSetted) {
                        this.mCheckedTagArray.put(i, true);
                        childView.setSelected(true);
                        isSetted = true;
                    }
                } else if (this.mTagCheckMode == 2 && isSelected) {
                    this.mCheckedTagArray.put(i, true);
                    childView.setSelected(true);
                }
            }
            childView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    int k;
                    if (FlowTagLayout.this.mTagCheckMode == 0) {
                        if (FlowTagLayout.this.mOnTagClickListener != null) {
                            FlowTagLayout.this.mOnTagClickListener.onItemClick(FlowTagLayout.this, childView, j);
                        }
                    } else if (FlowTagLayout.this.mTagCheckMode == 1) {
                        if (!FlowTagLayout.this.mCheckedTagArray.get(j)) {
                            for (k = 0; k < FlowTagLayout.this.mAdapter.getCount(); k++) {
                                FlowTagLayout.this.mCheckedTagArray.put(k, false);
                                FlowTagLayout.this.getChildAt(k).setSelected(false);
                            }
                            FlowTagLayout.this.mCheckedTagArray.put(j, true);
                            childView.setSelected(true);
                            if (FlowTagLayout.this.mOnTagSelectListener != null) {
                                FlowTagLayout.this.mOnTagSelectListener.onItemSelect(FlowTagLayout.this, Arrays.asList(new Integer[]{Integer.valueOf(j)}));
                            }
                        } else if (!FlowTagLayout.this.MUST_SELECT_ONE_MODE) {
                            FlowTagLayout.this.mCheckedTagArray.put(j, false);
                            childView.setSelected(false);
                            if (FlowTagLayout.this.mOnTagSelectListener != null) {
                                FlowTagLayout.this.mOnTagSelectListener.onItemSelect(FlowTagLayout.this, new ArrayList());
                            }
                        }
                    } else if (FlowTagLayout.this.mTagCheckMode == 2 && !FlowTagLayout.this.selectedLock) {
                        if (!FlowTagLayout.this.mCheckedTagArray.get(j)) {
                            FlowTagLayout.this.mCheckedTagArray.put(j, true);
                            childView.setSelected(true);
                        } else if (FlowTagLayout.this.MUST_SELECT_ONE_MODE) {
                            int trueCount = 0;
                            for (int i = 0; i < FlowTagLayout.this.mCheckedTagArray.size(); i++) {
                                if (FlowTagLayout.this.mCheckedTagArray.get(i)) {
                                    trueCount++;
                                }
                            }
                            if (trueCount > 1) {
                                FlowTagLayout.this.mCheckedTagArray.put(j, false);
                                childView.setSelected(false);
                            }
                        } else {
                            FlowTagLayout.this.mCheckedTagArray.put(j, false);
                            childView.setSelected(false);
                        }
                        if (FlowTagLayout.this.mOnTagSelectListener != null) {
                            List<Integer> list = new ArrayList();
                            for (k = 0; k < FlowTagLayout.this.mAdapter.getCount(); k++) {
                                if (FlowTagLayout.this.mCheckedTagArray.get(k)) {
                                    list.add(Integer.valueOf(k));
                                }
                            }
                            FlowTagLayout.this.mOnTagSelectListener.onItemSelect(FlowTagLayout.this, list);
                        }
                    }
                }
            });
            this.childViewList.add(childView);
        }
    }

    public void clearAllOption() {
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            if (this.mCheckedTagArray.get(i)) {
                getChildAt(i).setSelected(false);
            }
        }
    }

    public void clearAllResult() {
        for (int i = 0; i < this.mCheckedTagArray.size(); i++) {
            this.mCheckedTagArray.put(i, false);
        }
    }

    public void clearAll() {
        clearAllOption();
        clearAllResult();
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public void setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        this.mOnTagSelectListener = onTagSelectListener;
    }

    public void setAdapter(ListAdapter adapter) {
        if (!(this.mAdapter == null || this.mDataSetObserver == null)) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        removeAllViews();
        this.mAdapter = adapter;
        if (this.mAdapter != null) {
            this.mDataSetObserver = new AdapterDataSetObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
    }

    public int getmTagCheckMode() {
        return this.mTagCheckMode;
    }

    public void setTagCheckedMode(int tagMode) {
        this.mTagCheckMode = tagMode;
    }

    private int getCheckedTagArrayTureNumber() {
        int result = 0;
        for (int i = 0; i < this.mCheckedTagArray.size(); i++) {
            if (this.mCheckedTagArray.get(i, false)) {
                result++;
            }
        }
        return result;
    }
}
