package org.global.dax.server;

import org.global.dax.shared.SharedClassExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerMain {

    private static final Logger LOG = LoggerFactory.getLogger(ServerMain.class);

    public static void main(String[] args) {

        LOG.info(message() + " " + SharedClassExample.sharedString() + "!");
    }

    public static String message() {

        return "hello, this is the server";
    }
}
