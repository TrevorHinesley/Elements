package com.hack.element.model.impl

import com.hack.element.model.Character

public class Hero implements Character {
  
  int x;
  int y;
  int width;
  int height;

  @Override
  public int getX(){
    return x;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public int getY() {
    return y;
  }
 
  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }
}
