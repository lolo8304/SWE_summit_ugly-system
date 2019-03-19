package com.zenika.uglysystem.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyEntityRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Test
    public void should_return_an_entity(){
        // Given
        MyEntity myEntity = new MyEntity();
        myEntity.setName("Test");
        myEntity.setAttribute("Attribute");
        entityManager.persist(myEntity);
        entityManager.flush();

        // When
        List<MyEntity> foundEntities = myEntityRepository.findByName(myEntity.getName());
        for(MyEntity entity : foundEntities){
            Assert.assertEquals(myEntity.getName(),entity.getName());
            Assert.assertEquals(myEntity.getAttribute(),entity.getAttribute());
        }
    }
}
