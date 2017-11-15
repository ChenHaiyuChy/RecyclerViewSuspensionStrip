package com.rss.recyclerviewsuspensionstrip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.main_rv) RecyclerView recyclerView;
  @BindView(R.id.header_layout) View layoutHeader;
  @BindView(R.id.header_tv) TextView tvHeader;
  MyAdapter myAdapter;
  List<JustObject> listData = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    dataInit();
    myAdapter = new MyAdapter();
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(myAdapter);
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        View stickyInfoView =
            recyclerView.findChildViewUnder(layoutHeader.getMeasuredWidth() / 2, 5);

        if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
          tvHeader.setText(
              listData.get(Integer.parseInt(String.valueOf(stickyInfoView.getContentDescription())))
                  .getType() + "");
        }

        View transInfoView = recyclerView.findChildViewUnder(layoutHeader.getMeasuredWidth() / 2,
            layoutHeader.getMeasuredHeight() + 1);

        if (transInfoView != null && transInfoView.getTag() != null) {

          int transViewStatus = (int) transInfoView.getTag();
          int dealtY = transInfoView.getTop() - layoutHeader.getMeasuredHeight();

          if (transViewStatus == MyAdapter.HAS_SS_VIEW) {
            if (transInfoView.getTop() > 0) {
              layoutHeader.setTranslationY(dealtY);
            } else {
              layoutHeader.setTranslationY(0);
            }
          } else if (transViewStatus == MyAdapter.NONE_SS_VIEW) {
            layoutHeader.setTranslationY(0);
          }
        }
      }
    });
  }

  public void dataInit() {
    JustObject justObject = new JustObject(0, "test1");
    listData.add(justObject);
    justObject = new JustObject(0, "test2");
    listData.add(justObject);
    justObject = new JustObject(0, "test3");
    listData.add(justObject);
    justObject = new JustObject(1, "test4");
    listData.add(justObject);
    justObject = new JustObject(1, "test5");
    listData.add(justObject);
    justObject = new JustObject(2, "test6");
    listData.add(justObject);
    justObject = new JustObject(2, "test7");
    listData.add(justObject);
    justObject = new JustObject(3, "test8");
    listData.add(justObject);
    justObject = new JustObject(3, "test9");
    listData.add(justObject);
    justObject = new JustObject(4, "test10");
    listData.add(justObject);
    justObject = new JustObject(4, "test11");
    listData.add(justObject);
    justObject = new JustObject(5, "test12");
    listData.add(justObject);
    justObject = new JustObject(5, "test13");
    listData.add(justObject);
    justObject = new JustObject(6, "test14");
    listData.add(justObject);
    justObject = new JustObject(6, "test15");
    listData.add(justObject);
  }

  final class MyAdapter extends RecyclerView.Adapter {

    public static final int FIRST_SS_VIEW = 1;
    public static final int HAS_SS_VIEW = 2;
    public static final int NONE_SS_VIEW = 3;

    private int position;
    private final LayoutInflater inflater;

    MyAdapter() {
      this(LayoutInflater.from(MainActivity.this));
    }

    MyAdapter(LayoutInflater inflater) {
      this.inflater = inflater;
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      final JustObject itemData = listData.get(position);
      if (position == 0) {
        holder.itemView.setTag(FIRST_SS_VIEW);
      } else {
        if (itemData.getType() != listData.get(position - 1).getType()) {
          holder.itemView.setTag(HAS_SS_VIEW);
        } else {
          holder.itemView.setTag(NONE_SS_VIEW);
        }
      }
      holder.itemView.setContentDescription(position + "");
      ((NormalViewHolder) holder).setRepayment(itemData, position, holder.itemView.getTag());
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new NormalViewHolder(this, inflater.inflate(R.layout.item_normal, parent, false));
    }

    @Override public int getItemCount() {
      return listData.size();
    }

    public int getPosition() {
      return position;
    }

    public void setPosition(int position) {
      this.position = position;
    }
  }

  final class NormalViewHolder extends RecyclerView.ViewHolder {
    private MyAdapter myAdapter;

    @BindView(R.id.header_layout) View layoutHeader;
    @BindView(R.id.header_tv) TextView itemHeader;
    @BindView(R.id.normal_tv) TextView itemNormal;

    NormalViewHolder(final MyAdapter adapter, final View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.myAdapter = adapter;
    }

    public void setRepayment(JustObject itemData, int position, Object tag) {
      itemHeader.setText(itemData.getType() + "");
      layoutHeader.setVisibility((int) tag == 3 ? View.GONE : View.VISIBLE);
      itemNormal.setText(itemData.getText());
    }
  }
}