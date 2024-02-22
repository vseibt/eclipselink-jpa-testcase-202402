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

public class TestEntityPersonId {

    private String firstName;

    private String lastName;

    public TestEntityPersonId() {
    }

    public TestEntityPersonId(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }
}
