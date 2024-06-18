/** 
 * 
This class represents an exception that relates to an invalid condition or operation while working 
with the LinkedNumber objects.
This class also inhertis from the RuntimeException class
*
*@author Eliandro Pizzonia
*@param msg
*/
class LinkedNumberException extends RuntimeException{
 
    public LinkedNumberException(String msg){
        super(msg);
    }
}
