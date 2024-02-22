/*
 * Copyright (c) 2023 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

package eclipselink.jpa.bugtest;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import eclipselink.jpa.bugtest.domain.TestEntityMaster;
import eclipselink.jpa.bugtest.domain.TestEntityPerson;
import eclipselink.jpa.bugtest.domain.TestEntityPersonId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestOk {

    @Test
    public void findByCompositeKeyTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa-pu");
        assertNotNull(entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            var id = new TestEntityPersonId("Stan", "Dandeliver");
            var testPerson = em.find(TestEntityPerson.class, id);
            Assert.assertNotNull(testPerson);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Test
    public void getReferenceBySingleKeyTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa-pu");
        assertNotNull(entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            var id = 1L;
            var testMaster = em.find(TestEntityMaster.class, id);
            Assert.assertNotNull(testMaster);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
