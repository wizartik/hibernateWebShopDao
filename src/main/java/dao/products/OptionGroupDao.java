package dao.products;

import dao.impl.products.OptionGroupDaoImpl;
import entities.products.OptionGroup;

public interface OptionGroupDao {

    static OptionGroupDao getInstance() {
        return new OptionGroupDaoImpl();
    }

    OptionGroup getOptionGroup(String name);

    OptionGroup getOptionGroup(long id);

    OptionGroup getOptionGroup(OptionGroup optionGroup);

    OptionGroup createOptionGroup(OptionGroup optionGroup);

    boolean isOptionGroupExists(OptionGroup optionGroup);
}
