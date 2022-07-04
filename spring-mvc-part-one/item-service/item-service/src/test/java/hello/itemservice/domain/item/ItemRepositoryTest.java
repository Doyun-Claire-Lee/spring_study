package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();

        Item updatedItem = new Item("item2", 20000, 20);
        itemRepository.update(id, updatedItem);

        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(updatedItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updatedItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updatedItem.getQuantity());
    }
}