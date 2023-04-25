package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.model.dto.BookingDtoIn;
import ru.practicum.shareit.booking.model.dto.BookingDtoOut;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    public static final String SHARER_USER_ID = "X-Sharer-User-Id";

    @PostMapping
    public BookingDtoOut create(@RequestHeader(SHARER_USER_ID) Long userId,
                                @RequestBody BookingDtoIn bookingDto) {
        return bookingService.create(bookingDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDtoOut update(@RequestParam("approved") Boolean approved,
                                @PathVariable("bookingId") Long bookingId,
                                @RequestHeader(SHARER_USER_ID) Long userId) {
        return bookingService.update(bookingId, userId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDtoOut getById(@PathVariable("bookingId") Long bookingId,
                                 @RequestHeader(SHARER_USER_ID) Long userId) {
        return bookingService.getById(bookingId, userId);
    }

    @GetMapping("/owner")
    public List<BookingDtoOut> getByOwner(@RequestParam(value = "state", defaultValue = "ALL") String state,
                                          @RequestHeader(SHARER_USER_ID) Long userId,
                                          @RequestParam(value = "from", defaultValue = "0")
                                          int from,
                                          @RequestParam(value = "size", defaultValue = "10")
                                          int size) {
        return bookingService.getByOwner(userId, state, from, size);
    }

    @GetMapping
    public List<BookingDtoOut> getByBooker(@RequestParam(value = "state", defaultValue = "ALL") String state,
                                           @RequestHeader(SHARER_USER_ID) Long userId,
                                           @RequestParam(value = "from", defaultValue = "0")
                                           int from,
                                           @RequestParam(value = "size", defaultValue = "10")
                                           int size) {
        return bookingService.getByBooker(userId, state, from, size);
    }

}
