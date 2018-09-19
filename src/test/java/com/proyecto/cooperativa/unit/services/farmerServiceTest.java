package com.proyecto.cooperativa.unit.services;

import com.proyecto.cooperativa.repositories.FarmerRepository;
import com.proyecto.cooperativa.services.FarmerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class farmerServiceTest {

    @Mock //Will create a mock implementation for the farmerRepository
    private FarmerRepository farmerRepository;

    @InjectMocks //will inject the mocks marked with @Mock to this instance when it is created.
    private FarmerService farmerService;

    @Before //So when or where are these instances created? Well, it is done by this line which reside in the setUp method.
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFarmerList(){

    }


}
