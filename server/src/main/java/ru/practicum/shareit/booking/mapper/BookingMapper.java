package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.dto.BookingDtoIn;
import ru.practicum.shareit.booking.model.dto.BookingDtoOut;

public class BookingMapper {

    public static BookingDtoOut toBookingDtoOut(Booking booking) {
        return new BookingDtoOut(
                booking.getId(),
                booking.getStart(),
                booking.getEnd(),
                new BookingDtoOut.Item(
                        booking.getItem().getId(),
                        booking.getItem().getName()),
                new BookingDtoOut.Booker(
                        booking.getBooker().getId(),
                        booking.getBooker().getName()),
                booking.getStatus()
        );
    }

    public static Booking toBooking(BookingDtoIn bookingDtoIn) {
        return new Booking(
                bookingDtoIn.getStart(),
                bookingDtoIn.getEnd()
        );
    }
}
