package com.hack.elements.model;

import com.hack.elements.model.impl.Direction;

public interface FreeObject {
  public int getX();
  public void setX(int x);

  public int getY();
  public void setY(int y);

  public int getWidth();
  public void setWidth(int width);

  public int getHeight();
  public void setHeight(int height);

  public boolean usesDirection();
  public Direction getFacing();
  public void setFacing(Direction d);
}
