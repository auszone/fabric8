/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.groovy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class GroovyResolverTest {
    @Test
    public void testGroovyExpressions() throws Exception {
        System.setProperty("CHEESE", "Edam");

        assertGroovy("sys.CHEESE ?: 'foo'", "Edam");
        assertGroovy("sys.DOES_NOT_EXIST ?: 'DoesNotExist'", "DoesNotExist");
    }

    public static void assertGroovy(String expression, String expectedValue) {
        String actual = GroovyPlaceholderResolver.resolveValue(null, expression);
        System.out.println("groovy> " + expression + " => " + actual);
        assertEquals("Expression " + expression, expectedValue, actual);
    }

}
