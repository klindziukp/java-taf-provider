CREATE TABLE IF NOT EXISTS modules (
    id SERIAL PRIMARY KEY,
    uuid varchar(36) NOT NULL,
    group_id varchar(36) NOT NULL,
    artifact_id varchar(36) NOT NULL,
    display_name varchar(36) NOT NULL,
    description varchar(1000) NOT NULL,
    module_group varchar(30) NOT NULL,
    version varchar(36) NOT NULL,
    created_by varchar(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by varchar(36) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);