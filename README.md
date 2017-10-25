# BindAdapter

## Including in your project

The library is pushed to Maven Central as a AAR, so you just need to add the following dependency to your `build.gradle`.
```java
dependencies {
    compile 'com.dhha22.bindadapter:bind-adapter:0.1.1-alpha@aar'
}

```

## Usage

### Step 1
Implement the Item class in your model class
```java
public class Feed implements Item {
    public String name;

    public Feed(String name) {
        this.name = name;
    }
}
```

### Step 2
Extend ItemView to the view and override the `setData(Item data)` function
```java
public class SimpleListItemView extends ItemView {
    private TextView nameTxt;

    public SimpleListItemView(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.simple_list_item, this, true);
        nameTxt = findViewById(R.id.nameTxt);
    }

    @Override
    public void setData(Item data) {
        super.setData(data);
        if(data instanceof Feed) {
            nameTxt.setText(((Feed)data).name);
        }
    }
}
```

### Step 3
And use it

#### Simple Mode
```java
BindAdapter adapter = new BindAdapter(this)
                .addHeaderView(HeaderView.class)
                .addLayout(SimpleListItemView.class)
                .addFooterView(FooterView.class);

recyclerView.setAdapter(adapter);
adapter.addHeaderItem(new User("Header", 0));	// add header data
adapter.addItem(new User("Item", 1));			// add item data
adapter.addFooterItem(new User("Footer", 2));	// add footer data
adapter.notifyData();
```



#### Custom Mode (HeaderView + GridView)

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
