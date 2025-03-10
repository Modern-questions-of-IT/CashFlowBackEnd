1. Users (Пользователи)
   Описание: Хранит информацию о зарегистрированных пользователях.

Поля:

user_id (PRIMARY KEY): Уникальный идентификатор пользователя. <br />
name: Имя пользователя.<br />
email: Адрес электронной почты (уникальный).<br />
password_hash: Хэш пароля пользователя.<br />
created_at: Дата и время создания учетной записи.<br />
updated_at: Дата и время последнего обновления учетной записи.<br />

2. Categories (Категории расходов)
   Описание: Хранит категории расходов, которые пользователь может создавать, редактировать и удалять.

Поля:

category_id (PRIMARY KEY): Уникальный идентификатор категории.<br />
user_id (FOREIGN KEY): Идентификатор пользователя, которому принадлежит категория.<br />
name: Название категории.<br />
created_at: Дата и время создания категории.<br />
updated_at: Дата и время последнего обновления категории.<br />

3. Transactions (Транзакции)
   Описание: Объединяет как доходы, так и расходы. Позволяет эффективно управлять финансовыми операциями пользователя.

Поля:

transaction_id (PRIMARY KEY): Уникальный идентификатор транзакции.<br />
user_id (FOREIGN KEY): Идентификатор пользователя, которому принадлежит транзакция.<br />
type: Тип транзакции ('income' или 'expense').<br />
category_id (FOREIGN KEY): Идентификатор категории (для расходов).<br />
title: Название или описание транзакции.<br />
amount: Сумма транзакции.<br />
date: Дата совершения транзакции.<br />
is_recurring: Флаг регулярности транзакции.<br />
frequency: Периодичность (например, 'Daily', 'Weekly', 'Monthly', 'Yearly').<br />
next_occurrence: Дата следующего появления (для регулярных транзакций).<br />
created_at: Дата и время создания записи.<br />
updated_at: Дата и время последнего обновления записи.<br />

4. Confirmations (Подтверждения транзакций)
   Описание: Хранит подтверждения регулярных транзакций от пользователя.

Поля:

confirmation_id (PRIMARY KEY): Уникальный идентификатор подтверждения.<br />
user_id (FOREIGN KEY): Идентификатор пользователя.<br />
transaction_id (FOREIGN KEY): Идентификатор регулярной транзакции.<br />
confirmed: Статус подтверждения (TRUE/FALSE).<br />
confirmation_date: Дата и время подтверждения.<br />