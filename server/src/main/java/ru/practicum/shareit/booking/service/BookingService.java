package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.model.dto.BookingDtoIn;
import ru.practicum.shareit.booking.model.dto.BookingDtoOut;

import java.util.List;

public interface BookingService {

    BookingDtoOut create(BookingDtoIn bookingDto, Long userId);

    BookingDtoOut update(Long bookingId, Long userId, Boolean approved);

    BookingDtoOut getById(Long bookingId, Long userId);

    List<BookingDtoOut> getByBooker(Long bookerId, String state, int from, int size);

    List<BookingDtoOut> getByOwner(Long ownerId, String state, int from, int size);

}
