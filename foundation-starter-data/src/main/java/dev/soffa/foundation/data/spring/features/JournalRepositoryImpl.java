package dev.soffa.foundation.data.spring.features;

import dev.soffa.foundation.data.DB;
import dev.soffa.foundation.data.SimpleRepository;
import dev.soffa.foundation.extra.journal.Journal;
import dev.soffa.foundation.extra.journal.JournalRepository;
import org.springframework.stereotype.Component;

@Component
public class JournalRepositoryImpl extends SimpleRepository<Journal, String> implements JournalRepository {

    public JournalRepositoryImpl(DB db) {
        super(db, Journal.class);
    }

}
