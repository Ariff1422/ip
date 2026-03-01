package vox;

/**
 * Represents errors specific to the Vox application,
 * such as invalid commands or missing task arguments.
 */
public class VoxException extends Exception {

    /**
     * Constructs a VoxException with the given error message.
     *
     * @param message a description of the error
     */
    public VoxException(String message) {
        super(message);
    }
}
