package com.be.common.dto;

import com.be.common.code.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ExceptionResDto {

    private String responseCode;
    private String responseMessage;

    public static ResponseEntity<ExceptionResDto> exceptionResponse(final ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ExceptionResDto.builder()
                        .responseCode(errorCode.name())
                        .responseMessage(errorCode.getMessage())
                        .build());
    }
}
