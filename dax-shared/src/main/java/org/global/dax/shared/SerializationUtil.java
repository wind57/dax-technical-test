package org.global.dax.shared;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class SerializationUtil {

    private SerializationUtil() {

    }

    public static byte[] serialize(CacheCommandWithArguments instance) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(instance);
            out.flush();
            return bos.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static CacheCommandWithArguments deserialize(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        try (ObjectInput in = new ObjectInputStream(bis)) {
            return (CacheCommandWithArguments) in.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
