package org.global.dax.server;

import org.global.dax.shared.CacheCommandWithArguments;

import java.util.Set;

final class CacheCommandInvoker {

    String output(CacheCommandWithArguments cacheCommandWithArguments) {
        return switch (cacheCommandWithArguments.command()) {

            case GET_SINGLE -> {
                String getSingleKey = cacheCommandWithArguments.arguments().get(0);
                String result = Cache.getSingle(getSingleKey);
                if (result == null) {
                    yield "key : " + getSingleKey + " not found";
                }
                yield result;
            }

            case GET_ALL -> {
                Set<String> allKeys = Cache.getAll();
                if (allKeys.isEmpty()) {
                    yield "no keys found";
                }
                yield allKeys.toString();
            }

            case ADD_SINGLE -> {
                String key = cacheCommandWithArguments.arguments().get(0);
                String value = cacheCommandWithArguments.arguments().get(1);
                Cache.addSingle(key, value);
                yield "key : " + key + " mapped ta " + value;
            }

            case DELETE_SINGLE -> {
                String key = cacheCommandWithArguments.arguments().get(0);
                String result = Cache.deleteSingle(key);
                if (result == null) {
                    yield "key : " + key + " not found";
                }
                yield "key : " + key + " deleted";
            }

            case HEARTBEAT -> null;
        };
    }

}
