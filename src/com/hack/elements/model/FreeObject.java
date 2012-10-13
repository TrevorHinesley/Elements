package com.hack.elements.model;

public interface FreeObject implements Direction {
  public int getX();
  public void setX(int x);

  public int getY();
  public void setY(int y);

  public int getWidth();
  public void setWidth(int width);

  public int getHeight();
  public void setHeight(int height);

  public Direction getDirection();
  public void setDirection(Direction d);
}
