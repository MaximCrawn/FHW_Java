# Toy Raffle Application

Добро пожаловать в Toy Raffle Application! Этот проект представляет собой консольное приложение, позволяющее управлять коллекцией игрушек, проводить розыгрыши призов, а также получать призы. Основные операции, которые вы можете выполнять в этом приложении:

- **Добавление Игрушек**: Добавьте новую игрушку, указав её ID, название, количество и вес.
- **Изменение Вес Игрушки**: Обновите вес игрушки по её ID.
- **Розыгрыш Приза**: Проведите розыгрыш среди доступных игрушек и добавьте призы в список выигранных.
- **Получение Приза**: Посмотрите список выигранных призов и очистите его после получения.

## Классы

### Toy
Представляет игрушку с атрибутами:
- `id`: Уникальный идентификатор.
- `name`: Название игрушки.
- `quantity`: Количество.
- `weight`: Вес.

### ToyFileWriter
Обеспечивает взаимодействие с файлами `toys.txt` и `prizes.txt`, включая чтение и запись игрушек и призов. Основные функции:
- Запись одной или нескольких игрушек в файл.
- Чтение игрушек из файла.
- Обновление веса игрушки по ID.
- Обновление файла с игрушками или призами.

### ToyStore
Представляет магазин с коллекцией игрушек и призов. Основные функции:
- Уменьшение количества игрушки по ID.
- Проведение розыгрыша призов.
- Получение списка игрушек и призов.

### ToyRaffleApp
Главный класс приложения, обеспечивающий взаимодействие с пользователем через консоль. Основные возможности:
- Добавление новой игрушки.
- Изменение веса игрушки.
- Проведение розыгрыша призов.
- Получение призов.
- Выход из приложения.

## Как использовать

1. **Запуск Приложения**: Запустите класс `ToyRaffleApp`.
2. **Выберите Операцию**: Следуйте инструкциям в консоли, чтобы выбрать желаемую операцию.
3. **Добавление Игрушки**: Введите необходимые данные для добавления новой игрушки.
4. **Розыгрыш Приза**: Проведите розыгрыш и посмотрите, какой приз вы выиграли.
5. **Получение Призов**: Просмотрите список призов и очистите его после получения.

## Зависимости
- Java 8 или выше
- Файлы `toys.txt` и `prizes.txt` для хранения данных.
