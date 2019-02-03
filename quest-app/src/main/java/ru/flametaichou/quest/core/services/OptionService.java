package ru.flametaichou.quest.core.services;

import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Option;
import ru.flametaichou.quest.core.dto.OptionDto;

public interface OptionService {

    void saveOrUpdate(Option option);
    void delete(Option option);
    void create(OptionDto optionDto);
    void createOption(Account account, OptionDto dto);
    void updateOption(Account account, OptionDto dto);

}
