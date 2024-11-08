CREATE SEQUENCE USERS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE VEHICLES_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE TRAFFIC_LIGHTS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SENSORS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE ROUTES_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE NOTIFICATIONS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE INCIDENTS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;

/* USER TABLE */
CREATE TABLE tbl_users
(
    id                        NUMBER(19) PRIMARY KEY,
    username                  VARCHAR2(255) NOT NULL,
    email                     VARCHAR2(255) NOT NULL UNIQUE,
    device_id                 VARCHAR2(255),
    push_notification_enabled NUMBER(1) DEFAULT 0
);


/* VEHICLES TABLE */
CREATE TABLE tbl_vehicles
(
    id            NUMBER(19) PRIMARY KEY,
    license_plate VARCHAR2(255) NOT NULL UNIQUE,
    type          VARCHAR2(255),
    CONSTRAINT chk_vehicle_type CHECK (type IN ('CAR', 'MOTORCYCLE', 'TRUCK', 'BUS'))
);


/* TRAFFIC LIGHTS TABLE */
CREATE TABLE tbl_traffic_lights
(
    id         NUMBER(19) PRIMARY KEY,
    location   VARCHAR2(255) NOT NULL,
    state      VARCHAR2(255) CHECK (state IN ('red', 'yellow', 'green')),
    cycle_time NUMBER(10),
    status     NUMBER(1)
);


/* SENSORS TABLE */
CREATE TABLE table_sensors
(
    id       NUMBER(19) PRIMARY KEY,
    location VARCHAR2(255) NOT NULL
);


/* ROUTES TABLE */
CREATE TABLE tbl_routes
(
    id             NUMBER(19) PRIMARY KEY,
    origin         VARCHAR2(255) NOT NULL,
    destination    VARCHAR2(255) NOT NULL,
    estimated_time NUMBER(10, 2)
);


/* NOTIFICATIONS TABLE */
CREATE TABLE tbl_notifications
(
    id      NUMBER(19) PRIMARY KEY,
    message VARCHAR2(255) NOT NULL,
    user_id NUMBER(19) REFERENCES tbl_users (id)
);


/* INCIDENTS TABLE */
CREATE TABLE tbl_incidents
(
    id                  NUMBER(19) PRIMARY KEY,
    description         VARCHAR2(255) NOT NULL,
    start_time          TIMESTAMP,
    end_time            TIMESTAMP,
    involved_vehicle_id NUMBER(19) REFERENCES tbl_vehicles (id)
);