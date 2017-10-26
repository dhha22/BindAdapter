package com.dhha22.bindadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class BindAdapter extends AbsAdapter implements BindAdapterContract.View{
    private static final String TAG = "BindAdapter";
    private static final int DEFAULT = 1;
    private static final String HEADER = "header_";
    private static final String FOOTER = "footer_";
    private int headerSize;
    private int footerSize;
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
                    Log.v(TAG, "header holder");
                    return new HeaderFooterHolder(view);
                }
            }
        }

        if (footerHashes.contains(viewType)) {
            for (ItemView view : footerViews) {
                if (view.getTag(R.id.tag) != null && Integer.valueOf(view.getTag(R.id.tag).toString()) == viewType) {
                    Log.v(TAG, "footer holder");
                    return new HeaderFooterHolder(view);
                }
            }
        }

        if (innerAdapter != null) {
            Log.v(TAG, "inner holder");
            return innerAdapter.onCreateViewHolder(parent, viewType);
        } else {
            Log.v(TAG, "simple holder");
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
            int absPosition = position - headerSize;
            if (innerAdapter != null) {
                innerAdapter.onBindViewHolder(holder, absPosition);
            } else {
                ((ItemView) holder.itemView).setData(items.get(absPosition));
            }
        } else {
            if (position < headerSize && getHeaderItemSize() > 0 && (getHeaderItemSize() - 1) >= position) {   // header
                ((ItemView) holder.itemView).setData(headerItems.get(position));
            } else if (position >= (headerSize + itemCount) &&
                    getFooterItemSize() > 0 && getFooterItemSize() >= (position - headerSize - itemCount)) { // footer
                ((ItemView) holder.itemView).setData(footerItems.get(position - headerSize - itemCount));
            }

        }
    }


    @Override
    public BindAdapter addHeaderView(Class<? extends ItemView> layoutClass) {
        ItemView v = getItemView(layoutClass);
        if (v != null && headerViews.indexOf(v) == -1) {
            headerViews.add(v);
            int hash = (HEADER + headerViews.indexOf(v)).hashCode();
            v.setTag(R.id.tag, hash);
            headerHashes.add(hash);
            notifyItemInserted(headerViews.size() - 1);
        }
        return this;
    }

    @Override
    public int getHeaderViewSize() {
        return headerSize;
    }

    @Override
    public void removeAllHeaderView() {
        int size = headerViews.size();
        if (size > 0) {
            headerViews.clear();
            headerHashes.clear();
            notifyItemRangeRemoved(0, size);
        }
    }


    @Override
    public BindAdapter addLayout(Class<? extends ItemView> layoutClass) {
        this.layoutClass = layoutClass;
        return this;
    }


    @Override
    public BindAdapter addFooterView(Class<? extends ItemView> layoutClass) {
        ItemView v = getItemView(layoutClass);
        if (v != null && footerViews.indexOf(v) == -1) {
            footerViews.add(v);
            int hash = (FOOTER + footerViews.indexOf(v)).hashCode();
            v.setTag(R.id.tag, hash);
            footerHashes.add(hash);
            notifyItemInserted(headerSize + itemCount + footerViews.size() - 1);
        }
        return this;
    }

    @Override
    public int getFooterViewSize() {
        return footerSize;
    }

    @Override
    public void removeAllFooterView() {
        footerViews.clear();
        footerHashes.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        headerSize = headerViews.size();
        itemCount = innerAdapter != null ? innerAdapter.getItemCount() : items.size();
        footerSize = footerViews.size();
        return headerSize + itemCount + footerSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headerSize) {    // header 일 경우
            return headerHashes.size() > position ? headerHashes.get(position) : 0;
        } else if (position >= headerSize + itemCount) { // footer 일 경우
            position -= (headerSize + itemCount);
            return footerHashes.size() > position ? footerHashes.get(position) : 0;
        } else {    // item 일 경우
            position -= headerSize;
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

    protected void setViewHolder(RecyclerView.ViewHolder viewHolder){

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
