package com.dhha22.bindadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dhha22.bindadapter.listener.OnItemClickListener;
import com.dhha22.bindadapter.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public class BindAdapter extends AbsAdapter implements BindAdapterContract.View {
    private static final String TAG = "BindAdapter";
    private static final int DEFAULT = 1;
    private static final String HEADER = "header_";
    private static final String FOOTER = "footer_";
    private int itemCount;
    private final List<Integer> headerHashes = new ArrayList<>();
    private final List<Integer> footerHashes = new ArrayList<>();
    private final List<ItemView> headerViews = new ArrayList<>();
    private final List<ItemView> footerViews = new ArrayList<>();
    private Context context;
    private Class<? extends ItemView> layoutClass;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public BindAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerHashes.contains(viewType)) {
            for (ItemView view : headerViews) {
                if (view.getTag(R.id.tag) != null && Integer.valueOf(view.getTag(R.id.tag).toString()) == viewType) {
                    Log.d(TAG, "header holder");
                    return new HeaderFooterHolder(view);
                }
            }
        }

        if (footerHashes.contains(viewType)) {
            for (ItemView view : footerViews) {
                if (view.getTag(R.id.tag) != null && Integer.valueOf(view.getTag(R.id.tag).toString()) == viewType) {
                    Log.d(TAG, "footer holder");
                    return new HeaderFooterHolder(view);
                }
            }
        }

        if (innerAdapter != null) {
            Log.d(TAG, "inner holder");
            return innerAdapter.onCreateViewHolder(parent, viewType);
        } else {
            Log.d(TAG, "simple holder");
            return new SimpleHolder(getItemView(layoutClass));
        }
    }

    public BindAdapter setInnerAdapter(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
        return this;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof HeaderFooterHolder)) {  // item
            int absPosition = position - getHeaderViewSize();
            if (innerAdapter != null) {
                innerAdapter.onBindViewHolder(holder, absPosition);
            } else if (layoutClass != null) {
                ((ItemView) holder.itemView).setData(items.get(absPosition));
            }
        } else {
            if (position < getHeaderViewSize() && getHeaderItemSize() > 0 && (getHeaderItemSize() - 1) >= position) {   // header
                ((ItemView) holder.itemView).setData(headerItems.get(position));
            } else if (position >= (getHeaderViewSize() + itemCount) &&
                    getFooterItemSize() > 0 && getFooterItemSize() >= (position - getHeaderViewSize() - itemCount)) { // footer
                ((ItemView) holder.itemView).setData(footerItems.get(position - getHeaderViewSize() - itemCount));
            }

        }
    }

    @Override
    public BindAdapter addHeaderView(Class<? extends ItemView> layoutClass) {
        ItemView v = getItemView(layoutClass);
        if (v != null && headerViews.indexOf(v) == -1) {
            headerViews.add(v);
            int hash = (HEADER + headerViews.indexOf(v) + v).hashCode();
            v.setTag(R.id.tag, hash);
            headerHashes.add(hash);
            Log.d(TAG, "addHeaderViewHash: " + hash);
            notifyItemInserted(headerViews.size() - 1);
        }
        return this;
    }

    @Override
    public BindAdapter addHeaderView(int position, Class<? extends ItemView> layoutClass) {
        ItemView v = getItemView(layoutClass);
        if (v != null && headerViews.indexOf(v) == -1) {
            headerViews.add(position, v);
            int hash = (HEADER + headerViews.indexOf(v) + v).hashCode();
            v.setTag(R.id.tag, hash);
            headerHashes.add(position, hash);
            Log.d(TAG, "addHeaderViewHash: " + hash);
            notifyItemInserted(position);
        }
        return this;
    }

    @Override
    public ItemView getHeaderView(int position) {
        return headerViews.get(position);
    }

    @Override
    public int getHeaderViewSize() {
        return headerViews.size();
    }

    @Override
    public void removeHeaderView(int position) {
        if (position >= 0 && position < headerViews.size()) {
            removeHeaderItem(position);
            headerViews.remove(position);
            Log.d(TAG, "removeHeaderViewHash: " + headerHashes.get(position));
            headerHashes.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void clearHeaderView() {
        if (headerViews.size() > 0) {
            clearHeaderItem();
            headerViews.clear();
            headerHashes.clear();
            notifyItemRangeRemoved(0, headerViews.size());
        }
    }


    @Override
    public BindAdapter addLayout(Class<? extends ItemView> layoutClass) {
        this.layoutClass = layoutClass;
        return this;
    }

    @Override
    public void removeItemView(int position) {
        Log.d(TAG, "removeItemView: " + position);
        if (position < getHeaderViewSize()) {
            Log.d(TAG, "removeHeaderView position: " + position);
            removeHeaderView(position);
        } else if (position >= getHeaderViewSize() && position < getHeaderViewSize() + getItemSize()) {
            Log.d(TAG, "removeItemView position: " + (position - getHeaderViewSize()));
            removeItem(position - getHeaderViewSize());
            notifyItemRemoved(position);
        } else {
            Log.d(TAG, "removeFooterView position: " + (position - (getHeaderViewSize() + getItemSize())));
            removeFooterView(position - (getHeaderViewSize() + getItemSize()));
        }
    }

    @Override
    public BindAdapter addFooterView(Class<? extends ItemView> layoutClass) {
        ItemView v = getItemView(layoutClass);
        if (v != null && footerViews.indexOf(v) == -1) {
            footerViews.add(v);
            int hash = (FOOTER + footerViews.indexOf(v) + v).hashCode();
            v.setTag(R.id.tag, hash);
            footerHashes.add(hash);
            Log.d(TAG, "addFooterViewHash: " + hash);
            notifyItemInserted(getHeaderViewSize() + itemCount + getFooterViewSize() - 1);
        }
        return this;
    }

    @Override
    public BindAdapter addFooterView(int position, Class<? extends ItemView> layoutClass) {
        ItemView v = getItemView(layoutClass);
        if (v != null && footerViews.indexOf(v) == -1) {
            footerViews.add(position, v);
            int hash = (FOOTER + footerViews.indexOf(v) + v).hashCode();
            v.setTag(R.id.tag, hash);
            footerHashes.add(position, hash);
            Log.d(TAG, "addFooterViewHash: " + hash);
            notifyItemInserted(getHeaderViewSize() + itemCount + position);
        }
        return this;
    }

    @Override
    public ItemView getFooterView(int position) {
        return footerViews.get(position);
    }

    @Override
    public int getFooterViewSize() {
        return footerViews.size();
    }

    @Override
    public void removeFooterView(int position) {
        if (position >= 0 && position < getFooterViewSize()) {
            removeFooterItem(position);
            footerViews.remove(position);
            Log.d(TAG, "removeFooterViewHash: " + footerHashes.get(position));
            footerHashes.remove(position);
            notifyItemRemoved(getHeaderViewSize() + getItemSize() + position);
        }
    }

    @Override
    public void clearFooterView() {
        if (getFooterViewSize() > 0) {
            clearFooterItem();
            footerViews.clear();
            footerHashes.clear();
            notifyItemRangeRemoved(getHeaderItemSize() + getItemSize(), getFooterViewSize());
        }
    }

    @Override
    public int getItemCount() {
        itemCount = innerAdapter != null ? innerAdapter.getItemCount() : items.size();
        return getHeaderViewSize() + itemCount + getFooterViewSize();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderViewSize()) {    // header 일 경우
            return headerHashes.size() > position ? headerHashes.get(position) : 0;
        } else if (position >= getHeaderViewSize() + itemCount) { // footer 일 경우
            position -= (getHeaderViewSize() + itemCount);
            return footerHashes.size() > position ? footerHashes.get(position) : 0;
        } else {    // item 일 경우
            position -= getHeaderViewSize();
            return innerAdapter != null ? innerAdapter.getItemViewType(position) : DEFAULT;
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    private ItemView getItemView(Class<? extends ItemView> layoutClass) {
        try {
            return layoutClass.getConstructor(Context.class).newInstance(context);
        } catch (Throwable e) {
            return null;
        }
    }


    public class HeaderFooterHolder extends RecyclerView.ViewHolder {
        protected HeaderFooterHolder(ItemView itemView) {
            super(itemView);
            setItemViewClickListener(itemView, this);
            setItemViewLongClickListener(itemView, this);
        }
    }

    public class SimpleHolder extends RecyclerView.ViewHolder {
        protected SimpleHolder(ItemView itemView) {
            super(itemView);
            setItemViewClickListener(itemView, this);
            setItemViewLongClickListener(itemView, this);
        }
    }

    private void setItemViewClickListener(ItemView itemView, final RecyclerView.ViewHolder holder) {
        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            });
        }
    }

    private void setItemViewLongClickListener(ItemView itemView, final RecyclerView.ViewHolder holder) {
        if (itemLongClickListener != null) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickListener.onItemLongClick(v, holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
