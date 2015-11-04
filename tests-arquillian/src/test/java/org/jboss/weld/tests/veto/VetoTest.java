/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.tests.veto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.weld.literal.AnyLiteral;
import org.jboss.weld.test.util.Utils;
import org.jboss.weld.tests.veto.package1.Hippo;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class VetoTest {

    @Inject
    private BeanManager manager;

    @Inject
    private VerifyingExtension extension;

    @Deployment
    public static JavaArchive getDeployment() {
        return ShrinkWrap.create(BeanArchive.class, Utils.getDeploymentNameAsHash(VetoTest.class)).addPackages(true, Elephant.class.getPackage())
                .addAsServiceProvider(Extension.class, VerifyingExtension.class);
    }

    @Test
    public void testClassLevelVeto() {
        assertFalse(extension.getClasses().contains(Elephant.class));
        assertEquals(0, manager.getBeans(Elephant.class, AnyLiteral.INSTANCE).size());
    }

    @Test
    public void testPackageLevelVeto() {
        assertFalse(extension.getClasses().contains(Hippo.class));
        assertEquals(0, manager.getBeans(Hippo.class, AnyLiteral.INSTANCE).size());
    }
}