# java-shareit
Проект 4го модуля курса Java разработчик Яндекс.Практикум.

В проекте реализован бекенд сервиса бронирования вещей. Бекенд представляет собой приложение с микросервисной архитектурой. Микросервис gateway создан для первичной валидации данных, которая не требует запросов в БД. Микросервис shareIt-server реализует бизнеслогику и работу с БД (postgreeSql). Реализован запуск через Docker.

В текущий момент реализована функциональность добавления, удаления, изменения пользователей, добавления, удаления, изменения вещей, создания, подтверждения, изменения статусов бронирований и добвления комментариев, добавления запросов на аренду вещи.

API:

Методы для работы с пользователями:

* POST /users - создание
* PATCH /users/{id} - редактирование
* GET /users - получение списка всех пользователей
* GET /users/{userId} - получение информации о пользователе по id
* DELETE /users/{userId} - удаление


Методы для работы с вещами:
* POST /items - создание
* PATCH /items/{id} - редактирование
* GET /items - получение списка всех вещей
* GET /items/{itemId} - получение информации по id
* GET /items/search?text={text} - текстовый поиск по имени и описанию
* POST /items//{itemId}/comment - создание отзыва к вещи

Для работы с бронированиями:
* GET /bookings/{id} - получение по id
* GET /bookings - получение всех бронирований пользователя
* GET /bookings/owner - получение всех бронирований владельца вещи
* POST /bookings - создание
* PATCH /bookings/{id} - изменение

Для работы с запросами на аренду:
* POST /requests - создание
* GET /requests/ - получение всех запросов аренды пользователя
* GET /requests/all - получение всех запросов аренды других пользователей
* GET /requests/{id} - получение по id запроса аренды

Схема базы данных:

<img src="util/dbScheme.png" alt="Alt text" title="Optional title">