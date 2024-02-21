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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import eclipselink.jpa.bugtest.domain.TestEntityMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;

public class TestBug {

    private static final long ID = 1L;

    @Test
    public void findAndChangeTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa-pu");
        assertNotNull(entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            TestEntityMaster testMaster = em.find(TestEntityMaster.class, ID);
            testMaster.setName("new name");
            em.getTransaction().commit();

            em.clear();
            TestEntityMaster testMaster2 = em.find(TestEntityMaster.class, ID);
            assertEquals("new name", testMaster2.getName());

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

    /*
     * The em.lock statement is the only difference to the test above.
     * em.lock should not change the state of the entity, but only execute a "select for update" statement.
     */
    @Test
    public void findChangeAndLockTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa-pu");
        assertNotNull(entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            TestEntityMaster testMaster = em.find(TestEntityMaster.class, ID);
            testMaster.setName("new name");
            em.lock(testMaster, LockModeType.PESSIMISTIC_WRITE);
            assertEquals("new name", testMaster.getName());
            em.getTransaction().commit();

            em.clear();
            TestEntityMaster testMaster2 = em.find(TestEntityMaster.class, ID);
            assertEquals("new name", testMaster2.getName());

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
