/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.sentry.provider.db.service.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.sentry.provider.db.service.persistent.SentryNoSuchObjectException;

@PersistenceCapable
public class MSentryRole {

  private String roleName;
  // set of privileges granted to this role
  private Set<MSentryPrivilege> privileges;
  // set of groups this role belongs to
  private Set<MSentryGroup> groups;
  private long createTime;
  private String grantorPrincipal;

  public MSentryRole() {
    privileges = new HashSet<MSentryPrivilege>();
  }

  MSentryRole(String roleName, long createTime, String grantorPrincipal,
              Set<MSentryPrivilege> privileges, Set<MSentryGroup> groups) {
    this.roleName = roleName;
    this.createTime = createTime;
    this.grantorPrincipal = grantorPrincipal;
    this.privileges = privileges;
    this.groups = groups;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public String getGrantorPrincipal() {
    return grantorPrincipal;
  }

  public void setGrantorPrincipal(String grantorPrincipal) {
    this.grantorPrincipal = grantorPrincipal;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public void setPrivileges(Set<MSentryPrivilege> privileges) {
    this.privileges = privileges;
  }

  public Set<MSentryPrivilege> getPrivileges() {
    return privileges;
  }

  public void setGroups(Set<MSentryGroup> groups) {
    this.groups = groups;
  }

  public Set<MSentryGroup> getGroups() {
    return groups;
  }

  public void removePrivilege(MSentryPrivilege privilege) {
    for (Iterator<MSentryPrivilege> iter = privileges.iterator(); iter.hasNext();) {
      if (iter.next().getPrivilegeName().equalsIgnoreCase(privilege.getPrivilegeName())) {
        iter.remove();
        privilege.removeRole(this);
        return;
      }
    }
  }

  public void appendPrivileges(Set<MSentryPrivilege> privileges) {
    this.privileges.addAll(privileges);
  }

  public void appendPrivilege(MSentryPrivilege privilege) {
    if (!privileges.contains(privilege)) {
      privileges.add(privilege);
      privilege.appendRole(this);
    }
  }

  public void appendGroups(Set<MSentryGroup> groups) {
    this.groups.addAll(groups);
  }

  public void removePrivileges() {
    this.privileges.clear();
  }
}