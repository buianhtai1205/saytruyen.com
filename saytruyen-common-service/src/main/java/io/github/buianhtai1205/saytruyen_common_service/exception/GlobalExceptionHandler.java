package io.github.buianhtai1205.saytruyen_common_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Handle null exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(NullException.class)
    public ResponseEntity<ApiResponse<?>> handleNullException(NullException ex) {
        log.error(ex.getMessage());
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    /**
     * Handle resource not found exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage());
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * Handle duplicate key exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateKeyException(DuplicateKeyException ex) {
        log.error(ex.getMessage());
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Handle business exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage());
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    /**
     * Handle bind exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<?>> handleBindException(BindException ex) {
        log.error(ex.getMessage());
        ApiResponse<?> response = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    /**
//     * Handle bad credentials exception response entity.
//     *
//     * @param ex the ex
//     * @return the response entity
//     */
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ApiResponse<?>> handleBadCredentialsException(BadCredentialsException ex) {
//        ApiResponse<?> response = ApiResponse.builder()
//                .code(HttpStatus.UNAUTHORIZED.value())
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }
//
//    /**
//     * Handle access denied exception response entity.
//     *
//     * @param ex the ex
//     * @return the response entity
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
//        log.error(ex.getMessage());
//        ApiResponse<?> response = ApiResponse.builder()
//                .code(HttpStatus.FORBIDDEN.value())
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//    }
//
//    /**
//     * Handle insufficient authentication exception response entity.
//     *
//     * @param ex the ex
//     * @return the response entity
//     */
//    @ExceptionHandler(InsufficientAuthenticationException.class)
//    public ResponseEntity<ApiResponse<?>> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
//        log.error(ex.getMessage());
//        ApiResponse<?> response = ApiResponse.builder()
//                .code(HttpStatus.FORBIDDEN.value())
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//    }
//
//    /**
//     * Handle signature exception response entity.
//     *
//     * @param ex the ex
//     * @return the response entity
//     */
//    @ExceptionHandler(SignatureException.class)
//    public ResponseEntity<ApiResponse<?>> handleSignatureException(SignatureException ex) {
//        log.error(ex.getMessage());
//        ApiResponse<?> response = ApiResponse.builder()
//                .code(HttpStatus.FORBIDDEN.value())
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//    }
//
//    /**
//     * Handle malformed jwt exception response entity.
//     *
//     * @param ex the ex
//     * @return the response entity
//     */
//    @ExceptionHandler(MalformedJwtException.class)
//    public ResponseEntity<ApiResponse<?>> handleMalformedJwtException(MalformedJwtException ex) {
//        log.error(ex.getMessage());
//        ApiResponse<?> response = ApiResponse.builder()
//                .code(HttpStatus.FORBIDDEN.value())
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//    }
//
//    /**
//     * Handle expired jwt exception response entity.
//     *
//     * @param ex the ex
//     * @return the response entity
//     */
//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ApiResponse<?>> handleExpiredJwtException(ExpiredJwtException ex) {
//        log.error(ex.getMessage());
//        ApiResponse<?> response = ApiResponse.builder()
//                .code(HttpStatus.FORBIDDEN.value())
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//    }

    /**
     * Handle exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        log.error(ex.getMessage());
        ApiResponse<?> response = ApiResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}