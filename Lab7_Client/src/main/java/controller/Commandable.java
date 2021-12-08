package controller;

import java.io.IOException;
import java.io.Serializable;

/**
 * interface for all commands
 * @author Anis
 */
public interface Commandable extends Serializable {
    String execute(Object object) throws IOException;
    String getName();
}