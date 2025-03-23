package org.global.dax.shared;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SerializationUtilTests {

    @Test
    void testGetSingleSerialization() {

        CacheCommandWithArguments instance = new CacheCommandWithArguments(CacheCommand.GET_SINGLE, List.of("one"));
        byte [] serialized = SerializationUtil.serialize(instance);

        CacheCommandWithArguments deserialized = SerializationUtil.deserialize(serialized);

        Assertions.assertThat(instance).isEqualTo(deserialized);
    }

    @Test
    void testGetAllSerialization() {

        CacheCommandWithArguments instance = new CacheCommandWithArguments(CacheCommand.GET_ALL, List.of());
        byte [] serialized = SerializationUtil.serialize(instance);

        CacheCommandWithArguments deserialized = SerializationUtil.deserialize(serialized);

        Assertions.assertThat(instance).isEqualTo(deserialized);
    }

    @Test
    void testAddSingleSerialization() {

        CacheCommandWithArguments instance = new CacheCommandWithArguments(CacheCommand.ADD_SINGLE,
                List.of("key", "value"));
        byte [] serialized = SerializationUtil.serialize(instance);

        CacheCommandWithArguments deserialized = SerializationUtil.deserialize(serialized);

        Assertions.assertThat(instance).isEqualTo(deserialized);
    }

    @Test
    void testDeleteSingleSerialization() {

        CacheCommandWithArguments instance = new CacheCommandWithArguments(CacheCommand.DELETE_SINGLE,
                List.of("one"));
        byte [] serialized = SerializationUtil.serialize(instance);

        CacheCommandWithArguments deserialized = SerializationUtil.deserialize(serialized);

        Assertions.assertThat(instance).isEqualTo(deserialized);
    }

}
