/**
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.springsource.insight.plugin.jsf;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springsource.insight.idk.AbstractOperationViewTest;
import com.springsource.insight.idk.WebApplicationContextLoader;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.intercept.operation.SourceCodeLocation;

/**
 * This tests the view-rendering portion of a plugin.  If your plugin defines its own
 * Operation, it should also provide a corresponding view to be rendered in the UI.
 * 
 * @see jsf_phase_listener_operation
 * @see phase_listener.ftl
 */
@ContextConfiguration(locations = { "classpath:META-INF/insight-plugin-jsf.xml", 
                                    "classpath:META-INF/test-app-context.xml" },
                      loader = WebApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PhaseListenerViewTest extends AbstractOperationViewTest {
    
    private static final OperationType TYPE = OperationType.valueOf("jsf_phase_listener_operation");
    
    public PhaseListenerViewTest() {
        super(TYPE);
    }
    
    /**
     * This tests that {@link jsf_phase_listener_operation} can be rendered via the FreeMarker
     * template.
     */
    @Test
    public void testLocalViewWithStatus() throws Exception {
        SourceCodeLocation scl = new SourceCodeLocation("MyClass", "methodName", 45);
        Operation operation = new Operation()
            .type(TYPE)
            .sourceCodeLocation(scl)
            .label("JSF Phase Listener [MyClass#methodName]")
            .put("phaseId", "test phase");
        
        String content = getRenderingOf(operation);
        System.out.println(content);
        
        // Simply test for some expected contents within the HTML.
        assertTrue(content.contains("Phase Listener Details"));
        assertTrue(content.contains("Phase Id"));
        assertTrue(content.contains("test phase"));
        assertTrue(content.contains("Implementation Details"));
    }    
}

