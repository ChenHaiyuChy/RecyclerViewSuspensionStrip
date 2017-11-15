package com.rss.recyclerviewsuspensionstrip;

/**
 * Created by haiyu.chen on 2017/11/15.
 */

public class JustObject {
  private int type;
  private String text;

  public JustObject(int type, String text) {
    this.type = type;
    this.text = text;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
