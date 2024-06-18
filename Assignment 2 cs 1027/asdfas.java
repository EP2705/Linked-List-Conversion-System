public LinkedNumber(int num){

    base = 10;
   
    String str_num = String.valueOf(num);
    char [] str_num_arry = str_num.toCharArray();
    DLNode<Digit> prevNode = null;

    for(char i : str_num_arry){
        Digit digit = new Digit(i);
        DLNode<Digit> curNode = new DLNode<>(digit);
        
        if(prevNode == null){
            front = curNode; 
        }      
        
        else{
            prevNode.setNext(curNode);
            curNode.setPrev(prevNode);
        }
        
        rear = curNode;

    }
} 