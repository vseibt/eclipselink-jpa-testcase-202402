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

package eclipselink.jpa.bugtest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(TestEntityPersonId.class)
@Table(name = "TEST_TAB_PERSON")
public class TestEntityPerson {

    @Id
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Id
    @Column(name = "LAST_NAME")
    private String lastName;

    public TestEntityPerson() {
    }
}
