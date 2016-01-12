/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
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
package org.mrobson.example.camelhibernatejpa.activator;

import org.apache.log4j.Logger;
import org.osgi.framework.*;
/**
 * @author <a href="mailto:mrobson@redhat.com">Matthew Robson</a>
 * 
 * Nov 12, 2015
 */
                                                                                                                                                                                                                                                                                                                                                                                  
public class Activator implements BundleActivator {                                                                                                                                        
                    
	private Logger log = Logger.getLogger(Activator.class);
	
    public void start(BundleContext context) throws Exception {                                                                                                                            
        Bundle[] bundles = context.getBundles();                                                                                                                                           
        try{                                                                                                                                                                               
            for(Bundle b : bundles){                                                                                                                                                       
                    if(b.getSymbolicName().equalsIgnoreCase("org.hibernate.osgi")) {
                    	if (b.getState() == Bundle.ACTIVE) {
                            log.info(b.getSymbolicName() + " is active, stopping and starting");
                    		b.stop();
                    		b.start();
                    	}
                    }
            }                                                                                                                                                                              
        } catch (BundleException var6) {                                                                                                                                                   
        } 
    }                                                                                                                             
    public void stop(BundleContext context) throws Exception {}                                                                                                                                                                                    
}
