# BindAdapter

## Including in your project

You must download the [bind-adapter.aar](https://github.com/dhha22/BindAdapter/raw/master/bind-adapter.aar) and save in your project `app/libs` folder.

and you just need to add the following dependency to your `build.gradle`.
```java
allprojects {
    repositories {
        flatDir {
            dirs 'libs'
        }
    }
}

dependencies {
    compile 'com.dhha22.bindadapter:bind-adapter:0.0.1-beta@aar'
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
        setContentView(R.layout.simple_list_item);
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
adapter.addHeaderItem(new Feed("Header"));	// add header data
adapter.addItem(new Feed("Item"));			// add item data
adapter.addFooterItem(new Feed("Footer"));	// add footer data
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
recyclerView.setLayoutManager(gridLayoutManager);	// set gridLayoutManager

BindAdapter adapter = new BindAdapter(this)
                .addHeaderView(HeaderView.class);

GridAdapter innerAdapter = new GridAdapter(this);	// inner adapter
recyclerView.setAdapter(adapter);
adapter.setInnerAdapter(innerAdapter);

adapter.addHeaderItem(new Feed("Header"));
innerAdapter.addItem(new Feed("inner item1"));
innerAdapter.addItem(new Feed("inner item2"));
innerAdapter.addItem(new Feed("inner item3"));
adapter.notifyData();
```
