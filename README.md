# binary-data-formats

Учебный проект для знакомства с Apache Avro IDL.

## Почему IDE показывает отсутствующие классы

Классы Avro (`Order`, `Product` и т.д.) **не лежат в `src/main/java`**,
они генерируются во время Maven-фазы `generate-sources` из файла:

- `src/main/avro/OrderProtocol.avdl`

## Как запустить генерацию

```bash
mvn generate-sources
```

После этого код появится в каталоге:

- `target/generated-sources/avro`

## Как собрать проект полностью

```bash
mvn clean package
```

## Если используете IntelliJ IDEA

1. Нажмите **Load Maven Changes** (иконка слона в Maven tool window).
2. Выполните Maven goal `generate-sources`.
3. Убедитесь, что папка `target/generated-sources/avro` отмечена как **Generated Sources Root**.

## Что настроено в `pom.xml`

Пайплайн генерации теперь двухшаговый:

1. `idl-protocol` — преобразует `.avdl` в Avro protocol (`.avpr`).
2. `protocol` — генерирует Java-классы из protocol.

Также `build-helper-maven-plugin` добавляет `target/generated-sources/avro` в source roots.
