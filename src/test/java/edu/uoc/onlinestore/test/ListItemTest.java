package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.model.ListItem;
import edu.uoc.onlinestore.model.Item;
import org.junit.jupiter.api.Test;

class ListItemTest {

    @Test
    void add() throws Exception {

            ListItem listItem = new ListItem();
            Item item = new Item();
            item.setCode(2111);

            listItem.add(item);
        }
    }
