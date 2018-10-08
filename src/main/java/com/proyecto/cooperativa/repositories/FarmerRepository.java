package com.proyecto.cooperativa.repositories;

import com.proyecto.cooperativa.models.Farmer;
import com.proyecto.cooperativa.models.Preffix;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.proyecto.cooperativa.models.Constants.*;
import static com.proyecto.cooperativa.utils.JdbcUtils.buildValuesWithQuestionMarks;
import static org.springframework.util.StringUtils.isEmpty;

@Repository
public class FarmerRepository {
    private static final Logger log = LoggerFactory.getLogger(FarmerRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<String> farmerFieldsToGet = Arrays.asList(Preffix.FARMER + "n_socio",
            Preffix.PERSON + "cif_nif",
            Preffix.PERSON + "nombre_razon_social",
            Preffix.PERSON + "apellidos",
            Preffix.PERSON + "direccion",
            Preffix.PERSON + "telefono",
            Preffix.PERSON + "email");
    private List<String> farmerFieldsToCreate = Arrays.asList(ID_PERSONA, BAJA);

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
                + FROM + PERSONAS + Preffix.PERSON.replace(".", "")
                + JOIN + AGRICULTORES + Preffix.FARMER.replace(".", "") + ON
                + INITIAL_PARENTHESIS + Preffix.PERSON + ID_PERSONA + EQUALS_SIGN + Preffix.FARMER + ID_PERSONA + FINAL_PARENTHESIS;
    }

    private String buildWhereList(String textToSearch) {
        String whereClause = "";
        if (!isEmpty(textToSearch)) {
            whereClause = WHERE
                    + farmerFieldsToGet.stream()
                    .collect(Collectors.joining(LIKE + OR))
                    + LIKE;
        }
        return whereClause;
    }

    public boolean create(Farmer farmer) {
        final String sql = INSERT_INTO
                + AGRICULTORES
                + INITIAL_PARENTHESIS
                + farmerFieldsToCreate.stream()
                    .collect(Collectors.joining(COMMA_SEPARATOR))
                + VALUES
                + buildValuesWithQuestionMarks(farmerFieldsToCreate.size())
                + FINAL_PARENTHESIS;
        return inserting(farmer, sql);
    }

    private boolean inserting(Farmer farmer, String sql) {
        boolean isInserted = false;
        try {
            isInserted = jdbcTemplate.update(sql, farmer.getPersonId(),
                    farmer.isDropOut()) > 0;
            log.info("Inserción en la tabla AGRICULTORES, query: {}", sql);
        } catch (Exception e) {
            log.error("Error: Insertar en la tabla AGRICULTORES, query: {} ", sql);
        }
        return isInserted;
    }

    public boolean dropOut(Farmer farmer) {
        final String sql = buildSqlDropOut();
        return setDropOut(farmer, sql);
    }

    private String buildSqlDropOut() {
        return UPDATE + AGRICULTORES + SET + farmerFieldsToCreate.stream()
                .map(entry -> entry + EQUALS_SIGN + QUESTION_MARK)
                .sorted(Comparator.comparing(n -> n))
                .collect(Collectors.joining(WHERE));
    }

    private boolean setDropOut(Farmer farmer, String sql) {
        boolean isUpdated = false;
        try {
            isUpdated = jdbcTemplate.update(sql, farmer.isDropOut(), farmer.getPersonId()) > 0;
            log.info("Se ha dado de baja una persona, query: {}", sql);
        } catch (Exception e) {
            log.error("No se ha podido dar de baja a la persona, query: {} ", sql);
        }
        return isUpdated;
    }

    public Farmer read(int personId) {
        final String query = SELECT
                + Preffix.PERSON
                + ALL
                + FROM
                + PERSONAS
                + JOIN
                + INITIAL_PARENTHESIS
                + Preffix.PERSON
                + ID_PERSONA
                + EQUALS_SIGN
                + Preffix.FARMER
                + ID_PERSONA
                + FINAL_PARENTHESIS
                + WHERE
                + Preffix.FARMER + ID_PERSONA + EQUALS_SIGN + QUESTION_MARK
                + AND + Preffix.FARMER + BAJA + EQUALS_SIGN + FALSE;
        return getFarmer(personId,query);
    }

    private Farmer getFarmer(int personId, String query){
        String message = "Retrieving farmer object, query: {}";
        Farmer farmer = new Farmer();
        try {
            farmer = jdbcTemplate.queryForObject(query, new Integer[]{personId}, new FarmerRowMapper());
            log.info(message, query);
        } catch (Exception e) {
            log.error("Error: {} {}", message, query);
        }
        return farmer;

    }

    class FarmerRowMapper implements RowMapper<Farmer> {
        @Override
        public Farmer mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Farmer farmer = new Farmer();
            farmer.setCifNif(rs.getString("cif_nif"));
            farmer.setName(rs.getString("nombre_razon_social"));
            farmer.setLastName(rs.getString("apellidos"));
            farmer.setAdress(rs.getString("direccion"));
            farmer.setPhoneNumber(rs.getString("telefono"));
            farmer.setEmail(rs.getString("email"));
            farmer.setFarmerId(rs.getInt("n_socio"));
            farmer.setPersonId(rs.getInt(ID_PERSONA));
            farmer.setDropOut(rs.getBoolean(BAJA));
            return farmer;
        }

    }

}
