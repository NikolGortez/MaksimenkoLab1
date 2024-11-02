package nicol.lab.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import nicol.lab.domain.product.Product;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public final class Product_rep_json extends SerializableRepository {
    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected List<Product> read(String fileContents) {
        /* сгенерировать тип, который нужно прочитать */
        /* преобразовать json строку в объект */
        Type type = TypeToken.getParameterized(List.class, Product.class).getType();
        return PRETTY_GSON.fromJson(fileContents, type);
    }

    public boolean writeTo(Path pathToFile) {
        /* записать в файл и вернуть true/false в зависимости от успешности */
        try {
            Files.writeString(pathToFile, PRETTY_GSON.toJson(super.products));
            return true;
        } catch (IOException io) {
            log.atError().setCause(io).log("Не удалось сохранить файл {}", pathToFile);
            return false;
        }
    }
}
