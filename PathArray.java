//package graphics;
import java.util.LinkedList;

public class PathArray{
  //Fields
  LinkedList<Integer> list;
  int[] array;
  int initPos;

  int curLength = 50;
  //Constructor
  public PathArray(int pos){
    initPos = pos;

    list = new LinkedList<Integer>();
    array = new int[curLength];
    //Initialize
    for(int i = 0; i < curLength; i++){
      list.add(initPos);
    }
  }

  //Getter
  public int[] getPath(){return array;}

  public int getLength(){return curLength;}
  //Methods
  public void append(int newInt){
    list.removeFirst();
    list.addLast(newInt);

    //Update path
    for(int i = 0; i < curLength; i++){
      array[i] = list.get(i);
    }
  }

  //Methods
  public void reset(){
      for(int i = 0; i < curLength; i++){
        list.set(i, initPos);
      }
  }

}
