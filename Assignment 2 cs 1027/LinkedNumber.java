/**
 * 
 * This class is used to represent a positive whole number of any number system (base 2-16) 
 * stored in a doubly linked list. Each digit of the number is stored in a separate 
 * node
 * 
 * @author Eliandro Pizzonia 
 * 
 */
public class LinkedNumber {
    
    // base of the number
    private int base;
    // front of the double linked list
    private DLNode<Digit> front;
    // rear of the double linked list
    private DLNode<Digit> rear;

    /**
     * Constructor to create a LinkedNumber object with a specific base in which the digits are stored in a 
     * double linked list
     * 
     * @param num
     * @param baseNum
     */
    public LinkedNumber(String num, int baseNum){


        this.base = baseNum;
        
        // if the string contains no digits, an exception is thrown
        if (num.isEmpty()) {
            throw new LinkedNumberException("no digits given");
        }

        // converting the number string into an array of characters
        char [] num_arry = num.toCharArray();
        DLNode<Digit> prevNode = null;

        // creating a digit node for each digit in the character array
        for(char i : num_arry){
            Digit digit = new Digit(i);
            DLNode<Digit> curNode = new DLNode<>(digit);
            
            // if the first digit, it is set to the front of the linked number
            if(prevNode == null){
                front = curNode; 
            }      
            
            // else, the previous digit is linked to the current one 
            else{
                prevNode.setNext(curNode);
                curNode.setPrev(prevNode);
            }
            
            rear = curNode;
            prevNode = curNode;

        }

    }
       
    /**
     * Constructor to create LinkedNumber object with a specific base of 10 in which the digits are stored in a 
     * double linked list
     * @param num
     */
    public LinkedNumber(int num){

        base = 10;

        // converting the integer into a string and then a array of characters
        String str_num = String.valueOf(num);
        char [] str_num_arry = str_num.toCharArray();
        DLNode<Digit> prevNode = null;
        
        // converting the number string into an array of characters
        for(char i : str_num_arry){
            Digit digit = new Digit(i);
            DLNode<Digit> curNode = new DLNode<>(digit);
            

            // if the first digit, it is set to the front of the linked number
            if(prevNode == null){
                front = curNode; 
            }      
            
            // else, the previous digit is linked to the current one 
            else{
                prevNode.setNext(curNode);
                curNode.setPrev(prevNode);
            }
            
            rear = curNode;
            prevNode = curNode;

        }

    }
        
    /**
     * Determine whether or not the number stored in this linked list is a valid positive 
     * number for the base number system specified by instance variable base
     * 
     * @return Return true if the number is valid or false otherwise.
     */
    public boolean isValidNumber(){   
        
        DLNode<Digit> curNode = front;
        
        //looping through the double linked list
        while (curNode.getNext() != null) {
           Digit value = curNode.getElement();
           int int_value = value.getValue();
            
           // checking if the value is positive and one of the possible digits of the specific base number system
           if(int_value < 0 || int_value >= base){
                return false;
           }
        curNode = curNode.getNext();
        }      

        return true;
    }   

    /**
     * @return the base
     */
    public int getBase(){
        return base;
    }

    /**
     * @return the front node of the double linked list
     */
    public DLNode<Digit> getFront(){
        return front;
    }

    /**
     * @return the last node of the double linked list
     */
    public DLNode<Digit> getRear(){
        return rear;
    }

    /**
     * @return the number of digits (the number of nodes) in the number
     */
    public int getNumDigits(){
        
        DLNode<Digit> curNode = front;
        
        // starting from the front of the double linked list and counting the number of nodes
        int node_count = 0;
        while (curNode != null) {
            node_count += 1;
            curNode = curNode.getNext();
        }

        return node_count;
    }

    /**
     * Creates and returns a String containing all the digits of the number with no spaces 
     * or other characters.
     * 
     */
    public String toString(){

        DLNode<Digit> curNode = front;
        String str_curNode = "";
        while (curNode != null) {
            Digit digit = curNode.getElement();
            str_curNode += digit.toString();
            curNode = curNode.getNext();
        }
        return str_curNode;
    }

    /**
     * Compares two LinkedNumber objects to see if they are considered 
     * equivalent
     * 
     * @param other
     * @return true if both objects are equal and the values stored in the nodes in both objects are 
     * in the same order.
     */
    public boolean equals(LinkedNumber other){
    
    // checking if the basses are different
    if(this.base != other.base){
        return false;
    }

    // starting from the front of the double linked lists
    DLNode<Digit> this_curNode = this.front;
    DLNode<Digit> other_curNode = other.front;

    // checking if both digits are the same and in the same order
    while(this_curNode != null && other_curNode != null){
        Digit this_digit = this_curNode.getElement();
        int val_this_digit = this_digit.getValue();

        Digit other_digit = other_curNode.getElement();
        int val_other_digit = other_digit.getValue();
         
        if(val_this_digit != val_other_digit){
            return false;
        }

        this_curNode =  this_curNode.getNext();
        other_curNode = other_curNode.getNext();

      
    }

    // checking if the double linked lists have the same amount of digits
    if (this.getNumDigits() != other.getNumDigits()) {
        return false;
    }

    return true;
}
    
