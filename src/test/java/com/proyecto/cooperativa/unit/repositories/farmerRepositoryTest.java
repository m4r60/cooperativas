package com.proyecto.cooperativa.unit.repositories;

import com.proyecto.cooperativa.repositories.FarmerRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static com.proyecto.cooperativa.models.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

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

    @Test
    public void testGetFarmerList() throws Exception {
        final String select = "SELECT  n_socio, " +
                "cif_nif, " +
                "nombre_razon_social, " +
                "apellido, " +
                "direccion, " +
                "telefono, " +
                "email " +
                "FROM PERSONAS p " +
                "JOIN (p.persona_id = a.persona_id) ON AGRICULTORES a ";
        testWithTextToSearch(select);
        testNoSearchingText(select);
    }

    private void testNoSearchingText(String sqlExpected) throws Exception  {
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
        final String sqlExpected = select +
                " WHERE cif_nif like ? " +
                "OR nombre_razon_social like ? " +
                "OR apellido like ? " +
                "OR direccion like ? " +
                "OR telefono like ?  " +
                "OR email like ?";
        //when
        List<Map<String, Object>> actual = farmerRepository.getFarmersList(textToSearch);
        List<Map<String, Object>> expected = jdbcTemplate.queryForList(sqlExpected);
        //then
        assertActualAndExpectedLists(actual, expected);
    }

    private void assertActualAndExpectedLists(List<Map<String,Object>> actual,
                                             List<Map<String,Object>> expected){
        assertThat(actual, is(expected));
        assertThat(actual.size(), is(expected.size()));
        assertThat(actual, contains(expected));
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertValidationStringFromField(EMAIL,actual);
        assertValidationStringFromField(PHONE,actual);
        assertValidationStringFromField(CIF_NIF,actual);
    }

    private void assertValidationStringFromField(String field,List<Map<String, Object>> actual){
        long ZERO = 0;
        long incorrectMaps = actual.stream()
                .filter(map -> map.containsKey(field)
                        && !StringUtils.isEmpty(map.get(field))
                        && !map.get(field).toString().matches(getValidationStringFrom(field)))
                .count();
        assertThat(incorrectMaps, is(ZERO));
    }

    private String getValidationStringFrom(String field){
        switch (field){
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


}
