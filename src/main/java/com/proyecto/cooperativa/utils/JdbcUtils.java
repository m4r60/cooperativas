package com.proyecto.cooperativa.utils;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

import static com.proyecto.cooperativa.models.Constants.QUESTION_MARK;
import static org.apache.logging.log4j.core.util.Patterns.COMMA_SEPARATOR;

@Component
public class JdbcUtils {

    public static String buildValuesWithQuestionMarks(int numberOfValues) {
        return Collections.nCopies(numberOfValues, QUESTION_MARK).stream()
                .collect(Collectors.joining(COMMA_SEPARATOR));
    }
}