    /**
     * converting a number from decimal to non decimal, non decimal to decimal or non decimal to non decimal
     * 
     * @param newBase
     * @return a converted Linkednumber to a differnce base
     */
    public LinkedNumber convert (int newBase){

        // throwing an exception if not a valid number
        if(this.isValidNumber() != true) {
            throw new LinkedNumberException("cannot convert invalid number");
        }        
        
        // starting from the rear of the douvle linked list 
        DLNode<Digit> curNode = rear;
        int val = 0;
        int position_counter = 0;

        // decimal to non decimal conversion
        if (base == 10 && newBase != 10 ) {
            
        //  getting the value of each node
        while(curNode != null){
            Digit digit = curNode.getElement();
            int int_value = digit.getValue();

            // converting the value to the new base
            val += int_value * Math.pow(base, position_counter);
            position_counter += 1;
            curNode = curNode.getPrev();
        }
            /*  converting the value to a string in the new base (The remainder from each 
            division gives us the value of the next (going from right to left)
            */
            String digit_string = "";
            while(val != 0){
            int remainder = val % newBase;
            val /= newBase;
            String str_remainder = String.valueOf(remainder);
            digit_string = str_remainder + digit_string;
                 
        }
        // Initializing a new LinkedNumber object with the completed String and the newBase as its parameters
        LinkedNumber converted_num = new LinkedNumber(digit_string, newBase);
        return converted_num;
    }
        // non decimal to decimal conversion 
        else if (base != 10 && newBase == 10 ){
        
        // starting from the rear of the linked list 
        DLNode<Digit> curNode2 = rear;
        int val2 = 0;
        int position_counter2 = 0;
            
        /* converting the value to a string in the new base (For each node, the value is multiplied by the base 
        raised to the exponent)
        */
        while(curNode2 != null){
            Digit digit = curNode2.getElement();
            int int_value = digit.getValue();

            val2 += int_value * Math.pow(base, position_counter2);
            position_counter2 += 1;
            curNode2 = curNode2.getPrev();
        }
        
       
        // Initializing a new LinkedNumber object with variable val and the number 10 as its parameters
        LinkedNumber converted_Number = new LinkedNumber(String.valueOf(val2), newBase);
        return converted_Number;

    }

    // non decimal to non decimal conversion
    else{

        // converting to base 10 first
        DLNode<Digit> curNode2 = rear;
        int val2 = 0;
        int position_counter2 = 0;
    
        while(curNode2 != null){
            Digit digit = curNode2.getElement();
            int int_value = digit.getValue();

            val2 += int_value * Math.pow(base, position_counter2);
            position_counter2 += 1;
            curNode2 = curNode2.getPrev();
        }
        
        LinkedNumber temp = new LinkedNumber(String.valueOf(val), 10);
        
        // converting the base 10 number to the new base
        String digit_string = "";
        while(val2 != 0){
            int remainder = val2 % newBase;
            String.valueOf(remainder);
            
            val2 /= newBase;
            char char_digit = Character.forDigit(remainder, newBase);
            char_digit = Character.toUpperCase(char_digit);
            Digit digit = new Digit(char_digit);
            
            digit.getValue();
            digit_string = digit + digit_string;    

        }

        // Initializing a new LinkedNumber object in the new base
        LinkedNumber converted_num = new LinkedNumber(digit_string, newBase);
        return converted_num; 

    }
}   
    
    /**
     * adding a digit to the double linked list at a specific position
     * 
     * @param digit
     * @param position
     */
    public void addDigit (Digit digit, int position){
        
        // staring from the rear of the double linked list
        DLNode<Digit> curNode = rear;

        // counting the number of digits
        int node_count = 0;
        while (curNode != null) {
            node_count += 1;
            curNode = curNode.getPrev();
        }
        
        // creating a new node for the position if the position is valid
        if(position >= 0 && position <= node_count){

            DLNode<Digit> newNode = new DLNode<>(digit);

            // adding the new node to the end of the double linked list if the position is 0
            if(position == 0){
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode;
            }
            
            /*  adding the new node to the beggining of the double linked list if the position is the same as the number of
            nodes
            */
            else if(position == node_count){
                front.setPrev(newNode);
                newNode.setNext(front);
                front = newNode;
            }
            
            // if the position is in the middle of the double linked list
            else{
    
            DLNode<Digit> current = rear;
            
            // getting the node at the position 
            for(int i = 0; i < position; i++){
                current = current.getPrev();
            }
            
            // inserting the node at the position 
            DLNode<Digit> nextNode = current.getNext();
            newNode.setPrev(current);
            newNode.setNext(nextNode);
            current.setNext(newNode);

            // if there is a node after the new node, update its previous pointer 
            if (nextNode != null) {
                nextNode.setPrev(newNode);
            }

        }
        
    }
        // throwing exception if the position is not valid
        else{
            throw new LinkedNumberException("invalid position");
        }
        
    }
    

    /**
     * removing a digit from the double linked list at a specific position
     * 
     * @param position
     * @return the decimal equivalent of the value regardless of the number system.
     */ 
    public int removeDigit(int position){
        
        // starting from the rear of the linked list
        DLNode<Digit> curNode = rear;

        // counting the number of digits
        int node_count = 0;
        while (curNode != null) {
            node_count += 1;
            curNode = curNode.getPrev();
        }
        
        // throwing an exception if the position is not valid
        if(position < 0 || position > node_count){
            throw new LinkedNumberException("invalid position");
        }

        // moving to the position of the double linked list
        DLNode<Digit> current = rear;
        for(int i = 0; i < position; i++){
            current = current.getPrev();
        }
            // removing the digit at the end of the double linked list if the position is at the end of the linked number
            if(current == rear){
                rear = rear.getPrev();
                if (rear.getNext() != null) {
                    rear.setNext(null);
                }
            
            }

            // removing the digit at the start of the double linked list if the position is at the start of the linked number
            else if(current == front){
                front = front.getNext();
                if (front.getPrev() !=  null){
                    front.setPrev(null);
                }

            }
            
            // removing the digit at the middle of the double linked list if the position is at the middle of the linked number
            else{
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            
            }

            // returing the decimal equivilant of the removed digit
            int value = current.getElement().getValue()* (int)Math.pow(base, position);
            return value;
    
        }   
    }
    
      






