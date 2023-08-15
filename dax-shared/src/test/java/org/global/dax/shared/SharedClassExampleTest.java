package org.global.dax.shared;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SharedClassExampleTest {

    @Test
    void shouldSupportMessageExampleTest() {

        final var result = SharedClassExample.sharedString();
        assertThat(result).isNotBlank();
    }
}
