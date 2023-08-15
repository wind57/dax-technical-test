package org.global.dax.client;

import org.global.dax.shared.SharedClassExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClientMain {

    private static final Logger LOG = LoggerFactory.getLogger(ClientMain.class);

    public static void main(String[] args) {

        LOG.info(message() + " " + SharedClassExample.sharedString() + "!");
    }

    public static String message() {

        return "hello, this is the client";
    }
}
