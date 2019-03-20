package com.zenika.uglysystem.persistence;

import org.junit.Assert;
import org.junit.Test;

public class MyEntityTest {

    @Test
    public void should_return_entity_id_equal_to_1(){
        //Given
        MyEntity myEntity = new MyEntity();

        //When
        myEntity.setId(1L);

        //Assert
        Assert.assertEquals(1L,myEntity.getId());
    }
}
