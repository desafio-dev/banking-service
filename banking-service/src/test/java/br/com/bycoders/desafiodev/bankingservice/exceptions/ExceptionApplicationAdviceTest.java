package br.com.bycoders.desafiodev.bankingservice.exceptions;

import br.com.bycoders.desafiodev.bankingservice.domains.records.ErrorResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ExceptionApplicationAdviceTest {

    @InjectMocks
    private ExceptionApplicationAdvice advice;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        ErrorResponse response = advice.errorHandlerBadRequest(exception, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.errorCode());
        assertEquals("Invalid argument", response.message());
    }

    @Test
    public void testHandleFileSizeLimitExceededException() {
        FileSizeLimitExceededException exception = new FileSizeLimitExceededException("File size limit exceeded", 10, 5);
        ErrorResponse response = advice.errorHandlerBadRequest(exception, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.errorCode());
        assertEquals("File size limit exceeded", response.message());
    }

    @Test
    public void testHandleMultipartException() {
        MultipartException exception = new MultipartException("Multipart exception");
        ErrorResponse response = advice.errorHandlerBadRequest(exception, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.errorCode());
        assertEquals("Multipart exception", response.message());
    }

    @Test
    public void testHandleStringIndexOutOfBoundsException() {
        StringIndexOutOfBoundsException exception = new StringIndexOutOfBoundsException("String index out of bounds");
        ErrorResponse response = advice.errorHandlerBadRequest(exception, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.errorCode());
        assertEquals("String index out of bounds", response.message());
    }

    @Test
    public void testHandleNullPointerException() {
        NullPointerException exception = new NullPointerException("Null pointer exception");
        ErrorResponse response = advice.errorHandlerBadRequest(exception, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.errorCode());
        assertEquals("Null pointer exception", response.message());
    }
}