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
package org.apache.sentry.hdfs;

import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.Sasl;

import com.google.common.collect.ImmutableMap;

public class ServiceConstants {

  private static final ImmutableMap<String, String> SASL_PROPERTIES;

  // number used in authz paths and permissions to request initial syncs
  public static final long SEQUENCE_NUMBER_UPDATE_UNINITIALIZED = -1L;

  // number used in authz paths and permissions to request initial syncs
  public static final long IMAGE_NUMBER_UPDATE_UNINITIALIZED = 0L;

  // number used in authz paths and permissions that specifies an unused image number
  public static final long IMAGE_NUMBER_UPDATE_UNUSED = -1L;

  static {
    Map<String, String> saslProps = new HashMap<String, String>();
    saslProps.put(Sasl.SERVER_AUTH, "true");
    saslProps.put(Sasl.QOP, "auth-conf");
    SASL_PROPERTIES = ImmutableMap.copyOf(saslProps);
  }

  public static class ServerConfig {
    public static final ImmutableMap<String, String> SASL_PROPERTIES = ServiceConstants.SASL_PROPERTIES;
    /**
     * This configuration parameter is only meant to be used for testing purposes.
     */
    public static final String SENTRY_HDFS_SYNC_METASTORE_CACHE_INIT_THREADS = "sentry.hdfs.sync.metastore.cache.init.threads";
    public static final int SENTRY_HDFS_SYNC_METASTORE_CACHE_INIT_THREADS_DEFAULT = 10;
    public static final String SENTRY_HDFS_SYNC_METASTORE_CACHE_RETRY_MAX_NUM = "sentry.hdfs.sync.metastore.cache.retry.max.num";
    public static final int SENTRY_HDFS_SYNC_METASTORE_CACHE_RETRY_MAX_NUM_DEFAULT = 1;
    public static final String SENTRY_HDFS_SYNC_METASTORE_CACHE_RETRY_WAIT_DURAION_IN_MILLIS = "sentry.hdfs.sync.metastore.cache.retry.wait.duration.millis";
    public static final int SENTRY_HDFS_SYNC_METASTORE_CACHE_RETRY_WAIT_DURAION_IN_MILLIS_DEFAULT = 1000;

    public static final String SENTRY_HDFS_SYNC_METASTORE_CACHE_MAX_PART_PER_RPC = "sentry.hdfs.sync.metastore.cache.max-partitions-per-rpc";
    public static final int SENTRY_HDFS_SYNC_METASTORE_CACHE_MAX_PART_PER_RPC_DEFAULT = 100;
    public static final String SENTRY_HDFS_SYNC_METASTORE_CACHE_MAX_TABLES_PER_RPC = "sentry.hdfs.sync.metastore.cache.max-tables-per-rpc";
    public static final int SENTRY_HDFS_SYNC_METASTORE_CACHE_MAX_TABLES_PER_RPC_DEFAULT = 100;
    public static final String SENTRY_SERVICE_FULL_UPDATE_SIGNAL = "sentry.hdfs.sync.full-update-signal";
  }

  public static class ClientConfig {
    public static final ImmutableMap<String, String> SASL_PROPERTIES = ServiceConstants.SASL_PROPERTIES;

    public static final String SECURITY_MODE = "sentry.hdfs.service.security.mode";
    public static final String SECURITY_MODE_KERBEROS = "kerberos";
    public static final String SECURITY_USE_UGI_TRANSPORT = "sentry.hdfs.service.security.use.ugi";
    public static final String PRINCIPAL = "sentry.hdfs.service.server.principal";

    public static final String SERVER_RPC_PORT = "sentry.hdfs.service.client.server.rpc-port";
  
    public static final String SERVER_RPC_ADDRESS = "sentry.hdfs.service.client.server.rpc-addresses";

    public static final String USE_COMPACT_TRANSPORT = "sentry.hdfs.service.client.compact.transport";
    public static final boolean USE_COMPACT_TRANSPORT_DEFAULT = false;

    // max message size for thrift messages
    public static final String SENTRY_HDFS_THRIFT_MAX_MESSAGE_SIZE = "sentry.hdfs.thrift.max.message.size";
    public static final long SENTRY_HDFS_THRIFT_MAX_MESSAGE_SIZE_DEFAULT = 100 * 1024 * 1024;
  }

}
