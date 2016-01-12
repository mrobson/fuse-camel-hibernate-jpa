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
package org.mrobson.example.camelhibernatejpa.datamodel;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:mrobson@redhat.com">Matthew Robson</a>
 * 
 * Apr 30, 2015
 */

@XmlRootElement
@XmlType
@Entity
@Table(name = Person.TABLE_NAME)
public class Person extends EntityCommons {
	
	private static final long serialVersionUID = 7686378197745250332L;

	protected static final String TABLE_NAME = "Person";

/*	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;*/
	
	@Column(nullable = false, length = 60)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String country;

	//@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//private Set<Address> addresses = new HashSet<Address>();
	
/*	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}*/
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first) {
		this.firstName = first;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last) {
		this.lastName = last;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/*public Set<Address> getAddresses() {
		return addresses;
	}

	public void addAddress(Address addr) {
		addr.setPerson(this);
		addresses.add(addr);

	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}


	
	public void attachAddresses() {
		for (Address addr : getAddresses()) {
			addr.setPerson(this);
		}		
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		sb.append("FirstName=" + getFirstName());
		sb.append(" LastName=" + getLastName());
		sb.append(" Country=" + getCountry());
		sb.append(" Addresses=" + getAddresses().toString());
		sb.append("]");
		return sb.toString();
	}*/
}
