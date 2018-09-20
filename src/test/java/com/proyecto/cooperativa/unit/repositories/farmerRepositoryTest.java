package com.proyecto.cooperativa.unit.repositories;

import com.proyecto.cooperativa.models.Farmer;
import com.proyecto.cooperativa.repositories.FarmerRepository;
import lombok.NonNull;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.proyecto.cooperativa.models.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class farmerRepositoryTest {

    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String EMAIL = "email";
    private static final String PHONE = "telefono";
    private static final String CIF_NIF = "cif_nif";
    private static final String PERCENT = "%";

    @Test
    public void testGetFarmerList() throws Exception {
        final String select = "SELECT  a.n_socio, " +
                "p.cif_nif, " +
                "p.nombre_razon_social, " +
                "p.apellidos, " +
                "p.direccion, " +
                "p.telefono, " +
                "p.email " +
                "FROM PERSONAS p " +
                "JOIN AGRICULTORES a ON (p.id_persona = a.id_persona) ";
        testWithTextToSearch(select);
        testNoSearchingText(select);
    }

    private void testNoSearchingText(String sqlExpected) throws Exception {
        //given
        String textToSearch = "";
        //when
        List<Map<String, Object>> actual = farmerRepository.getFarmersList(textToSearch);
        List<Map<String, Object>> expected = jdbcTemplate.queryForList(sqlExpected);
        //then
        assertActualAndExpectedLists(actual, expected);
    }

    private void testWithTextToSearch(String select) throws Exception {
        //given
        String textToSearch = "usuario";
        final String sqlExpected = select
                + " WHERE a.n_socio like ? "
                + "OR p.cif_nif like ? "
                + "OR p.nombre_razon_social like ? "
                + "OR p.apellidos like ? "
                + "OR p.direccion like ? "
                + "OR p.telefono like ?  "
                + "OR p.email like ? ";
        //when
        List<Map<String, Object>> actual = farmerRepository.getFarmersList(textToSearch);
        List<Map<String, Object>> expected = getFilteredList(sqlExpected, textToSearch);
        //then
        assertActualAndExpectedLists(actual, expected);
    }

    private List<Map<String, Object>> getFilteredList(String sqlExpected, String textToSearch) {
        textToSearch = PERCENT + textToSearch + PERCENT;
        return jdbcTemplate.queryForList(sqlExpected,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch,
                textToSearch);
    }

    private void assertActualAndExpectedLists(List<Map<String, Object>> actual,
                                              List<Map<String, Object>> expected) {
        assertThat(actual, is(expected));
        assertThat(actual.size(), is(expected.size()));
        assertEquals(actual, expected);
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertValidationStringFromField(EMAIL, actual);
        assertValidationStringFromField(PHONE, actual);
        assertValidationStringFromField(CIF_NIF, actual);
    }

    private void assertValidationStringFromField(String field, List<Map<String, Object>> actual) {
        long ZERO = 0;
        long incorrectMaps = actual.stream()
                .filter(map -> map.containsKey(field)
                        && !StringUtils.isEmpty(map.get(field))
                        && !map.get(field).toString().matches(getValidationStringFrom(field)))
                .count();
        assertThat(incorrectMaps, is(ZERO));
    }

    private String getValidationStringFrom(@NonNull String field) {
        switch (field) {
            case EMAIL:
                return EMAIL_VALIDATION;
            case PHONE:
                return PHONE_NUMBER_VALIDATION;
            case CIF_NIF:
                return CIF_NIF_VALIDATION;
            default:
                return "";
        }
    }


    @Test
    public void testCreateFarmer() {
        //Given
        int personId = 5;
        Farmer current = createDummyFarmer(personId, false);
        boolean isCreated = farmerRepository.createFarmer(current);
        //when
        Farmer expected = readFarmer(personId);
        boolean isDeleted = deleteFarmer(personId);
        //then
        assertCreations(current, expected, isCreated, isDeleted);
    }

    private Farmer createDummyFarmer(@NonNull int personId, @NonNull boolean dropOut) {
        Farmer farmer = new Farmer();
        farmer.setPersonId(personId);
        farmer.setDropOut(dropOut);
        return farmer;
    }

    private Farmer readFarmer(int personId) {
        final String readSql = "SELECT id_persona, baja FROM AGRICULTORES WHERE ID_PERSONA = ?";
        return jdbcTemplate.queryForObject(readSql,new FarmerRowMapper(), personId);
    }

    private boolean deleteFarmer(int personId){
        final String deleteSql = "DELETE FROM AGRICULTORES WHERE ID_PERSONA = ?";
        return jdbcTemplate.update(deleteSql,personId)>0;
    }

    private void assertCreations(@NonNull Farmer current,
                                      @NonNull Farmer expected,
                                      @NonNull boolean isCreated,
                                      @NonNull boolean isDeleted) {
        assertEquals(current, expected);
        assertTrue(isDeleted);
        assertTrue(isCreated);
    }

    class FarmerRowMapper implements RowMapper<Farmer> {
        @Override
        public Farmer mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Farmer farmer = new Farmer();
            farmer.setPersonId(rs.getInt(1));
            farmer.setDropOut(rs.getBoolean(2));
            return farmer;
        }

    }
}
