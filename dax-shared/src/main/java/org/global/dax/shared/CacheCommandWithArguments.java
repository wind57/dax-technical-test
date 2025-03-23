package org.global.dax.shared;

import java.io.Serializable;
import java.util.List;

/**
 * holds the actual command to be invoked on the cache, with its arguments, if any.
 */
public record CacheCommandWithArguments(CacheCommand command, List<String> arguments)
    implements Serializable {

    @Override
    public String toString() {
        return command.name() + " " + String.join(" ", arguments);
    }
}
