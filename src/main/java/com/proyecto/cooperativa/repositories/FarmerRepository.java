package com.proyecto.cooperativa.repositories;

import com.proyecto.cooperativa.models.Farmer;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FarmerRepository {
    private static final Logger log = LoggerFactory.getLogger(FarmerRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT = "SELECT ";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String LIKE = " LIKE ?";
    private static final String OR = " OR ";
    private static final String PERCENT = "%";
    private static final String COMMA_SEPARATOR = ", ";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String VALUES = " ) VALUES (";
    private static final String QUESTION_MARK = "?";
    private static final String TABLE_NAME = " AGRICULTORES ";
    private static final String PARENTHESIS = " (";
    private static final String UPDATE = "UPDATE ";
    private static final String SET = " SET ";
    private static final String EQUALS_SIGN = " = ";

    private List<String> farmerFieldsToGet = Arrays.asList("a.n_socio", "p.cif_nif",
            "p.nombre_razon_social",
            "p.apellidos",
            "p.direccion",
            "p.telefono",
            "p.email");
    private List<String> farmerFieldsToCreate = Arrays.asList("id_persona", "baja");

    public List<Map<String, Object>> getFarmersList(String textToSearch) {
        String query = buildSql(textToSearch);
        if (hasSearchingText(query)) {
            return getFilteredList(query, textToSearch);
        } else {
            return jdbcTemplate.queryForList(query);
        }
    }

    private List<Map<String, Object>> getFilteredList(@NonNull String query, String textToSearch) {
        textToSearch = PERCENT + textToSearch + PERCENT;
        return jdbcTemplate.queryForList(query,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch);
    }

    private String buildSql(String textToSearch) {
        return buildSelectList() + buildWhereList(textToSearch);
    }

    private boolean hasSearchingText(String query) {
        return query.contains(WHERE);
    }

    private String buildSelectList() {
        return SELECT
                + farmerFieldsToGet.stream().collect(Collectors.joining(COMMA_SEPARATOR))
                + FROM + "PERSONAS p "
                + " JOIN agricultores a ON (p.id_persona = a.id_persona) ";
    }

    private String buildWhereList(String textToSearch) {
        String whereClause = "";
        if (!StringUtils.isEmpty(textToSearch)) {
            whereClause = WHERE
                    + farmerFieldsToGet.stream()
                    .collect(Collectors.joining(LIKE + OR))
                    + LIKE;
        }
        return whereClause;
    }

    public boolean createFarmer(Farmer farmer) {
        final String sql = INSERT_INTO
                + TABLE_NAME
                + PARENTHESIS
                + farmerFieldsToCreate.stream()
                    .collect(Collectors.joining(COMMA_SEPARATOR))
                + VALUES
                + buildValuesWithQuestionMarks(farmerFieldsToCreate.size());
        return inserting(farmer, sql);
    }

    private boolean inserting(Farmer farmer, String sql) {
        boolean isUpdated = false;
        try {
            isUpdated = jdbcTemplate.update(sql, farmer.getPersonId(),
                    farmer.isDropOut()) > 0;
            log.info("Insercion en la tabla AGRICULTORES, query: " + sql);
        } catch (Exception e) {
            log.error("Error: Insertar en la tabla AGRICULTORES, query: " + sql);
        }
        return isUpdated;
    }

    private String buildValuesWithQuestionMarks(int numberOfValues) {
        return Collections.nCopies(numberOfValues, QUESTION_MARK).stream()
                .collect(Collectors.joining(COMMA_SEPARATOR));
    }


    public boolean dropOut(Farmer farmer){
        final String sql = buildSqlDropOut();
        return setDropOut(farmer,sql);
    }

    private String buildSqlDropOut(){
        return  UPDATE + TABLE_NAME + SET + farmerFieldsToCreate.stream()
                .map(entry -> entry + EQUALS_SIGN + QUESTION_MARK)
                .sorted(Comparator.comparing(n -> n))
                .collect(Collectors.joining(WHERE));
    }

    private boolean setDropOut(Farmer farmer, String sql){
        boolean isUpdated = false;
        try{
            isUpdated= jdbcTemplate.update(sql,farmer.isDropOut(), farmer.getPersonId()) > 0;
            log.info("Se ha dado de baja una persona, query:" + sql);
        }catch(Exception e){
            log.error("No se ha podido dar de baja a la persona, query: " + sql);
        }
        return isUpdated;
    }


}
