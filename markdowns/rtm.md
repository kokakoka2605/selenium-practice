| ID     | Требование                                          |
|--------|-----------------------------------------------------|
| REQ-01 | Пользователь входит по логину и паролю              |
| REQ-02 | Пользователь добавляет товары в корзину             |
| REQ-03 | В каталоге у каждого товара своя картинка           |
| REQ-04 | Пользователь может удалять товары из корзины        |
| REQ-05 | Пользователь сортирует товары в каталоге            |
| REQ-06 | Пользователь оформляет заказ                        |
| REQ-07 | Выход из системы, каталог недоступен без входа      |

# RTM: saucedemo.com
| Требование                  | Тест-кейсы      | Автотесты                                                                              | Баги    | Статус                    |
|-----------------------------|-----------------|----------------------------------------------------------------------------------------|---------|---------------------------|
| REQ-01 Вход по логину       | TC-001...TC-005 | LoginTest (5 тестов + параметризованный на 4 пользователей)                            | -       | Покрыто                   |
| REQ-02 Добавление в корзину | TC-006          | CartTest.testAddSixItemsToCart, testCartBadgeCounter, testCartContainsAddedItems       | -       | Покрыто                   |
| REQ-03 Уникальные картинки  | TC-007          | ProductsTest.testProductImagesAreUniqueForProblemUser                                  | BUG-001 | Тест падает, баг открыт   |
| REQ-04 Удаление из корзины  | TC-008, TC-009  | CartTest.testRemoveItemFromInventory, testRemoveItemFromCartPage                       | -       | Покрыто                   |
| REQ-05 Сортировка           | TC-010...TC-013 | ProductsTest: testSortByNameAscending/Descending, testSortByPriceLowToHigh/HighToLow   | -       | Покрыто                   |
| REQ-06 Оформление заказа    | TC-014...TC-018 | CheckoutTest (5 тестов)                                                                | -       | Покрыто                   |
| REQ-07 Выход / доступ       | TC-019, TC-020  | LoginTest.testLogout, testInventoryNotAvailableWithoutLogin                            | -       | Покрыто                   |
