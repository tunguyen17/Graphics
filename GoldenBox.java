public class GoldenBox{
  //Fields
  public int xPos;
  public int yPos;

  public int width;
  public int height;

  //Constructor
  public GoldenBox(int newX, int newY, int newWidth, int newHeight){
    xPos = newX;
    yPos = newY;

    width = newWidth;
    height = newHeight;
  }

  public int[] getBox(){
    return new int[]{xPos, yPos, width, height};
  }
}
