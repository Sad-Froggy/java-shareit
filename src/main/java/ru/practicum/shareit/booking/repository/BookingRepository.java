package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE i.owner.id = ?1")
    List<Booking> getByOwnerId(Long ownerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE i.owner.id = ?1 " +
            "AND ?2 BETWEEN b.start AND b.end")
    List<Booking> getByOwnerIdCurrentState(Long ownerId, LocalDateTime time, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE i.owner.id = ?1 " +
            "AND (b.status = 'APPROVED' " +
            "OR b.status = 'WAITING')")
    List<Booking> getByOwnerIdApprovedFutureState(Long ownerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE i.owner.id = ?1 " +
            "AND b.status = 'APPROVED' " +
            "AND b.end < ?2")
    List<Booking> getByOwnerIdApprovedPastState(Long ownerId, LocalDateTime time, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE i.owner.id = ?1 " +
            "AND b.status = 'WAITING'")
    List<Booking> getByOwnerIdWaitingState(Long ownerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE i.owner.id = ?1 " +
            "AND (b.status = 'REJECTED' " +
            "OR b.status = 'CANCELED')")
    List<Booking> getByOwnerIdRejectedState(Long ownerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.booker.id = ?1")
    List<Booking> getByBookerId(Long bookerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.booker.id = ?1 " +
            "AND ?2 BETWEEN b.start AND b.end")
    List<Booking> getAllByBookerCurrenState(Long bookerId, LocalDateTime time, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "JOIN Item i ON i.id = b.item.id " +
            "WHERE b.booker.id = ?1 " +
            "AND (b.status = 'APPROVED' " +
            "OR b.status = 'WAITING')")
    List<Booking> getByBookerIdFutureState(Long bookerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.booker.id = ?1 " +
            "AND b.status = 'APPROVED' " +
            "AND b.end < ?2")
    List<Booking> getByBookerIdPastState(Long bookerId, LocalDateTime time, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.booker.id = ?1 " +
            "AND b.status = 'WAITING'")
    List<Booking> getByBookerIdWaitingState(Long bookerId, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.booker.id = ?1 " +
            "AND (b.status = 'REJECTED' " +
            "OR b.status = 'CANCELED')")
    List<Booking> getByBookerIdRejectedState(Long bookerId, Sort sort);

    @Query("SELECT b FROM Booking b WHERE b.status = 'APPROVED' " +
            "AND b.item IN ?1 ")
    List<Booking> findApprovedForItems(List<Item> items, Sort sort);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.item.id = :itemId AND b.item.owner.id = :userId AND b.status = 'APPROVED' " +
            "AND b.end < :now " +
            "ORDER BY b.start DESC")
    Booking findLastBooking(LocalDateTime now, Long userId, Long itemId);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.item.id = :itemId AND b.item.owner.id = :userId AND b.status = 'APPROVED' " +
            "AND b.start > :now " +
            "ORDER BY b.start")
    Booking findNextBooking(LocalDateTime now, Long userId, Long itemId);

}
