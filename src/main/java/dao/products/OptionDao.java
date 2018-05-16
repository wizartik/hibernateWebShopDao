package dao.products;

import dao.impl.products.OptionDaoImpl;
import entities.products.Option;
import entities.products.OptionGroup;

import java.util.List;

public interface OptionDao {

    static OptionDao getInstance(){
        return new OptionDaoImpl();
    }


    Option createOption(Option option);

    Option getOption(Option option);

    Option getOption(long id);

    boolean isOptionExists(Option option);

    List<Option> getOptions(OptionGroup optionGroup);
}
