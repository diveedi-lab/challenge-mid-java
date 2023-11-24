
CREATE TABLE IF NOT EXISTS TLCPERS (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    birth_date NUMERIC(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS TLCDOCS (
    id SERIAL PRIMARY KEY,
    owner_id INTEGER NOT NULL,
    name VARCHAR(128) NOT NULL,
    created_at NUMERIC(8) NOT NULL,
    updated_at NUMERIC(8),
    FOREIGN KEY (owner_id) REFERENCES TLCPERS (id)
);

CREATE INDEX TLCDOCS_OWNER_IDX ON TLCDOCS (owner_id);
