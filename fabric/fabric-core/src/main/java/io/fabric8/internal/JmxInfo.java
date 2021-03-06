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
package io.fabric8.internal;

import javax.management.openmbean.CompositeData;

/**
 * Base class for JMX info beans.
 */
public class JmxInfo {

    /**
     * Composite data.
     */
    protected final CompositeData data;

    /**
     * Name of ID field.
     */
    private final String identifier;

    public JmxInfo(CompositeData data, String identifier) {
        this.data = data;
        this.identifier = identifier;
    }

    public Long getId() {
        return (Long) data.get(identifier);
    }

}
