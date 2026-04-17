# InBox

Кроссплатформенное мобильное приложение доставки на **Kotlin Multiplatform** с общим бизнес-слоем для Android и iOS.

Проект демонстрирует production-подход: модульная архитектура, разделение `data/domain/features`, DI, реактивные state-пайплайны, сетевой слой с авторизацией и realtime-обновлениями.

## Что реализовано

### Основные пользовательские сценарии
- Онбординг и launch-поток с предзагрузкой ключевых данных.
- Авторизация по номеру телефона (`sms` / `call`) с подтверждением кода.
- Выбор адреса доставки:
  - через карту (Yandex Maps),
  - через поиск адреса,
  - с автоматическим подбором ближайшего ресторана.
- Главный экран:
  - категории,
  - товары,
  - текущее состояние корзины,
  - активные заказы.
- Каталог категории и карточка товара (в т.ч. dialog/bottom sheet сценарии).
- Корзина:
  - изменение количества,
  - пересчёт стоимости,
  - переход к оплате только для авторизованного пользователя.
- Оформление заказа:
  - `delivery` / `pickup`,
  - валидация полей адреса,
  - выбор типа оплаты,
  - создание заказа.
- Экран текущего заказа с обновлением статуса в реальном времени.
- Профиль пользователя: просмотр/редактирование, logout, удаление аккаунта.

### Инженерные возможности
- Realtime через WebSocket:
  - обновления статусов заказов,
  - поток подтверждения call-авторизации.
- Централизованная обработка ошибок (user-friendly сообщения).
- Кроссплатформенный snackbar/event канал для UI-эффектов.
- Платформенное безопасное хранение токенов:
  - Android: Keystore + AES/GCM,
  - iOS: Keychain.
- Авто-refresh access token при `401` в Ktor-клиенте.

## Архитектура

Проект разделён на модули:
- `shared` — общий KMP-слой (бизнес-логика, сеть, репозитории, use cases, feature-компоненты).
- `composeApp` — Android UI (Jetpack Compose).
- `iosApp` — iOS UI (SwiftUI) + интеграция с общим `shared` framework.

Внутри `shared`:
- `data`:
  - API-клиенты (Ktor),
  - remote/local data stores,
  - mappers,
  - repository-реализации.
- `domain`:
  - модели,
  - repository interfaces,
  - use cases.
- `features`:
  - stateful компоненты экранов,
  - reducer-подход (`ViewState` / `ViewEvent` / `ViewEffect`),
  - единый `BaseComponent` с lifecycle-интеграцией.
- `navigation`:
  - root stack + dialog slot на Decompose.

## Технологический стек

### Ядро
- Kotlin Multiplatform (Kotlin `2.3.0`)
- Compose Multiplatform / Jetpack Compose (`1.10.x`)
- SwiftUI (iOS слой)

### Архитектура и DI
- Decompose (`3.4.0`) + Essenty
- Koin (`4.1.1`)

### Сеть и данные
- Ktor Client (`3.3.3`): REST + WebSocket
- Kotlinx Serialization
- Coroutines / Flow

### Платформенные интеграции
- Yandex Maps:
  - Android SDK (`maps.mobile 4.19.0-lite`)
  - iOS SDK (`YandexMapsMobile 4.5.1-lite`)
- AndroidX Security Crypto
- iOS Keychain API

### Android
- AGP `9.0.0`
- minSdk `30`, target/compileSdk `36`

### iOS
- iOS deployment target `14.0`

## Запуск проекта

### Prerequisites
- JDK 17+
- Android Studio (для Android части)
- Xcode + CocoaPods (для iOS части)

### Android
1. Открыть проект в Android Studio.
2. Дождаться sync Gradle.
3. Запустить конфигурацию `composeApp` на эмуляторе/устройстве.

CLI-сборка:
```bash
./gradlew :composeApp:assembleDebug
```

### iOS
1. Перейти в директорию `iosApp`.
2. Установить pods.
3. Открыть workspace и запустить target `iosApp`.

```bash
cd iosApp
pod install
open iosApp.xcworkspace
```

## Дорожная карта

- Закрыть незавершённые ветки UI/flow (включая отдельные TODO-участки в `main_tabs` и reducers).
- Завершить iOS card-payment контур (криптограмма + полный E2E checkout flow).
- Вынести API keys/секреты в безопасную конфигурацию окружений (dev/stage/prod).
- Добавить автоматизированные тесты:
  - unit-тесты reducers/use cases,
  - integration-тесты репозиториев,
  - smoke UI-тесты критичных сценариев.
- Настроить CI/CD:
  - сборка Android,
  - проверка shared-модуля,
  - статический анализ (lint/detekt/format checks).
- Усилить observability: crash reporting, метрики сетевых ошибок, мониторинг realtime-каналов.

## Статус

Проект в активной разработке. Текущая версия демонстрирует полный сквозной пользовательский путь от авторизации и выбора адреса до создания и отслеживания заказа, с единой кроссплатформенной бизнес-логикой.
