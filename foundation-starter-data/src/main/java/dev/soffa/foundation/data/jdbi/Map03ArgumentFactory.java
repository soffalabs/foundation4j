package dev.soffa.foundation.data.jdbi;

import dev.soffa.foundation.commons.Mappers;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Types;
import java.util.Map;

public class Map03ArgumentFactory extends AbstractArgumentFactory<Map<String, Map<String, Object>>> {

    public Map03ArgumentFactory() {
        super(Types.VARCHAR);
    }

    @Override
    protected Argument build(Map<String, Map<String, Object>> value, ConfigRegistry config) {
        return (position, statement, ctx) -> {
            statement.setString(position, value == null ? null : Mappers.JSON_DEFAULT.serialize(value));
        };
    }
}
