/**
 * This class represents an exception where an item code is already taken by another item.
 *
 * @author Charles Walford
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
