package ru.practicum.shareit.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.exceptionHandler.exception.BookingException;
import ru.practicum.shareit.exceptionHandler.exception.CommentException;
import ru.practicum.shareit.exceptionHandler.exception.DuplicateException;
import ru.practicum.shareit.exceptionHandler.exception.EntityNotFoundException;
import ru.practicum.shareit.item.controller.ItemController;
import ru.practicum.shareit.request.controller.ItemRequestController;
import ru.practicum.shareit.user.controller.UserController;

@Slf4j
@RestControllerAdvice(assignableTypes = {
        ItemController.class,
        UserController.class,
        BookingController.class,
        ItemRequestController.class
})

public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(final EntityNotFoundException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Объект не был найден.", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyExists(final DuplicateException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Объект уже существует", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookingException(final BookingException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Ошибка при бронировании", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCommentException(final CommentException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Ошибка при создании комментария", e.getMessage());
    }
}
