/**
 * This class represents an exception where an item code is already taken by another item.
 *
 * @author Charles Walford
 * Solar ID: 116237064
 * Email: charles.walford@stonybrook.edu
 * Assignment number: 6
 * Course: CSE 214
 * Recitation number: 1
 * TAs: Yvette Han, Vincent Zheng
 */
public class ItemCodeTakenException extends Throwable {
    /**
     * This is a Constructor method that is used to create a new FullSceneException object.
     *
     * @param message
     * The message to be shown when the exception is thrown
     */
    public ItemCodeTakenException(String message){
        super(message);
    }
}
