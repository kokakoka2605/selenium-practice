# selenium-practice

UI-автотесты для демо-магазина [saucedemo.com](https://www.saucedemo.com/).
Проект учебный, но оформлен как полноценный: тесты, Page Object, отчёты и тестовая документация.

## Стек
Java 24 · Selenium 4 · JUnit 5 · Maven · Allure 

## Что проверяется
| Область  | Что покрыто                                                                                                                                       |
|----------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| Вход     | валидные/невалидные данные, пустые поля, заблокированный пользователь, 4 валидных пользователя (параметризованный тест), выход, доступ без логина |
| Каталог  | количество товаров, сортировка (имя, цена), уникальные картинки                                                                                   |
| Корзина  | добавление, удаление, счётчик на значке, содержимое корзины, Continue Shopping                                                                    |
| Checkout | успешный заказ, расчёт суммы, валидация обязательных полей                                                                                        |

Всего ~28 тестов. Тест `testProductImagesAreUniqueForProblemUser` — **известный баг** (BUG-001), он помечен `@Tag("known-bug")`.

## Структура
```
src/test/java/org/example/
├── base/      BaseTest — запуск/закрытие браузера, общие страницы
├── config/    Config — настройки (url, браузер, headless, таймаут)
├── data/      Users — тестовые аккаунты
├── factory/   DriverFactory — Chrome / Firefox, headless
├── pages/     Page Object: BasePage, LoginPage, InventoryPage, CartPage, CheckoutPage
├── tests/     LoginTest, ProductsTest, CartTest, CheckoutTest
└── utils/     ScreenshotExtension — скриншот при падении теста в Allure
markdowns/     тест-план, тест-кейсы, чек-лист, RTM, баг-репорт, отчёт
```

## Запуск
```bash
mvn test                              # все тесты (Chrome с окном)
mvn test -Dheadless=true              # без окна
mvn test -Dgroups=smoke               # только smoke
mvn test -Dgroups=regression          # только regression
mvn test -Dbrowser=firefox            # другой браузер
mvn test -DexcludedGroups=            # включая тест на известный баг
```

## Allure-отчёт
```bash
mvn test
allure serve target/allure-results
```
(нужен установленный [Allure CLI](https://allurereport.org/docs/install/)). При падении теста в отчёт прикрепляется скриншот.

## Приёмы, которые использованы
- **Page Object** + общий `BasePage` (явные ожидания вместо `sleep`)
- **BaseTest** — без дублирования setUp/tearDown
- **Конфиг через -D параметры**
- **Теги** smoke / regression / known-bug
- **Параметризованные тесты** (`@ParameterizedTest`)
- **Скриншот при падении** (JUnit 5 extension)
- Тестовая документация: тест-план, тест-кейсы, RTM, баг-репорт
