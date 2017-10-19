# BindAdapter

## Usage

Simple Mode

```java
BindAdapter adapter = new BindAdapter(this)
  					.addHeaderView(HeaderView.class)
  					.addLayout(SimpleListItemView.class)
  					.addFooterView(FooterView.class);

recyclerView.setAdapter(adapter);
adapter.addHeaderItem(new User("Header", 0));	// add header data
adapter.addItem(new User("Item", 1));			// add item data
adapter.addFooterItem(new User("Footer", 2))	// add footer data
adapter.notifyData();
```



Custom Mode (HeaderView + GridView)

```java
GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position < adapter.getHeaderViewSize()){
                    return 3;
                }
                return 1;
            }
        });
recyclerView.setLayoutManager(gridLayoutManager);	// set GridLayoutManager

BindAdapter adapter = new BindAdapter(this)
  					.addHeaderView(HeaderView.class);
GridAdapter innerAdapter = new GridAdapter(this);	// inner adapter
recyclerView.setAdapter(adapter);
adapter.setInnerAdapter(innerAdapter);

adapter.addHeaderItem(new User("Header", 0));
innerAdapter.addItem(new User("inner item1", 1));
innerAdapter.addItem(new User("inner item2", 2));
innerAdapter.addItem(new User("inner item3", 3));
adapter.notifyData();
```
