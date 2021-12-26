В БД создать пару sql табличек со связями (foreign keys)

Сделать HTTP POST эндпоинт который получает данные в json вида :

> {
>    name: "имя отправителя"
>    password: "пароль" 
> }
этот эндпоинт проверяет пароль по БД и создает jwt токен (срок действия токена и алгоритм подписи не принципиален, для генерации и работе с токеном можно использовать готовую библиотечку) в токен записывает данные: name: "имя отправителя" 
и отправляет токен в ответ, тоже json вида:
{
    token: "тут сгенерированный токен" 
}

Сервер слушает и отвечает в какой-нибудь эндпоинт в него на вход поступают данные в формате json:
Сообщения клиента-пользователя:
{
    name:       "имя отправителя",
    message:    "текст сообщение"
}
В заголовках указан Bearer токен (полученный из эндпоинта выше)
Проверить токен, в случае успешной проверки токена, полученное сообщение сохранить в БД.

Если пришло сообщение вида:
{
    name:       "имя отправителя",
    message:    "history 10"
}
проверить токен, в случае успешной проверки токена отправить отправителю 10 последних сообщений из БД

Добавить описание и инструкцию по запуску и комментарии в коде, если изменяете формат сообщений, то подробное описание ендпоинтов и их полей.

Завернуть все компоненты в докер, покрыть код тестами
