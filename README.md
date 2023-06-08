### Для запуска приложения выполнить команду
```
docker-compose up -d
```
Перейти в браузере по адресу http://localhost:8080/api

Несколько замечаний к проекту:
- запросы к сервису не авторизованы
- структура данных пересоздается автоматически на старте приложения
- поиск учетной записи по полям регистрозависимый
- для добавления новой логики валидации полей достаточно создать имплементацию интерфейса Validatable 