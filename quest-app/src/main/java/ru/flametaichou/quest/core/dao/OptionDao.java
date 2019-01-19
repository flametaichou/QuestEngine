package ru.flametaichou.quest.core.dao;

import ru.flametaichou.quest.core.domain.Option;

public interface OptionDao {

    void saveOrUpdate(Option option);
    Option findById(long id);
    void delete(Option option);
}
