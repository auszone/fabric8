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
package io.fabric8.tooling.archetype.commands;

import io.fabric8.api.scr.ValidatingReference;
import io.fabric8.boot.commands.support.AbstractCommandComponent;
import io.fabric8.tooling.archetype.ArchetypeService;
import io.fabric8.tooling.archetype.commands.support.ArchetypeShortCompleter;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.CompleterValues;
import org.apache.felix.gogo.commands.basic.AbstractCommand;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.service.command.Function;
import org.apache.karaf.shell.console.completer.FileCompleter;

@Component(immediate = true)
@Service({ Function.class, AbstractCommand.class })
@org.apache.felix.scr.annotations.Properties({
    @Property(name = "osgi.command.scope", value = ArchetypeGenerate.SCOPE_VALUE),
    @Property(name = "osgi.command.function", value = ArchetypeGenerate.FUNCTION_VALUE)
})
public class ArchetypeGenerate extends AbstractCommandComponent {

    public static final String SCOPE_VALUE = "fabric";
    public static final String FUNCTION_VALUE = "archetype-generate";
    public static final String DESCRIPTION = "Generates a new project from a fabric Maven archetype";

    @Reference(referenceInterface = ArchetypeShortCompleter.class, bind = "bindArchetypeCompleter", unbind = "unbindArchetypeCompleter")
    @CompleterValues(index = 0)
    private ArchetypeShortCompleter archetypeCompleter; // dummy field

    @Reference(referenceInterface = ArchetypeService.class)
    private final ValidatingReference<ArchetypeService> archetypeService = new ValidatingReference<ArchetypeService>();

    @Override
    public Action createNewAction() {
        assertValid();
        return new ArchetypeGenerateAction(archetypeService.get());
    }

    @Activate
    void activate() {
        bindOptionalCompleter("-d", new FileCompleter(null));
        activateComponent();
    }

    @Deactivate
    void deactivate() {
        deactivateComponent();
    }

    void bindArchetypeCompleter(ArchetypeShortCompleter completer) {
        bindCompleter(completer);
    }

    void unbindArchetypeCompleter(ArchetypeShortCompleter completer) {
        unbindCompleter(completer);
    }

    public void bindArchetypeService(ArchetypeService archetypeService) {
        this.archetypeService.bind(archetypeService);
    }

    public void unbindArchetypeService(ArchetypeService archetypeService) {
        this.archetypeService.unbind(archetypeService);
    }

}
