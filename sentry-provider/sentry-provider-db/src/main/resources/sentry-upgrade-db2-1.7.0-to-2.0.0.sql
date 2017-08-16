-- SENTRY-711
CREATE TABLE SENTRY_USER
(
    USER_ID BIGINT NOT NULL generated always as identity (start with 1),
    CREATE_TIME BIGINT NOT NULL,
    USER_NAME VARCHAR(128)
);

ALTER TABLE SENTRY_USER ADD CONSTRAINT SENTRY_USER_PK PRIMARY KEY (USER_ID);

CREATE UNIQUE INDEX SENTRYUSERNAME ON SENTRY_USER (USER_NAME);

CREATE TABLE SENTRY_ROLE_USER_MAP
(
    USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,
    GRANTOR_PRINCIPAL VARCHAR(128)
);

ALTER TABLE SENTRY_ROLE_USER_MAP ADD CONSTRAINT SENTRY_ROLE_USER_MAP_PK PRIMARY KEY (USER_ID,ROLE_ID);

CREATE INDEX SENTRY_ROLE_USER_MAP_N49 ON SENTRY_ROLE_USER_MAP (USER_ID);

CREATE INDEX SENTRY_ROLE_USER_MAP_N50 ON SENTRY_ROLE_USER_MAP (ROLE_ID);

ALTER TABLE SENTRY_ROLE_USER_MAP ADD CONSTRAINT SENTRY_ROLE_USER_MAP_FK2 FOREIGN KEY (ROLE_ID) REFERENCES SENTRY_ROLE (ROLE_ID) ;

ALTER TABLE SENTRY_ROLE_USER_MAP ADD CONSTRAINT SENTRY_ROLE_USER_MAP_FK1 FOREIGN KEY (USER_ID) REFERENCES SENTRY_USER (USER_ID) ;

-- SENTRY-1805
-- Table AUTHZ_PATHS_SNAPSHOT_ID for class [org.apache.sentry.provider.db.service.model.MAuthzPathsSnapshotId]
CREATE TABLE AUTHZ_PATHS_SNAPSHOT_ID
(
    AUTHZ_SNAPSHOT_ID bigint NOT NULL
);

-- Constraints for table AUTHZ_PATHS_SNAPSHOT_ID for class [org.apache.sentry.provider.db.service.model.MAuthzPathsSnapshotId]
ALTER TABLE AUTHZ_PATHS_SNAPSHOT_ID ADD CONSTRAINT AUTHZ_SNAPSHOT_ID_PK PRIMARY KEY (AUTHZ_SNAPSHOT_ID);

-- SENTRY-1365
-- Table AUTHZ_PATHS_MAPPING for classes [org.apache.sentry.provider.db.service.model.MAuthzPathsMapping]
 CREATE TABLE AUTHZ_PATHS_MAPPING(
    AUTHZ_OBJ_ID BIGINT NOT NULL generated always as identity (start with 1),
    AUTHZ_OBJ_NAME VARCHAR(384),
    CREATE_TIME_MS BIGINT NOT NULL,
    AUTHZ_SNAPSHOT_ID BIGINT NOT NULL
);

 ALTER TABLE AUTHZ_PATHS_MAPPING ADD CONSTRAINT AUTHZ_PATHSCO7K_PK PRIMARY KEY (AUTHZ_OBJ_ID);

-- Constraints for table AUTHZ_PATHS_MAPPING for class(es) [org.apache.sentry.provider.db.service.model.MAuthzPathsMapping]
 CREATE UNIQUE INDEX AUTHZOBJNAME ON AUTHZ_PATHS_MAPPING (AUTHZ_OBJ_NAME);

-- Table `AUTHZ_PATH` for classes [org.apache.sentry.provider.db.service.model.MPath]
CREATE TABLE AUTHZ_PATH
 (
    PATH_ID BIGINT NOT NULL,
    PATH_NAME VARCHAR(4000),
    AUTHZ_OBJ_ID BIGINT
);

-- Constraints for table `AUTHZ_PATH`
ALTER TABLE AUTHZ_PATH
  ADD CONSTRAINT AUTHZ_PATH_PK PRIMARY KEY (PATH_ID);

ALTER TABLE AUTHZ_PATH
  ADD CONSTRAINT AUTHZ_PATH_FK
  FOREIGN KEY (AUTHZ_OBJ_ID) REFERENCES AUTHZ_PATHS_MAPPING (AUTHZ_OBJ_ID);

-- Table `SENTRY_PERM_CHANGE` for classes [org.apache.sentry.provider.db.service.model.MSentryPermChange]
CREATE TABLE "SENTRY_PERM_CHANGE"
(
    "CHANGE_ID" bigint NOT NULL,
    "CREATE_TIME_MS" bigint NOT NULL,
    "PERM_CHANGE" VARCHAR(4000) NOT NULL
);

ALTER TABLE "SENTRY_PERM_CHANGE" ADD CONSTRAINT "SENTRY_PERM_CHANGE_PK" PRIMARY KEY ("CHANGE_ID");

-- Table `SENTRY_PATH_CHANGE` for classes [org.apache.sentry.provider.db.service.model.MSentryPathChange]
CREATE TABLE SENTRY_PATH_CHANGE
(
    CHANGE_ID bigint NOT NULL,
    NOTIFICATION_ID bigint NOT NULL,
    CREATE_TIME_MS bigint NOT NULL,
    PATH_CHANGE VARCHAR(4000) NOT NULL
);

-- Constraints for table SENTRY_PATH_CHANGE for class [org.apache.sentry.provider.db.service.model.MSentryPathChange]
ALTER TABLE SENTRY_PATH_CHANGE ADD CONSTRAINT SENTRY_PATH_CHANGE_PK PRIMARY KEY (CHANGE_ID);

-- Table SENTRY_HMS_NOTIFICATION_ID for classes [org.apache.sentry.provider.db.service.model.MSentryHmsNotification]
CREATE TABLE SENTRY_HMS_NOTIFICATION_ID
(
    NOTIFICATION_ID bigint NOT NULL
);

-- Version update
UPDATE SENTRY_VERSION SET SCHEMA_VERSION='2.0.0', VERSION_COMMENT='Sentry release version 2.0.0' WHERE VER_ID=1;
