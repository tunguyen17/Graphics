package graphics;

public class PathArray{
  //Fields
  int[] array;
  int i = 0;
  int curLength = 1000;
  //Constructor
  public PathArray(){
    array = new int[curLength];
  }

  //Getter
  public int[] getPath(){return array;}
  public int getCurI(){return i;}
  //Methods
  public void append(int newInt){

    //Duplicating array
    if(i >= curLength){
      curLength *=2;
      int[] newArray = new int[curLength];
      for(int j = 0; j < curLength/2; j++){
        newArray[j] = array[j];
      }
      array = newArray;
    }
    //add new int
    array[i] = newInt;
    i++;
  }

}
