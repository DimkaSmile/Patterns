package ru.netology.web.data;

import lombok.Data;
import static ru.netology.web.data.DataGenerator.*;

@Data

public class RegistrationInfo {
    public RegistrationInfo() {
        generateName();
        generatePhone();
        generateCity();

    }
}
