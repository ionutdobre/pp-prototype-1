package com.example.demo.providers;

import com.example.demo.constants.WebConstants;
import com.example.demo.persistence.GenericPersistable;
import com.example.demo.services.TextSearchableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.wicketstuff.select2.ChoiceProvider;
import org.wicketstuff.select2.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class GenericPersistableJpaTextChoiceProvider<T extends GenericPersistable & Serializable>
        extends ChoiceProvider<T> {
    private static final Logger logger = LoggerFactory.getLogger(GenericPersistableJpaTextChoiceProvider.class);

    private final TextSearchableService<T> textSearchableService;

    private Sort sort;

    public GenericPersistableJpaTextChoiceProvider(final TextSearchableService<T> textSearchableService) {
        this.textSearchableService = textSearchableService;
    }

    @Override
    public String getIdValue(final T choice) {
        return choice.getId().toString();
    }

    @Override
    public void query(final String term, final int page, final Response<T> response) {
        final Page<T> itemsByTerm;
        if (term == null || term.isEmpty()) {
            itemsByTerm = findAll(page);
            response.setHasMore(itemsByTerm.hasNext());
        } else {
            itemsByTerm = getItemsByTerm(term.toLowerCase(), page);
        }

        if (itemsByTerm != null) {
            response.setHasMore(itemsByTerm.hasNext());
            response.addAll(itemsByTerm.getContent());
        }
    }

    private Page<T> getItemsByTerm(final String term, final int page) {
        final PageRequest pageRequest = sort == null
                ? PageRequest.of(page, WebConstants.SELECT_PAGE_SIZE)
                : PageRequest.of(page, WebConstants.SELECT_PAGE_SIZE, sort);
        return textSearchableService.searchText(term, pageRequest);
    }

    private Page<T> findAll(final int page) {
        final PageRequest pageRequest = sort == null
                ? PageRequest.of(page, WebConstants.SELECT_PAGE_SIZE)
                : PageRequest.of(page, WebConstants.SELECT_PAGE_SIZE, sort);
        return textSearchableService.findAll(pageRequest);
    }


    @Override
    public Collection<T> toChoices(final Collection<String> ids) {
        final ArrayList<String> idsList = new ArrayList<>();

        for (final String id : ids) {
            idsList.add(id);
        }

        final ArrayList<T> response = new ArrayList<>();
        for (final String s : idsList) {
            final Long id = Long.parseLong(s);
            final Optional<T> findOne = textSearchableService.findByIdCached(id);
            if (!findOne.isPresent()) {
                logger.error("Cannot find entity with id=" + id + " in service " + textSearchableService.getClass());
            } else {
                response.add(findOne.get());
            }
        }
        return response;
    }

    @Override
    public String getDisplayValue(final T choice) {
        return choice.toString();
    }

    public void setSort(final Sort sort) {
        this.sort = sort;
    }
}
