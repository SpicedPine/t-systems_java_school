# Reha final task

## Текущие задания:
- [x] Создание README файла
- [ ] Настройка CI/CD
- [ ] Рефакторинг модуля domain
- [ ] Добавление sql для инициализации таблиц

## I Часть

### Описание:
В задании требуется написать приложение, моделирующее работу информационной системы автоматизации  документооборота медицинского реабилитационного учреждения.

### Технические требования:

В итоге требуется получить многопользовательское приложение типа клиент-сервер с соединением по сети.
Все данные хранятся на стороне сервера. Каждый клиент может загружать некоторые данные, после каждой операции изменения данные должны быть синхронизованы с сервером.
Клиент должен иметь графический интерфейс.
Приложение должно обрабатывать аппаратные и программные ошибки. 

**Требуемая функциональность:**
* Для врачей клиники:
  - Добавление пациента
  - Выписка пациента (подразумевает завершение всех назначений с момента выписки)
  - Назначение процедур и лекарств
  - Редактирование назначений (изменение периода, дозы, паттерна)
  - Отмена назначений
  
При создании назначения необходимо сгенерировать все предусмотренные им события. Например, в случае с назначением делать физиотерапию 2 раза в неделю по вторникам и четвергам с периодом на 2 недели должно сгенерироваться 4 события.
При отмене назначения, все ещё не выполненные предусмотренные им события должны быть отменены.

* Для медсестёр клиники: 
  - Просмотр всех событий 
  - Фильтрация событий по дате (на сегодня, на ближайший час) и по пациенту 
  - Изменение статуса событий из «запланировано» в «выполнено» и из «запланировано» в «отменено»

  
При переводе события в статус «отменено» медсестре необходимо указать причину отмены: пациент плохо себя чувствовал, не было технической возможности выполнить назначение и т.д.

**Используемые технологии:**
*	IDE - Any (Eclipse, IDEA)
*	Tomcat 
*	DB – MySQL 
*	Maven 
*	JPA 
*	Spring Framework (кроме Boot, Data)
*	JSP

## II Часть

### Описание:
В задании требуется реализовать отдельное клиент-приложение для электронного табло.

### Технические требования:

Приложение должно отображать список всех событий, назначенных на текущий день. Данные должны подгружаться при старте и храниться на стороне клиента. 
Перезагрузка данных осуществляется в случае получения уведомления от сервера об изменениях в списке событий (добавлены новые или отменены старые).

**Используемые технологии:**
*	Maven
*	AS – WildFly 
*	EJB 
*	JSF 
*	MQ (для уведомлений от сервера 
*	WebServices (для обмена данных между клиентом и сервером)

## Критерии успешного выполнения:
1.	[ ] Функциональность работает (обязательно наличие UI)
2.	[ ] Maven-based проект, разбитый на модули (билд одной командой, деплой одной командой)
3.	[ ] Описаны интерфейсы предметной области
4.	[ ] Подключена БД MySQL
5.	[ ] Созданы сущности предметной области; маппинг на таблицы в БД
6.	[ ] Работа с сущностями через DAO	
7.	[ ] Приложение развернуто на AS
8.	[ ] Реализована обработка исключений
9.	[ ] Подключено логгирование
10. [ ] Наличие technical solution description
11. [ ] Наличие unit-тестов на бизнес логику
