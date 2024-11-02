package nicol.lab.repository;

import lombok.extern.slf4j.Slf4j;
import nicol.lab.domain.product.Product;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class Product_rep_yaml extends SerializableRepository {

    @Override
    protected List<Product> read(String fileContents) throws IOException {
        /* преобразовать строку в yaml-дерево и конвертировать в объект */
        ConfigurationNode root = YamlConfigurationLoader.builder().buildAndLoadString(fileContents);
        return root.getList(Product.class);
    }

    public boolean writeTo(Path pathToFile) {
        /* записать в файл и вернуть true/false в зависимости от успешности */
        try {
            YamlConfigurationLoader loader = YamlConfigurationLoader.builder().path(pathToFile).build();
            CommentedConfigurationNode root = loader.createNode();
            root.setList(Product.class, this.products);
            loader.save(root);
            return true;
        } catch (IOException io) {
            log.atError().setCause(io).log("Не удалось сохранить файл {}", pathToFile);
            return false;
        }
    }
}
