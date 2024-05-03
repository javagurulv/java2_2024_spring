package lv.javaguru.travel.insurance.dto.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BigDecimalSerializerTest {

    @Mock
    private JsonGenerator genMock;
    @Mock
    private SerializerProvider serializersMock;

    @InjectMocks
    private BigDecimalSerializer serializer;

    @Test
    void serialize_ShouldSerializeBigDecimalCorrectly() throws IOException {
        BigDecimal testValue = new BigDecimal("123.456");

        serializer.serialize(testValue, genMock, serializersMock);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(genMock).writeNumber(argumentCaptor.capture());

        String capturedValue = argumentCaptor.getValue();
        assertEquals("123.46", capturedValue);
    }

    @Test
    void serialize_ShouldFailForInvalidBigDecimalValue() throws IOException {
        BigDecimal invalidValue = null;

        Throwable exception = assertThrows(NullPointerException.class, () -> {
            serializer.serialize(invalidValue, genMock, serializersMock);
        });

        assertNotNull(exception);
    }

}