package ru.yandex.practicum.assignment.avro;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import ru.yandex.practicum.avro.Gender;
import ru.yandex.practicum.avro.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AvroAssignment1 {
    public static void main(String[] args) throws IOException {
        byte[] bytes = serialize(getUser());
        log.info("\n\nСкопируйте строку ниже в поле ответа на сайте Практикума:\n\n{}",
                HexFormat.of().formatHex(bytes));
    }

    public static byte[] serialize(User user) throws IOException {
        // Реализуйте метод в соответствии с заданием
        // Он должен принимать в качестве параметра экземпляр класса User,
        // который будет сгенерирован из вашей схемы Avro.
        // Напишите реализацию, сериализующую этот экземпляр в массив байтов,
        // который нужно вернуть из метода в качестве результата.


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // сюда будем сохранять байты
        DatumWriter<User> datumWriter = new SpecificDatumWriter<>(User.class);
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);

        // сериализуем данные заказа
        datumWriter.write(user, encoder);

        // сбрасываем все данные из буфера в поток
        encoder.flush();
        // закрываем поток
        outputStream.close();

        // преобразуем поток с записанными в него двоичными данными в массив байт
        return outputStream.toByteArray();
    }

    // этот метод менять нельзя!
    public static User getUser() {
        return User.newBuilder()
                .setFirstName("Abcde")
                .setGender(Gender.male)
                .setLastName("Fghij")
                .setId(1)
                .setInterests(List.of("swimming", "coding"))
                .setBirthdate(LocalDate.of(2024, 1, 1))
                .setContacts(new LinkedHashMap<>(Map.of(
                        "Ivan", "+7 (123) 456-78-13",
                        "Maria", "+7 (456) 789-12-34"
                )))
                .build();
    }
}
