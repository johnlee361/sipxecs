/**
 *
 *
 * Copyright (c) 2010 / 2011 eZuce, Inc. All rights reserved.
 * Contributed to SIPfoundry under a Contributor Agreement
 *
 * This software is free software; you can redistribute it and/or modify it under
 * the terms of the Affero General Public License (AGPL) as published by the
 * Free Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 */
package org.sipfoundry.sipxconfig.openacd;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class OpenAcdSkill extends OpenAcdConfigObject {
    private String m_name;
    private String m_atom;
    private OpenAcdSkillGroup m_group;
    private String m_description;
    private boolean m_defaultSkill;

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public String getAtom() {
        return m_atom;
    }

    public void setAtom(String atom) {
        m_atom = atom;
    }

    public OpenAcdSkillGroup getGroup() {
        return m_group;
    }

    public void setGroup(OpenAcdSkillGroup group) {
        m_group = group;
    }

    public String getGroupName() {
        if (getGroup() != null) {
            return getGroup().getName();
        }
        return null;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(String description) {
        m_description = description;
    }

    public boolean isDefaultSkill() {
        return m_defaultSkill;
    }

    public void setDefaultSkill(boolean defaultSkill) {
        m_defaultSkill = defaultSkill;
    }

    @Override
    public List<String> getProperties() {
        List<String> props = new LinkedList<String>();
        props.add("name");
        props.add("atom");
        props.add("groupName");
        props.add("description");
        return props;
    }

    @Override
    public String getType() {
        return "skill";
    }

    public int hashCode() {
        return new HashCodeBuilder().append(m_name).append(m_atom).toHashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof OpenAcdSkill)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        OpenAcdSkill bean = (OpenAcdSkill) other;
        return new EqualsBuilder().append(m_name, bean.getName()).append(m_atom, bean.getAtom()).isEquals();
    }
}
